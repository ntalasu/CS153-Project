package backend.converter;

import intermediate.antlr4.Pcl_C4Parser.*;
import intermediate.antlr4.Pcl_C4BaseVisitor;
import intermediate.type.Typespec;

import static intermediate.semantics.Semantics.programEntry;

public class Converter extends Pcl_C4BaseVisitor<Object>
{
    protected static JavaEmitter code;
    protected static boolean programDeclarations = true;
    protected static boolean recordFields = false;
    protected static String currentSeparator = "";
    protected static String programName;

    protected static Program     programImpl;  
    protected static Constants   constantsImpl;
    protected static Types       typesImpl;
    protected static ProcFuncs   procFuncsImpl;
    protected static Variables   variablesImpl;
    protected static Statements  statementsImpl;
    protected static Expressions expressionsImpl;

    static
    {
        programImpl     = new Program();
        constantsImpl   = new Constants();
        typesImpl       = new Types();
        procFuncsImpl   = new ProcFuncs();
        variablesImpl   = new Variables();
        statementsImpl  = new Statements();
        expressionsImpl = new Expressions();
    }
    
    public String getObjectFileName() { return code.getObjectFileName(); }

    @Override
    public Object visitProgram(ProgramContext ctx)
    {
        programName = programEntry.getName();
        code = new JavaEmitter(programName);

        return programImpl.program(ctx);
    }

    @Override 
    public Object visitConstantDefinition(ConstantDefinitionContext ctx) 
    {
        return constantsImpl.constantDefinition(ctx);
    }
    
    @Override
    public Object visitConstantSignedIdentifier(ConstantSignedIdentifierContext ctx)
    {
        return constantsImpl.constantSignedIdentifier(ctx);
    }
    
    @Override 
    public Object visitConstantIdentifier(ConstantIdentifierContext ctx) 
    { 
        return constantsImpl.constantIdentifier(ctx); 
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
    public Object visitCharacterConstant(CharacterConstantContext ctx) 
    { 
        return constantsImpl.characterConstant(ctx); 
    }

    @Override 
    public Object visitStringConstant(StringConstantContext ctx) 
    { 
        return constantsImpl.stringConstant(ctx); 
    }

    @Override 
    public Object visitBooleanConstant(BooleanConstantContext ctx) 
    { 
        return constantsImpl.booleanConstant(ctx); 
    }

    @Override 
    public Object visitTypeDefinition(TypeDefinitionContext ctx) 
    {
        return typesImpl.typeDefinition(ctx);
    }

    @Override 
    public Object visitTypeIdentifier(TypeIdentifierContext ctx) 
    {
        Typespec pascalTypespec = ctx.identifier().typespec;
        String javaTypeName = Types.javaTypeName(pascalTypespec);        
        code.emit(javaTypeName);
        
        return null;
    }
    
    @Override
    public Object visitEnumeratedType(EnumeratedTypeContext ctx)
    {
        return typesImpl.enumeratedType(ctx);
    }
    
    @Override
    public Object visitRecordType(RecordTypeContext ctx)
    {
        return typesImpl.recordType(ctx);
    }

    @Override 
    public Object visitVariableDeclarations(
                                VariableDeclarationsContext ctx) 
    { 
        return variablesImpl.variableDeclarations(ctx);
    }

    @Override 
    public Object visitProcFuncDefinition(
                            ProcFuncDefinitionContext ctx) 
    {
        return procFuncsImpl.procFuncDefinition(ctx);
    }
    
    @Override 
    public Object visitParameters(ParametersContext ctx) 
    {
        currentSeparator = "";
        return visit(ctx.parameterDeclarationsList());
    }

    @Override 
    public Object visitParameterDeclarations(
                            ParameterDeclarationsContext ctx) 
    {
        return procFuncsImpl.parameterDeclarations(ctx);
    }
    
    @Override
    public Object visitCompoundStatement(CompoundStatementContext ctx) 
    {
        return statementsImpl.compoundStatement(ctx); 
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
    public Object visitCaseBranch(CaseBranchContext ctx)
    {
        return statementsImpl.caseBranch(ctx);
    }

    @Override 
    public Object visitProcedureCallStatement(ProcedureCallStatementContext ctx) 
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
    public Object visitFunctionCall(FunctionCallContext ctx) 
    {
        return expressionsImpl.functionCall(ctx);
    }
    
    @Override
    public Object visitArgumentList(ArgumentListContext ctx)
    {
        return expressionsImpl.argumentList(ctx);
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
