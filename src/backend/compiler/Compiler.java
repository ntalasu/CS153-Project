package backend.compiler;

import java.util.ArrayDeque;
import java.util.ArrayList;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.TerminalNode;

import intermediate.antlr4.BrainrotBaseVisitor;
import intermediate.antlr4.BrainrotParser;
import intermediate.antlr4.BrainrotParser.*;
import intermediate.symtab.Predefined;
import intermediate.symtab.SymtabEntry;
import intermediate.type.Typespec;

import static backend.compiler.Directive.*;
import static backend.compiler.Instruction.*;
import static intermediate.semantics.Semantics.*;

/**
 * PASS 3: Generate Jasmin assembly code for a Brainrot program.
 *
 *   quandale dingle Name:   ->  Name.j  (.class public Name)
 *   rizz <type> f(...):     ->  .method private static f(...)<type>
 *   tung tung sahur:        ->  .method public static main([Ljava/lang/String;)V
 *
 * All variables are JVM locals; their slot numbers were assigned by the
 * semantics pass. dabloons = int (I), chat = java/lang/String.
 * Comparisons leave 0 or 1 on the stack.
 *
 * Runtime errors are caught in main:
 *   java/lang/ArithmeticException (divide by zero) -> "chopped cheese!"
 *   any other RuntimeException                     -> "sussy baka!"
 */
public class Compiler extends BrainrotBaseVisitor<Object>
{
    private static final String PRINT_STREAM  = "java/io/PrintStream";
    private static final String STRING        = "java/lang/String";
    private static final String STRING_DESC   = "Ljava/lang/String;";
    private static final String BUILDER       = "java/lang/StringBuilder";

    private JasminEmitter code;
    private LocalStack localStack;
    private String programName;
    private final ArrayList<String> objectFileNames = new ArrayList<>();

    // Jump targets for ohio (break) and tonka jahari (continue).
    private final ArrayDeque<Label> breakLabels    = new ArrayDeque<>();
    private final ArrayDeque<Label> continueLabels = new ArrayDeque<>();

    public ArrayList<String> getObjectFileNames() { return objectFileNames; }

    // =================
    // Program structure
    // =================

    @Override
    public Object visitProgram(ProgramContext ctx)
    {
        programName = programEntry.getName();
        code = new JasminEmitter(programName);

        code.emitDirective(CLASS_PUBLIC, programName);
        code.emitDirective(SUPER, "java/lang/Object");
        emitConstructor();

        for (FunctionDeclarationContext funcCtx : ctx.functionDeclaration())
        {
            visit(funcCtx);
        }
        visit(ctx.mainMethod());

        code.closeObjectFile();
        objectFileNames.add(programName + ".j");
        return null;
    }

    /** The default constructor every Java class needs. */
    private void emitConstructor()
    {
        startMethod();
        code.emitLine();
        code.emitDirective(METHOD_PUBLIC, "<init>()V");
        code.emit(ALOAD_0);
        code.emit(INVOKESPECIAL, "java/lang/Object/<init>()V");
        localStack.decrease(1);
        code.emit(RETURN);
        endMethod(1);
    }

    @Override
    public Object visitFunctionDeclaration(FunctionDeclarationContext ctx)
    {
        SymtabEntry funcEntry = entryOf(ctx);
        String name = funcEntry.getName();
        Typespec returnType = funcEntry.getTypespec();

        startMethod();
        code.emitLine();
        code.emitComment("FUNCTION " + name);
        code.emitDirective(METHOD_PRIVATE_STATIC, name + signature(funcEntry));
        code.emitLine();

        visit(ctx.block());

        code.emitLine();
        if (returnType == null)
        {
            code.emit(RETURN);
        }
        else
        {
            // Only reached if the function falls off its end without a yeet.
            emitThrow("sussy baka: function " + name + " ended without a yeet");
        }

        endMethod(localsCount(funcEntry));
        return null;
    }

