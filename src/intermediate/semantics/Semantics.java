package intermediate.semantics;

import java.util.ArrayList;
import java.util.HashMap;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;

import intermediate.antlr4.BrainrotBaseVisitor;
import intermediate.antlr4.BrainrotParser;
import intermediate.antlr4.BrainrotParser.*;
import intermediate.symtab.Predefined;
import intermediate.symtab.Symtab;
import intermediate.symtab.SymtabEntry;
import intermediate.symtab.SymtabEntry.Kind;
import intermediate.symtab.SymtabStack;
import intermediate.type.Typespec;
import backend.BackendMode.Mode;

import static intermediate.semantics.SemanticErrorHandler.Code.*;
import static intermediate.symtab.SymtabEntry.Kind.*;

/**
 * PASS 2: Semantic analysis for the Brainrot language.
 *
 * - Builds the symbol tables (intermediate tier):
 *     level 0  predefined entries (from Predefined)
 *     level 1  program symtab: the "quandale dingle" class + all rizz functions
 *     level 2+ one symtab per function / main body, plus one per nested block
 * - Type checks everything ("negative aura" errors).
 * - Annotates the parse tree with types and symtab entries so the
 *   backend (compiler) doesn't have to redo any lookups.
 *
 * Types:  dabloons -> Predefined.integerType
 *         chat     -> Predefined.stringType
 *         result of a comparison -> Predefined.booleanType (only usable as
 *         a condition or printed; the language has no boolean variables)
 */
public class Semantics extends BrainrotBaseVisitor<Object>
{
    public static SymtabEntry programEntry;   // the "quandale dingle" class
    public static SymtabEntry mainEntry;      // the "tung tung sahur" method
    public static Mode mode;

    // Parse tree annotations that the backend reads.
    private static final ParseTreeProperty<Typespec>    types   = new ParseTreeProperty<>();
    private static final ParseTreeProperty<SymtabEntry> entries = new ParseTreeProperty<>();
    private static final HashMap<SymtabEntry, Integer>  localsCounts = new HashMap<>();

    protected static SymtabStack symtabStack;
    protected static SemanticErrorHandler error;

    // State while walking a function or main body.
    private SymtabEntry currentFunction = null;
    private int functionNestingLevel = 0;  // nesting level of the function's own symtab
    private int nextSlot = 0;              // next free JVM local variable slot
    private int loopDepth = 0;             // > 0 when inside a loop (for ohio / tonka jahari)

    static
    {
        symtabStack = new SymtabStack();
        error = new SemanticErrorHandler();
        Predefined.initialize(symtabStack);
    }

    public int getErrorCount() { return error.getCount(); }

    // ===== Accessors used by the backend =====

    /** Type of an expression node (null for a sigma call). */
    public static Typespec typeOf(ParseTree node) { return types.get(node); }

    /** Symtab entry attached to an identifier, declaration, or call node. */
    public static SymtabEntry entryOf(ParseTree node) { return entries.get(node); }

    /** Number of JVM local slots a function (or main) needs. */
    public static int localsCount(SymtabEntry routine)
    {
        Integer count = localsCounts.get(routine);
        return count != null ? count : 1;
    }

    /** Brainrot name of a type, for error messages and debugging. */
    public static String typeName(Typespec type)
    {
        if (type == null)                     return "sigma";
        if (type == Predefined.integerType)   return "dabloons";
        if (type == Predefined.stringType)    return "chat";
        if (type == Predefined.booleanType)   return "boolean";
        return "unknown";
    }

    // =================
    // Program structure
    // =================

