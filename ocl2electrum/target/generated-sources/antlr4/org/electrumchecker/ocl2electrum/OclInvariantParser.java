// Generated from org/electrumchecker/ocl2electrum/OclInvariant.g4 by ANTLR 4.13.2
package org.electrumchecker.ocl2electrum;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class OclInvariantParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		CONTEXT=1, INV=2, IMPLIES=3, OR=4, XOR=5, AND=6, NOT=7, DIV=8, MOD=9, 
		TRUE=10, FALSE=11, SELF=12, EQUAL=13, NOT_EQUAL=14, LESS_EQUAL=15, GREATER_EQUAL=16, 
		LESS=17, GREATER=18, PLUS=19, MINUS=20, STAR=21, SLASH=22, PERCENT=23, 
		DOT=24, COLON=25, SEMI=26, LPAREN=27, RPAREN=28, INTEGER=29, ID=30, LINE_COMMENT=31, 
		BLOCK_COMMENT=32, WS=33;
	public static final int
		RULE_document = 0, RULE_invariantDeclaration = 1, RULE_expression = 2, 
		RULE_implicationExpression = 3, RULE_orExpression = 4, RULE_xorExpression = 5, 
		RULE_andExpression = 6, RULE_equalityExpression = 7, RULE_relationalExpression = 8, 
		RULE_additiveExpression = 9, RULE_multiplicativeExpression = 10, RULE_unaryExpression = 11, 
		RULE_primaryExpression = 12;
	private static String[] makeRuleNames() {
		return new String[] {
			"document", "invariantDeclaration", "expression", "implicationExpression", 
			"orExpression", "xorExpression", "andExpression", "equalityExpression", 
			"relationalExpression", "additiveExpression", "multiplicativeExpression", 
			"unaryExpression", "primaryExpression"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'context'", "'inv'", "'implies'", "'or'", "'xor'", "'and'", "'not'", 
			"'div'", "'mod'", "'true'", "'false'", "'self'", "'='", null, "'<='", 
			"'>='", "'<'", "'>'", "'+'", "'-'", "'*'", "'/'", "'%'", "'.'", "':'", 
			"';'", "'('", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "CONTEXT", "INV", "IMPLIES", "OR", "XOR", "AND", "NOT", "DIV", 
			"MOD", "TRUE", "FALSE", "SELF", "EQUAL", "NOT_EQUAL", "LESS_EQUAL", "GREATER_EQUAL", 
			"LESS", "GREATER", "PLUS", "MINUS", "STAR", "SLASH", "PERCENT", "DOT", 
			"COLON", "SEMI", "LPAREN", "RPAREN", "INTEGER", "ID", "LINE_COMMENT", 
			"BLOCK_COMMENT", "WS"
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
	public String getGrammarFileName() { return "OclInvariant.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public OclInvariantParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DocumentContext extends ParserRuleContext {
		public TerminalNode CONTEXT() { return getToken(OclInvariantParser.CONTEXT, 0); }
		public TerminalNode ID() { return getToken(OclInvariantParser.ID, 0); }
		public TerminalNode EOF() { return getToken(OclInvariantParser.EOF, 0); }
		public List<InvariantDeclarationContext> invariantDeclaration() {
			return getRuleContexts(InvariantDeclarationContext.class);
		}
		public InvariantDeclarationContext invariantDeclaration(int i) {
			return getRuleContext(InvariantDeclarationContext.class,i);
		}
		public DocumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_document; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof OclInvariantVisitor ) return ((OclInvariantVisitor<? extends T>)visitor).visitDocument(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DocumentContext document() throws RecognitionException {
		DocumentContext _localctx = new DocumentContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_document);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(26);
			match(CONTEXT);
			setState(27);
			match(ID);
			setState(29); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(28);
				invariantDeclaration();
				}
				}
				setState(31); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==INV );
			setState(33);
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
	public static class InvariantDeclarationContext extends ParserRuleContext {
		public TerminalNode INV() { return getToken(OclInvariantParser.INV, 0); }
		public TerminalNode COLON() { return getToken(OclInvariantParser.COLON, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode ID() { return getToken(OclInvariantParser.ID, 0); }
		public TerminalNode SEMI() { return getToken(OclInvariantParser.SEMI, 0); }
		public InvariantDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_invariantDeclaration; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof OclInvariantVisitor ) return ((OclInvariantVisitor<? extends T>)visitor).visitInvariantDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InvariantDeclarationContext invariantDeclaration() throws RecognitionException {
		InvariantDeclarationContext _localctx = new InvariantDeclarationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_invariantDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(35);
			match(INV);
			setState(37);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(36);
				match(ID);
				}
			}

			setState(39);
			match(COLON);
			setState(40);
			expression();
			setState(42);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMI) {
				{
				setState(41);
				match(SEMI);
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
	public static class ExpressionContext extends ParserRuleContext {
		public ImplicationExpressionContext implicationExpression() {
			return getRuleContext(ImplicationExpressionContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof OclInvariantVisitor ) return ((OclInvariantVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44);
			implicationExpression();
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
	public static class ImplicationExpressionContext extends ParserRuleContext {
		public OrExpressionContext orExpression() {
			return getRuleContext(OrExpressionContext.class,0);
		}
		public TerminalNode IMPLIES() { return getToken(OclInvariantParser.IMPLIES, 0); }
		public ImplicationExpressionContext implicationExpression() {
			return getRuleContext(ImplicationExpressionContext.class,0);
		}
		public ImplicationExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_implicationExpression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof OclInvariantVisitor ) return ((OclInvariantVisitor<? extends T>)visitor).visitImplicationExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImplicationExpressionContext implicationExpression() throws RecognitionException {
		ImplicationExpressionContext _localctx = new ImplicationExpressionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_implicationExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			orExpression();
			setState(49);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IMPLIES) {
				{
				setState(47);
				match(IMPLIES);
				setState(48);
				implicationExpression();
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
	public static class OrExpressionContext extends ParserRuleContext {
		public List<XorExpressionContext> xorExpression() {
			return getRuleContexts(XorExpressionContext.class);
		}
		public XorExpressionContext xorExpression(int i) {
			return getRuleContext(XorExpressionContext.class,i);
		}
		public List<TerminalNode> OR() { return getTokens(OclInvariantParser.OR); }
		public TerminalNode OR(int i) {
			return getToken(OclInvariantParser.OR, i);
		}
		public OrExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orExpression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof OclInvariantVisitor ) return ((OclInvariantVisitor<? extends T>)visitor).visitOrExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OrExpressionContext orExpression() throws RecognitionException {
		OrExpressionContext _localctx = new OrExpressionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_orExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			xorExpression();
			setState(56);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(52);
				match(OR);
				setState(53);
				xorExpression();
				}
				}
				setState(58);
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
	public static class XorExpressionContext extends ParserRuleContext {
		public List<AndExpressionContext> andExpression() {
			return getRuleContexts(AndExpressionContext.class);
		}
		public AndExpressionContext andExpression(int i) {
			return getRuleContext(AndExpressionContext.class,i);
		}
		public List<TerminalNode> XOR() { return getTokens(OclInvariantParser.XOR); }
		public TerminalNode XOR(int i) {
			return getToken(OclInvariantParser.XOR, i);
		}
		public XorExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_xorExpression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof OclInvariantVisitor ) return ((OclInvariantVisitor<? extends T>)visitor).visitXorExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final XorExpressionContext xorExpression() throws RecognitionException {
		XorExpressionContext _localctx = new XorExpressionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_xorExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			andExpression();
			setState(64);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==XOR) {
				{
				{
				setState(60);
				match(XOR);
				setState(61);
				andExpression();
				}
				}
				setState(66);
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
	public static class AndExpressionContext extends ParserRuleContext {
		public List<EqualityExpressionContext> equalityExpression() {
			return getRuleContexts(EqualityExpressionContext.class);
		}
		public EqualityExpressionContext equalityExpression(int i) {
			return getRuleContext(EqualityExpressionContext.class,i);
		}
		public List<TerminalNode> AND() { return getTokens(OclInvariantParser.AND); }
		public TerminalNode AND(int i) {
			return getToken(OclInvariantParser.AND, i);
		}
		public AndExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_andExpression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof OclInvariantVisitor ) return ((OclInvariantVisitor<? extends T>)visitor).visitAndExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AndExpressionContext andExpression() throws RecognitionException {
		AndExpressionContext _localctx = new AndExpressionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_andExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			equalityExpression();
			setState(72);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(68);
				match(AND);
				setState(69);
				equalityExpression();
				}
				}
				setState(74);
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
	public static class EqualityExpressionContext extends ParserRuleContext {
		public List<RelationalExpressionContext> relationalExpression() {
			return getRuleContexts(RelationalExpressionContext.class);
		}
		public RelationalExpressionContext relationalExpression(int i) {
			return getRuleContext(RelationalExpressionContext.class,i);
		}
		public List<TerminalNode> EQUAL() { return getTokens(OclInvariantParser.EQUAL); }
		public TerminalNode EQUAL(int i) {
			return getToken(OclInvariantParser.EQUAL, i);
		}
		public List<TerminalNode> NOT_EQUAL() { return getTokens(OclInvariantParser.NOT_EQUAL); }
		public TerminalNode NOT_EQUAL(int i) {
			return getToken(OclInvariantParser.NOT_EQUAL, i);
		}
		public EqualityExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equalityExpression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof OclInvariantVisitor ) return ((OclInvariantVisitor<? extends T>)visitor).visitEqualityExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EqualityExpressionContext equalityExpression() throws RecognitionException {
		EqualityExpressionContext _localctx = new EqualityExpressionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_equalityExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			relationalExpression();
			setState(80);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EQUAL || _la==NOT_EQUAL) {
				{
				{
				setState(76);
				_la = _input.LA(1);
				if ( !(_la==EQUAL || _la==NOT_EQUAL) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(77);
				relationalExpression();
				}
				}
				setState(82);
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
	public static class RelationalExpressionContext extends ParserRuleContext {
		public List<AdditiveExpressionContext> additiveExpression() {
			return getRuleContexts(AdditiveExpressionContext.class);
		}
		public AdditiveExpressionContext additiveExpression(int i) {
			return getRuleContext(AdditiveExpressionContext.class,i);
		}
		public List<TerminalNode> LESS() { return getTokens(OclInvariantParser.LESS); }
		public TerminalNode LESS(int i) {
			return getToken(OclInvariantParser.LESS, i);
		}
		public List<TerminalNode> LESS_EQUAL() { return getTokens(OclInvariantParser.LESS_EQUAL); }
		public TerminalNode LESS_EQUAL(int i) {
			return getToken(OclInvariantParser.LESS_EQUAL, i);
		}
		public List<TerminalNode> GREATER() { return getTokens(OclInvariantParser.GREATER); }
		public TerminalNode GREATER(int i) {
			return getToken(OclInvariantParser.GREATER, i);
		}
		public List<TerminalNode> GREATER_EQUAL() { return getTokens(OclInvariantParser.GREATER_EQUAL); }
		public TerminalNode GREATER_EQUAL(int i) {
			return getToken(OclInvariantParser.GREATER_EQUAL, i);
		}
		public RelationalExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationalExpression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof OclInvariantVisitor ) return ((OclInvariantVisitor<? extends T>)visitor).visitRelationalExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationalExpressionContext relationalExpression() throws RecognitionException {
		RelationalExpressionContext _localctx = new RelationalExpressionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_relationalExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			additiveExpression();
			setState(88);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 491520L) != 0)) {
				{
				{
				setState(84);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 491520L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(85);
				additiveExpression();
				}
				}
				setState(90);
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
	public static class AdditiveExpressionContext extends ParserRuleContext {
		public List<MultiplicativeExpressionContext> multiplicativeExpression() {
			return getRuleContexts(MultiplicativeExpressionContext.class);
		}
		public MultiplicativeExpressionContext multiplicativeExpression(int i) {
			return getRuleContext(MultiplicativeExpressionContext.class,i);
		}
		public List<TerminalNode> PLUS() { return getTokens(OclInvariantParser.PLUS); }
		public TerminalNode PLUS(int i) {
			return getToken(OclInvariantParser.PLUS, i);
		}
		public List<TerminalNode> MINUS() { return getTokens(OclInvariantParser.MINUS); }
		public TerminalNode MINUS(int i) {
			return getToken(OclInvariantParser.MINUS, i);
		}
		public AdditiveExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additiveExpression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof OclInvariantVisitor ) return ((OclInvariantVisitor<? extends T>)visitor).visitAdditiveExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AdditiveExpressionContext additiveExpression() throws RecognitionException {
		AdditiveExpressionContext _localctx = new AdditiveExpressionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_additiveExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			multiplicativeExpression();
			setState(96);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PLUS || _la==MINUS) {
				{
				{
				setState(92);
				_la = _input.LA(1);
				if ( !(_la==PLUS || _la==MINUS) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(93);
				multiplicativeExpression();
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
	public static class MultiplicativeExpressionContext extends ParserRuleContext {
		public List<UnaryExpressionContext> unaryExpression() {
			return getRuleContexts(UnaryExpressionContext.class);
		}
		public UnaryExpressionContext unaryExpression(int i) {
			return getRuleContext(UnaryExpressionContext.class,i);
		}
		public List<TerminalNode> STAR() { return getTokens(OclInvariantParser.STAR); }
		public TerminalNode STAR(int i) {
			return getToken(OclInvariantParser.STAR, i);
		}
		public List<TerminalNode> SLASH() { return getTokens(OclInvariantParser.SLASH); }
		public TerminalNode SLASH(int i) {
			return getToken(OclInvariantParser.SLASH, i);
		}
		public List<TerminalNode> PERCENT() { return getTokens(OclInvariantParser.PERCENT); }
		public TerminalNode PERCENT(int i) {
			return getToken(OclInvariantParser.PERCENT, i);
		}
		public List<TerminalNode> DIV() { return getTokens(OclInvariantParser.DIV); }
		public TerminalNode DIV(int i) {
			return getToken(OclInvariantParser.DIV, i);
		}
		public List<TerminalNode> MOD() { return getTokens(OclInvariantParser.MOD); }
		public TerminalNode MOD(int i) {
			return getToken(OclInvariantParser.MOD, i);
		}
		public MultiplicativeExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplicativeExpression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof OclInvariantVisitor ) return ((OclInvariantVisitor<? extends T>)visitor).visitMultiplicativeExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultiplicativeExpressionContext multiplicativeExpression() throws RecognitionException {
		MultiplicativeExpressionContext _localctx = new MultiplicativeExpressionContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_multiplicativeExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			unaryExpression();
			setState(104);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 14680832L) != 0)) {
				{
				{
				setState(100);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 14680832L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(101);
				unaryExpression();
				}
				}
				setState(106);
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
		public UnaryExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryExpression; }
	 
		public UnaryExpressionContext() { }
		public void copyFrom(UnaryExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PrimaryOperationContext extends UnaryExpressionContext {
		public PrimaryExpressionContext primaryExpression() {
			return getRuleContext(PrimaryExpressionContext.class,0);
		}
		public PrimaryOperationContext(UnaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof OclInvariantVisitor ) return ((OclInvariantVisitor<? extends T>)visitor).visitPrimaryOperation(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnaryOperationContext extends UnaryExpressionContext {
		public UnaryExpressionContext unaryExpression() {
			return getRuleContext(UnaryExpressionContext.class,0);
		}
		public TerminalNode NOT() { return getToken(OclInvariantParser.NOT, 0); }
		public TerminalNode MINUS() { return getToken(OclInvariantParser.MINUS, 0); }
		public UnaryOperationContext(UnaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof OclInvariantVisitor ) return ((OclInvariantVisitor<? extends T>)visitor).visitUnaryOperation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnaryExpressionContext unaryExpression() throws RecognitionException {
		UnaryExpressionContext _localctx = new UnaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_unaryExpression);
		int _la;
		try {
			setState(110);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NOT:
			case MINUS:
				_localctx = new UnaryOperationContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(107);
				_la = _input.LA(1);
				if ( !(_la==NOT || _la==MINUS) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(108);
				unaryExpression();
				}
				break;
			case TRUE:
			case FALSE:
			case SELF:
			case LPAREN:
			case INTEGER:
			case ID:
				_localctx = new PrimaryOperationContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(109);
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
		public PrimaryExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primaryExpression; }
	 
		public PrimaryExpressionContext() { }
		public void copyFrom(PrimaryExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NameOrNavigationContext extends PrimaryExpressionContext {
		public List<TerminalNode> ID() { return getTokens(OclInvariantParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(OclInvariantParser.ID, i);
		}
		public List<TerminalNode> DOT() { return getTokens(OclInvariantParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(OclInvariantParser.DOT, i);
		}
		public NameOrNavigationContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof OclInvariantVisitor ) return ((OclInvariantVisitor<? extends T>)visitor).visitNameOrNavigation(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParenthesizedExpressionContext extends PrimaryExpressionContext {
		public TerminalNode LPAREN() { return getToken(OclInvariantParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(OclInvariantParser.RPAREN, 0); }
		public ParenthesizedExpressionContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof OclInvariantVisitor ) return ((OclInvariantVisitor<? extends T>)visitor).visitParenthesizedExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FalseLiteralContext extends PrimaryExpressionContext {
		public TerminalNode FALSE() { return getToken(OclInvariantParser.FALSE, 0); }
		public FalseLiteralContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof OclInvariantVisitor ) return ((OclInvariantVisitor<? extends T>)visitor).visitFalseLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IntegerLiteralContext extends PrimaryExpressionContext {
		public TerminalNode INTEGER() { return getToken(OclInvariantParser.INTEGER, 0); }
		public IntegerLiteralContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof OclInvariantVisitor ) return ((OclInvariantVisitor<? extends T>)visitor).visitIntegerLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TrueLiteralContext extends PrimaryExpressionContext {
		public TerminalNode TRUE() { return getToken(OclInvariantParser.TRUE, 0); }
		public TrueLiteralContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof OclInvariantVisitor ) return ((OclInvariantVisitor<? extends T>)visitor).visitTrueLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SelfNavigationContext extends PrimaryExpressionContext {
		public TerminalNode SELF() { return getToken(OclInvariantParser.SELF, 0); }
		public List<TerminalNode> DOT() { return getTokens(OclInvariantParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(OclInvariantParser.DOT, i);
		}
		public List<TerminalNode> ID() { return getTokens(OclInvariantParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(OclInvariantParser.ID, i);
		}
		public SelfNavigationContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof OclInvariantVisitor ) return ((OclInvariantVisitor<? extends T>)visitor).visitSelfNavigation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryExpressionContext primaryExpression() throws RecognitionException {
		PrimaryExpressionContext _localctx = new PrimaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_primaryExpression);
		int _la;
		try {
			setState(135);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SELF:
				_localctx = new SelfNavigationContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(112);
				match(SELF);
				setState(117);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==DOT) {
					{
					{
					setState(113);
					match(DOT);
					setState(114);
					match(ID);
					}
					}
					setState(119);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case ID:
				_localctx = new NameOrNavigationContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(120);
				match(ID);
				setState(125);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==DOT) {
					{
					{
					setState(121);
					match(DOT);
					setState(122);
					match(ID);
					}
					}
					setState(127);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case INTEGER:
				_localctx = new IntegerLiteralContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(128);
				match(INTEGER);
				}
				break;
			case TRUE:
				_localctx = new TrueLiteralContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(129);
				match(TRUE);
				}
				break;
			case FALSE:
				_localctx = new FalseLiteralContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(130);
				match(FALSE);
				}
				break;
			case LPAREN:
				_localctx = new ParenthesizedExpressionContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(131);
				match(LPAREN);
				setState(132);
				expression();
				setState(133);
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

	public static final String _serializedATN =
		"\u0004\u0001!\u008a\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0001\u0000\u0001\u0000\u0001\u0000\u0004\u0000\u001e\b\u0000"+
		"\u000b\u0000\f\u0000\u001f\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001"+
		"\u0003\u0001&\b\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001"+
		"+\b\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0003\u00032\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0005\u0004"+
		"7\b\u0004\n\u0004\f\u0004:\t\u0004\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0005\u0005?\b\u0005\n\u0005\f\u0005B\t\u0005\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0005\u0006G\b\u0006\n\u0006\f\u0006J\t\u0006\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0005\u0007O\b\u0007\n\u0007\f\u0007R\t\u0007"+
		"\u0001\b\u0001\b\u0001\b\u0005\bW\b\b\n\b\f\bZ\t\b\u0001\t\u0001\t\u0001"+
		"\t\u0005\t_\b\t\n\t\f\tb\t\t\u0001\n\u0001\n\u0001\n\u0005\ng\b\n\n\n"+
		"\f\nj\t\n\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000bo\b\u000b\u0001"+
		"\f\u0001\f\u0001\f\u0005\ft\b\f\n\f\f\fw\t\f\u0001\f\u0001\f\u0001\f\u0005"+
		"\f|\b\f\n\f\f\f\u007f\t\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0003\f\u0088\b\f\u0001\f\u0000\u0000\r\u0000\u0002\u0004\u0006"+
		"\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u0000\u0005\u0001\u0000\r\u000e"+
		"\u0001\u0000\u000f\u0012\u0001\u0000\u0013\u0014\u0002\u0000\b\t\u0015"+
		"\u0017\u0002\u0000\u0007\u0007\u0014\u0014\u008f\u0000\u001a\u0001\u0000"+
		"\u0000\u0000\u0002#\u0001\u0000\u0000\u0000\u0004,\u0001\u0000\u0000\u0000"+
		"\u0006.\u0001\u0000\u0000\u0000\b3\u0001\u0000\u0000\u0000\n;\u0001\u0000"+
		"\u0000\u0000\fC\u0001\u0000\u0000\u0000\u000eK\u0001\u0000\u0000\u0000"+
		"\u0010S\u0001\u0000\u0000\u0000\u0012[\u0001\u0000\u0000\u0000\u0014c"+
		"\u0001\u0000\u0000\u0000\u0016n\u0001\u0000\u0000\u0000\u0018\u0087\u0001"+
		"\u0000\u0000\u0000\u001a\u001b\u0005\u0001\u0000\u0000\u001b\u001d\u0005"+
		"\u001e\u0000\u0000\u001c\u001e\u0003\u0002\u0001\u0000\u001d\u001c\u0001"+
		"\u0000\u0000\u0000\u001e\u001f\u0001\u0000\u0000\u0000\u001f\u001d\u0001"+
		"\u0000\u0000\u0000\u001f \u0001\u0000\u0000\u0000 !\u0001\u0000\u0000"+
		"\u0000!\"\u0005\u0000\u0000\u0001\"\u0001\u0001\u0000\u0000\u0000#%\u0005"+
		"\u0002\u0000\u0000$&\u0005\u001e\u0000\u0000%$\u0001\u0000\u0000\u0000"+
		"%&\u0001\u0000\u0000\u0000&\'\u0001\u0000\u0000\u0000\'(\u0005\u0019\u0000"+
		"\u0000(*\u0003\u0004\u0002\u0000)+\u0005\u001a\u0000\u0000*)\u0001\u0000"+
		"\u0000\u0000*+\u0001\u0000\u0000\u0000+\u0003\u0001\u0000\u0000\u0000"+
		",-\u0003\u0006\u0003\u0000-\u0005\u0001\u0000\u0000\u0000.1\u0003\b\u0004"+
		"\u0000/0\u0005\u0003\u0000\u000002\u0003\u0006\u0003\u00001/\u0001\u0000"+
		"\u0000\u000012\u0001\u0000\u0000\u00002\u0007\u0001\u0000\u0000\u0000"+
		"38\u0003\n\u0005\u000045\u0005\u0004\u0000\u000057\u0003\n\u0005\u0000"+
		"64\u0001\u0000\u0000\u00007:\u0001\u0000\u0000\u000086\u0001\u0000\u0000"+
		"\u000089\u0001\u0000\u0000\u00009\t\u0001\u0000\u0000\u0000:8\u0001\u0000"+
		"\u0000\u0000;@\u0003\f\u0006\u0000<=\u0005\u0005\u0000\u0000=?\u0003\f"+
		"\u0006\u0000><\u0001\u0000\u0000\u0000?B\u0001\u0000\u0000\u0000@>\u0001"+
		"\u0000\u0000\u0000@A\u0001\u0000\u0000\u0000A\u000b\u0001\u0000\u0000"+
		"\u0000B@\u0001\u0000\u0000\u0000CH\u0003\u000e\u0007\u0000DE\u0005\u0006"+
		"\u0000\u0000EG\u0003\u000e\u0007\u0000FD\u0001\u0000\u0000\u0000GJ\u0001"+
		"\u0000\u0000\u0000HF\u0001\u0000\u0000\u0000HI\u0001\u0000\u0000\u0000"+
		"I\r\u0001\u0000\u0000\u0000JH\u0001\u0000\u0000\u0000KP\u0003\u0010\b"+
		"\u0000LM\u0007\u0000\u0000\u0000MO\u0003\u0010\b\u0000NL\u0001\u0000\u0000"+
		"\u0000OR\u0001\u0000\u0000\u0000PN\u0001\u0000\u0000\u0000PQ\u0001\u0000"+
		"\u0000\u0000Q\u000f\u0001\u0000\u0000\u0000RP\u0001\u0000\u0000\u0000"+
		"SX\u0003\u0012\t\u0000TU\u0007\u0001\u0000\u0000UW\u0003\u0012\t\u0000"+
		"VT\u0001\u0000\u0000\u0000WZ\u0001\u0000\u0000\u0000XV\u0001\u0000\u0000"+
		"\u0000XY\u0001\u0000\u0000\u0000Y\u0011\u0001\u0000\u0000\u0000ZX\u0001"+
		"\u0000\u0000\u0000[`\u0003\u0014\n\u0000\\]\u0007\u0002\u0000\u0000]_"+
		"\u0003\u0014\n\u0000^\\\u0001\u0000\u0000\u0000_b\u0001\u0000\u0000\u0000"+
		"`^\u0001\u0000\u0000\u0000`a\u0001\u0000\u0000\u0000a\u0013\u0001\u0000"+
		"\u0000\u0000b`\u0001\u0000\u0000\u0000ch\u0003\u0016\u000b\u0000de\u0007"+
		"\u0003\u0000\u0000eg\u0003\u0016\u000b\u0000fd\u0001\u0000\u0000\u0000"+
		"gj\u0001\u0000\u0000\u0000hf\u0001\u0000\u0000\u0000hi\u0001\u0000\u0000"+
		"\u0000i\u0015\u0001\u0000\u0000\u0000jh\u0001\u0000\u0000\u0000kl\u0007"+
		"\u0004\u0000\u0000lo\u0003\u0016\u000b\u0000mo\u0003\u0018\f\u0000nk\u0001"+
		"\u0000\u0000\u0000nm\u0001\u0000\u0000\u0000o\u0017\u0001\u0000\u0000"+
		"\u0000pu\u0005\f\u0000\u0000qr\u0005\u0018\u0000\u0000rt\u0005\u001e\u0000"+
		"\u0000sq\u0001\u0000\u0000\u0000tw\u0001\u0000\u0000\u0000us\u0001\u0000"+
		"\u0000\u0000uv\u0001\u0000\u0000\u0000v\u0088\u0001\u0000\u0000\u0000"+
		"wu\u0001\u0000\u0000\u0000x}\u0005\u001e\u0000\u0000yz\u0005\u0018\u0000"+
		"\u0000z|\u0005\u001e\u0000\u0000{y\u0001\u0000\u0000\u0000|\u007f\u0001"+
		"\u0000\u0000\u0000}{\u0001\u0000\u0000\u0000}~\u0001\u0000\u0000\u0000"+
		"~\u0088\u0001\u0000\u0000\u0000\u007f}\u0001\u0000\u0000\u0000\u0080\u0088"+
		"\u0005\u001d\u0000\u0000\u0081\u0088\u0005\n\u0000\u0000\u0082\u0088\u0005"+
		"\u000b\u0000\u0000\u0083\u0084\u0005\u001b\u0000\u0000\u0084\u0085\u0003"+
		"\u0004\u0002\u0000\u0085\u0086\u0005\u001c\u0000\u0000\u0086\u0088\u0001"+
		"\u0000\u0000\u0000\u0087p\u0001\u0000\u0000\u0000\u0087x\u0001\u0000\u0000"+
		"\u0000\u0087\u0080\u0001\u0000\u0000\u0000\u0087\u0081\u0001\u0000\u0000"+
		"\u0000\u0087\u0082\u0001\u0000\u0000\u0000\u0087\u0083\u0001\u0000\u0000"+
		"\u0000\u0088\u0019\u0001\u0000\u0000\u0000\u000f\u001f%*18@HPX`hnu}\u0087";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}