    @Override
    public Object visitMainMethod(MainMethodContext ctx)
    {
        Label tryStart     = new Label();
        Label tryEnd       = new Label();
        Label arithHandler = new Label();
        Label otherHandler = new Label();

        startMethod();
        code.emitLine();
        code.emitComment("MAIN (tung tung sahur)");
        code.emitDirective(METHOD_PUBLIC_STATIC, "main([Ljava/lang/String;)V");

        // ArithmeticException must come first: it's also a RuntimeException.
        code.emitDirective(CATCH, "java/lang/ArithmeticException from " + tryStart
                + " to " + tryEnd + " using " + arithHandler);
        code.emitDirective(CATCH, "java/lang/RuntimeException from " + tryStart
                + " to " + tryEnd + " using " + otherHandler);
        code.emitLine();

        code.emitLabel(tryStart);
        visit(ctx.block());
        code.emitLabel(tryEnd);
        code.emit(RETURN);

        // chopped cheese: divide by zero
        code.emitLine();
        code.emitLabel(arithHandler);
        localStack.increase(1);  // the exception object
        code.emit(POP);
        code.emit(GETSTATIC, "java/lang/System/out L" + PRINT_STREAM + ";");
        code.emit(LDC, "\"chopped cheese! (runtime error: division by zero)\"");
        code.emit(INVOKEVIRTUAL, PRINT_STREAM + "/println(" + STRING_DESC + ")V");
        localStack.decrease(2);
        emitExit(1);
        code.emit(RETURN);

        // sussy baka: any other runtime error
        code.emitLine();
        code.emitLabel(otherHandler);
        localStack.increase(1);
        code.emit(INVOKEVIRTUAL, "java/lang/Throwable/getMessage()" + STRING_DESC);
        code.emit(INVOKESTATIC, STRING + "/valueOf(Ljava/lang/Object;)" + STRING_DESC);
        code.emit(LDC, "\"sussy baka! (runtime error) \"");
        code.emit(SWAP);
        code.emit(INVOKEVIRTUAL, STRING + "/concat(" + STRING_DESC + ")" + STRING_DESC);
        localStack.decrease(1);
        code.emit(GETSTATIC, "java/lang/System/out L" + PRINT_STREAM + ";");
        code.emit(SWAP);
        code.emit(INVOKEVIRTUAL, PRINT_STREAM + "/println(" + STRING_DESC + ")V");
        localStack.decrease(2);
        emitExit(1);
        code.emit(RETURN);

        endMethod(localsCount(mainEntry));
        return null;
    }

    private void startMethod()
    {
        localStack = new LocalStack();
        code.setLocalStack(localStack);
    }

    private void endMethod(int locals)
    {
        code.emitLine();
        code.emitDirective(LIMIT_LOCALS, Math.max(locals, 1));
        code.emitDirective(LIMIT_STACK,  localStack.capacity() + 2);
        code.emitDirective(END_METHOD);
    }

    // ==========
    // Statements
    // ==========

    @Override
    public Object visitBlock(BlockContext ctx)
    {
        for (StatementContext stmtCtx : ctx.statement())
        {
            emitSourceComment(stmtCtx);
            visit(stmtCtx);
        }
        return null;
    }

    @Override
    public Object visitVariableDeclaration(VariableDeclarationContext ctx)
    {
        emitDeclaration(ctx.identifier(), ctx.expression());
        return null;
    }

    @Override
    public Object visitConstantDeclaration(ConstantDeclarationContext ctx)
    {
        emitDeclaration(ctx.identifier(), ctx.expression());
        return null;
    }

    /**
     * Store the initial value. A "bet" with no initializer gets 0 or "",
     * otherwise the JVM verifier rejects a later load of the variable.
     */
    private void emitDeclaration(IdentifierContext idCtx, ExpressionContext initCtx)
    {
        SymtabEntry entry = entryOf(idCtx);
        Typespec type = entry.getTypespec();

        if (initCtx != null)                         visit(initCtx);
        else if (type == Predefined.stringType)      code.emit(LDC, "\"\"");
        else                                         code.emit(ICONST_0);

        code.emitStoreLocal(type, entry.getSlotNumber());
    }

    @Override
    public Object visitAssignmentStatement(AssignmentStatementContext ctx)
    {
        SymtabEntry entry = entryOf(ctx.identifier());

        visit(ctx.expression());
        code.emitStoreLocal(entry.getTypespec(), entry.getSlotNumber());
        return null;
    }

    /**
     * six (c1): ... seven six (c2): ... seven: ...
     */
    @Override
    public Object visitIfStatement(IfStatementContext ctx)
    {
        Label exitLabel = new Label();
        int condCount = ctx.expression().size();

        for (int i = 0; i < condCount; i++)
        {
            Label nextLabel = new Label();

            visit(ctx.expression(i));
            code.emit(IFEQ, nextLabel);
            visit(ctx.block(i));
            code.emit(GOTO, exitLabel);
            code.emitLabel(nextLabel);
        }

        // There's one more block than conditions when there is a seven: (else).
        if (ctx.block().size() > condCount) visit(ctx.block(condCount));

        code.emitLabel(exitLabel);
        return null;
    }