    @Override
    public Object visitProgram(ProgramContext ctx)
    {
        // Program (class) entry goes in the level 0 table, and gets its own
        // level 1 symtab that holds all the functions.
        String programName = ctx.identifier().getText();
        programEntry = symtabStack.enterLocal(programName, PROGRAM);
        programEntry.appendLineNumber(ctx.getStart().getLine());

        Symtab programSymtab = symtabStack.push();
        programSymtab.setOwner(programEntry);
        programEntry.setProcFuncSymtab(programSymtab);
        symtabStack.setProgramEntry(programEntry);

        // tim cheese imports are reserved and ignored for now.
        for (ImportDeclarationContext importCtx : ctx.importDeclaration())
        {
            visit(importCtx);
        }

        // First pass over the functions: enter every function header, so a
        // function can be called before (or after) it is declared, and
        // functions declared after tung tung sahur still work.
        for (FunctionDeclarationContext funcCtx : ctx.functionDeclaration())
        {
            declareFunctionHeader(funcCtx);
        }

        // Second pass: check the function bodies and the main method.
        for (FunctionDeclarationContext funcCtx : ctx.functionDeclaration())
        {
            visit(funcCtx);
        }
        visit(ctx.mainMethod());

        symtabStack.pop();
        return null;
    }

    @Override
    public Object visitImportDeclaration(ImportDeclarationContext ctx)
    {
        return null;  // reserved keyword, nothing to check yet
    }

    /**
     * Enter a function's name, return type, and parameters into the
     * symbol tables. Parameters go into the function's own symtab.
     */
    private void declareFunctionHeader(FunctionDeclarationContext ctx)
    {
        String name = ctx.identifier().getText();
        Typespec returnType = returnTypeOf(ctx.returnType());
        Kind kind = (returnType == null) ? PROCEDURE : FUNCTION;

        Symtab programSymtab = programEntry.getProcFuncSymtab();
        SymtabEntry funcEntry;

        if (programSymtab.lookup(name) != null)
        {
            error.flag(REDECLARED_IDENTIFIER, ctx.identifier());
            // Detached entry so we can still check the body.
            funcEntry = new SymtabEntry(name, kind, programSymtab);
        }
        else
        {
            funcEntry = programSymtab.enter(name, kind);
            programEntry.appendProcFunc(funcEntry);
        }

        funcEntry.setTypespec(returnType);
        funcEntry.appendLineNumber(ctx.getStart().getLine());

        Symtab funcSymtab = new Symtab(programSymtab.getNestingLevel() + 1);
        funcSymtab.setOwner(funcEntry);
        funcEntry.setProcFuncSymtab(funcSymtab);

        ArrayList<SymtabEntry> parms = new ArrayList<>();
        int slot = 0;

        if (ctx.parameterList() != null)
        {
            for (ParameterContext parmCtx : ctx.parameterList().parameter())
            {
                String parmName = parmCtx.identifier().getText();
                Typespec parmType = valueTypeOf(parmCtx.valueType());

                SymtabEntry parmEntry;
                if (funcSymtab.lookup(parmName) != null)
                {
                    error.flag(REDECLARED_IDENTIFIER, parmCtx.identifier());
                    parmEntry = new SymtabEntry(parmName, VALUE_PARAMETER, funcSymtab);
                }
                else
                {
                    parmEntry = funcSymtab.enter(parmName, VALUE_PARAMETER);
                }

                parmEntry.setTypespec(parmType);
                parmEntry.setSlotNumber(slot++);
                parmEntry.appendLineNumber(parmCtx.getStart().getLine());
                parms.add(parmEntry);

                entries.put(parmCtx.identifier(), parmEntry);
            }
        }

        funcEntry.setProcFuncParameters(parms);
        entries.put(ctx, funcEntry);
    }

    @Override
    public Object visitFunctionDeclaration(FunctionDeclarationContext ctx)
    {
        SymtabEntry funcEntry = entries.get(ctx);

        symtabStack.push(funcEntry.getProcFuncSymtab());
        beginRoutine(funcEntry, funcEntry.getProcFuncParameters().size());

        // Parameters and the body's top-level locals share one scope
        // (like Java, you can't redeclare a parameter in the body).
        visitStatements(ctx.block());

        endRoutine(funcEntry);
        symtabStack.pop();
        return null;
    }

