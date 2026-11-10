package backend.compiler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import intermediate.antlr4.Pcl_C4Parser.*;
import intermediate.symtab.Predefined;
import intermediate.symtab.SymtabEntry;
import intermediate.type.Typespec;
import intermediate.type.Typespec.Form;

import static intermediate.symtab.SymtabEntry.ProcFuncCode.*;
import static intermediate.type.Typespec.Form.*;
import static backend.compiler.Instruction.*;

public class Statements extends Compiler
{
    Object statement(StatementContext ctx) 
    {
        if (   (ctx.compoundStatement() == null) 
            && (ctx.emptyStatement() == null))
        {
            code.emitComment(ctx);
        }
        
        return visitChildren(ctx);
    }
    
    Object assignmentStatement(AssignmentStatementContext ctx)
    {
        VariableContext   variableCtx  = ctx.variable();
        ExpressionContext exprCtx = ctx.expression();
        SymtabEntry variableEntry = variableCtx.entry;
        Typespec variableTypespec = variableCtx.typespec;
        Typespec exprType = exprCtx.typespec;

        // The last modifier, if any, is the variable's last subscript or field.
        int modifierCount = variableCtx.modifier().size();
        ModifierContext lastModCtx = modifierCount == 0
                            ? null : variableCtx.modifier()
                                                .get(modifierCount - 1);

        // The target variable has subscripts and/or fields.
        if (modifierCount > 0) 
        {
            lastModCtx = variableCtx.modifier().get(modifierCount - 1);
            visit(variableCtx);
        }
        
        // Emit code to evaluate the expression.
        visit(exprCtx);
        
        // real variable := integer constant
        if (   (variableTypespec == Predefined.realType)
            && (exprType.baseType() == Predefined.integerType)) 
        {
            code.emit(I2D);
        }
        
        // Emit code to store the expression value into the target variable.
        // The target variable has no subscripts or fields.
        if (lastModCtx == null) 
        {
            code.emitStoreValue(variableEntry, variableEntry.getTypespec());
        }

        // The target variable is a field.
        else if (lastModCtx.field() != null)
        {
            code.emitStoreValue(lastModCtx.field().entry, 
                                lastModCtx.field().typespec);
        }

        // The target variable is an array element.
        else
        {
            code.emitStoreValue(null, variableTypespec);
        }

        return null;
    }

    Object ifStatement(IfStatementContext ctx)
    {
        TrueStatementContext  trueCtx  = ctx.trueStatement();
        FalseStatementContext falseCtx = ctx.falseStatement();
        Label loopExitLabel = new Label();

        // Generate code for the boolean expression.
        visit(ctx.expression());

        // Generate code for a THEN statement only.
        if (falseCtx == null) 
        {
            code.emit(IFEQ, loopExitLabel);
            visit(trueCtx);
        }

        // Generate code for a THEN statement and an ELSE statement.
        else 
        {
            Label falseLabel = new Label();

            code.emit(IFEQ, falseLabel);
            visit(trueCtx);
            code.emit(GOTO, loopExitLabel);

            code.emitLabel(falseLabel);
            visit(falseCtx);
        }

        code.emitLabel(loopExitLabel);
        
        return null;
    }
    
    Object repeatStatement(RepeatStatementContext ctx) 
    {
        Label loopTopLabel  = new Label();
        Label loopExitLabel = new Label();

        code.emitLabel(loopTopLabel);
        
        visit(ctx.statementList());
        visit(ctx.expression());
        code.emit(IFNE, loopExitLabel);
        code.emit(GOTO, loopTopLabel);
        
        code.emitLabel(loopExitLabel);
        
        return null;
    }
    
    Object whileStatement(WhileStatementContext ctx) 
    {
        Label loopTopLabel  = new Label();
        Label loopExitLabel = new Label();

        code.emitLabel(loopTopLabel);
        
        visit(ctx.expression());
        code.emit(IFEQ, loopExitLabel);
        visit(ctx.statement());
        code.emit(GOTO, loopTopLabel);
        
        code.emitLabel(loopExitLabel);
        
        return null;
    }
    
    Object caseStatement(CaseStatementContext ctx)
    {
        ExpressionContext exprCtx = ctx.expression();
        CaseBranchListContext branchListCtx = ctx.caseBranchList();
        ArrayList<Label> branchLabels = new ArrayList<Label>();

        branchLabels.add(new Label());  // exit label

        // Emit code to evaluate the SELECT expression.
        visit(exprCtx);

        // Process the select branches.
        TreeMap<Integer, Label> labelMap = createCaseMap(branchListCtx,
                                                               branchLabels);

        // Emit code for the LOOKUPSWITCH and the branch statements.
        emitLookupSwitch(labelMap, branchLabels);
        emitBranchStatements(branchListCtx, branchLabels);
        
        return null;
    }
    