    /**
     * tralalero tralala (cond): ...
     */
    @Override
    public Object visitWhileStatement(WhileStatementContext ctx)
    {
        Label loopTop  = new Label();
        Label loopExit = new Label();

        code.emitLabel(loopTop);
        visit(ctx.expression());
        code.emit(IFEQ, loopExit);

        visitLoopBody(ctx.block(), loopExit, loopTop);
        code.emit(GOTO, loopTop);
        code.emitLabel(loopExit);

        return null;
    }

    /**
     * ballerina cappuccina (init; cond; step): ...
     */
    @Override
    public Object visitForStatement(ForStatementContext ctx)
    {
        Label loopTop  = new Label();
        Label loopStep = new Label();
        Label loopExit = new Label();

        if (ctx.forInitializer() != null) visit(ctx.forInitializer());

        code.emitLabel(loopTop);
        if (ctx.expression() != null)
        {
            visit(ctx.expression());
            code.emit(IFEQ, loopExit);
        }

        visitLoopBody(ctx.block(), loopExit, loopStep);  // continue -> step

        code.emitLabel(loopStep);
        if (ctx.assignmentStatement() != null) visit(ctx.assignmentStatement());
        code.emit(GOTO, loopTop);
        code.emitLabel(loopExit);

        return null;
    }

    private void visitLoopBody(BlockContext body, Label breakTo, Label continueTo)
    {
        breakLabels.push(breakTo);
        continueLabels.push(continueTo);
        visit(body);
        continueLabels.pop();
        breakLabels.pop();
    }

    @Override
    public Object visitBreakStatement(BreakStatementContext ctx)
    {
        code.emit(GOTO, breakLabels.peek());
        return null;
    }

    @Override
    public Object visitContinueStatement(ContinueStatementContext ctx)
    {
        code.emit(GOTO, continueLabels.peek());
        return null;
    }

    /** skibidi -> System.exit(0) */
    @Override
    public Object visitExitStatement(ExitStatementContext ctx)
    {
        emitExit(0);
        return null;
    }

    @Override
    public Object visitReturnStatement(ReturnStatementContext ctx)
    {
        if (ctx.expression() != null)
        {
            visit(ctx.expression());
            code.emitReturnValue(typeOf(ctx.expression()));
        }
        else
        {
            code.emit(RETURN);
        }
        return null;
    }

    /**
     * yo gurt(a, b, c) prints the values separated by spaces, then a newline.
     */
    @Override
    public Object visitPrintStatement(PrintStatementContext ctx)
    {
        code.emit(GETSTATIC, "java/lang/System/out L" + PRINT_STREAM + ";");
        code.emit(NEW, BUILDER);
        code.emit(DUP);
        code.emit(INVOKESPECIAL, BUILDER + "/<init>()V");
        localStack.decrease(1);

        if (ctx.argumentList() != null)
        {
            boolean first = true;
            for (ExpressionContext argCtx : ctx.argumentList().expression())
            {
                if (!first)
                {
                    code.emit(LDC, "\" \"");
                    emitAppend(Predefined.stringType);
                }
                first = false;

                visit(argCtx);
                emitAppend(typeOf(argCtx));
            }
        }

        code.emit(INVOKEVIRTUAL, BUILDER + "/toString()" + STRING_DESC);
        code.emit(INVOKEVIRTUAL, PRINT_STREAM + "/println(" + STRING_DESC + ")V");
        localStack.decrease(2);
        return null;
    }

    private void emitAppend(Typespec type)
    {
        String desc = (type == Predefined.integerType) ? "I"
                : (type == Predefined.booleanType) ? "Z"
                :                                    STRING_DESC;
        code.emit(INVOKEVIRTUAL, BUILDER + "/append(" + desc + ")L" + BUILDER + ";");
        localStack.decrease(1);
    }

    /** john pork f(...) as a statement: throw away any returned value. */
    @Override
    public Object visitCallStatement(CallStatementContext ctx)
    {
        visit(ctx.functionCall());
        if (entryOf(ctx.functionCall()).getTypespec() != null) code.emit(POP);
        return null;
    }