    @Override
    public Object visitMainMethod(MainMethodContext ctx)
    {
        Symtab programSymtab = programEntry.getProcFuncSymtab();

        // Not entered in the symtab, so a user function may also be called main.
        mainEntry = new SymtabEntry("main", PROCEDURE, programSymtab);
        mainEntry.appendLineNumber(ctx.getStart().getLine());

        Symtab mainSymtab = symtabStack.push();
        mainSymtab.setOwner(mainEntry);
        mainEntry.setProcFuncSymtab(mainSymtab);

        beginRoutine(mainEntry, 1);  // slot 0 is String[] args
        visitStatements(ctx.block());
        endRoutine(mainEntry);

        symtabStack.pop();
        return null;
    }

    private void beginRoutine(SymtabEntry routine, int firstFreeSlot)
    {
        currentFunction = routine;
        functionNestingLevel = symtabStack.getCurrentNestingLevel();
        nextSlot = firstFreeSlot;
        loopDepth = 0;
    }

    private void endRoutine(SymtabEntry routine)
    {
        localsCounts.put(routine, Math.max(nextSlot, 1));
        currentFunction = null;
    }

    // ======
    // Blocks
    // ======

    /** A nested block (if / while / for body) gets its own scope. */
    @Override
    public Object visitBlock(BlockContext ctx)
    {
        symtabStack.push();
        visitStatements(ctx);
        symtabStack.pop();
        return null;
    }

    /** Visit a block's statements without opening a new scope. */
    private void visitStatements(BlockContext ctx)
    {
        for (StatementContext stmtCtx : ctx.statement()) visit(stmtCtx);
    }

    // ============
    // Declarations
    // ============

    @Override
    public Object visitVariableDeclaration(VariableDeclarationContext ctx)
    {
        declareLocal(ctx.valueType(), ctx.identifier(), ctx.expression(), VARIABLE);
        return null;
    }

    @Override
    public Object visitConstantDeclaration(ConstantDeclarationContext ctx)
    {
        declareLocal(ctx.valueType(), ctx.identifier(), ctx.expression(), CONSTANT);
        return null;
    }

    /**
     * Shared code for "bet" variables and "24k gold labubu" constants.
     */
    private void declareLocal(ValueTypeContext typeCtx, IdentifierContext idCtx,
                              ExpressionContext initCtx, Kind kind)
    {
        Typespec type = valueTypeOf(typeCtx);
        String name = idCtx.getText();

        // Check the initializer first, so "bet dabloons x is giving x" is an error.
        if (initCtx != null)
        {
            Typespec initType = (Typespec) visit(initCtx);
            if (!isUnknown(initType) && initType != type)
            {
                error.flag(INCOMPATIBLE_ASSIGNMENT, initCtx);
            }
        }

        // No redeclaring a name anywhere in the same function (like Java).
        SymtabEntry entry;
        if (lookupInFunction(name) != null)
        {
            error.flag(REDECLARED_IDENTIFIER, idCtx);
            entry = new SymtabEntry(name, kind, symtabStack.getLocalSymtab());
        }
        else
        {
            entry = symtabStack.enterLocal(name, kind);
        }

        entry.setTypespec(type);
        entry.setSlotNumber(nextSlot++);
        entry.appendLineNumber(idCtx.getStart().getLine());
        entries.put(idCtx, entry);
    }

    // ==========
    // Statements
    // ==========

    @Override
    public Object visitAssignmentStatement(AssignmentStatementContext ctx)
    {
        Typespec exprType = (Typespec) visit(ctx.expression());
        IdentifierContext idCtx = ctx.identifier();
        SymtabEntry target = lookupVariable(idCtx);

        if (target == null) return null;  // already flagged

        if (target.getKind() == CONSTANT)
        {
            error.flag(ASSIGN_TO_CONSTANT, idCtx);
        }
        else if (!isUnknown(exprType) && exprType != target.getTypespec())
        {
            error.flag(INCOMPATIBLE_ASSIGNMENT, ctx);
        }

        return null;
    }

    @Override
    public Object visitIfStatement(IfStatementContext ctx)
    {
        for (ExpressionContext condCtx : ctx.expression()) checkCondition(condCtx);
        for (BlockContext blockCtx : ctx.block()) visit(blockCtx);
        return null;
    }

