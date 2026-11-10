package backend.interpreter;

import java.util.Scanner;

import intermediate.antlr4.Pcl_C4Parser.*;
import intermediate.antlr4.Pcl_C4BaseVisitor;
import intermediate.semantics.Semantics;
import intermediate.symtab.SymtabEntry;
import backend.BackendMode;

import static backend.BackendMode.Mode.DEBUGGER;

public class Executor extends Pcl_C4BaseVisitor<Object>
{
    protected static String currentSeparator = "";
    protected static String programName;
    
    protected static int executionCount = 0;     // count of executed statements
    protected static RuntimeStack runtimeStack;  // runtime stack
    protected static Scanner sysin;              // runtime input
    protected static RuntimeErrorHandler error;  // runtime error handler
    protected static Debugger debugger;          // interactive debugger
    protected static BackendMode mode;           // backend mode
    protected static SymtabEntry programEntry;
    protected static boolean debugging;
    
    protected static Program     programImpl;  
    protected static Constants   constantsImpl;
    protected static Variables   variablesImpl;
    protected static Statements  statementsImpl;
    protected static Expressions expressionsImpl;

    static
    {
        programImpl     = new Program();
        constantsImpl   = new Constants();
        variablesImpl   = new Variables();
        statementsImpl  = new Statements();
        expressionsImpl = new Expressions();
        
        runtimeStack = new RuntimeStack();
        sysin = new Scanner(System.in);
        error = new RuntimeErrorHandler();
        debugger = new Debugger(runtimeStack);
        
        programEntry = Semantics.programEntry;
    }
    
    public Executor()
    {
        debugging = BackendMode.mode == DEBUGGER;
    }
    
    /**
     * Convert a Pascal string to a Java string.
     * @param pascalString the Pascal string.
     * @return the Java string.
     */
    protected String convertString(String pascalString)
    {
        String unquoted = pascalString.substring(1, pascalString.length()-1);
        return unquoted.replace("''", "'");
    }

    @Override
    public Object visitProgram(ProgramContext ctx)
    {
        programName = programEntry.getName();
        return programImpl.program(ctx);
    }

    
    @Override 
    public Object visitParameters(ParametersContext ctx) 
    {
        currentSeparator = "";
        return visit(ctx.parameterDeclarationsList());
    }
    
    @Override
    public Object visitStatement(StatementContext ctx)
    {
        return statementsImpl.statement(ctx);
    }
    
    @Override
    public Object visitStatementList(StatementListContext ctx) 
    {
        return statementsImpl.statementList(ctx);
    }
    
    @Override
    public Object visitAssignmentStatement(AssignmentStatementContext ctx) 
    { 
        return statementsImpl.assignmentStatement(ctx); 
    }
    
    @Override 
    public Object visitIfStatement(IfStatementContext ctx)
    {
        return statementsImpl.ifStatement(ctx);
    }
    
    @Override
    public Object visitRepeatStatement(RepeatStatementContext ctx)
    {
        return statementsImpl.repeatStatement(ctx);
    }

    @Override 
    public Object visitWhileStatement(WhileStatementContext ctx) 
    { 
        return statementsImpl.whileStatement(ctx); 
    }

    @Override 
    public Object visitForStatement(ForStatementContext ctx) 
    {
        return statementsImpl.forStatement(ctx);
    }
    
    @Override
    public Object visitCaseStatement(CaseStatementContext ctx)
    {
        return statementsImpl.caseStatement(ctx);
    }

    @Override 
    public Object visitProcedureCallStatement(ProcedureCallStatementContext ctx) 
    {
        return statementsImpl.procedureCallStatement(ctx);
    }
    
    @Override
    public Object visitWritelnStatement(WritelnStatementContext ctx)
    {
        return statementsImpl.writelnStatement(ctx);
    }
    
    @Override
    public Object visitWriteArguments(WriteArgumentsContext ctx)
    {
        return statementsImpl.writeArguments(ctx);
    }
    
    @Override 
    public Object visitExpression(ExpressionContext ctx) 
    { 
        return expressionsImpl.expression(ctx); 
    }

    @Override 
    public Object visitSimpleExpression(SimpleExpressionContext ctx) 
    { 
        return expressionsImpl.simpleExpression(ctx); 
    }

    @Override 
    public Object visitTerm(TermContext ctx) 
    { 
        return expressionsImpl.term(ctx); 
    }

    @Override 
    public Object visitVariable(VariableContext ctx) 
    { 
        return variablesImpl.variable(ctx); 
    }

    @Override 
    public Object visitFactorVariable(FactorVariableContext ctx) 
    { 
        return expressionsImpl.factorVariable(ctx); 
    }
    
    @Override 
    public Object visitFactorIntegerConstant(FactorIntegerConstantContext ctx)
    { 
        return constantsImpl.integerConstant(ctx.integerConstant()); 
    }

    @Override 
    public Object visitFactorRealConstant(FactorRealConstantContext ctx)
    { 
        return constantsImpl.realConstant(ctx.realConstant()); 
    }

    @Override 
    public Object visitFactorCharacterConstant(FactorCharacterConstantContext ctx) 
    { 
        return constantsImpl.characterConstant(ctx.characterConstant()); 
    }

    @Override 
    public Object visitFactorStringConstant(FactorStringConstantContext ctx) 
    { 
        return constantsImpl.stringConstant(ctx.stringConstant()); 
    }

    @Override 
    public Object visitFactorBooleanConstant(FactorBooleanConstantContext ctx) 
    { 
        return constantsImpl.booleanConstant(ctx.booleanConstant()); 
    }

    @Override 
    public Object visitFactorFunctionCall(FactorFunctionCallContext ctx) 
    {
        return expressionsImpl.factorFunctionCall(ctx);
    }
    
    @Override
    public Object visitFactorNot(FactorNotContext ctx)
    {
        return expressionsImpl.factorNot(ctx);
    }
    
    @Override
    public Object visitFactorParens(FactorParensContext ctx)
    {
        return expressionsImpl.factorParens(ctx);
    }
}
