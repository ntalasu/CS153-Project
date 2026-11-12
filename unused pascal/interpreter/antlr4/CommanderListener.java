// Generated from Commander.g4 by ANTLR 4.13.2

    package backend.interpreter.antlr4;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CommanderParser}.
 */
public interface CommanderListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CommanderParser#command}.
	 * @param ctx the parse tree
	 */
	void enterCommand(CommanderParser.CommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommanderParser#command}.
	 * @param ctx the parse tree
	 */
	void exitCommand(CommanderParser.CommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommanderParser#goCommand}.
	 * @param ctx the parse tree
	 */
	void enterGoCommand(CommanderParser.GoCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommanderParser#goCommand}.
	 * @param ctx the parse tree
	 */
	void exitGoCommand(CommanderParser.GoCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommanderParser#stepCommand}.
	 * @param ctx the parse tree
	 */
	void enterStepCommand(CommanderParser.StepCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommanderParser#stepCommand}.
	 * @param ctx the parse tree
	 */
	void exitStepCommand(CommanderParser.StepCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommanderParser#quitCommand}.
	 * @param ctx the parse tree
	 */
	void enterQuitCommand(CommanderParser.QuitCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommanderParser#quitCommand}.
	 * @param ctx the parse tree
	 */
	void exitQuitCommand(CommanderParser.QuitCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommanderParser#breakCommand}.
	 * @param ctx the parse tree
	 */
	void enterBreakCommand(CommanderParser.BreakCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommanderParser#breakCommand}.
	 * @param ctx the parse tree
	 */
	void exitBreakCommand(CommanderParser.BreakCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommanderParser#unbreakCommand}.
	 * @param ctx the parse tree
	 */
	void enterUnbreakCommand(CommanderParser.UnbreakCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommanderParser#unbreakCommand}.
	 * @param ctx the parse tree
	 */
	void exitUnbreakCommand(CommanderParser.UnbreakCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommanderParser#showCommand}.
	 * @param ctx the parse tree
	 */
	void enterShowCommand(CommanderParser.ShowCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommanderParser#showCommand}.
	 * @param ctx the parse tree
	 */
	void exitShowCommand(CommanderParser.ShowCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommanderParser#stackCommand}.
	 * @param ctx the parse tree
	 */
	void enterStackCommand(CommanderParser.StackCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommanderParser#stackCommand}.
	 * @param ctx the parse tree
	 */
	void exitStackCommand(CommanderParser.StackCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommanderParser#watchCommand}.
	 * @param ctx the parse tree
	 */
	void enterWatchCommand(CommanderParser.WatchCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommanderParser#watchCommand}.
	 * @param ctx the parse tree
	 */
	void exitWatchCommand(CommanderParser.WatchCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommanderParser#unwatchCommand}.
	 * @param ctx the parse tree
	 */
	void enterUnwatchCommand(CommanderParser.UnwatchCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommanderParser#unwatchCommand}.
	 * @param ctx the parse tree
	 */
	void exitUnwatchCommand(CommanderParser.UnwatchCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommanderParser#lineNumberList}.
	 * @param ctx the parse tree
	 */
	void enterLineNumberList(CommanderParser.LineNumberListContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommanderParser#lineNumberList}.
	 * @param ctx the parse tree
	 */
	void exitLineNumberList(CommanderParser.LineNumberListContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommanderParser#variableList}.
	 * @param ctx the parse tree
	 */
	void enterVariableList(CommanderParser.VariableListContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommanderParser#variableList}.
	 * @param ctx the parse tree
	 */
	void exitVariableList(CommanderParser.VariableListContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommanderParser#simpleVariableList}.
	 * @param ctx the parse tree
	 */
	void enterSimpleVariableList(CommanderParser.SimpleVariableListContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommanderParser#simpleVariableList}.
	 * @param ctx the parse tree
	 */
	void exitSimpleVariableList(CommanderParser.SimpleVariableListContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommanderParser#lineNumber}.
	 * @param ctx the parse tree
	 */
	void enterLineNumber(CommanderParser.LineNumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommanderParser#lineNumber}.
	 * @param ctx the parse tree
	 */
	void exitLineNumber(CommanderParser.LineNumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommanderParser#simpleVariable}.
	 * @param ctx the parse tree
	 */
	void enterSimpleVariable(CommanderParser.SimpleVariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommanderParser#simpleVariable}.
	 * @param ctx the parse tree
	 */
	void exitSimpleVariable(CommanderParser.SimpleVariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommanderParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(CommanderParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommanderParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(CommanderParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommanderParser#modifier}.
	 * @param ctx the parse tree
	 */
	void enterModifier(CommanderParser.ModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommanderParser#modifier}.
	 * @param ctx the parse tree
	 */
	void exitModifier(CommanderParser.ModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommanderParser#indexList}.
	 * @param ctx the parse tree
	 */
	void enterIndexList(CommanderParser.IndexListContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommanderParser#indexList}.
	 * @param ctx the parse tree
	 */
	void exitIndexList(CommanderParser.IndexListContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommanderParser#index}.
	 * @param ctx the parse tree
	 */
	void enterIndex(CommanderParser.IndexContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommanderParser#index}.
	 * @param ctx the parse tree
	 */
	void exitIndex(CommanderParser.IndexContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommanderParser#field}.
	 * @param ctx the parse tree
	 */
	void enterField(CommanderParser.FieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommanderParser#field}.
	 * @param ctx the parse tree
	 */
	void exitField(CommanderParser.FieldContext ctx);
}