    /**
     * Process the CASE map from the CASE branches.
     * @param branchListCtx the CaseBranchListContext.
     * @param branchLabels the branch labels.
     * @return the array list of table entries.
     */
    private TreeMap<Integer, Label> createCaseMap(
                            CaseBranchListContext branchListCtx,
                            ArrayList<Label> branchLabels)
    {
        TreeMap<Integer, Label> caseMap = new TreeMap<>();

        // Loop over the CASE branches.
        for (CaseBranchContext branchCtx : 
                                                    branchListCtx.caseBranch())
        {
            CaseConstantListContext constListCtx = 
                                                    branchCtx.caseConstantList();
        
            if (constListCtx != null)
            {
                Label branchLabel = new Label();
                branchLabels.add(branchLabel);

                // Loop over the CASE constants of each CASE branch.
                for (CaseConstantContext caseConstCtx : 
                                                    constListCtx.caseConstant())
                {
                    int value = caseConstCtx.value;
                    caseMap.put(value, branchLabel);
                }
            }
        }
        
        return caseMap;
    }

    /**
     * Emit code for the LOOKUPSWITCH instruction.
     * @param entries the table entries.
     * @param branchLabels the branch labels.
     */
    private void emitLookupSwitch(TreeMap<Integer, Label> labelMap,
                                  ArrayList<Label> branchLabels)
    {
        code.emitLine();
        code.emit(LOOKUPSWITCH);
        
        Set<Map.Entry<Integer, Label>> labelSet = labelMap.entrySet();
        Iterator<Map.Entry<Integer, Label>> it = labelSet.iterator();
        
        // For each entry, emit the value and its label.
        while (it.hasNext())
        {
            Map.Entry<Integer, Label> mapEntry = it.next();
            code.emitLabel(mapEntry.getKey(), mapEntry.getValue());
        }

        // Emit the default label;
        code.emitLabel("default", branchLabels.get(0));
    }

    /**
     * Emit code for the branch statements.
     * @param branchListCtx the CaseBranchListContext.
     * @param branchLabels the branch labels
     * @throws PascalCompilerException if an error occurred.
     */
    private void emitBranchStatements(
                            CaseBranchListContext branchListCtx,
                            ArrayList<Label> branchLabels)
    {
        code.emitLine();

        // Loop to emit each branch label and then generate code for
        // the corresponding branch statement.
        int i = 1;
        for (CaseBranchContext branchCtx : 
                                                    branchListCtx.caseBranch())
        {
            if (branchCtx.caseConstantList() != null)
            {
                StatementContext stmtCtx = branchCtx.statement();
    
                code.emitLabel(branchLabels.get(i++));
                visit(stmtCtx);
    
                // Branch to the exit label.
                code.emit(GOTO, branchLabels.get(0));
            }
        }

        // Emit the exit label.
        code.emitLabel(branchLabels.get(0));

        code.emitLine();
    }
    
    Object forStatement(ForStatementContext ctx)
    {
        VariableContext   controlCtx   = ctx.variable();
        ExpressionContext startExprCtx = ctx.expression().get(0);
        ExpressionContext stopExprCtx  = ctx.expression().get(1);
        boolean to = ctx.TO() != null;

        Label loopTopLabel  = new Label();
        Label loopExitLabel = new Label();
        
        // Initial control value.
        visit(startExprCtx);
        code.emitStoreValue(controlCtx.entry, ctx.variable().typespec);

        code.emitLabel(loopTopLabel);
        code.emitLoadValue(controlCtx.entry);
        
        // Compare against the terminal control value.
        visit(stopExprCtx);
        if (to) code.emit(IF_ICMPGT, loopExitLabel);
        else    code.emit(IF_ICMPLT, loopExitLabel);
        
        visit(ctx.statement());
        
        // Increment or decrement the control value.
        code.emitLoadValue(controlCtx.entry);
        code.emitLoadConstant(1);
        if (to) code.emit(IADD);
        else    code.emit(ISUB);
        code.emitStoreValue(controlCtx.entry, ctx.variable().typespec);

        code.emit(GOTO, loopTopLabel);
        code.emitLabel(loopExitLabel);

        return null;
    }
    
    Object procedureCallStatement(ProcedureCallStatementContext ctx)
    {
        SymtabEntry procedureEntry = ctx.procedureName().entry;
        emitCall(procedureEntry, ctx.argumentList(), null);
        return null;
    }
    
