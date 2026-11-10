// Generated from Commander.g4 by ANTLR 4.13.2

    package backend.interpreter.antlr4;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class CommanderParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, BREAK=5, UNBREAK=6, WATCH=7, UNWATCH=8, 
		SHOW=9, STACK=10, STEP=11, GO=12, QUIT=13, IDENTIFIER=14, INTEGER=15, 
		NEWLINE=16, WS=17;
	public static final int
		RULE_command = 0, RULE_goCommand = 1, RULE_stepCommand = 2, RULE_quitCommand = 3, 
		RULE_breakCommand = 4, RULE_unbreakCommand = 5, RULE_showCommand = 6, 
		RULE_stackCommand = 7, RULE_watchCommand = 8, RULE_unwatchCommand = 9, 
		RULE_lineNumberList = 10, RULE_variableList = 11, RULE_simpleVariableList = 12, 
		RULE_lineNumber = 13, RULE_simpleVariable = 14, RULE_variable = 15, RULE_modifier = 16, 
		RULE_indexList = 17, RULE_index = 18, RULE_field = 19;
	private static String[] makeRuleNames() {
		return new String[] {
			"command", "goCommand", "stepCommand", "quitCommand", "breakCommand", 
			"unbreakCommand", "showCommand", "stackCommand", "watchCommand", "unwatchCommand", 
			"lineNumberList", "variableList", "simpleVariableList", "lineNumber", 
			"simpleVariable", "variable", "modifier", "indexList", "index", "field"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "','", "'['", "']'", "'.'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, "BREAK", "UNBREAK", "WATCH", "UNWATCH", 
			"SHOW", "STACK", "STEP", "GO", "QUIT", "IDENTIFIER", "INTEGER", "NEWLINE", 
			"WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Commander.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CommanderParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CommandContext extends ParserRuleContext {
		public GoCommandContext goCommand() {
			return getRuleContext(GoCommandContext.class,0);
		}
		public StepCommandContext stepCommand() {
			return getRuleContext(StepCommandContext.class,0);
		}
		public QuitCommandContext quitCommand() {
			return getRuleContext(QuitCommandContext.class,0);
		}
		public BreakCommandContext breakCommand() {
			return getRuleContext(BreakCommandContext.class,0);
		}
		public UnbreakCommandContext unbreakCommand() {
			return getRuleContext(UnbreakCommandContext.class,0);
		}
		public ShowCommandContext showCommand() {
			return getRuleContext(ShowCommandContext.class,0);
		}
		public StackCommandContext stackCommand() {
			return getRuleContext(StackCommandContext.class,0);
		}
		public WatchCommandContext watchCommand() {
			return getRuleContext(WatchCommandContext.class,0);
		}
		public UnwatchCommandContext unwatchCommand() {
			return getRuleContext(UnwatchCommandContext.class,0);
		}
		public CommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_command; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommanderListener ) ((CommanderListener)listener).enterCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommanderListener ) ((CommanderListener)listener).exitCommand(this);
		}
	}

	public final CommandContext command() throws RecognitionException {
		CommandContext _localctx = new CommandContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_command);
		try {
			setState(49);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case GO:
				enterOuterAlt(_localctx, 1);
				{
				setState(40);
				goCommand();
				}
				break;
			case STEP:
			case NEWLINE:
				enterOuterAlt(_localctx, 2);
				{
				setState(41);
				stepCommand();
				}
				break;
			case QUIT:
				enterOuterAlt(_localctx, 3);
				{
				setState(42);
				quitCommand();
				}
				break;
			case BREAK:
				enterOuterAlt(_localctx, 4);
				{
				setState(43);
				breakCommand();
				}
				break;
			case UNBREAK:
				enterOuterAlt(_localctx, 5);
				{
				setState(44);
				unbreakCommand();
				}
				break;
			case SHOW:
				enterOuterAlt(_localctx, 6);
				{
				setState(45);
				showCommand();
				}
				break;
			case STACK:
				enterOuterAlt(_localctx, 7);
				{
				setState(46);
				stackCommand();
				}
				break;
			case WATCH:
				enterOuterAlt(_localctx, 8);
				{
				setState(47);
				watchCommand();
				}
				break;
			case UNWATCH:
				enterOuterAlt(_localctx, 9);
				{
				setState(48);
				unwatchCommand();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class GoCommandContext extends ParserRuleContext {
		public TerminalNode GO() { return getToken(CommanderParser.GO, 0); }
		public TerminalNode NEWLINE() { return getToken(CommanderParser.NEWLINE, 0); }
		public GoCommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_goCommand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommanderListener ) ((CommanderListener)listener).enterGoCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommanderListener ) ((CommanderListener)listener).exitGoCommand(this);
		}
	}

	public final GoCommandContext goCommand() throws RecognitionException {
		GoCommandContext _localctx = new GoCommandContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_goCommand);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			match(GO);
			setState(52);
			match(NEWLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StepCommandContext extends ParserRuleContext {
		public TerminalNode STEP() { return getToken(CommanderParser.STEP, 0); }
		public TerminalNode NEWLINE() { return getToken(CommanderParser.NEWLINE, 0); }
		public StepCommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stepCommand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommanderListener ) ((CommanderListener)listener).enterStepCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommanderListener ) ((CommanderListener)listener).exitStepCommand(this);
		}
	}

	public final StepCommandContext stepCommand() throws RecognitionException {
		StepCommandContext _localctx = new StepCommandContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_stepCommand);
		try {
			setState(57);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STEP:
				enterOuterAlt(_localctx, 1);
				{
				setState(54);
				match(STEP);
				setState(55);
				match(NEWLINE);
				}
				break;
			case NEWLINE:
				enterOuterAlt(_localctx, 2);
				{
				setState(56);
				match(NEWLINE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class QuitCommandContext extends ParserRuleContext {
		public TerminalNode QUIT() { return getToken(CommanderParser.QUIT, 0); }
		public TerminalNode NEWLINE() { return getToken(CommanderParser.NEWLINE, 0); }
		public QuitCommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_quitCommand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommanderListener ) ((CommanderListener)listener).enterQuitCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommanderListener ) ((CommanderListener)listener).exitQuitCommand(this);
		}
	}

	public final QuitCommandContext quitCommand() throws RecognitionException {
		QuitCommandContext _localctx = new QuitCommandContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_quitCommand);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			match(QUIT);
			setState(60);
			match(NEWLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BreakCommandContext extends ParserRuleContext {
		public TerminalNode BREAK() { return getToken(CommanderParser.BREAK, 0); }
		public TerminalNode NEWLINE() { return getToken(CommanderParser.NEWLINE, 0); }
		public LineNumberListContext lineNumberList() {
			return getRuleContext(LineNumberListContext.class,0);
		}
		public BreakCommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_breakCommand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommanderListener ) ((CommanderListener)listener).enterBreakCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommanderListener ) ((CommanderListener)listener).exitBreakCommand(this);
		}
	}

	public final BreakCommandContext breakCommand() throws RecognitionException {
		BreakCommandContext _localctx = new BreakCommandContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_breakCommand);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			match(BREAK);
			setState(64);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==INTEGER) {
				{
				setState(63);
				lineNumberList();
				}
			}

			setState(66);
			match(NEWLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UnbreakCommandContext extends ParserRuleContext {
		public TerminalNode UNBREAK() { return getToken(CommanderParser.UNBREAK, 0); }
		public LineNumberListContext lineNumberList() {
			return getRuleContext(LineNumberListContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(CommanderParser.NEWLINE, 0); }
		public UnbreakCommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unbreakCommand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommanderListener ) ((CommanderListener)listener).enterUnbreakCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommanderListener ) ((CommanderListener)listener).exitUnbreakCommand(this);
		}
	}

	public final UnbreakCommandContext unbreakCommand() throws RecognitionException {
		UnbreakCommandContext _localctx = new UnbreakCommandContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_unbreakCommand);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			match(UNBREAK);
			setState(69);
			lineNumberList();
			setState(70);
			match(NEWLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ShowCommandContext extends ParserRuleContext {
		public TerminalNode SHOW() { return getToken(CommanderParser.SHOW, 0); }
		public VariableListContext variableList() {
			return getRuleContext(VariableListContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(CommanderParser.NEWLINE, 0); }
		public ShowCommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_showCommand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommanderListener ) ((CommanderListener)listener).enterShowCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommanderListener ) ((CommanderListener)listener).exitShowCommand(this);
		}
	}

	public final ShowCommandContext showCommand() throws RecognitionException {
		ShowCommandContext _localctx = new ShowCommandContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_showCommand);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			match(SHOW);
			setState(73);
			variableList();
			setState(74);
			match(NEWLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StackCommandContext extends ParserRuleContext {
		public TerminalNode STACK() { return getToken(CommanderParser.STACK, 0); }
		public TerminalNode NEWLINE() { return getToken(CommanderParser.NEWLINE, 0); }
		public StackCommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stackCommand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommanderListener ) ((CommanderListener)listener).enterStackCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommanderListener ) ((CommanderListener)listener).exitStackCommand(this);
		}
	}

	public final StackCommandContext stackCommand() throws RecognitionException {
		StackCommandContext _localctx = new StackCommandContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_stackCommand);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			match(STACK);
			setState(77);
			match(NEWLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WatchCommandContext extends ParserRuleContext {
		public TerminalNode WATCH() { return getToken(CommanderParser.WATCH, 0); }
		public TerminalNode NEWLINE() { return getToken(CommanderParser.NEWLINE, 0); }
		public SimpleVariableListContext simpleVariableList() {
			return getRuleContext(SimpleVariableListContext.class,0);
		}
		public WatchCommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_watchCommand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommanderListener ) ((CommanderListener)listener).enterWatchCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommanderListener ) ((CommanderListener)listener).exitWatchCommand(this);
		}
	}

	public final WatchCommandContext watchCommand() throws RecognitionException {
		WatchCommandContext _localctx = new WatchCommandContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_watchCommand);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			match(WATCH);
			setState(81);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(80);
				simpleVariableList();
				}
			}

			setState(83);
			match(NEWLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UnwatchCommandContext extends ParserRuleContext {
		public TerminalNode UNWATCH() { return getToken(CommanderParser.UNWATCH, 0); }
		public SimpleVariableListContext simpleVariableList() {
			return getRuleContext(SimpleVariableListContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(CommanderParser.NEWLINE, 0); }
		public UnwatchCommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unwatchCommand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommanderListener ) ((CommanderListener)listener).enterUnwatchCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommanderListener ) ((CommanderListener)listener).exitUnwatchCommand(this);
		}
	}

	public final UnwatchCommandContext unwatchCommand() throws RecognitionException {
		UnwatchCommandContext _localctx = new UnwatchCommandContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_unwatchCommand);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			match(UNWATCH);
			setState(86);
			simpleVariableList();
			setState(87);
			match(NEWLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LineNumberListContext extends ParserRuleContext {
		public List<LineNumberContext> lineNumber() {
			return getRuleContexts(LineNumberContext.class);
		}
		public LineNumberContext lineNumber(int i) {
			return getRuleContext(LineNumberContext.class,i);
		}
		public LineNumberListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lineNumberList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommanderListener ) ((CommanderListener)listener).enterLineNumberList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommanderListener ) ((CommanderListener)listener).exitLineNumberList(this);
		}
	}

	public final LineNumberListContext lineNumberList() throws RecognitionException {
		LineNumberListContext _localctx = new LineNumberListContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_lineNumberList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			lineNumber();
			setState(96);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0 || _la==INTEGER) {
				{
				{
				setState(91);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__0) {
					{
					setState(90);
					match(T__0);
					}
				}

				setState(93);
				lineNumber();
				}
				}
				setState(98);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VariableListContext extends ParserRuleContext {
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public VariableListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommanderListener ) ((CommanderListener)listener).enterVariableList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommanderListener ) ((CommanderListener)listener).exitVariableList(this);
		}
	}

	public final VariableListContext variableList() throws RecognitionException {
		VariableListContext _localctx = new VariableListContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_variableList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			variable();
			setState(106);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0 || _la==IDENTIFIER) {
				{
				{
				setState(101);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__0) {
					{
					setState(100);
					match(T__0);
					}
				}

				setState(103);
				variable();
				}
				}
				setState(108);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SimpleVariableListContext extends ParserRuleContext {
		public List<SimpleVariableContext> simpleVariable() {
			return getRuleContexts(SimpleVariableContext.class);
		}
		public SimpleVariableContext simpleVariable(int i) {
			return getRuleContext(SimpleVariableContext.class,i);
		}
		public SimpleVariableListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleVariableList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommanderListener ) ((CommanderListener)listener).enterSimpleVariableList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommanderListener ) ((CommanderListener)listener).exitSimpleVariableList(this);
		}
	}

	public final SimpleVariableListContext simpleVariableList() throws RecognitionException {
		SimpleVariableListContext _localctx = new SimpleVariableListContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_simpleVariableList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			simpleVariable();
			setState(116);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0 || _la==IDENTIFIER) {
				{
				{
				setState(111);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__0) {
					{
					setState(110);
					match(T__0);
					}
				}

				setState(113);
				simpleVariable();
				}
				}
				setState(118);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LineNumberContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(CommanderParser.INTEGER, 0); }
		public LineNumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lineNumber; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommanderListener ) ((CommanderListener)listener).enterLineNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommanderListener ) ((CommanderListener)listener).exitLineNumber(this);
		}
	}

	public final LineNumberContext lineNumber() throws RecognitionException {
		LineNumberContext _localctx = new LineNumberContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_lineNumber);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			match(INTEGER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SimpleVariableContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(CommanderParser.IDENTIFIER, 0); }
		public SimpleVariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleVariable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommanderListener ) ((CommanderListener)listener).enterSimpleVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommanderListener ) ((CommanderListener)listener).exitSimpleVariable(this);
		}
	}

	public final SimpleVariableContext simpleVariable() throws RecognitionException {
		SimpleVariableContext _localctx = new SimpleVariableContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_simpleVariable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VariableContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(CommanderParser.IDENTIFIER, 0); }
		public List<ModifierContext> modifier() {
			return getRuleContexts(ModifierContext.class);
		}
		public ModifierContext modifier(int i) {
			return getRuleContext(ModifierContext.class,i);
		}
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommanderListener ) ((CommanderListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommanderListener ) ((CommanderListener)listener).exitVariable(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_variable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123);
			match(IDENTIFIER);
			setState(127);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1 || _la==T__3) {
				{
				{
				setState(124);
				modifier();
				}
				}
				setState(129);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ModifierContext extends ParserRuleContext {
		public IndexListContext indexList() {
			return getRuleContext(IndexListContext.class,0);
		}
		public FieldContext field() {
			return getRuleContext(FieldContext.class,0);
		}
		public ModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_modifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommanderListener ) ((CommanderListener)listener).enterModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommanderListener ) ((CommanderListener)listener).exitModifier(this);
		}
	}

	public final ModifierContext modifier() throws RecognitionException {
		ModifierContext _localctx = new ModifierContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_modifier);
		try {
			setState(136);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
				enterOuterAlt(_localctx, 1);
				{
				setState(130);
				match(T__1);
				setState(131);
				indexList();
				setState(132);
				match(T__2);
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(134);
				match(T__3);
				setState(135);
				field();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IndexListContext extends ParserRuleContext {
		public List<IndexContext> index() {
			return getRuleContexts(IndexContext.class);
		}
		public IndexContext index(int i) {
			return getRuleContext(IndexContext.class,i);
		}
		public IndexListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommanderListener ) ((CommanderListener)listener).enterIndexList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommanderListener ) ((CommanderListener)listener).exitIndexList(this);
		}
	}

	public final IndexListContext indexList() throws RecognitionException {
		IndexListContext _localctx = new IndexListContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_indexList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			index();
			setState(143);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(139);
				match(T__0);
				setState(140);
				index();
				}
				}
				setState(145);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IndexContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(CommanderParser.INTEGER, 0); }
		public IndexContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_index; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommanderListener ) ((CommanderListener)listener).enterIndex(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommanderListener ) ((CommanderListener)listener).exitIndex(this);
		}
	}

	public final IndexContext index() throws RecognitionException {
		IndexContext _localctx = new IndexContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_index);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			match(INTEGER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FieldContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(CommanderParser.IDENTIFIER, 0); }
		public FieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommanderListener ) ((CommanderListener)listener).enterField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommanderListener ) ((CommanderListener)listener).exitField(this);
		}
	}

	public final FieldContext field() throws RecognitionException {
		FieldContext _localctx = new FieldContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_field);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u0011\u0097\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007"+
		"\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007"+
		"\u0012\u0002\u0013\u0007\u0013\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0003"+
		"\u00002\b\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0003\u0002:\b\u0002\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0004\u0001\u0004\u0003\u0004A\b\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\b\u0001\b\u0003\bR\b\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\n\u0001\n\u0003\n\\\b\n\u0001\n\u0005\n_\b\n\n\n\f\nb\t\n\u0001"+
		"\u000b\u0001\u000b\u0003\u000bf\b\u000b\u0001\u000b\u0005\u000bi\b\u000b"+
		"\n\u000b\f\u000bl\t\u000b\u0001\f\u0001\f\u0003\fp\b\f\u0001\f\u0005\f"+
		"s\b\f\n\f\f\fv\t\f\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000f"+
		"\u0001\u000f\u0005\u000f~\b\u000f\n\u000f\f\u000f\u0081\t\u000f\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0003"+
		"\u0010\u0089\b\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0005\u0011\u008e"+
		"\b\u0011\n\u0011\f\u0011\u0091\t\u0011\u0001\u0012\u0001\u0012\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0000\u0000\u0014\u0000\u0002\u0004\u0006\b\n"+
		"\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&\u0000\u0000"+
		"\u0096\u00001\u0001\u0000\u0000\u0000\u00023\u0001\u0000\u0000\u0000\u0004"+
		"9\u0001\u0000\u0000\u0000\u0006;\u0001\u0000\u0000\u0000\b>\u0001\u0000"+
		"\u0000\u0000\nD\u0001\u0000\u0000\u0000\fH\u0001\u0000\u0000\u0000\u000e"+
		"L\u0001\u0000\u0000\u0000\u0010O\u0001\u0000\u0000\u0000\u0012U\u0001"+
		"\u0000\u0000\u0000\u0014Y\u0001\u0000\u0000\u0000\u0016c\u0001\u0000\u0000"+
		"\u0000\u0018m\u0001\u0000\u0000\u0000\u001aw\u0001\u0000\u0000\u0000\u001c"+
		"y\u0001\u0000\u0000\u0000\u001e{\u0001\u0000\u0000\u0000 \u0088\u0001"+
		"\u0000\u0000\u0000\"\u008a\u0001\u0000\u0000\u0000$\u0092\u0001\u0000"+
		"\u0000\u0000&\u0094\u0001\u0000\u0000\u0000(2\u0003\u0002\u0001\u0000"+
		")2\u0003\u0004\u0002\u0000*2\u0003\u0006\u0003\u0000+2\u0003\b\u0004\u0000"+
		",2\u0003\n\u0005\u0000-2\u0003\f\u0006\u0000.2\u0003\u000e\u0007\u0000"+
		"/2\u0003\u0010\b\u000002\u0003\u0012\t\u00001(\u0001\u0000\u0000\u0000"+
		"1)\u0001\u0000\u0000\u00001*\u0001\u0000\u0000\u00001+\u0001\u0000\u0000"+
		"\u00001,\u0001\u0000\u0000\u00001-\u0001\u0000\u0000\u00001.\u0001\u0000"+
		"\u0000\u00001/\u0001\u0000\u0000\u000010\u0001\u0000\u0000\u00002\u0001"+
		"\u0001\u0000\u0000\u000034\u0005\f\u0000\u000045\u0005\u0010\u0000\u0000"+
		"5\u0003\u0001\u0000\u0000\u000067\u0005\u000b\u0000\u00007:\u0005\u0010"+
		"\u0000\u00008:\u0005\u0010\u0000\u000096\u0001\u0000\u0000\u000098\u0001"+
		"\u0000\u0000\u0000:\u0005\u0001\u0000\u0000\u0000;<\u0005\r\u0000\u0000"+
		"<=\u0005\u0010\u0000\u0000=\u0007\u0001\u0000\u0000\u0000>@\u0005\u0005"+
		"\u0000\u0000?A\u0003\u0014\n\u0000@?\u0001\u0000\u0000\u0000@A\u0001\u0000"+
		"\u0000\u0000AB\u0001\u0000\u0000\u0000BC\u0005\u0010\u0000\u0000C\t\u0001"+
		"\u0000\u0000\u0000DE\u0005\u0006\u0000\u0000EF\u0003\u0014\n\u0000FG\u0005"+
		"\u0010\u0000\u0000G\u000b\u0001\u0000\u0000\u0000HI\u0005\t\u0000\u0000"+
		"IJ\u0003\u0016\u000b\u0000JK\u0005\u0010\u0000\u0000K\r\u0001\u0000\u0000"+
		"\u0000LM\u0005\n\u0000\u0000MN\u0005\u0010\u0000\u0000N\u000f\u0001\u0000"+
		"\u0000\u0000OQ\u0005\u0007\u0000\u0000PR\u0003\u0018\f\u0000QP\u0001\u0000"+
		"\u0000\u0000QR\u0001\u0000\u0000\u0000RS\u0001\u0000\u0000\u0000ST\u0005"+
		"\u0010\u0000\u0000T\u0011\u0001\u0000\u0000\u0000UV\u0005\b\u0000\u0000"+
		"VW\u0003\u0018\f\u0000WX\u0005\u0010\u0000\u0000X\u0013\u0001\u0000\u0000"+
		"\u0000Y`\u0003\u001a\r\u0000Z\\\u0005\u0001\u0000\u0000[Z\u0001\u0000"+
		"\u0000\u0000[\\\u0001\u0000\u0000\u0000\\]\u0001\u0000\u0000\u0000]_\u0003"+
		"\u001a\r\u0000^[\u0001\u0000\u0000\u0000_b\u0001\u0000\u0000\u0000`^\u0001"+
		"\u0000\u0000\u0000`a\u0001\u0000\u0000\u0000a\u0015\u0001\u0000\u0000"+
		"\u0000b`\u0001\u0000\u0000\u0000cj\u0003\u001e\u000f\u0000df\u0005\u0001"+
		"\u0000\u0000ed\u0001\u0000\u0000\u0000ef\u0001\u0000\u0000\u0000fg\u0001"+
		"\u0000\u0000\u0000gi\u0003\u001e\u000f\u0000he\u0001\u0000\u0000\u0000"+
		"il\u0001\u0000\u0000\u0000jh\u0001\u0000\u0000\u0000jk\u0001\u0000\u0000"+
		"\u0000k\u0017\u0001\u0000\u0000\u0000lj\u0001\u0000\u0000\u0000mt\u0003"+
		"\u001c\u000e\u0000np\u0005\u0001\u0000\u0000on\u0001\u0000\u0000\u0000"+
		"op\u0001\u0000\u0000\u0000pq\u0001\u0000\u0000\u0000qs\u0003\u001c\u000e"+
		"\u0000ro\u0001\u0000\u0000\u0000sv\u0001\u0000\u0000\u0000tr\u0001\u0000"+
		"\u0000\u0000tu\u0001\u0000\u0000\u0000u\u0019\u0001\u0000\u0000\u0000"+
		"vt\u0001\u0000\u0000\u0000wx\u0005\u000f\u0000\u0000x\u001b\u0001\u0000"+
		"\u0000\u0000yz\u0005\u000e\u0000\u0000z\u001d\u0001\u0000\u0000\u0000"+
		"{\u007f\u0005\u000e\u0000\u0000|~\u0003 \u0010\u0000}|\u0001\u0000\u0000"+
		"\u0000~\u0081\u0001\u0000\u0000\u0000\u007f}\u0001\u0000\u0000\u0000\u007f"+
		"\u0080\u0001\u0000\u0000\u0000\u0080\u001f\u0001\u0000\u0000\u0000\u0081"+
		"\u007f\u0001\u0000\u0000\u0000\u0082\u0083\u0005\u0002\u0000\u0000\u0083"+
		"\u0084\u0003\"\u0011\u0000\u0084\u0085\u0005\u0003\u0000\u0000\u0085\u0089"+
		"\u0001\u0000\u0000\u0000\u0086\u0087\u0005\u0004\u0000\u0000\u0087\u0089"+
		"\u0003&\u0013\u0000\u0088\u0082\u0001\u0000\u0000\u0000\u0088\u0086\u0001"+
		"\u0000\u0000\u0000\u0089!\u0001\u0000\u0000\u0000\u008a\u008f\u0003$\u0012"+
		"\u0000\u008b\u008c\u0005\u0001\u0000\u0000\u008c\u008e\u0003$\u0012\u0000"+
		"\u008d\u008b\u0001\u0000\u0000\u0000\u008e\u0091\u0001\u0000\u0000\u0000"+
		"\u008f\u008d\u0001\u0000\u0000\u0000\u008f\u0090\u0001\u0000\u0000\u0000"+
		"\u0090#\u0001\u0000\u0000\u0000\u0091\u008f\u0001\u0000\u0000\u0000\u0092"+
		"\u0093\u0005\u000f\u0000\u0000\u0093%\u0001\u0000\u0000\u0000\u0094\u0095"+
		"\u0005\u000e\u0000\u0000\u0095\'\u0001\u0000\u0000\u0000\r19@Q[`ejot\u007f"+
		"\u0088\u008f";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}