    @Override
    public Object visitWhileStatement(WhileStatementContext ctx)
    {
        checkCondition(ctx.expression());

        loopDepth++;
        visit(ctx.block());
        loopDepth--;

        return null;
    }

    @Override
    public Object visitForStatement(ForStatementContext ctx)
    {
        // The initializer's variable is scoped to the whole for statement.
        symtabStack.push();

        if (ctx.forInitializer() != null) visit(ctx.forInitializer());
        if (ctx.expression() != null) checkCondition(ctx.expression());
        if (ctx.assignmentStatement() != null) visit(ctx.assignmentStatement());

        loopDepth++;
        visit(ctx.block());
        loopDepth--;

        symtabStack.pop();
        return null;
    }

    @Override
    public Object visitBreakStatement(BreakStatementContext ctx)
    {
        if (loopDepth == 0) error.flag(BREAK_OUTSIDE_LOOP, ctx);
        return null;
    }

    @Override
    public Object visitContinueStatement(ContinueStatementContext ctx)
    {
        if (loopDepth == 0) error.flag(BREAK_OUTSIDE_LOOP, ctx);
        return null;
    }

    @Override
    public Object visitExitStatement(ExitStatementContext ctx)
    {
        return null;
    }

    @Override
    public Object visitReturnStatement(ReturnStatementContext ctx)
    {
        Typespec returnType = currentFunction.getTypespec();  // null = void
        ExpressionContext exprCtx = ctx.expression();

        if (returnType == null)  // sigma function or main
        {
            if (exprCtx != null)
            {
                visit(exprCtx);
                error.flag(RETURN_VALUE_IN_VOID, ctx);
            }
        }
        else if (exprCtx == null)
        {
            error.flag(MISSING_RETURN_VALUE, ctx);
        }
        else
        {
            Typespec exprType = (Typespec) visit(exprCtx);
            if (!isUnknown(exprType) && exprType != returnType)
            {
                error.flag(INVALID_RETURN_TYPE, exprCtx);
            }
        }

        return null;
    }

    @Override
    public Object visitPrintStatement(PrintStatementContext ctx)
    {
        if (ctx.argumentList() != null)
        {
            for (ExpressionContext argCtx : ctx.argumentList().expression())
            {
                visit(argCtx);  // void calls are flagged in visitPrimaryExpression
            }
        }
        return null;
    }

    @Override
    public Object visitCallStatement(CallStatementContext ctx)
    {
        visit(ctx.functionCall());  // a returned value is simply discarded
        return null;
    }

    /**
     * john pork name(args)
     * @return the function's return type (null for sigma, unknown on error).
     */
    @Override
    public Object visitFunctionCall(FunctionCallContext ctx)
    {
        IdentifierContext idCtx = ctx.identifier();
        String name = idCtx.getText();

        ArrayList<ExpressionContext> args = new ArrayList<>();
        if (ctx.argumentList() != null) args.addAll(ctx.argumentList().expression());

        ArrayList<Typespec> argTypes = new ArrayList<>();
        for (ExpressionContext argCtx : args) argTypes.add((Typespec) visit(argCtx));

        SymtabEntry funcEntry = lookup(name);

        if (funcEntry == null)
        {
            error.flag(UNDECLARED_IDENTIFIER, idCtx);
            return Predefined.undefinedType;
        }

        Kind kind = funcEntry.getKind();
        if ((kind != FUNCTION) && (kind != PROCEDURE))
        {
            error.flag(NAME_MUST_BE_FUNCTION, idCtx);
            return Predefined.undefinedType;
        }

        funcEntry.appendLineNumber(idCtx.getStart().getLine());
        entries.put(ctx, funcEntry);

        ArrayList<SymtabEntry> parms = funcEntry.getProcFuncParameters();
        if (parms.size() != args.size())
        {
            error.flag(ARGUMENT_COUNT_MISMATCH, ctx);
        }
        else
        {
            for (int i = 0; i < args.size(); i++)
            {
                Typespec argType = argTypes.get(i);
                if (!isUnknown(argType) && argType != parms.get(i).getTypespec())
                {
                    error.flag(TYPE_MISMATCH, args.get(i));
                }
            }
        }

        return funcEntry.getTypespec();
    }

