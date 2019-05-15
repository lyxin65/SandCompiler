// Generated from Mxstar.g4 by ANTLR 4.7.1
package Mxstar.Parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MxstarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, BOOL_LITERAL=34, NULL_LITERAL=35, INT=36, STRING=37, 
		VOID=38, BOOL=39, IF=40, ELSE=41, FOR=42, WHILE=43, RETURN=44, BREAK=45, 
		CONTINUE=46, NEW=47, CLASS=48, CSTRING=49, ID=50, CINT=51, WS=52, BLOCK_COMMENT=53, 
		LINE_COMMENT=54;
	public static final int
		RULE_program = 0, RULE_varDef = 1, RULE_funcDef = 2, RULE_paraList = 3, 
		RULE_classDef = 4, RULE_constructDef = 5, RULE_para = 6, RULE_baseType = 7, 
		RULE_atomType = 8, RULE_exType = 9, RULE_block = 10, RULE_stmt = 11, RULE_empty = 12, 
		RULE_exprList = 13, RULE_expr = 14, RULE_newObject = 15, RULE_funcCall = 16, 
		RULE_literal = 17;
	public static final String[] ruleNames = {
		"program", "varDef", "funcDef", "paraList", "classDef", "constructDef", 
		"para", "baseType", "atomType", "exType", "block", "stmt", "empty", "exprList", 
		"expr", "newObject", "funcCall", "literal"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'='", "';'", "'('", "')'", "','", "'{'", "'}'", "'['", "']'", "'this'", 
		"'.'", "'++'", "'--'", "'+'", "'-'", "'~'", "'!'", "'*'", "'/'", "'%'", 
		"'>>'", "'<<'", "'<'", "'>'", "'>='", "'<='", "'=='", "'!='", "'&'", "'^'", 
		"'|'", "'&&'", "'||'", null, "'null'", "'int'", "'string'", "'void'", 
		"'bool'", "'if'", "'else'", "'for'", "'while'", "'return'", "'break'", 
		"'continue'", "'new'", "'class'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, "BOOL_LITERAL", 
		"NULL_LITERAL", "INT", "STRING", "VOID", "BOOL", "IF", "ELSE", "FOR", 
		"WHILE", "RETURN", "BREAK", "CONTINUE", "NEW", "CLASS", "CSTRING", "ID", 
		"CINT", "WS", "BLOCK_COMMENT", "LINE_COMMENT"
	};
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
	public String getGrammarFileName() { return "Mxstar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MxstarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(MxstarParser.EOF, 0); }
		public List<VarDefContext> varDef() {
			return getRuleContexts(VarDefContext.class);
		}
		public VarDefContext varDef(int i) {
			return getRuleContext(VarDefContext.class,i);
		}
		public List<FuncDefContext> funcDef() {
			return getRuleContexts(FuncDefContext.class);
		}
		public FuncDefContext funcDef(int i) {
			return getRuleContext(FuncDefContext.class,i);
		}
		public List<ClassDefContext> classDef() {
			return getRuleContexts(ClassDefContext.class);
		}
		public ClassDefContext classDef(int i) {
			return getRuleContext(ClassDefContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << STRING) | (1L << VOID) | (1L << BOOL) | (1L << CLASS) | (1L << ID))) != 0)) {
				{
				setState(39);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(36);
					varDef();
					}
					break;
				case 2:
					{
					setState(37);
					funcDef();
					}
					break;
				case 3:
					{
					setState(38);
					classDef();
					}
					break;
				}
				}
				setState(43);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(44);
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

	public static class VarDefContext extends ParserRuleContext {
		public ExTypeContext exType() {
			return getRuleContext(ExTypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(MxstarParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public VarDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDef; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitVarDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDefContext varDef() throws RecognitionException {
		VarDefContext _localctx = new VarDefContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_varDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			exType();
			setState(47);
			match(ID);
			setState(50);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(48);
				match(T__0);
				setState(49);
				expr(0);
				}
			}

			setState(52);
			match(T__1);
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

	public static class FuncDefContext extends ParserRuleContext {
		public ExTypeContext result;
		public Token name;
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ExTypeContext exType() {
			return getRuleContext(ExTypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(MxstarParser.ID, 0); }
		public ParaListContext paraList() {
			return getRuleContext(ParaListContext.class,0);
		}
		public FuncDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcDef; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitFuncDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncDefContext funcDef() throws RecognitionException {
		FuncDefContext _localctx = new FuncDefContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_funcDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			((FuncDefContext)_localctx).result = exType();
			setState(55);
			((FuncDefContext)_localctx).name = match(ID);
			setState(56);
			match(T__2);
			setState(58);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << STRING) | (1L << VOID) | (1L << BOOL) | (1L << ID))) != 0)) {
				{
				setState(57);
				paraList();
				}
			}

			setState(60);
			match(T__3);
			setState(61);
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

	public static class ParaListContext extends ParserRuleContext {
		public List<ParaContext> para() {
			return getRuleContexts(ParaContext.class);
		}
		public ParaContext para(int i) {
			return getRuleContext(ParaContext.class,i);
		}
		public ParaListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paraList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitParaList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParaListContext paraList() throws RecognitionException {
		ParaListContext _localctx = new ParaListContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_paraList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
			para();
			setState(68);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(64);
				match(T__4);
				setState(65);
				para();
				}
				}
				setState(70);
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

	public static class ClassDefContext extends ParserRuleContext {
		public Token name;
		public TerminalNode CLASS() { return getToken(MxstarParser.CLASS, 0); }
		public TerminalNode ID() { return getToken(MxstarParser.ID, 0); }
		public List<FuncDefContext> funcDef() {
			return getRuleContexts(FuncDefContext.class);
		}
		public FuncDefContext funcDef(int i) {
			return getRuleContext(FuncDefContext.class,i);
		}
		public List<ConstructDefContext> constructDef() {
			return getRuleContexts(ConstructDefContext.class);
		}
		public ConstructDefContext constructDef(int i) {
			return getRuleContext(ConstructDefContext.class,i);
		}
		public List<VarDefContext> varDef() {
			return getRuleContexts(VarDefContext.class);
		}
		public VarDefContext varDef(int i) {
			return getRuleContext(VarDefContext.class,i);
		}
		public ClassDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDef; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitClassDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassDefContext classDef() throws RecognitionException {
		ClassDefContext _localctx = new ClassDefContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_classDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71);
			match(CLASS);
			setState(72);
			((ClassDefContext)_localctx).name = match(ID);
			setState(73);
			match(T__5);
			setState(79);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << STRING) | (1L << VOID) | (1L << BOOL) | (1L << ID))) != 0)) {
				{
				setState(77);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
				case 1:
					{
					setState(74);
					funcDef();
					}
					break;
				case 2:
					{
					setState(75);
					constructDef();
					}
					break;
				case 3:
					{
					setState(76);
					varDef();
					}
					break;
				}
				}
				setState(81);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(82);
			match(T__6);
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

	public static class ConstructDefContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(MxstarParser.ID, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ParaListContext paraList() {
			return getRuleContext(ParaListContext.class,0);
		}
		public ConstructDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constructDef; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitConstructDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstructDefContext constructDef() throws RecognitionException {
		ConstructDefContext _localctx = new ConstructDefContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_constructDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			match(ID);
			setState(85);
			match(T__2);
			setState(87);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << STRING) | (1L << VOID) | (1L << BOOL) | (1L << ID))) != 0)) {
				{
				setState(86);
				paraList();
				}
			}

			setState(89);
			match(T__3);
			setState(90);
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

	public static class ParaContext extends ParserRuleContext {
		public ExTypeContext exType() {
			return getRuleContext(ExTypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(MxstarParser.ID, 0); }
		public ParaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_para; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitPara(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParaContext para() throws RecognitionException {
		ParaContext _localctx = new ParaContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_para);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			exType();
			setState(93);
			match(ID);
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

	public static class BaseTypeContext extends ParserRuleContext {
		public Token type;
		public TerminalNode BOOL() { return getToken(MxstarParser.BOOL, 0); }
		public TerminalNode INT() { return getToken(MxstarParser.INT, 0); }
		public TerminalNode VOID() { return getToken(MxstarParser.VOID, 0); }
		public BaseTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_baseType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitBaseType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BaseTypeContext baseType() throws RecognitionException {
		BaseTypeContext _localctx = new BaseTypeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_baseType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			((BaseTypeContext)_localctx).type = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << VOID) | (1L << BOOL))) != 0)) ) {
				((BaseTypeContext)_localctx).type = (Token)_errHandler.recoverInline(this);
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

	public static class AtomTypeContext extends ParserRuleContext {
		public Token type;
		public BaseTypeContext baseType() {
			return getRuleContext(BaseTypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(MxstarParser.ID, 0); }
		public TerminalNode STRING() { return getToken(MxstarParser.STRING, 0); }
		public AtomTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atomType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitAtomType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomTypeContext atomType() throws RecognitionException {
		AtomTypeContext _localctx = new AtomTypeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_atomType);
		try {
			setState(100);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
			case VOID:
			case BOOL:
				enterOuterAlt(_localctx, 1);
				{
				setState(97);
				baseType();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(98);
				((AtomTypeContext)_localctx).type = match(ID);
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 3);
				{
				setState(99);
				((AtomTypeContext)_localctx).type = match(STRING);
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

	public static class ExTypeContext extends ParserRuleContext {
		public AtomTypeContext atomType() {
			return getRuleContext(AtomTypeContext.class,0);
		}
		public List<EmptyContext> empty() {
			return getRuleContexts(EmptyContext.class);
		}
		public EmptyContext empty(int i) {
			return getRuleContext(EmptyContext.class,i);
		}
		public ExTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitExType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExTypeContext exType() throws RecognitionException {
		ExTypeContext _localctx = new ExTypeContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_exType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			atomType();
			setState(109);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__7) {
				{
				{
				setState(103);
				match(T__7);
				setState(104);
				empty();
				setState(105);
				match(T__8);
				}
				}
				setState(111);
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

	public static class BlockContext extends ParserRuleContext {
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			match(T__5);
			setState(116);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__5) | (1L << T__9) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << BOOL_LITERAL) | (1L << NULL_LITERAL) | (1L << INT) | (1L << STRING) | (1L << VOID) | (1L << BOOL) | (1L << IF) | (1L << FOR) | (1L << WHILE) | (1L << RETURN) | (1L << BREAK) | (1L << CONTINUE) | (1L << NEW) | (1L << CSTRING) | (1L << ID) | (1L << CINT))) != 0)) {
				{
				{
				setState(113);
				stmt();
				}
				}
				setState(118);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(119);
			match(T__6);
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

	public static class StmtContext extends ParserRuleContext {
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
	 
		public StmtContext() { }
		public void copyFrom(StmtContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class PassStmtContext extends StmtContext {
		public PassStmtContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitPassStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprStmtContext extends StmtContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ExprStmtContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitExprStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VarDefStmtContext extends StmtContext {
		public VarDefContext varDef() {
			return getRuleContext(VarDefContext.class,0);
		}
		public VarDefStmtContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitVarDefStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ForStmtContext extends StmtContext {
		public ExprContext init;
		public ExprContext cond;
		public ExprContext outit;
		public TerminalNode FOR() { return getToken(MxstarParser.FOR, 0); }
		public StmtContext stmt() {
			return getRuleContext(StmtContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ForStmtContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitForStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class WhileStmtContext extends StmtContext {
		public TerminalNode WHILE() { return getToken(MxstarParser.WHILE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public StmtContext stmt() {
			return getRuleContext(StmtContext.class,0);
		}
		public WhileStmtContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitWhileStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfStmtContext extends StmtContext {
		public TerminalNode IF() { return getToken(MxstarParser.IF, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(MxstarParser.ELSE, 0); }
		public IfStmtContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitIfStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BlockStmtContext extends StmtContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public BlockStmtContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitBlockStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BreakStmtContext extends StmtContext {
		public TerminalNode BREAK() { return getToken(MxstarParser.BREAK, 0); }
		public BreakStmtContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitBreakStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ReturnStmtContext extends StmtContext {
		public TerminalNode RETURN() { return getToken(MxstarParser.RETURN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ReturnStmtContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitReturnStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ContinueStmtContext extends StmtContext {
		public TerminalNode CONTINUE() { return getToken(MxstarParser.CONTINUE, 0); }
		public ContinueStmtContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitContinueStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_stmt);
		int _la;
		try {
			setState(166);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				_localctx = new BlockStmtContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(121);
				block();
				}
				break;
			case 2:
				_localctx = new ExprStmtContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(122);
				expr(0);
				setState(123);
				match(T__1);
				}
				break;
			case 3:
				_localctx = new VarDefStmtContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(125);
				varDef();
				}
				break;
			case 4:
				_localctx = new IfStmtContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(126);
				match(IF);
				setState(127);
				match(T__2);
				setState(128);
				expr(0);
				setState(129);
				match(T__3);
				setState(130);
				stmt();
				setState(133);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
				case 1:
					{
					setState(131);
					match(ELSE);
					setState(132);
					stmt();
					}
					break;
				}
				}
				break;
			case 5:
				_localctx = new ForStmtContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(135);
				match(FOR);
				setState(136);
				match(T__2);
				setState(138);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__9) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << BOOL_LITERAL) | (1L << NULL_LITERAL) | (1L << NEW) | (1L << CSTRING) | (1L << ID) | (1L << CINT))) != 0)) {
					{
					setState(137);
					((ForStmtContext)_localctx).init = expr(0);
					}
				}

				setState(140);
				match(T__1);
				setState(142);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__9) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << BOOL_LITERAL) | (1L << NULL_LITERAL) | (1L << NEW) | (1L << CSTRING) | (1L << ID) | (1L << CINT))) != 0)) {
					{
					setState(141);
					((ForStmtContext)_localctx).cond = expr(0);
					}
				}

				setState(144);
				match(T__1);
				setState(146);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__9) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << BOOL_LITERAL) | (1L << NULL_LITERAL) | (1L << NEW) | (1L << CSTRING) | (1L << ID) | (1L << CINT))) != 0)) {
					{
					setState(145);
					((ForStmtContext)_localctx).outit = expr(0);
					}
				}

				setState(148);
				match(T__3);
				setState(149);
				stmt();
				}
				break;
			case 6:
				_localctx = new WhileStmtContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(150);
				match(WHILE);
				setState(151);
				match(T__2);
				setState(152);
				expr(0);
				setState(153);
				match(T__3);
				setState(154);
				stmt();
				}
				break;
			case 7:
				_localctx = new ReturnStmtContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(156);
				match(RETURN);
				setState(158);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__9) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << BOOL_LITERAL) | (1L << NULL_LITERAL) | (1L << NEW) | (1L << CSTRING) | (1L << ID) | (1L << CINT))) != 0)) {
					{
					setState(157);
					expr(0);
					}
				}

				setState(160);
				match(T__1);
				}
				break;
			case 8:
				_localctx = new BreakStmtContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(161);
				match(BREAK);
				setState(162);
				match(T__1);
				}
				break;
			case 9:
				_localctx = new ContinueStmtContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(163);
				match(CONTINUE);
				setState(164);
				match(T__1);
				}
				break;
			case 10:
				_localctx = new PassStmtContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(165);
				match(T__1);
				}
				break;
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

	public static class EmptyContext extends ParserRuleContext {
		public EmptyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_empty; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitEmpty(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EmptyContext empty() throws RecognitionException {
		EmptyContext _localctx = new EmptyContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_empty);
		try {
			enterOuterAlt(_localctx, 1);
			{
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

	public static class ExprListContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ExprListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitExprList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprListContext exprList() throws RecognitionException {
		ExprListContext _localctx = new ExprListContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_exprList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(170);
			expr(0);
			setState(175);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(171);
				match(T__4);
				setState(172);
				expr(0);
				}
				}
				setState(177);
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

	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class NewExprContext extends ExprContext {
		public TerminalNode NEW() { return getToken(MxstarParser.NEW, 0); }
		public NewObjectContext newObject() {
			return getRuleContext(NewObjectContext.class,0);
		}
		public NewExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitNewExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ThisExprContext extends ExprContext {
		public Token token;
		public ThisExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitThisExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VarExprContext extends ExprContext {
		public Token token;
		public TerminalNode ID() { return getToken(MxstarParser.ID, 0); }
		public VarExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitVarExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PrefixExprContext extends ExprContext {
		public Token op;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public PrefixExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitPrefixExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ArrayExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitArrayExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LiteralExprContext extends ExprContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public LiteralExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitLiteralExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MemberExprContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode ID() { return getToken(MxstarParser.ID, 0); }
		public FuncCallContext funcCall() {
			return getRuleContext(FuncCallContext.class,0);
		}
		public MemberExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitMemberExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SuffixExprContext extends ExprContext {
		public Token op;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public SuffixExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitSuffixExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BinaryExprContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public BinaryExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitBinaryExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FuncCallExprContext extends ExprContext {
		public FuncCallContext funcCall() {
			return getRuleContext(FuncCallContext.class,0);
		}
		public FuncCallExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitFuncCallExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SubExprContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public SubExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitSubExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public AssignExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitAssignExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 28;
		enterRecursionRule(_localctx, 28, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(193);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				{
				_localctx = new SubExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(179);
				match(T__2);
				setState(180);
				expr(0);
				setState(181);
				match(T__3);
				}
				break;
			case 2:
				{
				_localctx = new ThisExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(183);
				((ThisExprContext)_localctx).token = match(T__9);
				}
				break;
			case 3:
				{
				_localctx = new VarExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(184);
				((VarExprContext)_localctx).token = match(ID);
				}
				break;
			case 4:
				{
				_localctx = new FuncCallExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(185);
				funcCall();
				}
				break;
			case 5:
				{
				_localctx = new LiteralExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(186);
				literal();
				}
				break;
			case 6:
				{
				_localctx = new NewExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(187);
				match(NEW);
				setState(188);
				newObject();
				}
				break;
			case 7:
				{
				_localctx = new PrefixExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(189);
				((PrefixExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14))) != 0)) ) {
					((PrefixExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(190);
				expr(13);
				}
				break;
			case 8:
				{
				_localctx = new PrefixExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(191);
				((PrefixExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__15 || _la==T__16) ) {
					((PrefixExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(192);
				expr(12);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(243);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(241);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
					case 1:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(195);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(196);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__17) | (1L << T__18) | (1L << T__19))) != 0)) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(197);
						expr(12);
						}
						break;
					case 2:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(198);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(199);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__13 || _la==T__14) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(200);
						expr(11);
						}
						break;
					case 3:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(201);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(202);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__20 || _la==T__21) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(203);
						expr(10);
						}
						break;
					case 4:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(204);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(205);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25))) != 0)) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(206);
						expr(9);
						}
						break;
					case 5:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(207);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(208);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__26 || _la==T__27) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(209);
						expr(8);
						}
						break;
					case 6:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(210);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(211);
						((BinaryExprContext)_localctx).op = match(T__28);
						setState(212);
						expr(7);
						}
						break;
					case 7:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(213);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(214);
						((BinaryExprContext)_localctx).op = match(T__29);
						setState(215);
						expr(6);
						}
						break;
					case 8:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(216);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(217);
						((BinaryExprContext)_localctx).op = match(T__30);
						setState(218);
						expr(5);
						}
						break;
					case 9:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(219);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(220);
						((BinaryExprContext)_localctx).op = match(T__31);
						setState(221);
						expr(4);
						}
						break;
					case 10:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(222);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(223);
						((BinaryExprContext)_localctx).op = match(T__32);
						setState(224);
						expr(3);
						}
						break;
					case 11:
						{
						_localctx = new AssignExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(225);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(226);
						match(T__0);
						setState(227);
						expr(1);
						}
						break;
					case 12:
						{
						_localctx = new MemberExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(228);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(229);
						match(T__10);
						setState(232);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
						case 1:
							{
							setState(230);
							match(ID);
							}
							break;
						case 2:
							{
							setState(231);
							funcCall();
							}
							break;
						}
						}
						break;
					case 13:
						{
						_localctx = new ArrayExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(234);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(235);
						match(T__7);
						setState(236);
						expr(0);
						setState(237);
						match(T__8);
						}
						break;
					case 14:
						{
						_localctx = new SuffixExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(239);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(240);
						((SuffixExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__11 || _la==T__12) ) {
							((SuffixExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					}
					} 
				}
				setState(245);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class NewObjectContext extends ParserRuleContext {
		public AtomTypeContext atomType() {
			return getRuleContext(AtomTypeContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<EmptyContext> empty() {
			return getRuleContexts(EmptyContext.class);
		}
		public EmptyContext empty(int i) {
			return getRuleContext(EmptyContext.class,i);
		}
		public NewObjectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_newObject; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitNewObject(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NewObjectContext newObject() throws RecognitionException {
		NewObjectContext _localctx = new NewObjectContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_newObject);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(246);
			atomType();
			setState(267);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				{
				setState(247);
				match(T__2);
				setState(248);
				match(T__3);
				}
				break;
			case 2:
				{
				setState(255);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(249);
						match(T__7);
						setState(250);
						expr(0);
						setState(251);
						match(T__8);
						}
						} 
					}
					setState(257);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
				}
				setState(264);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(258);
						match(T__7);
						setState(259);
						empty();
						setState(260);
						match(T__8);
						}
						} 
					}
					setState(266);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
				}
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

	public static class FuncCallContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(MxstarParser.ID, 0); }
		public ExprListContext exprList() {
			return getRuleContext(ExprListContext.class,0);
		}
		public FuncCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcCall; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitFuncCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncCallContext funcCall() throws RecognitionException {
		FuncCallContext _localctx = new FuncCallContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_funcCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(269);
			match(ID);
			setState(270);
			match(T__2);
			setState(272);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__9) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << BOOL_LITERAL) | (1L << NULL_LITERAL) | (1L << NEW) | (1L << CSTRING) | (1L << ID) | (1L << CINT))) != 0)) {
				{
				setState(271);
				exprList();
				}
			}

			setState(274);
			match(T__3);
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

	public static class LiteralContext extends ParserRuleContext {
		public Token token;
		public TerminalNode CINT() { return getToken(MxstarParser.CINT, 0); }
		public TerminalNode CSTRING() { return getToken(MxstarParser.CSTRING, 0); }
		public TerminalNode BOOL_LITERAL() { return getToken(MxstarParser.BOOL_LITERAL, 0); }
		public TerminalNode NULL_LITERAL() { return getToken(MxstarParser.NULL_LITERAL, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_literal);
		try {
			setState(280);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CINT:
				enterOuterAlt(_localctx, 1);
				{
				setState(276);
				((LiteralContext)_localctx).token = match(CINT);
				}
				break;
			case CSTRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(277);
				((LiteralContext)_localctx).token = match(CSTRING);
				}
				break;
			case BOOL_LITERAL:
				enterOuterAlt(_localctx, 3);
				{
				setState(278);
				((LiteralContext)_localctx).token = match(BOOL_LITERAL);
				}
				break;
			case NULL_LITERAL:
				enterOuterAlt(_localctx, 4);
				{
				setState(279);
				((LiteralContext)_localctx).token = match(NULL_LITERAL);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 14:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 11);
		case 1:
			return precpred(_ctx, 10);
		case 2:
			return precpred(_ctx, 9);
		case 3:
			return precpred(_ctx, 8);
		case 4:
			return precpred(_ctx, 7);
		case 5:
			return precpred(_ctx, 6);
		case 6:
			return precpred(_ctx, 5);
		case 7:
			return precpred(_ctx, 4);
		case 8:
			return precpred(_ctx, 3);
		case 9:
			return precpred(_ctx, 2);
		case 10:
			return precpred(_ctx, 1);
		case 11:
			return precpred(_ctx, 17);
		case 12:
			return precpred(_ctx, 15);
		case 13:
			return precpred(_ctx, 14);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\38\u011d\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\3\2\3\2\3\2\7\2*\n\2\f\2\16\2-\13\2\3\2\3\2\3\3\3\3\3\3\3\3"+
		"\5\3\65\n\3\3\3\3\3\3\4\3\4\3\4\3\4\5\4=\n\4\3\4\3\4\3\4\3\5\3\5\3\5\7"+
		"\5E\n\5\f\5\16\5H\13\5\3\6\3\6\3\6\3\6\3\6\3\6\7\6P\n\6\f\6\16\6S\13\6"+
		"\3\6\3\6\3\7\3\7\3\7\5\7Z\n\7\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\n\3\n"+
		"\3\n\5\ng\n\n\3\13\3\13\3\13\3\13\3\13\7\13n\n\13\f\13\16\13q\13\13\3"+
		"\f\3\f\7\fu\n\f\f\f\16\fx\13\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\5\r\u0088\n\r\3\r\3\r\3\r\5\r\u008d\n\r\3\r\3\r\5\r\u0091"+
		"\n\r\3\r\3\r\5\r\u0095\n\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r"+
		"\u00a1\n\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u00a9\n\r\3\16\3\16\3\17\3\17\3"+
		"\17\7\17\u00b0\n\17\f\17\16\17\u00b3\13\17\3\20\3\20\3\20\3\20\3\20\3"+
		"\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u00c4\n\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u00eb\n\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\7\20\u00f4\n\20\f\20\16\20\u00f7\13\20\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\7\21\u0100\n\21\f\21\16\21\u0103\13\21\3\21"+
		"\3\21\3\21\3\21\7\21\u0109\n\21\f\21\16\21\u010c\13\21\5\21\u010e\n\21"+
		"\3\22\3\22\3\22\5\22\u0113\n\22\3\22\3\22\3\23\3\23\3\23\3\23\5\23\u011b"+
		"\n\23\3\23\2\3\36\24\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$\2\13\4"+
		"\2&&()\3\2\16\21\3\2\22\23\3\2\24\26\3\2\20\21\3\2\27\30\3\2\31\34\3\2"+
		"\35\36\3\2\16\17\2\u0144\2+\3\2\2\2\4\60\3\2\2\2\68\3\2\2\2\bA\3\2\2\2"+
		"\nI\3\2\2\2\fV\3\2\2\2\16^\3\2\2\2\20a\3\2\2\2\22f\3\2\2\2\24h\3\2\2\2"+
		"\26r\3\2\2\2\30\u00a8\3\2\2\2\32\u00aa\3\2\2\2\34\u00ac\3\2\2\2\36\u00c3"+
		"\3\2\2\2 \u00f8\3\2\2\2\"\u010f\3\2\2\2$\u011a\3\2\2\2&*\5\4\3\2\'*\5"+
		"\6\4\2(*\5\n\6\2)&\3\2\2\2)\'\3\2\2\2)(\3\2\2\2*-\3\2\2\2+)\3\2\2\2+,"+
		"\3\2\2\2,.\3\2\2\2-+\3\2\2\2./\7\2\2\3/\3\3\2\2\2\60\61\5\24\13\2\61\64"+
		"\7\64\2\2\62\63\7\3\2\2\63\65\5\36\20\2\64\62\3\2\2\2\64\65\3\2\2\2\65"+
		"\66\3\2\2\2\66\67\7\4\2\2\67\5\3\2\2\289\5\24\13\29:\7\64\2\2:<\7\5\2"+
		"\2;=\5\b\5\2<;\3\2\2\2<=\3\2\2\2=>\3\2\2\2>?\7\6\2\2?@\5\26\f\2@\7\3\2"+
		"\2\2AF\5\16\b\2BC\7\7\2\2CE\5\16\b\2DB\3\2\2\2EH\3\2\2\2FD\3\2\2\2FG\3"+
		"\2\2\2G\t\3\2\2\2HF\3\2\2\2IJ\7\62\2\2JK\7\64\2\2KQ\7\b\2\2LP\5\6\4\2"+
		"MP\5\f\7\2NP\5\4\3\2OL\3\2\2\2OM\3\2\2\2ON\3\2\2\2PS\3\2\2\2QO\3\2\2\2"+
		"QR\3\2\2\2RT\3\2\2\2SQ\3\2\2\2TU\7\t\2\2U\13\3\2\2\2VW\7\64\2\2WY\7\5"+
		"\2\2XZ\5\b\5\2YX\3\2\2\2YZ\3\2\2\2Z[\3\2\2\2[\\\7\6\2\2\\]\5\26\f\2]\r"+
		"\3\2\2\2^_\5\24\13\2_`\7\64\2\2`\17\3\2\2\2ab\t\2\2\2b\21\3\2\2\2cg\5"+
		"\20\t\2dg\7\64\2\2eg\7\'\2\2fc\3\2\2\2fd\3\2\2\2fe\3\2\2\2g\23\3\2\2\2"+
		"ho\5\22\n\2ij\7\n\2\2jk\5\32\16\2kl\7\13\2\2ln\3\2\2\2mi\3\2\2\2nq\3\2"+
		"\2\2om\3\2\2\2op\3\2\2\2p\25\3\2\2\2qo\3\2\2\2rv\7\b\2\2su\5\30\r\2ts"+
		"\3\2\2\2ux\3\2\2\2vt\3\2\2\2vw\3\2\2\2wy\3\2\2\2xv\3\2\2\2yz\7\t\2\2z"+
		"\27\3\2\2\2{\u00a9\5\26\f\2|}\5\36\20\2}~\7\4\2\2~\u00a9\3\2\2\2\177\u00a9"+
		"\5\4\3\2\u0080\u0081\7*\2\2\u0081\u0082\7\5\2\2\u0082\u0083\5\36\20\2"+
		"\u0083\u0084\7\6\2\2\u0084\u0087\5\30\r\2\u0085\u0086\7+\2\2\u0086\u0088"+
		"\5\30\r\2\u0087\u0085\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u00a9\3\2\2\2"+
		"\u0089\u008a\7,\2\2\u008a\u008c\7\5\2\2\u008b\u008d\5\36\20\2\u008c\u008b"+
		"\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008e\3\2\2\2\u008e\u0090\7\4\2\2\u008f"+
		"\u0091\5\36\20\2\u0090\u008f\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u0092\3"+
		"\2\2\2\u0092\u0094\7\4\2\2\u0093\u0095\5\36\20\2\u0094\u0093\3\2\2\2\u0094"+
		"\u0095\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u0097\7\6\2\2\u0097\u00a9\5\30"+
		"\r\2\u0098\u0099\7-\2\2\u0099\u009a\7\5\2\2\u009a\u009b\5\36\20\2\u009b"+
		"\u009c\7\6\2\2\u009c\u009d\5\30\r\2\u009d\u00a9\3\2\2\2\u009e\u00a0\7"+
		".\2\2\u009f\u00a1\5\36\20\2\u00a0\u009f\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1"+
		"\u00a2\3\2\2\2\u00a2\u00a9\7\4\2\2\u00a3\u00a4\7/\2\2\u00a4\u00a9\7\4"+
		"\2\2\u00a5\u00a6\7\60\2\2\u00a6\u00a9\7\4\2\2\u00a7\u00a9\7\4\2\2\u00a8"+
		"{\3\2\2\2\u00a8|\3\2\2\2\u00a8\177\3\2\2\2\u00a8\u0080\3\2\2\2\u00a8\u0089"+
		"\3\2\2\2\u00a8\u0098\3\2\2\2\u00a8\u009e\3\2\2\2\u00a8\u00a3\3\2\2\2\u00a8"+
		"\u00a5\3\2\2\2\u00a8\u00a7\3\2\2\2\u00a9\31\3\2\2\2\u00aa\u00ab\3\2\2"+
		"\2\u00ab\33\3\2\2\2\u00ac\u00b1\5\36\20\2\u00ad\u00ae\7\7\2\2\u00ae\u00b0"+
		"\5\36\20\2\u00af\u00ad\3\2\2\2\u00b0\u00b3\3\2\2\2\u00b1\u00af\3\2\2\2"+
		"\u00b1\u00b2\3\2\2\2\u00b2\35\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b4\u00b5"+
		"\b\20\1\2\u00b5\u00b6\7\5\2\2\u00b6\u00b7\5\36\20\2\u00b7\u00b8\7\6\2"+
		"\2\u00b8\u00c4\3\2\2\2\u00b9\u00c4\7\f\2\2\u00ba\u00c4\7\64\2\2\u00bb"+
		"\u00c4\5\"\22\2\u00bc\u00c4\5$\23\2\u00bd\u00be\7\61\2\2\u00be\u00c4\5"+
		" \21\2\u00bf\u00c0\t\3\2\2\u00c0\u00c4\5\36\20\17\u00c1\u00c2\t\4\2\2"+
		"\u00c2\u00c4\5\36\20\16\u00c3\u00b4\3\2\2\2\u00c3\u00b9\3\2\2\2\u00c3"+
		"\u00ba\3\2\2\2\u00c3\u00bb\3\2\2\2\u00c3\u00bc\3\2\2\2\u00c3\u00bd\3\2"+
		"\2\2\u00c3\u00bf\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c4\u00f5\3\2\2\2\u00c5"+
		"\u00c6\f\r\2\2\u00c6\u00c7\t\5\2\2\u00c7\u00f4\5\36\20\16\u00c8\u00c9"+
		"\f\f\2\2\u00c9\u00ca\t\6\2\2\u00ca\u00f4\5\36\20\r\u00cb\u00cc\f\13\2"+
		"\2\u00cc\u00cd\t\7\2\2\u00cd\u00f4\5\36\20\f\u00ce\u00cf\f\n\2\2\u00cf"+
		"\u00d0\t\b\2\2\u00d0\u00f4\5\36\20\13\u00d1\u00d2\f\t\2\2\u00d2\u00d3"+
		"\t\t\2\2\u00d3\u00f4\5\36\20\n\u00d4\u00d5\f\b\2\2\u00d5\u00d6\7\37\2"+
		"\2\u00d6\u00f4\5\36\20\t\u00d7\u00d8\f\7\2\2\u00d8\u00d9\7 \2\2\u00d9"+
		"\u00f4\5\36\20\b\u00da\u00db\f\6\2\2\u00db\u00dc\7!\2\2\u00dc\u00f4\5"+
		"\36\20\7\u00dd\u00de\f\5\2\2\u00de\u00df\7\"\2\2\u00df\u00f4\5\36\20\6"+
		"\u00e0\u00e1\f\4\2\2\u00e1\u00e2\7#\2\2\u00e2\u00f4\5\36\20\5\u00e3\u00e4"+
		"\f\3\2\2\u00e4\u00e5\7\3\2\2\u00e5\u00f4\5\36\20\3\u00e6\u00e7\f\23\2"+
		"\2\u00e7\u00ea\7\r\2\2\u00e8\u00eb\7\64\2\2\u00e9\u00eb\5\"\22\2\u00ea"+
		"\u00e8\3\2\2\2\u00ea\u00e9\3\2\2\2\u00eb\u00f4\3\2\2\2\u00ec\u00ed\f\21"+
		"\2\2\u00ed\u00ee\7\n\2\2\u00ee\u00ef\5\36\20\2\u00ef\u00f0\7\13\2\2\u00f0"+
		"\u00f4\3\2\2\2\u00f1\u00f2\f\20\2\2\u00f2\u00f4\t\n\2\2\u00f3\u00c5\3"+
		"\2\2\2\u00f3\u00c8\3\2\2\2\u00f3\u00cb\3\2\2\2\u00f3\u00ce\3\2\2\2\u00f3"+
		"\u00d1\3\2\2\2\u00f3\u00d4\3\2\2\2\u00f3\u00d7\3\2\2\2\u00f3\u00da\3\2"+
		"\2\2\u00f3\u00dd\3\2\2\2\u00f3\u00e0\3\2\2\2\u00f3\u00e3\3\2\2\2\u00f3"+
		"\u00e6\3\2\2\2\u00f3\u00ec\3\2\2\2\u00f3\u00f1\3\2\2\2\u00f4\u00f7\3\2"+
		"\2\2\u00f5\u00f3\3\2\2\2\u00f5\u00f6\3\2\2\2\u00f6\37\3\2\2\2\u00f7\u00f5"+
		"\3\2\2\2\u00f8\u010d\5\22\n\2\u00f9\u00fa\7\5\2\2\u00fa\u010e\7\6\2\2"+
		"\u00fb\u00fc\7\n\2\2\u00fc\u00fd\5\36\20\2\u00fd\u00fe\7\13\2\2\u00fe"+
		"\u0100\3\2\2\2\u00ff\u00fb\3\2\2\2\u0100\u0103\3\2\2\2\u0101\u00ff\3\2"+
		"\2\2\u0101\u0102\3\2\2\2\u0102\u010a\3\2\2\2\u0103\u0101\3\2\2\2\u0104"+
		"\u0105\7\n\2\2\u0105\u0106\5\32\16\2\u0106\u0107\7\13\2\2\u0107\u0109"+
		"\3\2\2\2\u0108\u0104\3\2\2\2\u0109\u010c\3\2\2\2\u010a\u0108\3\2\2\2\u010a"+
		"\u010b\3\2\2\2\u010b\u010e\3\2\2\2\u010c\u010a\3\2\2\2\u010d\u00f9\3\2"+
		"\2\2\u010d\u0101\3\2\2\2\u010e!\3\2\2\2\u010f\u0110\7\64\2\2\u0110\u0112"+
		"\7\5\2\2\u0111\u0113\5\34\17\2\u0112\u0111\3\2\2\2\u0112\u0113\3\2\2\2"+
		"\u0113\u0114\3\2\2\2\u0114\u0115\7\6\2\2\u0115#\3\2\2\2\u0116\u011b\7"+
		"\65\2\2\u0117\u011b\7\63\2\2\u0118\u011b\7$\2\2\u0119\u011b\7%\2\2\u011a"+
		"\u0116\3\2\2\2\u011a\u0117\3\2\2\2\u011a\u0118\3\2\2\2\u011a\u0119\3\2"+
		"\2\2\u011b%\3\2\2\2\35)+\64<FOQYfov\u0087\u008c\u0090\u0094\u00a0\u00a8"+
		"\u00b1\u00c3\u00ea\u00f3\u00f5\u0101\u010a\u010d\u0112\u011a";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}