    /**
     * Emit a call to a procedure or a function.
     * @param procFuncEntry the routine name's symbol table entry.
     * @param argListCtx the ArgumentListContext.
     */
    void emitCall(SymtabEntry procFuncEntry,
                  ArgumentListContext argListCtx,
                  Typespec functionTypespec)
    {
        if (procFuncEntry.getProcFuncCode() == LIBRARY)
        {
            emitStandardCall(procFuncEntry, argListCtx, functionTypespec);
            return;
        }
        
        String procFuncName = procFuncEntry.getName();
        ArrayList<SymtabEntry> parmEntries = 
                            procFuncEntry.getProcFuncParameters();
        
        // Emit code to evaluate any arguments.
        int i = 0;
        if (argListCtx != null)
        {
            for (ArgumentContext argCtx : argListCtx.argument())
            {
                ExpressionContext exprCtx = argCtx.expression();
                visit(exprCtx);
                
                Typespec parmTypespec = parmEntries.get(i++).getTypespec();
                Typespec  argTypespec = exprCtx.typespec;
                
                if (   (parmTypespec == Predefined.realType)
                    && ( argTypespec == Predefined.integerType))
                {
                    code.emit(I2D);
                }
            }
        }
        
        StringBuilder buffer = new StringBuilder();

        // Procedure or function name.
        buffer.append(programName);
        buffer.append("/");
        buffer.append(procFuncName);
        buffer.append("(");

        // Parameter and return type descriptors.
        if (argListCtx != null) 
        {
            for (SymtabEntry parmEntry : parmEntries) 
            {
                buffer.append(code.typeDescriptor(parmEntry));
            }
        }
        buffer.append(")");
        buffer.append(code.typeDescriptor(procFuncEntry));

        // Generate a call to the procedure/function.
        code.emit(INVOKESTATIC, buffer.toString());
        if (argListCtx != null) 
        {
            LocalStack localStack = code.getLocalStack();
            localStack.decrease(parmEntries.size());
        }
    }
    
    private void emitStandardCall(SymtabEntry functionEntry,
                                  ArgumentListContext argListCtx,
                                  Typespec functionTypespec)
    {
        ExpressionContext argCtx = argListCtx != null 
                ? argListCtx.argument(0).expression() : null;
        Typespec argTypespec = null;
        
        if (argCtx != null) 
        {
            visit(argCtx);
            argTypespec = argCtx.typespec;
        }

        StringBuilder buffer = new StringBuilder();

        // Procedure or function name.
        buffer.append("Stdlib/");
        buffer.append(functionEntry.getName());
        
        buffer.append("(");
        if (argCtx != null)
        {
            buffer.append(code.typeDescriptor(argTypespec));
        }
        buffer.append(")");
        buffer.append(code.typeDescriptor(functionTypespec));

        // Generate a call to the procedure/function.
        code.emit(INVOKESTATIC, buffer.toString());
        if (argListCtx != null) 
        {
            LocalStack localStack = code.getLocalStack();
            localStack.decrease(1);
        }
    }
  
    Object writeStatement(WriteStatementContext ctx)
    {
        emitWrite(ctx.writeArguments(), false);
        return null;
    }
    
    Object writelnStatement(WritelnStatementContext ctx)
    {
        emitWrite(ctx.writeArguments(), true);
        return null;
    }

    /**
     * Emit code for a call to WRITE or WRITELN.
     * @param argsCtx the WriteArgumentsContext.
     * @param needLF true if need a line feed.
     */
    private void emitWrite(WriteArgumentsContext argsCtx,
                           boolean needLF)
    {
        LocalStack localStack = code.getLocalStack();

        code.emit(GETSTATIC, "java/lang/System/out", 
                             "Ljava/io/PrintStream;");
        localStack.increase(1);

        // WRITELN with no arguments.
        if (argsCtx == null) 
        {
            code.emit(INVOKEVIRTUAL, "java/io/PrintStream.println()V");
            localStack.decrease(1);
        }
            
        // Generate code for the arguments.
        else
        {
            StringBuffer formatString = new StringBuffer();
            int exprCount = createWriteFormat(argsCtx, formatString, 
                                              needLF);
            
            // Load the format string.
            code.emitLoadConstant(formatString.toString());
            
            // Emit the arguments array.
            if (exprCount > 0)
            {
                emitArgumentsArray(argsCtx, exprCount);

                code.emit(INVOKEVIRTUAL,
                          "java/io/PrintStream/printf(Ljava/lang/String;" +
                          "[Ljava/lang/Object;)" +
                          "Ljava/io/PrintStream;");
                localStack.decrease(2);
                code.emit(POP);
            }
            else
            {
                code.emit(INVOKEVIRTUAL,
                          "java/io/PrintStream/print(Ljava/lang/String;)V");
                localStack.decrease(2);
            }
        }
    }
    