    // ===========
    // Expressions
    // ===========

    /** additive (relop additive)* */
    @Override
    public Object visitExpression(ExpressionContext ctx)
    {
        Typespec type = (Typespec) visit(ctx.additiveExpression(0));

        for (int i = 0; i < ctx.relationalOperator().size(); i++)
        {
            Typespec rightType = (Typespec) visit(ctx.additiveExpression(i + 1));
            int op = ((TerminalNode) ctx.relationalOperator(i).getChild(0))
                    .getSymbol().getType();

            if (isUnknown(type) || isUnknown(rightType))
            {
                type = Predefined.undefinedType;
                continue;
            }

            boolean ok;
            if (type != rightType)                     ok = false;
            else if (type == Predefined.booleanType)   ok = isEqualityOp(op);
            else                                       ok = true;  // ints, chats

            if (!ok) error.flag(INCOMPATIBLE_COMPARISON, ctx);
            type = ok ? Predefined.booleanType : Predefined.undefinedType;
        }

        return annotate(ctx, type);
    }

    /** multiplicative ((aura gain | aura loss | + | -) multiplicative)* */
    @Override
    public Object visitAdditiveExpression(AdditiveExpressionContext ctx)
    {
        Typespec type = (Typespec) visit(ctx.multiplicativeExpression(0));

        // Children alternate: operand, operator, operand, operator, ...
        for (int i = 1; i < ctx.getChildCount(); i += 2)
        {
            int op = ((TerminalNode) ctx.getChild(i)).getSymbol().getType();
            Typespec rightType = (Typespec) visit(ctx.getChild(i + 1));

            if (isUnknown(type) || isUnknown(rightType))
            {
                type = Predefined.undefinedType;
            }
            else if (isPlus(op))
            {
                if ((type == rightType) &&
                        ((type == Predefined.integerType) || (type == Predefined.stringType)))
                {
                    // int + int, or chat + chat (join), keeps the same type
                }
                else
                {
                    error.flag(TYPE_MISMATCH, ctx);
                    type = Predefined.undefinedType;
                }
            }
            else  // minus
            {
                type = requireIntegers(type, rightType, ctx);
            }
        }

        return annotate(ctx, type);
    }

    /** unary ((aura farm | chopped cheese | * | /) unary)* */
    @Override
    public Object visitMultiplicativeExpression(MultiplicativeExpressionContext ctx)
    {
        Typespec type = (Typespec) visit(ctx.unaryExpression(0));

        for (int i = 1; i < ctx.getChildCount(); i += 2)
        {
            Typespec rightType = (Typespec) visit(ctx.getChild(i + 1));
            type = requireIntegers(type, rightType, ctx);
        }

        return annotate(ctx, type);
    }

    @Override
    public Object visitUnaryExpression(UnaryExpressionContext ctx)
    {
        Typespec type;

        if (ctx.primaryExpression() != null)
        {
            type = (Typespec) visit(ctx.primaryExpression());
        }
        else
        {
            type = (Typespec) visit(ctx.unaryExpression());
            if (!isUnknown(type) && type != Predefined.integerType)
            {
                error.flag(TYPE_MUST_BE_INTEGER, ctx);
                type = Predefined.undefinedType;
            }
        }

        return annotate(ctx, type);
    }

