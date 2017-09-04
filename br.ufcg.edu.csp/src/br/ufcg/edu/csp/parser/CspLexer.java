// Generated from Csp.g4 by ANTLR 4.4
package br.ufcg.edu.csp.parser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CspLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		AND=1, OR=2, EQ=3, NEQ=4, LT=5, GT=6, LTEQ=7, GTEQ=8, NOT=9, PLUS=10, 
		TIMES=11, MINUS=12, DIV=13, MOD=14, IF=15, THEN=16, ELSE=17, Skip=18, 
		Stop=19, DIVERGE=20, TRUE=21, FALSE=22, GUARD=23, LPAREN=24, RPAREN=25, 
		LSYNC=26, RSYNC=27, INTL=28, ECHOICE=29, ICHOICE=30, COMMA=31, COLLON=32, 
		SEMICOL=33, ARROW=34, QUERY=35, PLING=36, CHANNEL=37, DOT=38, LBRACE=39, 
		RBRACE=40, EQUAL=41, BACKSLASH=42, TIMEOUT=43, INTR=44, DIGIT=45, ID=46, 
		LINECOMMENT=47, WS=48;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'", "'\\u000E'", "'\\u000F'", "'\\u0010'", "'\\u0011'", "'\\u0012'", 
		"'\\u0013'", "'\\u0014'", "'\\u0015'", "'\\u0016'", "'\\u0017'", "'\\u0018'", 
		"'\\u0019'", "'\\u001A'", "'\\u001B'", "'\\u001C'", "'\\u001D'", "'\\u001E'", 
		"'\\u001F'", "' '", "'!'", "'\"'", "'#'", "'$'", "'%'", "'&'", "'''", 
		"'('", "')'", "'*'", "'+'", "','", "'-'", "'.'", "'/'", "'0'"
	};
	public static final String[] ruleNames = {
		"AND", "OR", "EQ", "NEQ", "LT", "GT", "LTEQ", "GTEQ", "NOT", "PLUS", "TIMES", 
		"MINUS", "DIV", "MOD", "IF", "THEN", "ELSE", "Skip", "Stop", "DIVERGE", 
		"TRUE", "FALSE", "GUARD", "LPAREN", "RPAREN", "LSYNC", "RSYNC", "INTL", 
		"ECHOICE", "ICHOICE", "COMMA", "COLLON", "SEMICOL", "ARROW", "QUERY", 
		"PLING", "CHANNEL", "DOT", "LBRACE", "RBRACE", "EQUAL", "BACKSLASH", "TIMEOUT", 
		"INTR", "DIGIT", "ID", "LINECOMMENT", "WS"
	};

	// CRIADO POR IGOR BRASILEIRO
	public static final Vocabulary VOCABULARY = new VocabularyImpl(tokenNames, ruleNames);
	//----
	
	
	public CspLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Csp.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\62\u0103\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\3\2\3\2\3\2\3\2\3\3\3\3"+
		"\3\3\3\4\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3"+
		"\n\3\n\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20"+
		"\3\20\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23"+
		"\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\26\3\26\3\26"+
		"\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32"+
		"\3\33\3\33\3\33\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\37"+
		"\3\37\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3#\3$\3$\3%\3%\3&\3&\3&\3&\3"+
		"&\3&\3&\3&\3\'\3\'\3(\3(\3)\3)\3*\3*\3+\3+\3,\3,\3,\3-\3-\3-\3.\3.\3/"+
		"\6/\u00ee\n/\r/\16/\u00ef\3\60\3\60\3\60\3\60\7\60\u00f6\n\60\f\60\16"+
		"\60\u00f9\13\60\3\60\3\60\3\61\6\61\u00fe\n\61\r\61\16\61\u00ff\3\61\3"+
		"\61\2\2\62\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33"+
		"\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67"+
		"\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62\3\2\5\4\2C\\"+
		"c|\4\2\f\f\17\17\5\2\13\f\17\17\"\"\u0105\2\3\3\2\2\2\2\5\3\2\2\2\2\7"+
		"\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2"+
		"\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2"+
		"\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2"+
		"\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2"+
		"\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2"+
		"\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M"+
		"\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2"+
		"\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\3c\3\2\2\2\5g\3\2\2\2"+
		"\7j\3\2\2\2\tm\3\2\2\2\13p\3\2\2\2\rr\3\2\2\2\17t\3\2\2\2\21w\3\2\2\2"+
		"\23z\3\2\2\2\25~\3\2\2\2\27\u0080\3\2\2\2\31\u0082\3\2\2\2\33\u0084\3"+
		"\2\2\2\35\u0086\3\2\2\2\37\u0088\3\2\2\2!\u008b\3\2\2\2#\u0090\3\2\2\2"+
		"%\u0095\3\2\2\2\'\u009a\3\2\2\2)\u009f\3\2\2\2+\u00a3\3\2\2\2-\u00a8\3"+
		"\2\2\2/\u00ae\3\2\2\2\61\u00b0\3\2\2\2\63\u00b2\3\2\2\2\65\u00b4\3\2\2"+
		"\2\67\u00b7\3\2\2\29\u00ba\3\2\2\2;\u00be\3\2\2\2=\u00c1\3\2\2\2?\u00c5"+
		"\3\2\2\2A\u00c7\3\2\2\2C\u00c9\3\2\2\2E\u00cb\3\2\2\2G\u00ce\3\2\2\2I"+
		"\u00d0\3\2\2\2K\u00d2\3\2\2\2M\u00da\3\2\2\2O\u00dc\3\2\2\2Q\u00de\3\2"+
		"\2\2S\u00e0\3\2\2\2U\u00e2\3\2\2\2W\u00e4\3\2\2\2Y\u00e7\3\2\2\2[\u00ea"+
		"\3\2\2\2]\u00ed\3\2\2\2_\u00f1\3\2\2\2a\u00fd\3\2\2\2cd\7c\2\2de\7p\2"+
		"\2ef\7f\2\2f\4\3\2\2\2gh\7q\2\2hi\7t\2\2i\6\3\2\2\2jk\7?\2\2kl\7?\2\2"+
		"l\b\3\2\2\2mn\7#\2\2no\7?\2\2o\n\3\2\2\2pq\7>\2\2q\f\3\2\2\2rs\7@\2\2"+
		"s\16\3\2\2\2tu\7>\2\2uv\7?\2\2v\20\3\2\2\2wx\7@\2\2xy\7?\2\2y\22\3\2\2"+
		"\2z{\7p\2\2{|\7q\2\2|}\7v\2\2}\24\3\2\2\2~\177\7-\2\2\177\26\3\2\2\2\u0080"+
		"\u0081\7,\2\2\u0081\30\3\2\2\2\u0082\u0083\7/\2\2\u0083\32\3\2\2\2\u0084"+
		"\u0085\7\61\2\2\u0085\34\3\2\2\2\u0086\u0087\7\'\2\2\u0087\36\3\2\2\2"+
		"\u0088\u0089\7k\2\2\u0089\u008a\7h\2\2\u008a \3\2\2\2\u008b\u008c\7v\2"+
		"\2\u008c\u008d\7j\2\2\u008d\u008e\7g\2\2\u008e\u008f\7p\2\2\u008f\"\3"+
		"\2\2\2\u0090\u0091\7g\2\2\u0091\u0092\7n\2\2\u0092\u0093\7u\2\2\u0093"+
		"\u0094\7g\2\2\u0094$\3\2\2\2\u0095\u0096\7U\2\2\u0096\u0097\7M\2\2\u0097"+
		"\u0098\7K\2\2\u0098\u0099\7R\2\2\u0099&\3\2\2\2\u009a\u009b\7U\2\2\u009b"+
		"\u009c\7V\2\2\u009c\u009d\7Q\2\2\u009d\u009e\7R\2\2\u009e(\3\2\2\2\u009f"+
		"\u00a0\7f\2\2\u00a0\u00a1\7k\2\2\u00a1\u00a2\7x\2\2\u00a2*\3\2\2\2\u00a3"+
		"\u00a4\7v\2\2\u00a4\u00a5\7t\2\2\u00a5\u00a6\7w\2\2\u00a6\u00a7\7g\2\2"+
		"\u00a7,\3\2\2\2\u00a8\u00a9\7h\2\2\u00a9\u00aa\7c\2\2\u00aa\u00ab\7n\2"+
		"\2\u00ab\u00ac\7u\2\2\u00ac\u00ad\7g\2\2\u00ad.\3\2\2\2\u00ae\u00af\7"+
		"(\2\2\u00af\60\3\2\2\2\u00b0\u00b1\7*\2\2\u00b1\62\3\2\2\2\u00b2\u00b3"+
		"\7+\2\2\u00b3\64\3\2\2\2\u00b4\u00b5\7]\2\2\u00b5\u00b6\7~\2\2\u00b6\66"+
		"\3\2\2\2\u00b7\u00b8\7~\2\2\u00b8\u00b9\7_\2\2\u00b98\3\2\2\2\u00ba\u00bb"+
		"\7~\2\2\u00bb\u00bc\7~\2\2\u00bc\u00bd\7~\2\2\u00bd:\3\2\2\2\u00be\u00bf"+
		"\7]\2\2\u00bf\u00c0\7_\2\2\u00c0<\3\2\2\2\u00c1\u00c2\7~\2\2\u00c2\u00c3"+
		"\7\u0080\2\2\u00c3\u00c4\7~\2\2\u00c4>\3\2\2\2\u00c5\u00c6\7.\2\2\u00c6"+
		"@\3\2\2\2\u00c7\u00c8\7<\2\2\u00c8B\3\2\2\2\u00c9\u00ca\7=\2\2\u00caD"+
		"\3\2\2\2\u00cb\u00cc\7/\2\2\u00cc\u00cd\7@\2\2\u00cdF\3\2\2\2\u00ce\u00cf"+
		"\7A\2\2\u00cfH\3\2\2\2\u00d0\u00d1\7#\2\2\u00d1J\3\2\2\2\u00d2\u00d3\7"+
		"e\2\2\u00d3\u00d4\7j\2\2\u00d4\u00d5\7c\2\2\u00d5\u00d6\7p\2\2\u00d6\u00d7"+
		"\7p\2\2\u00d7\u00d8\7g\2\2\u00d8\u00d9\7n\2\2\u00d9L\3\2\2\2\u00da\u00db"+
		"\7\60\2\2\u00dbN\3\2\2\2\u00dc\u00dd\7}\2\2\u00ddP\3\2\2\2\u00de\u00df"+
		"\7\177\2\2\u00dfR\3\2\2\2\u00e0\u00e1\7?\2\2\u00e1T\3\2\2\2\u00e2\u00e3"+
		"\7^\2\2\u00e3V\3\2\2\2\u00e4\u00e5\7]\2\2\u00e5\u00e6\7@\2\2\u00e6X\3"+
		"\2\2\2\u00e7\u00e8\7\61\2\2\u00e8\u00e9\7^\2\2\u00e9Z\3\2\2\2\u00ea\u00eb"+
		"\4\62;\2\u00eb\\\3\2\2\2\u00ec\u00ee\t\2\2\2\u00ed\u00ec\3\2\2\2\u00ee"+
		"\u00ef\3\2\2\2\u00ef\u00ed\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0^\3\2\2\2"+
		"\u00f1\u00f2\7/\2\2\u00f2\u00f3\7/\2\2\u00f3\u00f7\3\2\2\2\u00f4\u00f6"+
		"\n\3\2\2\u00f5\u00f4\3\2\2\2\u00f6\u00f9\3\2\2\2\u00f7\u00f5\3\2\2\2\u00f7"+
		"\u00f8\3\2\2\2\u00f8\u00fa\3\2\2\2\u00f9\u00f7\3\2\2\2\u00fa\u00fb\b\60"+
		"\2\2\u00fb`\3\2\2\2\u00fc\u00fe\t\4\2\2\u00fd\u00fc\3\2\2\2\u00fe\u00ff"+
		"\3\2\2\2\u00ff\u00fd\3\2\2\2\u00ff\u0100\3\2\2\2\u0100\u0101\3\2\2\2\u0101"+
		"\u0102\b\61\2\2\u0102b\3\2\2\2\6\2\u00ef\u00f7\u00ff\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}