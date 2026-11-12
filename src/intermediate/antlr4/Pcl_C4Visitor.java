// Generated from Pcl_C4.g4 by ANTLR 4.13.2

    package intermediate.antlr4;

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
	 * Visit a parse tree produced by {@link Pcl_C4Parser#importDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportDeclaration(Pcl_C4Parser.ImportDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#qualifiedName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQualifiedName(Pcl_C4Parser.QualifiedNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#mainMethod}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMainMethod(Pcl_C4Parser.MainMethodContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#functionDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDeclaration(Pcl_C4Parser.FunctionDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#returnType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnType(Pcl_C4Parser.ReturnTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#valueType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValueType(Pcl_C4Parser.ValueTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#parameterList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterList(Pcl_C4Parser.ParameterListContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter(Pcl_C4Parser.ParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(Pcl_C4Parser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#separator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSeparator(Pcl_C4Parser.SeparatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(Pcl_C4Parser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#variableDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclaration(Pcl_C4Parser.VariableDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#constantDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantDeclaration(Pcl_C4Parser.ConstantDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#assignmentStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentStatement(Pcl_C4Parser.AssignmentStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#assignmentOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentOperator(Pcl_C4Parser.AssignmentOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(Pcl_C4Parser.IfStatementContext ctx);
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
	 * Visit a parse tree produced by {@link Pcl_C4Parser#forInitializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForInitializer(Pcl_C4Parser.ForInitializerContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#breakStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakStatement(Pcl_C4Parser.BreakStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#continueStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinueStatement(Pcl_C4Parser.ContinueStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#exitStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExitStatement(Pcl_C4Parser.ExitStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#returnStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStatement(Pcl_C4Parser.ReturnStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#printStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintStatement(Pcl_C4Parser.PrintStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#callStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallStatement(Pcl_C4Parser.CallStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#argumentList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgumentList(Pcl_C4Parser.ArgumentListContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(Pcl_C4Parser.FunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(Pcl_C4Parser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#relationalOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationalOperator(Pcl_C4Parser.RelationalOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#additiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveExpression(Pcl_C4Parser.AdditiveExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicativeExpression(Pcl_C4Parser.MultiplicativeExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#unaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpression(Pcl_C4Parser.UnaryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryExpression(Pcl_C4Parser.PrimaryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Pcl_C4Parser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(Pcl_C4Parser.IdentifierContext ctx);
}