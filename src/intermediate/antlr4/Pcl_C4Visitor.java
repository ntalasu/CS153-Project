// Generated from Pcl_C4.g4 by ANTLR 4.13.2

    package intermediate.antlr4;
    
    import java.util.HashMap;
    import intermediate.symtab.SymtabEntry;
    import intermediate.type.Typespec;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link Pcl_C4Parser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface Pcl_C4Visitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(Pcl_C4Parser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#programHeader}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgramHeader(Pcl_C4Parser.ProgramHeaderContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#programParameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgramParameters(Pcl_C4Parser.ProgramParametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(Pcl_C4Parser.IdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(Pcl_C4Parser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#declarations}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarations(Pcl_C4Parser.DeclarationsContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#constantsPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantsPart(Pcl_C4Parser.ConstantsPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#constantDefinitionsList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantDefinitionsList(Pcl_C4Parser.ConstantDefinitionsListContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#constantDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantDefinition(Pcl_C4Parser.ConstantDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code constantSignedIdentifier}
	 * labeled alternative in {@link Pcl_C4Parser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantSignedIdentifier(Pcl_C4Parser.ConstantSignedIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by the {@code constantSignedInteger}
	 * labeled alternative in {@link Pcl_C4Parser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantSignedInteger(Pcl_C4Parser.ConstantSignedIntegerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code constantSignedReal}
	 * labeled alternative in {@link Pcl_C4Parser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantSignedReal(Pcl_C4Parser.ConstantSignedRealContext ctx);
	/**
	 * Visit a parse tree produced by the {@code constantCharacter}
	 * labeled alternative in {@link Pcl_C4Parser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantCharacter(Pcl_C4Parser.ConstantCharacterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code constantString}
	 * labeled alternative in {@link Pcl_C4Parser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantString(Pcl_C4Parser.ConstantStringContext ctx);
	/**
	 * Visit a parse tree produced by the {@code constantBoolean}
	 * labeled alternative in {@link Pcl_C4Parser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantBoolean(Pcl_C4Parser.ConstantBooleanContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#sign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSign(Pcl_C4Parser.SignContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#typesPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypesPart(Pcl_C4Parser.TypesPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#typeDefinitionsList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDefinitionsList(Pcl_C4Parser.TypeDefinitionsListContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#typeDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDefinition(Pcl_C4Parser.TypeDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#typeSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeSpecification(Pcl_C4Parser.TypeSpecificationContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#typeIdentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeIdentifier(Pcl_C4Parser.TypeIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#ordinalType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrdinalType(Pcl_C4Parser.OrdinalTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#structuredType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructuredType(Pcl_C4Parser.StructuredTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#enumeratedType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumeratedType(Pcl_C4Parser.EnumeratedTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#subrangeType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubrangeType(Pcl_C4Parser.SubrangeTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#arrayType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayType(Pcl_C4Parser.ArrayTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#dimensionList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDimensionList(Pcl_C4Parser.DimensionListContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#indexType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexType(Pcl_C4Parser.IndexTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#elmtType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElmtType(Pcl_C4Parser.ElmtTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#recordType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecordType(Pcl_C4Parser.RecordTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#recordFields}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecordFields(Pcl_C4Parser.RecordFieldsContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#variableDeclarations}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclarations(Pcl_C4Parser.VariableDeclarationsContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#variableIdentifierList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableIdentifierList(Pcl_C4Parser.VariableIdentifierListContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#procedureAndFunctionsPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedureAndFunctionsPart(Pcl_C4Parser.ProcedureAndFunctionsPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#procFuncDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcFuncDefinition(Pcl_C4Parser.ProcFuncDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#procedureHead}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedureHead(Pcl_C4Parser.ProcedureHeadContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#functionHead}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionHead(Pcl_C4Parser.FunctionHeadContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#parameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameters(Pcl_C4Parser.ParametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#parameterDeclarationsList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterDeclarationsList(Pcl_C4Parser.ParameterDeclarationsListContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#parameterDeclarations}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterDeclarations(Pcl_C4Parser.ParameterDeclarationsContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#parameterIdentifierList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterIdentifierList(Pcl_C4Parser.ParameterIdentifierListContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#variablesPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariablesPart(Pcl_C4Parser.VariablesPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#variableDeclarationsList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclarationsList(Pcl_C4Parser.VariableDeclarationsListContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#compoundStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompoundStatement(Pcl_C4Parser.CompoundStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#statementList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementList(Pcl_C4Parser.StatementListContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(Pcl_C4Parser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#assignmentStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentStatement(Pcl_C4Parser.AssignmentStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(Pcl_C4Parser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#trueStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrueStatement(Pcl_C4Parser.TrueStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#falseStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFalseStatement(Pcl_C4Parser.FalseStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#caseStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseStatement(Pcl_C4Parser.CaseStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#caseBranchList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseBranchList(Pcl_C4Parser.CaseBranchListContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#caseBranch}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseBranch(Pcl_C4Parser.CaseBranchContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#caseConstantList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseConstantList(Pcl_C4Parser.CaseConstantListContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#caseConstant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseConstant(Pcl_C4Parser.CaseConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#repeatStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepeatStatement(Pcl_C4Parser.RepeatStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#whileStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStatement(Pcl_C4Parser.WhileStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#forStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStatement(Pcl_C4Parser.ForStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#procedureCallStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedureCallStatement(Pcl_C4Parser.ProcedureCallStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#procedureName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedureName(Pcl_C4Parser.ProcedureNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#argumentList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgumentList(Pcl_C4Parser.ArgumentListContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#argument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgument(Pcl_C4Parser.ArgumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#writeStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWriteStatement(Pcl_C4Parser.WriteStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#writelnStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWritelnStatement(Pcl_C4Parser.WritelnStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#writeArguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWriteArguments(Pcl_C4Parser.WriteArgumentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#writeArgument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWriteArgument(Pcl_C4Parser.WriteArgumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#format}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormat(Pcl_C4Parser.FormatContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#width}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWidth(Pcl_C4Parser.WidthContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#precision}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrecision(Pcl_C4Parser.PrecisionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#readStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReadStatement(Pcl_C4Parser.ReadStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#readlnStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReadlnStatement(Pcl_C4Parser.ReadlnStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#readArguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReadArguments(Pcl_C4Parser.ReadArgumentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#emptyStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptyStatement(Pcl_C4Parser.EmptyStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(Pcl_C4Parser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#simpleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleExpression(Pcl_C4Parser.SimpleExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(Pcl_C4Parser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#relOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelOp(Pcl_C4Parser.RelOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#addOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddOp(Pcl_C4Parser.AddOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#mulOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulOp(Pcl_C4Parser.MulOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code factorVariable}
	 * labeled alternative in {@link Pcl_C4Parser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactorVariable(Pcl_C4Parser.FactorVariableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code factorIntegerConstant}
	 * labeled alternative in {@link Pcl_C4Parser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactorIntegerConstant(Pcl_C4Parser.FactorIntegerConstantContext ctx);
	/**
	 * Visit a parse tree produced by the {@code factorRealConstant}
	 * labeled alternative in {@link Pcl_C4Parser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactorRealConstant(Pcl_C4Parser.FactorRealConstantContext ctx);
	/**
	 * Visit a parse tree produced by the {@code factorCharacterConstant}
	 * labeled alternative in {@link Pcl_C4Parser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactorCharacterConstant(Pcl_C4Parser.FactorCharacterConstantContext ctx);
	/**
	 * Visit a parse tree produced by the {@code factorStringConstant}
	 * labeled alternative in {@link Pcl_C4Parser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactorStringConstant(Pcl_C4Parser.FactorStringConstantContext ctx);
	/**
	 * Visit a parse tree produced by the {@code factorBooleanConstant}
	 * labeled alternative in {@link Pcl_C4Parser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactorBooleanConstant(Pcl_C4Parser.FactorBooleanConstantContext ctx);
	/**
	 * Visit a parse tree produced by the {@code factorFunctionCall}
	 * labeled alternative in {@link Pcl_C4Parser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactorFunctionCall(Pcl_C4Parser.FactorFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code factorNot}
	 * labeled alternative in {@link Pcl_C4Parser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactorNot(Pcl_C4Parser.FactorNotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code factorParens}
	 * labeled alternative in {@link Pcl_C4Parser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactorParens(Pcl_C4Parser.FactorParensContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(Pcl_C4Parser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#modifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModifier(Pcl_C4Parser.ModifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#indexList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexList(Pcl_C4Parser.IndexListContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#index}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndex(Pcl_C4Parser.IndexContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#field}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitField(Pcl_C4Parser.FieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(Pcl_C4Parser.FunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#functionName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionName(Pcl_C4Parser.FunctionNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#constantIdentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantIdentifier(Pcl_C4Parser.ConstantIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#integerConstant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerConstant(Pcl_C4Parser.IntegerConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#realConstant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRealConstant(Pcl_C4Parser.RealConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#characterConstant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharacterConstant(Pcl_C4Parser.CharacterConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#stringConstant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringConstant(Pcl_C4Parser.StringConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#booleanConstant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanConstant(Pcl_C4Parser.BooleanConstantContext ctx);
}