    @Override
    public Object visitFunctionCall(FunctionCallContext ctx)
    {
        SymtabEntry funcEntry = entryOf(ctx);
        int argCount = 0;

        if (ctx.argumentList() != null)
        {
            for (ExpressionContext argCtx : ctx.argumentList().expression())
            {
                visit(argCtx);  // pass by value: just push each value
                argCount++;
            }
        }

        code.emit(INVOKESTATIC, programName + "/" + funcEntry.getName()
                + signature(funcEntry));
        localStack.decrease(argCount);
        if (funcEntry.getTypespec() != null) localStack.increase(1);

        return null;
    }

    // ===========
    // Expressions
    // ===========

    /** additive (relop additive)* -- each comparison leaves 0 or 1. */
    @Override
    public Object visitExpression(ExpressionContext ctx)
    {
        visit(ctx.additiveExpression(0));
        Typespec type = typeOf(ctx.additiveExpression(0));

        for (int i = 0; i < ctx.relationalOperator().size(); i++)
        {
            visit(ctx.additiveExpression(i + 1));
            int op = ((TerminalNode) ctx.relationalOperator(i).getChild(0))
                    .getSymbol().getType();
            emitComparison(op, type);
            type = Predefined.booleanType;
        }

        return null;
    }

    private void emitComparison(int op, Typespec operandType)
    {
        if (operandType == Predefined.stringType)
        {
            if (isEq(op) || isNe(op))
            {
                code.emit(INVOKEVIRTUAL, STRING + "/equals(Ljava/lang/Object;)Z");
                localStack.decrease(1);
                if (isNe(op))
                {
                    code.emit(ICONST_1);
                    code.emit(IXOR);
                }
                return;
            }

            // < > <= >= on chats: compare alphabetically with compareTo,
            // then compare that result against 0.
            code.emit(INVOKEVIRTUAL, STRING + "/compareTo(" + STRING_DESC + ")I");
            localStack.decrease(1);
            emitBranchToBoolean(zeroCompare(op));
            return;
        }

        // dabloons (and booleans with == / !=)
        emitBranchToBoolean(intCompare(op));
    }

    /** Turn a conditional branch into 0 / 1 on the stack. */
    private void emitBranchToBoolean(Instruction branch)
    {
        Label trueLabel = new Label();
        Label doneLabel = new Label();

        code.emit(branch, trueLabel);
        code.emit(ICONST_0);
        code.emit(GOTO, doneLabel);
        code.emitLabel(trueLabel);
        localStack.decrease(1);  // only one of the two constants is ever pushed
        code.emit(ICONST_1);
        code.emitLabel(doneLabel);
    }

    @Override
    public Object visitAdditiveExpression(AdditiveExpressionContext ctx)
    {
        visit(ctx.multiplicativeExpression(0));
        boolean isString = typeOf(ctx.multiplicativeExpression(0)) == Predefined.stringType;

        for (int i = 1; i < ctx.getChildCount(); i += 2)
        {
            int op = ((TerminalNode) ctx.getChild(i)).getSymbol().getType();
            visit(ctx.getChild(i + 1));

            if (isString)  // aura gain on two chats joins them
            {
                code.emit(INVOKEVIRTUAL, STRING + "/concat(" + STRING_DESC + ")" + STRING_DESC);
                localStack.decrease(1);
            }
            else if ((op == BrainrotParser.AURA_GAIN) || (op == BrainrotParser.PLUS))
            {
                code.emit(IADD);
            }
            else
            {
                code.emit(ISUB);
            }
        }

        return null;
    }

    @Override
    public Object visitMultiplicativeExpression(MultiplicativeExpressionContext ctx)
    {
        visit(ctx.unaryExpression(0));

        for (int i = 1; i < ctx.getChildCount(); i += 2)
        {
            int op = ((TerminalNode) ctx.getChild(i)).getSymbol().getType();
            visit(ctx.getChild(i + 1));

            if ((op == BrainrotParser.AURA_FARM) || (op == BrainrotParser.STAR))
            {
                code.emit(IMUL);
            }
            else
            {
                code.emit(IDIV);  // throws ArithmeticException on / 0
            }
        }

        return null;
    }

    @Override
    public Object visitUnaryExpression(UnaryExpressionContext ctx)
    {
        if (ctx.primaryExpression() != null)
        {
            visit(ctx.primaryExpression());
        }
        else
        {
            visit(ctx.unaryExpression());
            if (ctx.MINUS() != null) code.emit(INEG);
        }
        return null;
    }

