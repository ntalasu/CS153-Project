package backend.compiler;

import java.util.ArrayList;

import intermediate.antlr4.Pcl_C4Parser.*;
import intermediate.antlr4.Pcl_C4BaseVisitor;

import static intermediate.semantics.Semantics.programEntry;

public class Compiler extends Pcl_C4BaseVisitor<Object>
{
    protected static JasminEmitter code;
    protected static boolean programDeclarations = true;
    protected static String programName;

    protected static Program     programImpl;  
    protected static ProcFuncs   procFuncsImpl;
    protected static Constants   constantsImpl;
    protected static Variables   variablesImpl;
    protected static Statements  statementsImpl;
    protected static Expressions expressionsImpl;
    
    protected static ArrayList<String> objectFileNames;

    static
    {
        programImpl     = new Program();
        procFuncsImpl   = new ProcFuncs();
        constantsImpl   = new Constants();
        variablesImpl   = new Variables();
        statementsImpl  = new Statements();
        expressionsImpl = new Expressions();
        
        objectFileNames = new ArrayList<>();
    }
    
    public ArrayList<String> getObjectFileNames()
    {
        return objectFileNames;
    }

    @Override
    public Object visitProgram(ProgramContext ctx)
    {
        programName = programEntry.getName();       
        programImpl.program(ctx);
        
        return null;
    }
    
    @Override
    public Object visitProcedureAndFunctionsPart(
                            ProcedureAndFunctionsPartContext ctx)
    {
        return procFuncsImpl.procedureAndFunctionsPart(ctx);
    }

    @Override 
    public Object visitStatement(StatementContext ctx) 
    {
        return statementsImpl.statement(ctx);
    }
    
    @Override
    public Object visitAssignmentStatement(AssignmentStatementContext ctx) 
    { 
        return statementsImpl.assignmentStatement(ctx); 
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
    public Object visitIfStatement(IfStatementContext ctx)
    {
        return statementsImpl.ifStatement(ctx);
    }
    
    @Override
    public Object visitCaseStatement(CaseStatementContext ctx)
    {
        return statementsImpl.caseStatement(ctx);
    }
    
    @Override
    public Object visitProcedureCallStatement(
                                ProcedureCallStatementContext ctx)
    {
        return statementsImpl.procedureCallStatement(ctx);
    }
    
    @Override
    public Object visitWriteStatement(WriteStatementContext ctx)
    {
        return statementsImpl.writeStatement(ctx);
    }
    
    @Override
    public Object visitWritelnStatement(WritelnStatementContext ctx)
    {
        return statementsImpl.writelnStatement(ctx);
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
    public Object visitFactorFunctionCall(FactorFunctionCallContext ctx) 
    {
        return expressionsImpl.factorFunctionCall(ctx);
    }
    
    @Override
    public Object visitFactorVariable(FactorVariableContext ctx)
    {
        return variablesImpl.factorVariable(ctx);
    }
    
    @Override 
    public Object visitFactorNot(FactorNotContext ctx) 
    { 
        return expressionsImpl.factorNot(ctx); 
    }
     
    @Override
    public Object visitVariable(VariableContext ctx)
    {
        return variablesImpl.variable(ctx);
    }
    
    @Override 
    public Object visitIntegerConstant(IntegerConstantContext ctx)
    {
        return constantsImpl.integerConstant(ctx);
    }
    
    @Override 
    public Object visitRealConstant(RealConstantContext ctx)
    {
        return constantsImpl.realConstant(ctx);
    }
    
    @Override 
    public Object visitBooleanConstant(BooleanConstantContext ctx)
    {
        return constantsImpl.booleanConstant(ctx);
    }
    
    @Override 
    public Object visitCharacterConstant(CharacterConstantContext ctx)
    {
        return constantsImpl.characterConstant(ctx);
    }
    
    @Override 
    public Object visitStringConstant(StringConstantContext ctx)
    {
        return constantsImpl.stringConstant(ctx);
    }
}
