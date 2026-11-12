// Generated from Pcl_C4.g4 by ANTLR 4.13.2

    package intermediate.antlr4;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class Pcl_C4Parser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		CLASS=1, PROGRAM_END=2, MAIN=3, IS_GIVING=4, CONST_DECL=5, AURA_FARM=6, 
		CHOPPED_CHEESE=7, AURA_GAIN=8, AURA_LOSS=9, MOGGED_BY=10, LOWK_MOGGED_BY=11, 
		LOWK_MOGGING=12, JOHN_PORK=13, YO_GURT=14, TIM_CHEESE=15, TONKA_JAHARI=16, 
		FADE=17, RIZZ=18, SIGMA=19, DABLOONS=20, CHAT=21, BET=22, SIX=23, SEVEN=24, 
		WHILE=25, FOR=26, OHIO=27, SKIBIDI=28, YEET=29, MOGGING=30, NO_CAP=31, 
		CAP=32, GT=33, LT=34, EQ=35, LE=36, GE=37, NE=38, PLUS=39, MINUS=40, STAR=41, 
		SLASH=42, ASSIGN=43, LPAREN=44, RPAREN=45, COLON=46, COMMA=47, SEMI=48, 
		DOT=49, LINE_COMMENT=50, NEWLINE=51, WHITESPACE=52, INTEGER=53, STRING=54, 
		IDENTIFIER=55;
	public static final int
		RULE_program = 0, RULE_importDeclaration = 1, RULE_qualifiedName = 2, 
		RULE_mainMethod = 3, RULE_functionDeclaration = 4, RULE_returnType = 5, 
		RULE_valueType = 6, RULE_parameterList = 7, RULE_parameter = 8, RULE_block = 9, 
		RULE_separator = 10, RULE_statement = 11, RULE_variableDeclaration = 12, 
		RULE_constantDeclaration = 13, RULE_assignmentStatement = 14, RULE_assignmentOperator = 15, 
		RULE_ifStatement = 16, RULE_whileStatement = 17, RULE_forStatement = 18, 
		RULE_forInitializer = 19, RULE_breakStatement = 20, RULE_continueStatement = 21, 
		RULE_exitStatement = 22, RULE_returnStatement = 23, RULE_printStatement = 24, 
		RULE_callStatement = 25, RULE_argumentList = 26, RULE_functionCall = 27, 
		RULE_expression = 28, RULE_relationalOperator = 29, RULE_additiveExpression = 30, 
		RULE_multiplicativeExpression = 31, RULE_unaryExpression = 32, RULE_primaryExpression = 33, 
		RULE_identifier = 34;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "importDeclaration", "qualifiedName", "mainMethod", "functionDeclaration", 
			"returnType", "valueType", "parameterList", "parameter", "block", "separator", 
			"statement", "variableDeclaration", "constantDeclaration", "assignmentStatement", 
			"assignmentOperator", "ifStatement", "whileStatement", "forStatement", 
			"forInitializer", "breakStatement", "continueStatement", "exitStatement", 
			"returnStatement", "printStatement", "callStatement", "argumentList", 
			"functionCall", "expression", "relationalOperator", "additiveExpression", 
			"multiplicativeExpression", "unaryExpression", "primaryExpression", "identifier"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, "'fade'", "'rizz'", "'sigma'", "'dabloons'", 
			"'chat'", "'bet'", "'six'", "'seven'", null, null, "'ohio'", "'skibidi'", 
			"'yeet'", "'mogging'", null, "'cap'", "'>'", "'<'", "'=='", "'<='", "'>='", 
			"'!='", "'+'", "'-'", "'*'", "'/'", "'='", "'('", "')'", "':'", "','", 
			"';'", "'.'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "CLASS", "PROGRAM_END", "MAIN", "IS_GIVING", "CONST_DECL", "AURA_FARM", 
			"CHOPPED_CHEESE", "AURA_GAIN", "AURA_LOSS", "MOGGED_BY", "LOWK_MOGGED_BY", 
			"LOWK_MOGGING", "JOHN_PORK", "YO_GURT", "TIM_CHEESE", "TONKA_JAHARI", 
			"FADE", "RIZZ", "SIGMA", "DABLOONS", "CHAT", "BET", "SIX", "SEVEN", "WHILE", 
			"FOR", "OHIO", "SKIBIDI", "YEET", "MOGGING", "NO_CAP", "CAP", "GT", "LT", 
			"EQ", "LE", "GE", "NE", "PLUS", "MINUS", "STAR", "SLASH", "ASSIGN", "LPAREN", 
			"RPAREN", "COLON", "COMMA", "SEMI", "DOT", "LINE_COMMENT", "NEWLINE", 
			"WHITESPACE", "INTEGER", "STRING", "IDENTIFIER"
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
	public String getGrammarFileName() { return "Pcl_C4.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public Pcl_C4Parser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode CLASS() { return getToken(Pcl_C4Parser.CLASS, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode COLON() { return getToken(Pcl_C4Parser.COLON, 0); }
		public MainMethodContext mainMethod() {
			return getRuleContext(MainMethodContext.class,0);
		}
		public TerminalNode PROGRAM_END() { return getToken(Pcl_C4Parser.PROGRAM_END, 0); }
		public TerminalNode EOF() { return getToken(Pcl_C4Parser.EOF, 0); }
		public List<SeparatorContext> separator() {
			return getRuleContexts(SeparatorContext.class);
		}
		public SeparatorContext separator(int i) {
			return getRuleContext(SeparatorContext.class,i);
		}
		public List<ImportDeclarationContext> importDeclaration() {
			return getRuleContexts(ImportDeclarationContext.class);
		}
		public ImportDeclarationContext importDeclaration(int i) {
			return getRuleContext(ImportDeclarationContext.class,i);
		}
		public List<FunctionDeclarationContext> functionDeclaration() {
			return getRuleContexts(FunctionDeclarationContext.class);
		}
		public FunctionDeclarationContext functionDeclaration(int i) {
			return getRuleContext(FunctionDeclarationContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Pcl_C4Visitor ) return ((Pcl_C4Visitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			match(CLASS);
			setState(71);
			identifier();
			setState(72);
			match(COLON);
			setState(76);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEMI || _la==NEWLINE) {
				{
				{
				setState(73);
				separator();
				}
				}
				setState(78);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(88);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==TIM_CHEESE) {
				{
				{
				setState(79);
				importDeclaration();
				setState(83);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEMI || _la==NEWLINE) {
					{
					{
					setState(80);
					separator();
					}
					}
					setState(85);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				setState(90);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(100);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==RIZZ) {
				{
				{
				setState(91);
				functionDeclaration();
				setState(95);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEMI || _la==NEWLINE) {
					{
					{
					setState(92);
					separator();
					}
					}
					setState(97);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				setState(102);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(103);
			mainMethod();
			setState(113);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(107);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==SEMI || _la==NEWLINE) {
						{
						{
						setState(104);
						separator();
						}
						}
						setState(109);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(110);
					functionDeclaration();
					}
					} 
				}
				setState(115);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			setState(119);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEMI || _la==NEWLINE) {
				{
				{
				setState(116);
				separator();
				}
				}
				setState(121);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(122);
			match(PROGRAM_END);
			setState(126);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEMI || _la==NEWLINE) {
				{
				{
				setState(123);
				separator();
				}
				}
				setState(128);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(129);
			match(EOF);
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
	public static class ImportDeclarationContext extends ParserRuleContext {
		public TerminalNode TIM_CHEESE() { return getToken(Pcl_C4Parser.TIM_CHEESE, 0); }
		public QualifiedNameContext qualifiedName() {
			return getRuleContext(QualifiedNameContext.class,0);
		}
		public TerminalNode DOT() { return getToken(Pcl_C4Parser.DOT, 0); }
		public TerminalNode STAR() { return getToken(Pcl_C4Parser.STAR, 0); }
		public TerminalNode SEMI() { return getToken(Pcl_C4Parser.SEMI, 0); }
		public ImportDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importDeclaration; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Pcl_C4Visitor ) return ((Pcl_C4Visitor<? extends T>)visitor).visitImportDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportDeclarationContext importDeclaration() throws RecognitionException {
		ImportDeclarationContext _localctx = new ImportDeclarationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_importDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			match(TIM_CHEESE);
			setState(132);
			qualifiedName();
			setState(135);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOT) {
				{
				setState(133);
				match(DOT);
				setState(134);
				match(STAR);
				}
			}

			setState(138);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				setState(137);
				match(SEMI);
				}
				break;
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
	public static class QualifiedNameContext extends ParserRuleContext {
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public List<TerminalNode> DOT() { return getTokens(Pcl_C4Parser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(Pcl_C4Parser.DOT, i);
		}
		public QualifiedNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qualifiedName; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Pcl_C4Visitor ) return ((Pcl_C4Visitor<? extends T>)visitor).visitQualifiedName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QualifiedNameContext qualifiedName() throws RecognitionException {
		QualifiedNameContext _localctx = new QualifiedNameContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_qualifiedName);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(140);
			identifier();
			setState(145);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(141);
					match(DOT);
					setState(142);
					identifier();
					}
					} 
				}
				setState(147);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
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
	public static class MainMethodContext extends ParserRuleContext {
		public TerminalNode MAIN() { return getToken(Pcl_C4Parser.MAIN, 0); }
		public TerminalNode COLON() { return getToken(Pcl_C4Parser.COLON, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public MainMethodContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mainMethod; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Pcl_C4Visitor ) return ((Pcl_C4Visitor<? extends T>)visitor).visitMainMethod(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MainMethodContext mainMethod() throws RecognitionException {
		MainMethodContext _localctx = new MainMethodContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_mainMethod);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
			match(MAIN);
			setState(149);
			match(COLON);
			setState(150);
			block();
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
	public static class FunctionDeclarationContext extends ParserRuleContext {
		public TerminalNode RIZZ() { return getToken(Pcl_C4Parser.RIZZ, 0); }
		public ReturnTypeContext returnType() {
			return getRuleContext(ReturnTypeContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(Pcl_C4Parser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(Pcl_C4Parser.RPAREN, 0); }
		public TerminalNode COLON() { return getToken(Pcl_C4Parser.COLON, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public FunctionDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDeclaration; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Pcl_C4Visitor ) return ((Pcl_C4Visitor<? extends T>)visitor).visitFunctionDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionDeclarationContext functionDeclaration() throws RecognitionException {
		FunctionDeclarationContext _localctx = new FunctionDeclarationContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_functionDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			match(RIZZ);
			setState(153);
			returnType();
			setState(154);
			identifier();
			setState(155);
			match(LPAREN);
			setState(157);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DABLOONS || _la==CHAT) {
				{
				setState(156);
				parameterList();
				}
			}

			setState(159);
			match(RPAREN);
			setState(160);
			match(COLON);
			setState(161);
			block();
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
	public static class ReturnTypeContext extends ParserRuleContext {
		public ValueTypeContext valueType() {
			return getRuleContext(ValueTypeContext.class,0);
		}
		public TerminalNode SIGMA() { return getToken(Pcl_C4Parser.SIGMA, 0); }
		public ReturnTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Pcl_C4Visitor ) return ((Pcl_C4Visitor<? extends T>)visitor).visitReturnType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnTypeContext returnType() throws RecognitionException {
		ReturnTypeContext _localctx = new ReturnTypeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_returnType);
		try {
			setState(165);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DABLOONS:
			case CHAT:
				enterOuterAlt(_localctx, 1);
				{
				setState(163);
				valueType();
				}
				break;
			case SIGMA:
				enterOuterAlt(_localctx, 2);
				{
				setState(164);
				match(SIGMA);
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
	public static class ValueTypeContext extends ParserRuleContext {
		public TerminalNode DABLOONS() { return getToken(Pcl_C4Parser.DABLOONS, 0); }
		public TerminalNode CHAT() { return getToken(Pcl_C4Parser.CHAT, 0); }
		public ValueTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_valueType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Pcl_C4Visitor ) return ((Pcl_C4Visitor<? extends T>)visitor).visitValueType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueTypeContext valueType() throws RecognitionException {
		ValueTypeContext _localctx = new ValueTypeContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_valueType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(167);
			_la = _input.LA(1);
			if ( !(_la==DABLOONS || _la==CHAT) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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
	public static class ParameterListContext extends ParserRuleContext {
		public List<ParameterContext> parameter() {
			return getRuleContexts(ParameterContext.class);
		}
		public ParameterContext parameter(int i) {
			return getRuleContext(ParameterContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(Pcl_C4Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(Pcl_C4Parser.COMMA, i);
		}
		public ParameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Pcl_C4Visitor ) return ((Pcl_C4Visitor<? extends T>)visitor).visitParameterList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterListContext parameterList() throws RecognitionException {
		ParameterListContext _localctx = new ParameterListContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_parameterList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(169);
			parameter();
			setState(174);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(170);
				match(COMMA);
				setState(171);
				parameter();
				}
				}
				setState(176);
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
	public static class ParameterContext extends ParserRuleContext {
		public ValueTypeContext valueType() {
			return getRuleContext(ValueTypeContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public ParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Pcl_C4Visitor ) return ((Pcl_C4Visitor<? extends T>)visitor).visitParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterContext parameter() throws RecognitionException {
		ParameterContext _localctx = new ParameterContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_parameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(177);
			valueType();
			setState(178);
			identifier();
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
	public static class BlockContext extends ParserRuleContext {
		public TerminalNode FADE() { return getToken(Pcl_C4Parser.FADE, 0); }
		public List<SeparatorContext> separator() {
			return getRuleContexts(SeparatorContext.class);
		}
		public SeparatorContext separator(int i) {
			return getRuleContext(SeparatorContext.class,i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Pcl_C4Visitor ) return ((Pcl_C4Visitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_block);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(180);
					separator();
					}
					} 
				}
				setState(185);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			}
			setState(199);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 36028798071824416L) != 0)) {
				{
				setState(186);
				statement();
				setState(196);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(188); 
						_errHandler.sync(this);
						_la = _input.LA(1);
						do {
							{
							{
							setState(187);
							separator();
							}
							}
							setState(190); 
							_errHandler.sync(this);
							_la = _input.LA(1);
						} while ( _la==SEMI || _la==NEWLINE );
						setState(192);
						statement();
						}
						} 
					}
					setState(198);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
				}
				}
			}

			setState(204);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEMI || _la==NEWLINE) {
				{
				{
				setState(201);
				separator();
				}
				}
				setState(206);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(207);
			match(FADE);
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
	public static class SeparatorContext extends ParserRuleContext {
		public TerminalNode NEWLINE() { return getToken(Pcl_C4Parser.NEWLINE, 0); }
		public TerminalNode SEMI() { return getToken(Pcl_C4Parser.SEMI, 0); }
		public SeparatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_separator; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Pcl_C4Visitor ) return ((Pcl_C4Visitor<? extends T>)visitor).visitSeparator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SeparatorContext separator() throws RecognitionException {
		SeparatorContext _localctx = new SeparatorContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_separator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(209);
			_la = _input.LA(1);
			if ( !(_la==SEMI || _la==NEWLINE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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
	public static class StatementContext extends ParserRuleContext {
		public VariableDeclarationContext variableDeclaration() {
			return getRuleContext(VariableDeclarationContext.class,0);
		}
		public ConstantDeclarationContext constantDeclaration() {
			return getRuleContext(ConstantDeclarationContext.class,0);
		}
		public AssignmentStatementContext assignmentStatement() {
			return getRuleContext(AssignmentStatementContext.class,0);
		}
		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class,0);
		}
		public WhileStatementContext whileStatement() {
			return getRuleContext(WhileStatementContext.class,0);
		}
		public ForStatementContext forStatement() {
			return getRuleContext(ForStatementContext.class,0);
		}
		public BreakStatementContext breakStatement() {
			return getRuleContext(BreakStatementContext.class,0);
		}
		public ContinueStatementContext continueStatement() {
			return getRuleContext(ContinueStatementContext.class,0);
		}
		public ExitStatementContext exitStatement() {
			return getRuleContext(ExitStatementContext.class,0);
		}
		public ReturnStatementContext returnStatement() {
			return getRuleContext(ReturnStatementContext.class,0);
		}
		public PrintStatementContext printStatement() {
			return getRuleContext(PrintStatementContext.class,0);
		}
		public CallStatementContext callStatement() {
			return getRuleContext(CallStatementContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Pcl_C4Visitor ) return ((Pcl_C4Visitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_statement);
		try {
			setState(223);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BET:
				enterOuterAlt(_localctx, 1);
				{
				setState(211);
				variableDeclaration();
				}
				break;
			case CONST_DECL:
				enterOuterAlt(_localctx, 2);
				{
				setState(212);
				constantDeclaration();
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 3);
				{
				setState(213);
				assignmentStatement();
				}
				break;
			case SIX:
				enterOuterAlt(_localctx, 4);
				{
				setState(214);
				ifStatement();
				}
				break;
			case WHILE:
				enterOuterAlt(_localctx, 5);
				{
				setState(215);
				whileStatement();
				}
				break;
			case FOR:
				enterOuterAlt(_localctx, 6);
				{
				setState(216);
				forStatement();
				}
				break;
			case OHIO:
				enterOuterAlt(_localctx, 7);
				{
				setState(217);
				breakStatement();
				}
				break;
			case TONKA_JAHARI:
				enterOuterAlt(_localctx, 8);
				{
				setState(218);
				continueStatement();
				}
				break;
			case SKIBIDI:
				enterOuterAlt(_localctx, 9);
				{
				setState(219);
				exitStatement();
				}
				break;
			case YEET:
				enterOuterAlt(_localctx, 10);
				{
				setState(220);
				returnStatement();
				}
				break;
			case YO_GURT:
				enterOuterAlt(_localctx, 11);
				{
				setState(221);
				printStatement();
				}
				break;
			case JOHN_PORK:
				enterOuterAlt(_localctx, 12);
				{
				setState(222);
				callStatement();
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
	public static class VariableDeclarationContext extends ParserRuleContext {
		public TerminalNode BET() { return getToken(Pcl_C4Parser.BET, 0); }
		public ValueTypeContext valueType() {
			return getRuleContext(ValueTypeContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public AssignmentOperatorContext assignmentOperator() {
			return getRuleContext(AssignmentOperatorContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public VariableDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclaration; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Pcl_C4Visitor ) return ((Pcl_C4Visitor<? extends T>)visitor).visitVariableDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableDeclarationContext variableDeclaration() throws RecognitionException {
		VariableDeclarationContext _localctx = new VariableDeclarationContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_variableDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(225);
			match(BET);
			setState(226);
			valueType();
			setState(227);
			identifier();
			setState(231);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IS_GIVING || _la==ASSIGN) {
				{
				setState(228);
				assignmentOperator();
				setState(229);
				expression();
				}
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
	public static class ConstantDeclarationContext extends ParserRuleContext {
		public TerminalNode CONST_DECL() { return getToken(Pcl_C4Parser.CONST_DECL, 0); }
		public ValueTypeContext valueType() {
			return getRuleContext(ValueTypeContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public AssignmentOperatorContext assignmentOperator() {
			return getRuleContext(AssignmentOperatorContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ConstantDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constantDeclaration; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Pcl_C4Visitor ) return ((Pcl_C4Visitor<? extends T>)visitor).visitConstantDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantDeclarationContext constantDeclaration() throws RecognitionException {
		ConstantDeclarationContext _localctx = new ConstantDeclarationContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_constantDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(233);
			match(CONST_DECL);
			setState(234);
			valueType();
			setState(235);
			identifier();
			setState(236);
			assignmentOperator();
			setState(237);
			expression();
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
	public static class AssignmentStatementContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public AssignmentOperatorContext assignmentOperator() {
			return getRuleContext(AssignmentOperatorContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AssignmentStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignmentStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Pcl_C4Visitor ) return ((Pcl_C4Visitor<? extends T>)visitor).visitAssignmentStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentStatementContext assignmentStatement() throws RecognitionException {
		AssignmentStatementContext _localctx = new AssignmentStatementContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_assignmentStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(239);
			identifier();
			setState(240);
			assignmentOperator();
			setState(241);
			expression();
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
	public static class AssignmentOperatorContext extends ParserRuleContext {
		public TerminalNode IS_GIVING() { return getToken(Pcl_C4Parser.IS_GIVING, 0); }
		public TerminalNode ASSIGN() { return getToken(Pcl_C4Parser.ASSIGN, 0); }
		public AssignmentOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignmentOperator; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Pcl_C4Visitor ) return ((Pcl_C4Visitor<? extends T>)visitor).visitAssignmentOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentOperatorContext assignmentOperator() throws RecognitionException {
		AssignmentOperatorContext _localctx = new AssignmentOperatorContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_assignmentOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(243);
			_la = _input.LA(1);
			if ( !(_la==IS_GIVING || _la==ASSIGN) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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
	public static class IfStatementContext extends ParserRuleContext {
		public List<TerminalNode> SIX() { return getTokens(Pcl_C4Parser.SIX); }
		public TerminalNode SIX(int i) {
			return getToken(Pcl_C4Parser.SIX, i);
		}
		public List<TerminalNode> LPAREN() { return getTokens(Pcl_C4Parser.LPAREN); }
		public TerminalNode LPAREN(int i) {
			return getToken(Pcl_C4Parser.LPAREN, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> RPAREN() { return getTokens(Pcl_C4Parser.RPAREN); }
		public TerminalNode RPAREN(int i) {
			return getToken(Pcl_C4Parser.RPAREN, i);
		}
		public List<TerminalNode> COLON() { return getTokens(Pcl_C4Parser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(Pcl_C4Parser.COLON, i);
		}
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public List<TerminalNode> SEVEN() { return getTokens(Pcl_C4Parser.SEVEN); }
		public TerminalNode SEVEN(int i) {
			return getToken(Pcl_C4Parser.SEVEN, i);
		}
		public List<SeparatorContext> separator() {
			return getRuleContexts(SeparatorContext.class);
		}
		public SeparatorContext separator(int i) {
			return getRuleContext(SeparatorContext.class,i);
		}
		public IfStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Pcl_C4Visitor ) return ((Pcl_C4Visitor<? extends T>)visitor).visitIfStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfStatementContext ifStatement() throws RecognitionException {
		IfStatementContext _localctx = new IfStatementContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_ifStatement);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(245);
			match(SIX);
			setState(246);
			match(LPAREN);
			setState(247);
			expression();
			setState(248);
			match(RPAREN);
			setState(249);
			match(COLON);
			setState(250);
			block();
			setState(267);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(254);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==SEMI || _la==NEWLINE) {
						{
						{
						setState(251);
						separator();
						}
						}
						setState(256);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(257);
					match(SEVEN);
					setState(258);
					match(SIX);
					setState(259);
					match(LPAREN);
					setState(260);
					expression();
					setState(261);
					match(RPAREN);
					setState(262);
					match(COLON);
					setState(263);
					block();
					}
					} 
				}
				setState(269);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			}
			setState(279);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				{
				setState(273);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEMI || _la==NEWLINE) {
					{
					{
					setState(270);
					separator();
					}
					}
					setState(275);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(276);
				match(SEVEN);
				setState(277);
				match(COLON);
				setState(278);
				block();
				}
				break;
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
	public static class WhileStatementContext extends ParserRuleContext {
		public TerminalNode WHILE() { return getToken(Pcl_C4Parser.WHILE, 0); }
		public TerminalNode LPAREN() { return getToken(Pcl_C4Parser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(Pcl_C4Parser.RPAREN, 0); }
		public TerminalNode COLON() { return getToken(Pcl_C4Parser.COLON, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public WhileStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Pcl_C4Visitor ) return ((Pcl_C4Visitor<? extends T>)visitor).visitWhileStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhileStatementContext whileStatement() throws RecognitionException {
		WhileStatementContext _localctx = new WhileStatementContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_whileStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(281);
			match(WHILE);
			setState(282);
			match(LPAREN);
			setState(283);
			expression();
			setState(284);
			match(RPAREN);
			setState(285);
			match(COLON);
			setState(286);
			block();
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
	public static class ForStatementContext extends ParserRuleContext {
		public TerminalNode FOR() { return getToken(Pcl_C4Parser.FOR, 0); }
		public TerminalNode LPAREN() { return getToken(Pcl_C4Parser.LPAREN, 0); }
		public List<TerminalNode> SEMI() { return getTokens(Pcl_C4Parser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(Pcl_C4Parser.SEMI, i);
		}
		public TerminalNode RPAREN() { return getToken(Pcl_C4Parser.RPAREN, 0); }
		public TerminalNode COLON() { return getToken(Pcl_C4Parser.COLON, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ForInitializerContext forInitializer() {
			return getRuleContext(ForInitializerContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AssignmentStatementContext assignmentStatement() {
			return getRuleContext(AssignmentStatementContext.class,0);
		}
		public ForStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Pcl_C4Visitor ) return ((Pcl_C4Visitor<? extends T>)visitor).visitForStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForStatementContext forStatement() throws RecognitionException {
		ForStatementContext _localctx = new ForStatementContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_forStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(288);
			match(FOR);
			setState(289);
			match(LPAREN);
			setState(291);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BET || _la==IDENTIFIER) {
				{
				setState(290);
				forInitializer();
				}
			}

			setState(293);
			match(SEMI);
			setState(295);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 63069636236681216L) != 0)) {
				{
				setState(294);
				expression();
				}
			}

			setState(297);
			match(SEMI);
			setState(299);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(298);
				assignmentStatement();
				}
			}

			setState(301);
			match(RPAREN);
			setState(302);
			match(COLON);
			setState(303);
			block();
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
	public static class ForInitializerContext extends ParserRuleContext {
		public VariableDeclarationContext variableDeclaration() {
			return getRuleContext(VariableDeclarationContext.class,0);
		}
		public AssignmentStatementContext assignmentStatement() {
			return getRuleContext(AssignmentStatementContext.class,0);
		}
		public ForInitializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forInitializer; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Pcl_C4Visitor ) return ((Pcl_C4Visitor<? extends T>)visitor).visitForInitializer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForInitializerContext forInitializer() throws RecognitionException {
		ForInitializerContext _localctx = new ForInitializerContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_forInitializer);
		try {
			setState(307);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BET:
				enterOuterAlt(_localctx, 1);
				{
				setState(305);
				variableDeclaration();
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(306);
				assignmentStatement();
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
	public static class BreakStatementContext extends ParserRuleContext {
		public TerminalNode OHIO() { return getToken(Pcl_C4Parser.OHIO, 0); }
		public BreakStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_breakStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Pcl_C4Visitor ) return ((Pcl_C4Visitor<? extends T>)visitor).visitBreakStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BreakStatementContext breakStatement() throws RecognitionException {
		BreakStatementContext _localctx = new BreakStatementContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_breakStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(309);
			match(OHIO);
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
	public static class ContinueStatementContext extends ParserRuleContext {
		public TerminalNode TONKA_JAHARI() { return getToken(Pcl_C4Parser.TONKA_JAHARI, 0); }
		public ContinueStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_continueStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Pcl_C4Visitor ) return ((Pcl_C4Visitor<? extends T>)visitor).visitContinueStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContinueStatementContext continueStatement() throws RecognitionException {
		ContinueStatementContext _localctx = new ContinueStatementContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_continueStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(311);
			match(TONKA_JAHARI);
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
	public static class ExitStatementContext extends ParserRuleContext {
		public TerminalNode SKIBIDI() { return getToken(Pcl_C4Parser.SKIBIDI, 0); }
		public ExitStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exitStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Pcl_C4Visitor ) return ((Pcl_C4Visitor<? extends T>)visitor).visitExitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExitStatementContext exitStatement() throws RecognitionException {
		ExitStatementContext _localctx = new ExitStatementContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_exitStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(313);
			match(SKIBIDI);
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
	public static class ReturnStatementContext extends ParserRuleContext {
		public TerminalNode YEET() { return getToken(Pcl_C4Parser.YEET, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Pcl_C4Visitor ) return ((Pcl_C4Visitor<? extends T>)visitor).visitReturnStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnStatementContext returnStatement() throws RecognitionException {
		ReturnStatementContext _localctx = new ReturnStatementContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_returnStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(315);
			match(YEET);
			setState(317);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 63069636236681216L) != 0)) {
				{
				setState(316);
				expression();
				}
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
	public static class PrintStatementContext extends ParserRuleContext {
		public TerminalNode YO_GURT() { return getToken(Pcl_C4Parser.YO_GURT, 0); }
		public TerminalNode LPAREN() { return getToken(Pcl_C4Parser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(Pcl_C4Parser.RPAREN, 0); }
		public ArgumentListContext argumentList() {
			return getRuleContext(ArgumentListContext.class,0);
		}
		public PrintStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_printStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Pcl_C4Visitor ) return ((Pcl_C4Visitor<? extends T>)visitor).visitPrintStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrintStatementContext printStatement() throws RecognitionException {
		PrintStatementContext _localctx = new PrintStatementContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_printStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(319);
			match(YO_GURT);
			setState(320);
			match(LPAREN);
			setState(322);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 63069636236681216L) != 0)) {
				{
				setState(321);
				argumentList();
				}
			}

			setState(324);
			match(RPAREN);
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
	public static class CallStatementContext extends ParserRuleContext {
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public CallStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_callStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Pcl_C4Visitor ) return ((Pcl_C4Visitor<? extends T>)visitor).visitCallStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CallStatementContext callStatement() throws RecognitionException {
		CallStatementContext _localctx = new CallStatementContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_callStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(326);
			functionCall();
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
	public static class ArgumentListContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(Pcl_C4Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(Pcl_C4Parser.COMMA, i);
		}
		public ArgumentListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argumentList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Pcl_C4Visitor ) return ((Pcl_C4Visitor<? extends T>)visitor).visitArgumentList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentListContext argumentList() throws RecognitionException {
		ArgumentListContext _localctx = new ArgumentListContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_argumentList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(328);
			expression();
			setState(333);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(329);
				match(COMMA);
				setState(330);
				expression();
				}
				}
				setState(335);
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
	public static class FunctionCallContext extends ParserRuleContext {
		public TerminalNode JOHN_PORK() { return getToken(Pcl_C4Parser.JOHN_PORK, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(Pcl_C4Parser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(Pcl_C4Parser.RPAREN, 0); }
		public ArgumentListContext argumentList() {
			return getRuleContext(ArgumentListContext.class,0);
		}
		public FunctionCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionCall; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Pcl_C4Visitor ) return ((Pcl_C4Visitor<? extends T>)visitor).visitFunctionCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionCallContext functionCall() throws RecognitionException {
		FunctionCallContext _localctx = new FunctionCallContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_functionCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(336);
			match(JOHN_PORK);
			setState(337);
			identifier();
			setState(338);
			match(LPAREN);
			setState(340);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 63069636236681216L) != 0)) {
				{
				setState(339);
				argumentList();
				}
			}

			setState(342);
			match(RPAREN);
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
	public static class ExpressionContext extends ParserRuleContext {
		public List<AdditiveExpressionContext> additiveExpression() {
			return getRuleContexts(AdditiveExpressionContext.class);
		}
		public AdditiveExpressionContext additiveExpression(int i) {
			return getRuleContext(AdditiveExpressionContext.class,i);
		}
		public List<RelationalOperatorContext> relationalOperator() {
			return getRuleContexts(RelationalOperatorContext.class);
		}
		public RelationalOperatorContext relationalOperator(int i) {
			return getRuleContext(RelationalOperatorContext.class,i);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Pcl_C4Visitor ) return ((Pcl_C4Visitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(344);
			additiveExpression();
			setState(350);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 548682079232L) != 0)) {
				{
				{
				setState(345);
				relationalOperator();
				setState(346);
				additiveExpression();
				}
				}
				setState(352);
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
	public static class RelationalOperatorContext extends ParserRuleContext {
		public TerminalNode MOGGING() { return getToken(Pcl_C4Parser.MOGGING, 0); }
		public TerminalNode MOGGED_BY() { return getToken(Pcl_C4Parser.MOGGED_BY, 0); }
		public TerminalNode NO_CAP() { return getToken(Pcl_C4Parser.NO_CAP, 0); }
		public TerminalNode LOWK_MOGGED_BY() { return getToken(Pcl_C4Parser.LOWK_MOGGED_BY, 0); }
		public TerminalNode LOWK_MOGGING() { return getToken(Pcl_C4Parser.LOWK_MOGGING, 0); }
		public TerminalNode CAP() { return getToken(Pcl_C4Parser.CAP, 0); }
		public TerminalNode GT() { return getToken(Pcl_C4Parser.GT, 0); }
		public TerminalNode LT() { return getToken(Pcl_C4Parser.LT, 0); }
		public TerminalNode EQ() { return getToken(Pcl_C4Parser.EQ, 0); }
		public TerminalNode LE() { return getToken(Pcl_C4Parser.LE, 0); }
		public TerminalNode GE() { return getToken(Pcl_C4Parser.GE, 0); }
		public TerminalNode NE() { return getToken(Pcl_C4Parser.NE, 0); }
		public RelationalOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationalOperator; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Pcl_C4Visitor ) return ((Pcl_C4Visitor<? extends T>)visitor).visitRelationalOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationalOperatorContext relationalOperator() throws RecognitionException {
		RelationalOperatorContext _localctx = new RelationalOperatorContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_relationalOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(353);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 548682079232L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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
	public static class AdditiveExpressionContext extends ParserRuleContext {
		public List<MultiplicativeExpressionContext> multiplicativeExpression() {
			return getRuleContexts(MultiplicativeExpressionContext.class);
		}
		public MultiplicativeExpressionContext multiplicativeExpression(int i) {
			return getRuleContext(MultiplicativeExpressionContext.class,i);
		}
		public List<TerminalNode> AURA_GAIN() { return getTokens(Pcl_C4Parser.AURA_GAIN); }
		public TerminalNode AURA_GAIN(int i) {
			return getToken(Pcl_C4Parser.AURA_GAIN, i);
		}
		public List<TerminalNode> AURA_LOSS() { return getTokens(Pcl_C4Parser.AURA_LOSS); }
		public TerminalNode AURA_LOSS(int i) {
			return getToken(Pcl_C4Parser.AURA_LOSS, i);
		}
		public List<TerminalNode> PLUS() { return getTokens(Pcl_C4Parser.PLUS); }
		public TerminalNode PLUS(int i) {
			return getToken(Pcl_C4Parser.PLUS, i);
		}
		public List<TerminalNode> MINUS() { return getTokens(Pcl_C4Parser.MINUS); }
		public TerminalNode MINUS(int i) {
			return getToken(Pcl_C4Parser.MINUS, i);
		}
		public AdditiveExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additiveExpression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Pcl_C4Visitor ) return ((Pcl_C4Visitor<? extends T>)visitor).visitAdditiveExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AdditiveExpressionContext additiveExpression() throws RecognitionException {
		AdditiveExpressionContext _localctx = new AdditiveExpressionContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_additiveExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(355);
			multiplicativeExpression();
			setState(360);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1649267442432L) != 0)) {
				{
				{
				setState(356);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1649267442432L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(357);
				multiplicativeExpression();
				}
				}
				setState(362);
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
	public static class MultiplicativeExpressionContext extends ParserRuleContext {
		public List<UnaryExpressionContext> unaryExpression() {
			return getRuleContexts(UnaryExpressionContext.class);
		}
		public UnaryExpressionContext unaryExpression(int i) {
			return getRuleContext(UnaryExpressionContext.class,i);
		}
		public List<TerminalNode> AURA_FARM() { return getTokens(Pcl_C4Parser.AURA_FARM); }
		public TerminalNode AURA_FARM(int i) {
			return getToken(Pcl_C4Parser.AURA_FARM, i);
		}
		public List<TerminalNode> CHOPPED_CHEESE() { return getTokens(Pcl_C4Parser.CHOPPED_CHEESE); }
		public TerminalNode CHOPPED_CHEESE(int i) {
			return getToken(Pcl_C4Parser.CHOPPED_CHEESE, i);
		}
		public List<TerminalNode> STAR() { return getTokens(Pcl_C4Parser.STAR); }
		public TerminalNode STAR(int i) {
			return getToken(Pcl_C4Parser.STAR, i);
		}
		public List<TerminalNode> SLASH() { return getTokens(Pcl_C4Parser.SLASH); }
		public TerminalNode SLASH(int i) {
			return getToken(Pcl_C4Parser.SLASH, i);
		}
		public MultiplicativeExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplicativeExpression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Pcl_C4Visitor ) return ((Pcl_C4Visitor<? extends T>)visitor).visitMultiplicativeExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultiplicativeExpressionContext multiplicativeExpression() throws RecognitionException {
		MultiplicativeExpressionContext _localctx = new MultiplicativeExpressionContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_multiplicativeExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(363);
			unaryExpression();
			setState(368);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 6597069766848L) != 0)) {
				{
				{
				setState(364);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 6597069766848L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(365);
				unaryExpression();
				}
				}
				setState(370);
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
	public static class UnaryExpressionContext extends ParserRuleContext {
		public UnaryExpressionContext unaryExpression() {
			return getRuleContext(UnaryExpressionContext.class,0);
		}
		public TerminalNode PLUS() { return getToken(Pcl_C4Parser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(Pcl_C4Parser.MINUS, 0); }
		public PrimaryExpressionContext primaryExpression() {
			return getRuleContext(PrimaryExpressionContext.class,0);
		}
		public UnaryExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryExpression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Pcl_C4Visitor ) return ((Pcl_C4Visitor<? extends T>)visitor).visitUnaryExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnaryExpressionContext unaryExpression() throws RecognitionException {
		UnaryExpressionContext _localctx = new UnaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_unaryExpression);
		int _la;
		try {
			setState(374);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PLUS:
			case MINUS:
				enterOuterAlt(_localctx, 1);
				{
				setState(371);
				_la = _input.LA(1);
				if ( !(_la==PLUS || _la==MINUS) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(372);
				unaryExpression();
				}
				break;
			case JOHN_PORK:
			case LPAREN:
			case INTEGER:
			case STRING:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(373);
				primaryExpression();
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
	public static class PrimaryExpressionContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(Pcl_C4Parser.INTEGER, 0); }
		public TerminalNode STRING() { return getToken(Pcl_C4Parser.STRING, 0); }
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(Pcl_C4Parser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(Pcl_C4Parser.RPAREN, 0); }
		public PrimaryExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primaryExpression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Pcl_C4Visitor ) return ((Pcl_C4Visitor<? extends T>)visitor).visitPrimaryExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryExpressionContext primaryExpression() throws RecognitionException {
		PrimaryExpressionContext _localctx = new PrimaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_primaryExpression);
		try {
			setState(384);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INTEGER:
				enterOuterAlt(_localctx, 1);
				{
				setState(376);
				match(INTEGER);
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(377);
				match(STRING);
				}
				break;
			case JOHN_PORK:
				enterOuterAlt(_localctx, 3);
				{
				setState(378);
				functionCall();
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 4);
				{
				setState(379);
				identifier();
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 5);
				{
				setState(380);
				match(LPAREN);
				setState(381);
				expression();
				setState(382);
				match(RPAREN);
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
	public static class IdentifierContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(Pcl_C4Parser.IDENTIFIER, 0); }
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Pcl_C4Visitor ) return ((Pcl_C4Visitor<? extends T>)visitor).visitIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_identifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(386);
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
		"\u0004\u00017\u0185\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0005\u0000K\b\u0000\n\u0000"+
		"\f\u0000N\t\u0000\u0001\u0000\u0001\u0000\u0005\u0000R\b\u0000\n\u0000"+
		"\f\u0000U\t\u0000\u0005\u0000W\b\u0000\n\u0000\f\u0000Z\t\u0000\u0001"+
		"\u0000\u0001\u0000\u0005\u0000^\b\u0000\n\u0000\f\u0000a\t\u0000\u0005"+
		"\u0000c\b\u0000\n\u0000\f\u0000f\t\u0000\u0001\u0000\u0001\u0000\u0005"+
		"\u0000j\b\u0000\n\u0000\f\u0000m\t\u0000\u0001\u0000\u0005\u0000p\b\u0000"+
		"\n\u0000\f\u0000s\t\u0000\u0001\u0000\u0005\u0000v\b\u0000\n\u0000\f\u0000"+
		"y\t\u0000\u0001\u0000\u0001\u0000\u0005\u0000}\b\u0000\n\u0000\f\u0000"+
		"\u0080\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0003\u0001\u0088\b\u0001\u0001\u0001\u0003\u0001\u008b\b"+
		"\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0005\u0002\u0090\b\u0002\n"+
		"\u0002\f\u0002\u0093\t\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003"+
		"\u0004\u009e\b\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0005\u0001\u0005\u0003\u0005\u00a6\b\u0005\u0001\u0006\u0001\u0006\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0005\u0007\u00ad\b\u0007\n\u0007\f\u0007"+
		"\u00b0\t\u0007\u0001\b\u0001\b\u0001\b\u0001\t\u0005\t\u00b6\b\t\n\t\f"+
		"\t\u00b9\t\t\u0001\t\u0001\t\u0004\t\u00bd\b\t\u000b\t\f\t\u00be\u0001"+
		"\t\u0001\t\u0005\t\u00c3\b\t\n\t\f\t\u00c6\t\t\u0003\t\u00c8\b\t\u0001"+
		"\t\u0005\t\u00cb\b\t\n\t\f\t\u00ce\t\t\u0001\t\u0001\t\u0001\n\u0001\n"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0003\u000b\u00e0\b\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0003\f\u00e8\b\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0005\u0010\u00fd\b\u0010\n\u0010\f\u0010\u0100\t\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0005\u0010\u010a\b\u0010\n\u0010\f\u0010\u010d\t\u0010\u0001"+
		"\u0010\u0005\u0010\u0110\b\u0010\n\u0010\f\u0010\u0113\t\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0003\u0010\u0118\b\u0010\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0003\u0012\u0124\b\u0012\u0001\u0012\u0001\u0012"+
		"\u0003\u0012\u0128\b\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u012c\b"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0001"+
		"\u0013\u0003\u0013\u0134\b\u0013\u0001\u0014\u0001\u0014\u0001\u0015\u0001"+
		"\u0015\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0003\u0017\u013e"+
		"\b\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0003\u0018\u0143\b\u0018"+
		"\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a"+
		"\u0001\u001a\u0005\u001a\u014c\b\u001a\n\u001a\f\u001a\u014f\t\u001a\u0001"+
		"\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0003\u001b\u0155\b\u001b\u0001"+
		"\u001b\u0001\u001b\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0005"+
		"\u001c\u015d\b\u001c\n\u001c\f\u001c\u0160\t\u001c\u0001\u001d\u0001\u001d"+
		"\u0001\u001e\u0001\u001e\u0001\u001e\u0005\u001e\u0167\b\u001e\n\u001e"+
		"\f\u001e\u016a\t\u001e\u0001\u001f\u0001\u001f\u0001\u001f\u0005\u001f"+
		"\u016f\b\u001f\n\u001f\f\u001f\u0172\t\u001f\u0001 \u0001 \u0001 \u0003"+
		" \u0177\b \u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0003"+
		"!\u0181\b!\u0001\"\u0001\"\u0001\"\u0000\u0000#\u0000\u0002\u0004\u0006"+
		"\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,."+
		"02468:<>@BD\u0000\u0007\u0001\u0000\u0014\u0015\u0002\u00000033\u0002"+
		"\u0000\u0004\u0004++\u0002\u0000\n\f\u001e&\u0002\u0000\b\t\'(\u0002\u0000"+
		"\u0006\u0007)*\u0001\u0000\'(\u0195\u0000F\u0001\u0000\u0000\u0000\u0002"+
		"\u0083\u0001\u0000\u0000\u0000\u0004\u008c\u0001\u0000\u0000\u0000\u0006"+
		"\u0094\u0001\u0000\u0000\u0000\b\u0098\u0001\u0000\u0000\u0000\n\u00a5"+
		"\u0001\u0000\u0000\u0000\f\u00a7\u0001\u0000\u0000\u0000\u000e\u00a9\u0001"+
		"\u0000\u0000\u0000\u0010\u00b1\u0001\u0000\u0000\u0000\u0012\u00b7\u0001"+
		"\u0000\u0000\u0000\u0014\u00d1\u0001\u0000\u0000\u0000\u0016\u00df\u0001"+
		"\u0000\u0000\u0000\u0018\u00e1\u0001\u0000\u0000\u0000\u001a\u00e9\u0001"+
		"\u0000\u0000\u0000\u001c\u00ef\u0001\u0000\u0000\u0000\u001e\u00f3\u0001"+
		"\u0000\u0000\u0000 \u00f5\u0001\u0000\u0000\u0000\"\u0119\u0001\u0000"+
		"\u0000\u0000$\u0120\u0001\u0000\u0000\u0000&\u0133\u0001\u0000\u0000\u0000"+
		"(\u0135\u0001\u0000\u0000\u0000*\u0137\u0001\u0000\u0000\u0000,\u0139"+
		"\u0001\u0000\u0000\u0000.\u013b\u0001\u0000\u0000\u00000\u013f\u0001\u0000"+
		"\u0000\u00002\u0146\u0001\u0000\u0000\u00004\u0148\u0001\u0000\u0000\u0000"+
		"6\u0150\u0001\u0000\u0000\u00008\u0158\u0001\u0000\u0000\u0000:\u0161"+
		"\u0001\u0000\u0000\u0000<\u0163\u0001\u0000\u0000\u0000>\u016b\u0001\u0000"+
		"\u0000\u0000@\u0176\u0001\u0000\u0000\u0000B\u0180\u0001\u0000\u0000\u0000"+
		"D\u0182\u0001\u0000\u0000\u0000FG\u0005\u0001\u0000\u0000GH\u0003D\"\u0000"+
		"HL\u0005.\u0000\u0000IK\u0003\u0014\n\u0000JI\u0001\u0000\u0000\u0000"+
		"KN\u0001\u0000\u0000\u0000LJ\u0001\u0000\u0000\u0000LM\u0001\u0000\u0000"+
		"\u0000MX\u0001\u0000\u0000\u0000NL\u0001\u0000\u0000\u0000OS\u0003\u0002"+
		"\u0001\u0000PR\u0003\u0014\n\u0000QP\u0001\u0000\u0000\u0000RU\u0001\u0000"+
		"\u0000\u0000SQ\u0001\u0000\u0000\u0000ST\u0001\u0000\u0000\u0000TW\u0001"+
		"\u0000\u0000\u0000US\u0001\u0000\u0000\u0000VO\u0001\u0000\u0000\u0000"+
		"WZ\u0001\u0000\u0000\u0000XV\u0001\u0000\u0000\u0000XY\u0001\u0000\u0000"+
		"\u0000Yd\u0001\u0000\u0000\u0000ZX\u0001\u0000\u0000\u0000[_\u0003\b\u0004"+
		"\u0000\\^\u0003\u0014\n\u0000]\\\u0001\u0000\u0000\u0000^a\u0001\u0000"+
		"\u0000\u0000_]\u0001\u0000\u0000\u0000_`\u0001\u0000\u0000\u0000`c\u0001"+
		"\u0000\u0000\u0000a_\u0001\u0000\u0000\u0000b[\u0001\u0000\u0000\u0000"+
		"cf\u0001\u0000\u0000\u0000db\u0001\u0000\u0000\u0000de\u0001\u0000\u0000"+
		"\u0000eg\u0001\u0000\u0000\u0000fd\u0001\u0000\u0000\u0000gq\u0003\u0006"+
		"\u0003\u0000hj\u0003\u0014\n\u0000ih\u0001\u0000\u0000\u0000jm\u0001\u0000"+
		"\u0000\u0000ki\u0001\u0000\u0000\u0000kl\u0001\u0000\u0000\u0000ln\u0001"+
		"\u0000\u0000\u0000mk\u0001\u0000\u0000\u0000np\u0003\b\u0004\u0000ok\u0001"+
		"\u0000\u0000\u0000ps\u0001\u0000\u0000\u0000qo\u0001\u0000\u0000\u0000"+
		"qr\u0001\u0000\u0000\u0000rw\u0001\u0000\u0000\u0000sq\u0001\u0000\u0000"+
		"\u0000tv\u0003\u0014\n\u0000ut\u0001\u0000\u0000\u0000vy\u0001\u0000\u0000"+
		"\u0000wu\u0001\u0000\u0000\u0000wx\u0001\u0000\u0000\u0000xz\u0001\u0000"+
		"\u0000\u0000yw\u0001\u0000\u0000\u0000z~\u0005\u0002\u0000\u0000{}\u0003"+
		"\u0014\n\u0000|{\u0001\u0000\u0000\u0000}\u0080\u0001\u0000\u0000\u0000"+
		"~|\u0001\u0000\u0000\u0000~\u007f\u0001\u0000\u0000\u0000\u007f\u0081"+
		"\u0001\u0000\u0000\u0000\u0080~\u0001\u0000\u0000\u0000\u0081\u0082\u0005"+
		"\u0000\u0000\u0001\u0082\u0001\u0001\u0000\u0000\u0000\u0083\u0084\u0005"+
		"\u000f\u0000\u0000\u0084\u0087\u0003\u0004\u0002\u0000\u0085\u0086\u0005"+
		"1\u0000\u0000\u0086\u0088\u0005)\u0000\u0000\u0087\u0085\u0001\u0000\u0000"+
		"\u0000\u0087\u0088\u0001\u0000\u0000\u0000\u0088\u008a\u0001\u0000\u0000"+
		"\u0000\u0089\u008b\u00050\u0000\u0000\u008a\u0089\u0001\u0000\u0000\u0000"+
		"\u008a\u008b\u0001\u0000\u0000\u0000\u008b\u0003\u0001\u0000\u0000\u0000"+
		"\u008c\u0091\u0003D\"\u0000\u008d\u008e\u00051\u0000\u0000\u008e\u0090"+
		"\u0003D\"\u0000\u008f\u008d\u0001\u0000\u0000\u0000\u0090\u0093\u0001"+
		"\u0000\u0000\u0000\u0091\u008f\u0001\u0000\u0000\u0000\u0091\u0092\u0001"+
		"\u0000\u0000\u0000\u0092\u0005\u0001\u0000\u0000\u0000\u0093\u0091\u0001"+
		"\u0000\u0000\u0000\u0094\u0095\u0005\u0003\u0000\u0000\u0095\u0096\u0005"+
		".\u0000\u0000\u0096\u0097\u0003\u0012\t\u0000\u0097\u0007\u0001\u0000"+
		"\u0000\u0000\u0098\u0099\u0005\u0012\u0000\u0000\u0099\u009a\u0003\n\u0005"+
		"\u0000\u009a\u009b\u0003D\"\u0000\u009b\u009d\u0005,\u0000\u0000\u009c"+
		"\u009e\u0003\u000e\u0007\u0000\u009d\u009c\u0001\u0000\u0000\u0000\u009d"+
		"\u009e\u0001\u0000\u0000\u0000\u009e\u009f\u0001\u0000\u0000\u0000\u009f"+
		"\u00a0\u0005-\u0000\u0000\u00a0\u00a1\u0005.\u0000\u0000\u00a1\u00a2\u0003"+
		"\u0012\t\u0000\u00a2\t\u0001\u0000\u0000\u0000\u00a3\u00a6\u0003\f\u0006"+
		"\u0000\u00a4\u00a6\u0005\u0013\u0000\u0000\u00a5\u00a3\u0001\u0000\u0000"+
		"\u0000\u00a5\u00a4\u0001\u0000\u0000\u0000\u00a6\u000b\u0001\u0000\u0000"+
		"\u0000\u00a7\u00a8\u0007\u0000\u0000\u0000\u00a8\r\u0001\u0000\u0000\u0000"+
		"\u00a9\u00ae\u0003\u0010\b\u0000\u00aa\u00ab\u0005/\u0000\u0000\u00ab"+
		"\u00ad\u0003\u0010\b\u0000\u00ac\u00aa\u0001\u0000\u0000\u0000\u00ad\u00b0"+
		"\u0001\u0000\u0000\u0000\u00ae\u00ac\u0001\u0000\u0000\u0000\u00ae\u00af"+
		"\u0001\u0000\u0000\u0000\u00af\u000f\u0001\u0000\u0000\u0000\u00b0\u00ae"+
		"\u0001\u0000\u0000\u0000\u00b1\u00b2\u0003\f\u0006\u0000\u00b2\u00b3\u0003"+
		"D\"\u0000\u00b3\u0011\u0001\u0000\u0000\u0000\u00b4\u00b6\u0003\u0014"+
		"\n\u0000\u00b5\u00b4\u0001\u0000\u0000\u0000\u00b6\u00b9\u0001\u0000\u0000"+
		"\u0000\u00b7\u00b5\u0001\u0000\u0000\u0000\u00b7\u00b8\u0001\u0000\u0000"+
		"\u0000\u00b8\u00c7\u0001\u0000\u0000\u0000\u00b9\u00b7\u0001\u0000\u0000"+
		"\u0000\u00ba\u00c4\u0003\u0016\u000b\u0000\u00bb\u00bd\u0003\u0014\n\u0000"+
		"\u00bc\u00bb\u0001\u0000\u0000\u0000\u00bd\u00be\u0001\u0000\u0000\u0000"+
		"\u00be\u00bc\u0001\u0000\u0000\u0000\u00be\u00bf\u0001\u0000\u0000\u0000"+
		"\u00bf\u00c0\u0001\u0000\u0000\u0000\u00c0\u00c1\u0003\u0016\u000b\u0000"+
		"\u00c1\u00c3\u0001\u0000\u0000\u0000\u00c2\u00bc\u0001\u0000\u0000\u0000"+
		"\u00c3\u00c6\u0001\u0000\u0000\u0000\u00c4\u00c2\u0001\u0000\u0000\u0000"+
		"\u00c4\u00c5\u0001\u0000\u0000\u0000\u00c5\u00c8\u0001\u0000\u0000\u0000"+
		"\u00c6\u00c4\u0001\u0000\u0000\u0000\u00c7\u00ba\u0001\u0000\u0000\u0000"+
		"\u00c7\u00c8\u0001\u0000\u0000\u0000\u00c8\u00cc\u0001\u0000\u0000\u0000"+
		"\u00c9\u00cb\u0003\u0014\n\u0000\u00ca\u00c9\u0001\u0000\u0000\u0000\u00cb"+
		"\u00ce\u0001\u0000\u0000\u0000\u00cc\u00ca\u0001\u0000\u0000\u0000\u00cc"+
		"\u00cd\u0001\u0000\u0000\u0000\u00cd\u00cf\u0001\u0000\u0000\u0000\u00ce"+
		"\u00cc\u0001\u0000\u0000\u0000\u00cf\u00d0\u0005\u0011\u0000\u0000\u00d0"+
		"\u0013\u0001\u0000\u0000\u0000\u00d1\u00d2\u0007\u0001\u0000\u0000\u00d2"+
		"\u0015\u0001\u0000\u0000\u0000\u00d3\u00e0\u0003\u0018\f\u0000\u00d4\u00e0"+
		"\u0003\u001a\r\u0000\u00d5\u00e0\u0003\u001c\u000e\u0000\u00d6\u00e0\u0003"+
		" \u0010\u0000\u00d7\u00e0\u0003\"\u0011\u0000\u00d8\u00e0\u0003$\u0012"+
		"\u0000\u00d9\u00e0\u0003(\u0014\u0000\u00da\u00e0\u0003*\u0015\u0000\u00db"+
		"\u00e0\u0003,\u0016\u0000\u00dc\u00e0\u0003.\u0017\u0000\u00dd\u00e0\u0003"+
		"0\u0018\u0000\u00de\u00e0\u00032\u0019\u0000\u00df\u00d3\u0001\u0000\u0000"+
		"\u0000\u00df\u00d4\u0001\u0000\u0000\u0000\u00df\u00d5\u0001\u0000\u0000"+
		"\u0000\u00df\u00d6\u0001\u0000\u0000\u0000\u00df\u00d7\u0001\u0000\u0000"+
		"\u0000\u00df\u00d8\u0001\u0000\u0000\u0000\u00df\u00d9\u0001\u0000\u0000"+
		"\u0000\u00df\u00da\u0001\u0000\u0000\u0000\u00df\u00db\u0001\u0000\u0000"+
		"\u0000\u00df\u00dc\u0001\u0000\u0000\u0000\u00df\u00dd\u0001\u0000\u0000"+
		"\u0000\u00df\u00de\u0001\u0000\u0000\u0000\u00e0\u0017\u0001\u0000\u0000"+
		"\u0000\u00e1\u00e2\u0005\u0016\u0000\u0000\u00e2\u00e3\u0003\f\u0006\u0000"+
		"\u00e3\u00e7\u0003D\"\u0000\u00e4\u00e5\u0003\u001e\u000f\u0000\u00e5"+
		"\u00e6\u00038\u001c\u0000\u00e6\u00e8\u0001\u0000\u0000\u0000\u00e7\u00e4"+
		"\u0001\u0000\u0000\u0000\u00e7\u00e8\u0001\u0000\u0000\u0000\u00e8\u0019"+
		"\u0001\u0000\u0000\u0000\u00e9\u00ea\u0005\u0005\u0000\u0000\u00ea\u00eb"+
		"\u0003\f\u0006\u0000\u00eb\u00ec\u0003D\"\u0000\u00ec\u00ed\u0003\u001e"+
		"\u000f\u0000\u00ed\u00ee\u00038\u001c\u0000\u00ee\u001b\u0001\u0000\u0000"+
		"\u0000\u00ef\u00f0\u0003D\"\u0000\u00f0\u00f1\u0003\u001e\u000f\u0000"+
		"\u00f1\u00f2\u00038\u001c\u0000\u00f2\u001d\u0001\u0000\u0000\u0000\u00f3"+
		"\u00f4\u0007\u0002\u0000\u0000\u00f4\u001f\u0001\u0000\u0000\u0000\u00f5"+
		"\u00f6\u0005\u0017\u0000\u0000\u00f6\u00f7\u0005,\u0000\u0000\u00f7\u00f8"+
		"\u00038\u001c\u0000\u00f8\u00f9\u0005-\u0000\u0000\u00f9\u00fa\u0005."+
		"\u0000\u0000\u00fa\u010b\u0003\u0012\t\u0000\u00fb\u00fd\u0003\u0014\n"+
		"\u0000\u00fc\u00fb\u0001\u0000\u0000\u0000\u00fd\u0100\u0001\u0000\u0000"+
		"\u0000\u00fe\u00fc\u0001\u0000\u0000\u0000\u00fe\u00ff\u0001\u0000\u0000"+
		"\u0000\u00ff\u0101\u0001\u0000\u0000\u0000\u0100\u00fe\u0001\u0000\u0000"+
		"\u0000\u0101\u0102\u0005\u0018\u0000\u0000\u0102\u0103\u0005\u0017\u0000"+
		"\u0000\u0103\u0104\u0005,\u0000\u0000\u0104\u0105\u00038\u001c\u0000\u0105"+
		"\u0106\u0005-\u0000\u0000\u0106\u0107\u0005.\u0000\u0000\u0107\u0108\u0003"+
		"\u0012\t\u0000\u0108\u010a\u0001\u0000\u0000\u0000\u0109\u00fe\u0001\u0000"+
		"\u0000\u0000\u010a\u010d\u0001\u0000\u0000\u0000\u010b\u0109\u0001\u0000"+
		"\u0000\u0000\u010b\u010c\u0001\u0000\u0000\u0000\u010c\u0117\u0001\u0000"+
		"\u0000\u0000\u010d\u010b\u0001\u0000\u0000\u0000\u010e\u0110\u0003\u0014"+
		"\n\u0000\u010f\u010e\u0001\u0000\u0000\u0000\u0110\u0113\u0001\u0000\u0000"+
		"\u0000\u0111\u010f\u0001\u0000\u0000\u0000\u0111\u0112\u0001\u0000\u0000"+
		"\u0000\u0112\u0114\u0001\u0000\u0000\u0000\u0113\u0111\u0001\u0000\u0000"+
		"\u0000\u0114\u0115\u0005\u0018\u0000\u0000\u0115\u0116\u0005.\u0000\u0000"+
		"\u0116\u0118\u0003\u0012\t\u0000\u0117\u0111\u0001\u0000\u0000\u0000\u0117"+
		"\u0118\u0001\u0000\u0000\u0000\u0118!\u0001\u0000\u0000\u0000\u0119\u011a"+
		"\u0005\u0019\u0000\u0000\u011a\u011b\u0005,\u0000\u0000\u011b\u011c\u0003"+
		"8\u001c\u0000\u011c\u011d\u0005-\u0000\u0000\u011d\u011e\u0005.\u0000"+
		"\u0000\u011e\u011f\u0003\u0012\t\u0000\u011f#\u0001\u0000\u0000\u0000"+
		"\u0120\u0121\u0005\u001a\u0000\u0000\u0121\u0123\u0005,\u0000\u0000\u0122"+
		"\u0124\u0003&\u0013\u0000\u0123\u0122\u0001\u0000\u0000\u0000\u0123\u0124"+
		"\u0001\u0000\u0000\u0000\u0124\u0125\u0001\u0000\u0000\u0000\u0125\u0127"+
		"\u00050\u0000\u0000\u0126\u0128\u00038\u001c\u0000\u0127\u0126\u0001\u0000"+
		"\u0000\u0000\u0127\u0128\u0001\u0000\u0000\u0000\u0128\u0129\u0001\u0000"+
		"\u0000\u0000\u0129\u012b\u00050\u0000\u0000\u012a\u012c\u0003\u001c\u000e"+
		"\u0000\u012b\u012a\u0001\u0000\u0000\u0000\u012b\u012c\u0001\u0000\u0000"+
		"\u0000\u012c\u012d\u0001\u0000\u0000\u0000\u012d\u012e\u0005-\u0000\u0000"+
		"\u012e\u012f\u0005.\u0000\u0000\u012f\u0130\u0003\u0012\t\u0000\u0130"+
		"%\u0001\u0000\u0000\u0000\u0131\u0134\u0003\u0018\f\u0000\u0132\u0134"+
		"\u0003\u001c\u000e\u0000\u0133\u0131\u0001\u0000\u0000\u0000\u0133\u0132"+
		"\u0001\u0000\u0000\u0000\u0134\'\u0001\u0000\u0000\u0000\u0135\u0136\u0005"+
		"\u001b\u0000\u0000\u0136)\u0001\u0000\u0000\u0000\u0137\u0138\u0005\u0010"+
		"\u0000\u0000\u0138+\u0001\u0000\u0000\u0000\u0139\u013a\u0005\u001c\u0000"+
		"\u0000\u013a-\u0001\u0000\u0000\u0000\u013b\u013d\u0005\u001d\u0000\u0000"+
		"\u013c\u013e\u00038\u001c\u0000\u013d\u013c\u0001\u0000\u0000\u0000\u013d"+
		"\u013e\u0001\u0000\u0000\u0000\u013e/\u0001\u0000\u0000\u0000\u013f\u0140"+
		"\u0005\u000e\u0000\u0000\u0140\u0142\u0005,\u0000\u0000\u0141\u0143\u0003"+
		"4\u001a\u0000\u0142\u0141\u0001\u0000\u0000\u0000\u0142\u0143\u0001\u0000"+
		"\u0000\u0000\u0143\u0144\u0001\u0000\u0000\u0000\u0144\u0145\u0005-\u0000"+
		"\u0000\u01451\u0001\u0000\u0000\u0000\u0146\u0147\u00036\u001b\u0000\u0147"+
		"3\u0001\u0000\u0000\u0000\u0148\u014d\u00038\u001c\u0000\u0149\u014a\u0005"+
		"/\u0000\u0000\u014a\u014c\u00038\u001c\u0000\u014b\u0149\u0001\u0000\u0000"+
		"\u0000\u014c\u014f\u0001\u0000\u0000\u0000\u014d\u014b\u0001\u0000\u0000"+
		"\u0000\u014d\u014e\u0001\u0000\u0000\u0000\u014e5\u0001\u0000\u0000\u0000"+
		"\u014f\u014d\u0001\u0000\u0000\u0000\u0150\u0151\u0005\r\u0000\u0000\u0151"+
		"\u0152\u0003D\"\u0000\u0152\u0154\u0005,\u0000\u0000\u0153\u0155\u0003"+
		"4\u001a\u0000\u0154\u0153\u0001\u0000\u0000\u0000\u0154\u0155\u0001\u0000"+
		"\u0000\u0000\u0155\u0156\u0001\u0000\u0000\u0000\u0156\u0157\u0005-\u0000"+
		"\u0000\u01577\u0001\u0000\u0000\u0000\u0158\u015e\u0003<\u001e\u0000\u0159"+
		"\u015a\u0003:\u001d\u0000\u015a\u015b\u0003<\u001e\u0000\u015b\u015d\u0001"+
		"\u0000\u0000\u0000\u015c\u0159\u0001\u0000\u0000\u0000\u015d\u0160\u0001"+
		"\u0000\u0000\u0000\u015e\u015c\u0001\u0000\u0000\u0000\u015e\u015f\u0001"+
		"\u0000\u0000\u0000\u015f9\u0001\u0000\u0000\u0000\u0160\u015e\u0001\u0000"+
		"\u0000\u0000\u0161\u0162\u0007\u0003\u0000\u0000\u0162;\u0001\u0000\u0000"+
		"\u0000\u0163\u0168\u0003>\u001f\u0000\u0164\u0165\u0007\u0004\u0000\u0000"+
		"\u0165\u0167\u0003>\u001f\u0000\u0166\u0164\u0001\u0000\u0000\u0000\u0167"+
		"\u016a\u0001\u0000\u0000\u0000\u0168\u0166\u0001\u0000\u0000\u0000\u0168"+
		"\u0169\u0001\u0000\u0000\u0000\u0169=\u0001\u0000\u0000\u0000\u016a\u0168"+
		"\u0001\u0000\u0000\u0000\u016b\u0170\u0003@ \u0000\u016c\u016d\u0007\u0005"+
		"\u0000\u0000\u016d\u016f\u0003@ \u0000\u016e\u016c\u0001\u0000\u0000\u0000"+
		"\u016f\u0172\u0001\u0000\u0000\u0000\u0170\u016e\u0001\u0000\u0000\u0000"+
		"\u0170\u0171\u0001\u0000\u0000\u0000\u0171?\u0001\u0000\u0000\u0000\u0172"+
		"\u0170\u0001\u0000\u0000\u0000\u0173\u0174\u0007\u0006\u0000\u0000\u0174"+
		"\u0177\u0003@ \u0000\u0175\u0177\u0003B!\u0000\u0176\u0173\u0001\u0000"+
		"\u0000\u0000\u0176\u0175\u0001\u0000\u0000\u0000\u0177A\u0001\u0000\u0000"+
		"\u0000\u0178\u0181\u00055\u0000\u0000\u0179\u0181\u00056\u0000\u0000\u017a"+
		"\u0181\u00036\u001b\u0000\u017b\u0181\u0003D\"\u0000\u017c\u017d\u0005"+
		",\u0000\u0000\u017d\u017e\u00038\u001c\u0000\u017e\u017f\u0005-\u0000"+
		"\u0000\u017f\u0181\u0001\u0000\u0000\u0000\u0180\u0178\u0001\u0000\u0000"+
		"\u0000\u0180\u0179\u0001\u0000\u0000\u0000\u0180\u017a\u0001\u0000\u0000"+
		"\u0000\u0180\u017b\u0001\u0000\u0000\u0000\u0180\u017c\u0001\u0000\u0000"+
		"\u0000\u0181C\u0001\u0000\u0000\u0000\u0182\u0183\u00057\u0000\u0000\u0183"+
		"E\u0001\u0000\u0000\u0000\'LSX_dkqw~\u0087\u008a\u0091\u009d\u00a5\u00ae"+
		"\u00b7\u00be\u00c4\u00c7\u00cc\u00df\u00e7\u00fe\u010b\u0111\u0117\u0123"+
		"\u0127\u012b\u0133\u013d\u0142\u014d\u0154\u015e\u0168\u0170\u0176\u0180";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}