    @Override
    public Object visitPrimaryExpression(PrimaryExpressionContext ctx)
    {
        if (ctx.INTEGER() != null)
        {
            code.emitLoadConstant(Integer.parseInt(ctx.INTEGER().getText()));
        }
        else if (ctx.STRING() != null)
        {
            code.emit(LDC, ctx.STRING().getText());  // token keeps its quotes
        }
        else if (ctx.functionCall() != null)
        {
            visit(ctx.functionCall());
        }
        else if (ctx.identifier() != null)
        {
            SymtabEntry entry = entryOf(ctx.identifier());
            code.emitLoadLocal(entry.getTypespec(), entry.getSlotNumber());
        }
        else
        {
            visit(ctx.expression());
        }
        return null;
    }

    // =========
    // Utilities
    // =========

    /** JVM method descriptor, e.g. (ILjava/lang/String;)I */
    private static String signature(SymtabEntry funcEntry)
    {
        StringBuilder sig = new StringBuilder("(");
        for (SymtabEntry parm : funcEntry.getProcFuncParameters())
        {
            sig.append(descriptor(parm.getTypespec()));
        }
        sig.append(")").append(descriptor(funcEntry.getTypespec()));
        return sig.toString();
    }

    private static String descriptor(Typespec type)
    {
        if (type == null)                    return "V";
        if (type == Predefined.integerType)  return "I";
        return STRING_DESC;
    }

    private void emitExit(int status)
    {
        code.emitLoadConstant(status);
        code.emit(INVOKESTATIC, "java/lang/System/exit(I)V");
        localStack.decrease(1);
    }

    private void emitThrow(String message)
    {
        code.emit(NEW, "java/lang/RuntimeException");
        code.emit(DUP);
        code.emit(LDC, "\"" + message + "\"");
        code.emit(INVOKESPECIAL, "java/lang/RuntimeException/<init>(" + STRING_DESC + ")V");
        localStack.decrease(2);
        code.emit(ATHROW);
    }

    /** Emit the original source line of a statement as a Jasmin comment. */
    private void emitSourceComment(ParserRuleContext ctx)
    {
        String text = ctx.getStart().getInputStream().getText(
                Interval.of(ctx.getStart().getStartIndex(), ctx.getStop().getStopIndex()));
        int newline = text.indexOf('\n');
        if (newline >= 0) text = text.substring(0, newline).trim() + " ...";
        if (text.length() > 60) text = text.substring(0, 60) + " ...";

        code.emitComment(String.format("%03d %s", ctx.getStart().getLine(), text));
    }

    private static boolean isEq(int op) { return op == BrainrotParser.NO_CAP || op == BrainrotParser.EQ; }
    private static boolean isNe(int op) { return op == BrainrotParser.CAP    || op == BrainrotParser.NE; }

    /** Branch for comparing two ints already on the stack. */
    private static Instruction intCompare(int op)
    {
        switch (op)
        {
            case BrainrotParser.MOGGING:        case BrainrotParser.GT: return IF_ICMPGT;
            case BrainrotParser.MOGGED_BY:      case BrainrotParser.LT: return IF_ICMPLT;
            case BrainrotParser.LOWK_MOGGING:   case BrainrotParser.GE: return IF_ICMPGE;
            case BrainrotParser.LOWK_MOGGED_BY: case BrainrotParser.LE: return IF_ICMPLE;
            case BrainrotParser.NO_CAP:         case BrainrotParser.EQ: return IF_ICMPEQ;
            default:                                                return IF_ICMPNE;
        }
    }

    /** Branch for comparing one int (a compareTo result) against 0. */
    private static Instruction zeroCompare(int op)
    {
        switch (op)
        {
            case BrainrotParser.MOGGING:        case BrainrotParser.GT: return IFGT;
            case BrainrotParser.MOGGED_BY:      case BrainrotParser.LT: return IFLT;
            case BrainrotParser.LOWK_MOGGING:   case BrainrotParser.GE: return IFGE;
            case BrainrotParser.LOWK_MOGGED_BY: case BrainrotParser.LE: return IFLE;
            case BrainrotParser.NO_CAP:         case BrainrotParser.EQ: return IFEQ;
            default:                                                return IFNE;
        }
    }
}