// Generated from Brainrot.g4 by ANTLR 4.13.2

    package intermediate.antlr4;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link BrainrotParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface BrainrotVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link BrainrotParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(BrainrotParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link BrainrotParser#importDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportDeclaration(BrainrotParser.ImportDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link BrainrotParser#qualifiedName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQualifiedName(BrainrotParser.QualifiedNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link BrainrotParser#mainMethod}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMainMethod(BrainrotParser.MainMethodContext ctx);
	/**
	 * Visit a parse tree produced by {@link BrainrotParser#functionDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDeclaration(BrainrotParser.FunctionDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link BrainrotParser#returnType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnType(BrainrotParser.ReturnTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link BrainrotParser#valueType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValueType(BrainrotParser.ValueTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link BrainrotParser#parameterList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterList(BrainrotParser.ParameterListContext ctx);
	/**
	 * Visit a parse tree produced by {@link BrainrotParser#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter(BrainrotParser.ParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link BrainrotParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(BrainrotParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link BrainrotParser#separator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSeparator(BrainrotParser.SeparatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link BrainrotParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(BrainrotParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link BrainrotParser#variableDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclaration(BrainrotParser.VariableDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link BrainrotParser#constantDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantDeclaration(BrainrotParser.ConstantDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link BrainrotParser#assignmentStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentStatement(BrainrotParser.AssignmentStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link BrainrotParser#assignmentOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentOperator(BrainrotParser.AssignmentOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link BrainrotParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(BrainrotParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link BrainrotParser#whileStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStatement(BrainrotParser.WhileStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link BrainrotParser#forStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStatement(BrainrotParser.ForStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link BrainrotParser#forInitializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForInitializer(BrainrotParser.ForInitializerContext ctx);
	/**
	 * Visit a parse tree produced by {@link BrainrotParser#breakStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakStatement(BrainrotParser.BreakStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link BrainrotParser#continueStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinueStatement(BrainrotParser.ContinueStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link BrainrotParser#exitStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExitStatement(BrainrotParser.ExitStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link BrainrotParser#returnStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStatement(BrainrotParser.ReturnStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link BrainrotParser#printStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintStatement(BrainrotParser.PrintStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link BrainrotParser#callStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallStatement(BrainrotParser.CallStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link BrainrotParser#argumentList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgumentList(BrainrotParser.ArgumentListContext ctx);
	/**
	 * Visit a parse tree produced by {@link BrainrotParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(BrainrotParser.FunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link BrainrotParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(BrainrotParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link BrainrotParser#relationalOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationalOperator(BrainrotParser.RelationalOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link BrainrotParser#additiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveExpression(BrainrotParser.AdditiveExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link BrainrotParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicativeExpression(BrainrotParser.MultiplicativeExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link BrainrotParser#unaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpression(BrainrotParser.UnaryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link BrainrotParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryExpression(BrainrotParser.PrimaryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link BrainrotParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(BrainrotParser.IdentifierContext ctx);
}