    @Override
    public Object visitPrimaryExpression(PrimaryExpressionContext ctx)
    {
        Typespec type;

        if (ctx.INTEGER() != null)
        {
            try
            {
                Integer.parseInt(ctx.INTEGER().getText());
                type = Predefined.integerType;
            }
            catch (NumberFormatException ex)
            {
                error.flag(INVALID_CONSTANT, ctx);
                type = Predefined.undefinedType;
            }
        }
        else if (ctx.STRING() != null)
        {
            type = Predefined.stringType;
        }
        else if (ctx.functionCall() != null)
        {
            type = (Typespec) visit(ctx.functionCall());
            if (type == null)  // sigma function used as a value
            {
                error.flag(VOID_IN_EXPRESSION, ctx);
                type = Predefined.undefinedType;
            }
        }
        else if (ctx.identifier() != null)
        {
            SymtabEntry entry = lookupVariable(ctx.identifier());
            type = (entry != null) ? entry.getTypespec() : Predefined.undefinedType;
        }
        else  // ( expression )
        {
            type = (Typespec) visit(ctx.expression());
        }

        return annotate(ctx, type);
    }

    // =========
    // Utilities
    // =========

    private Typespec annotate(ParserRuleContext ctx, Typespec type)
    {
        types.put(ctx, type);
        return type;
    }

    private void checkCondition(ExpressionContext ctx)
    {
        Typespec type = (Typespec) visit(ctx);
        if (!isUnknown(type) && type != Predefined.booleanType)
        {
            error.flag(TYPE_MUST_BE_BOOLEAN, ctx);
        }
    }

    private Typespec requireIntegers(Typespec left, Typespec right, ParserRuleContext ctx)
    {
        if (isUnknown(left) || isUnknown(right)) return Predefined.undefinedType;

        if ((left == Predefined.integerType) && (right == Predefined.integerType))
        {
            return Predefined.integerType;
        }

        error.flag(TYPE_MUST_BE_INTEGER, ctx);
        return Predefined.undefinedType;
    }

    private static boolean isUnknown(Typespec type)
    {
        return (type == null) || (type == Predefined.undefinedType);
    }

    private static boolean isPlus(int op)
    {
        return (op == BrainrotParser.AURA_GAIN) || (op == BrainrotParser.PLUS);
    }

    private static boolean isEqualityOp(int op)
    {
        return (op == BrainrotParser.NO_CAP) || (op == BrainrotParser.EQ)
                || (op == BrainrotParser.CAP)    || (op == BrainrotParser.NE);
    }

    private static Typespec valueTypeOf(ValueTypeContext ctx)
    {
        return (ctx.DABLOONS() != null) ? Predefined.integerType
                : Predefined.stringType;
    }

    private static Typespec returnTypeOf(ReturnTypeContext ctx)
    {
        return (ctx.SIGMA() != null) ? null : valueTypeOf(ctx.valueType());
    }

    /**
     * Look up a name in the program's own scopes, skipping the level 0
     * predefined (Pascal) entries like "integer", "true", "abs".
     */
    private SymtabEntry lookup(String name)
    {
        SymtabEntry entry = symtabStack.lookup(name);
        if ((entry != null) && (entry.getSymtab().getNestingLevel() == 0)) return null;
        return entry;
    }

    /** Look up a name only within the current function's scopes. */
    private SymtabEntry lookupInFunction(String name)
    {
        for (int level = symtabStack.getCurrentNestingLevel();
             level >= functionNestingLevel; level--)
        {
            SymtabEntry entry = symtabStack.get(level).lookup(name);
            if (entry != null) return entry;
        }
        return null;
    }

    /**
     * Look up a variable, constant, or parameter used in an expression or
     * as an assignment target. Flags undeclared names ("sussy baka").
     */
    private SymtabEntry lookupVariable(IdentifierContext idCtx)
    {
        SymtabEntry entry = lookupInFunction(idCtx.getText());

        if (entry == null)
        {
            if (lookup(idCtx.getText()) != null) error.flag(INVALID_VARIABLE, idCtx);
            else                                 error.flag(UNDECLARED_IDENTIFIER, idCtx);
            return null;
        }

        Kind kind = entry.getKind();
        if ((kind != VARIABLE) && (kind != CONSTANT) && (kind != VALUE_PARAMETER))
        {
            error.flag(INVALID_VARIABLE, idCtx);
            return null;
        }

        entry.appendLineNumber(idCtx.getStart().getLine());
        entries.put(idCtx, entry);
        return entry;
    }
}