    /**
     * Create the printf format string.
     * @param argsCtx the WriteArgumentsContext.
     * @param format the format string to create.
     * @return the count of expression arguments.
     */
    private int createWriteFormat(WriteArgumentsContext ctx,
                                  StringBuffer formatString, 
                                  boolean needLF)
    {
        int exprCount = 0;
        
        // Loop over the write arguments to build the format string.
        for (WriteArgumentContext writeArgCtx : 
                                    ctx.writeArgument())
        {
            ExpressionContext exprCtx = writeArgCtx.expression();
            FormatContext formatCtx = writeArgCtx.format();  
            
            Typespec typespec = exprCtx.typespec;
            boolean isIntegerType = typespec == Predefined.integerType;
            boolean isRealType    = typespec == Predefined.realType;
            boolean isCharType    = typespec == Predefined.charType;
            boolean isBooleanType = typespec == Predefined.booleanType;
            
            String arg = exprCtx.getText();
            
            // A string or character constant without formatting.
            if (   (formatCtx == null)
                && isSingleton(exprCtx)
                && (   (arg.charAt(0) == '"') 
                    || (arg.charAt(0) == '\'')))
            {
                String format = 
                        arg.substring(1, arg.length()-1);
                format = format.replace("''", "'")
                               .replace("\"", "\\\"");
                formatString.append(format);
            }
            
            // For any other argument, append a field specifier.
            else
            {
                ++exprCount;
                formatString.append("%");
                
                if (formatCtx != null)
                {
                    WidthContext widthCtx = formatCtx.width();
                    SignContext signCtx = widthCtx.sign();
                    String sign = (   (signCtx != null) 
                                   && (signCtx.getText().equals("-"))) 
                                ? "-" : "";
                    formatString.append(sign)
                                .append(widthCtx.integerConstant()
                                                .getText());
                    
                    if (isRealType)
                    {
                        formatString.append(".");
                        
                        PrecisionContext precisionCtx = 
                                                formatCtx.precision();
                        String precisionText = precisionCtx != null
                            ? precisionCtx.integerConstant().getText()
                            : "0";
                        formatString.append(precisionText);
                    }
                }

                String typeFlag = isIntegerType ? "d"
                                : isRealType    ? "f"
                                : isBooleanType ? "b"
                                : isCharType    ? "c" 
                                :                 "s";
                formatString.append(typeFlag);
            }
        }
        
        if (needLF) formatString.append("\\n"); 
        return exprCount;
    }
    
    /**
     * Emit the printf arguments array.
     * @param argsCtx
     * @param exprCount
     */
    private void emitArgumentsArray(WriteArgumentsContext argsCtx,
                                    int exprCount)
    {
        // Create the arguments array.
        code.emitLoadConstant(exprCount);
        code.emit(ANEWARRAY, "java/lang/Object");
        code.getLocalStack().increase(1);

        int index = 0;

        // Loop over the write arguments to fill the arguments array.
        for (WriteArgumentContext argCtx : argsCtx.writeArgument())
        {
            ExpressionContext exprCtx = argCtx.expression();
            FormatContext formatCtx = argCtx.format();  
            Typespec type = exprCtx.typespec.baseType();
            
            String arg = exprCtx.getText();
            
            // Skip unformatted string constants, 
            // which were made part of the format string.
            if (   (formatCtx != null)
                || (!isSingleton(exprCtx))
                || (   (arg.charAt(0) != '"') 
                    && (arg.charAt(0) != '\'')))
            {
                code.emit(DUP);
                code.emitLoadConstant(index++);

                visit(exprCtx);

                Form form = type.getForm();
                if (    ((form == SCALAR) || (form == ENUMERATED))
                     && (type != Predefined.stringType))
                {
                    code.emit(INVOKESTATIC, code.valueOfSignature(type));
                }

                // Store the value into the array.
                code.emit(AASTORE);
            }
        }
    }
    
    private boolean isSingleton(ExpressionContext exprCtx)
    {
        if (exprCtx.simpleExpression().size() > 1) return false;
        
        SimpleExpressionContext simpleExpressionCtx = exprCtx.simpleExpression(0);
        if (simpleExpressionCtx.term().size() > 1) return false;
        
        TermContext termCtx = simpleExpressionCtx.term(0);
        if (termCtx.factor().size() > 1) return false;
        
        return true;
    }
}