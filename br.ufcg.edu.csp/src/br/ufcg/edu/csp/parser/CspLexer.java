package br.ufcg.edu.csp.parser;

// Generated from Csp.g4 by ANTLR 4.7
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CspLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, AND=3, OR=4, EQ=5, NEQ=6, LT=7, GT=8, LTEQ=9, GTEQ=10, 
		NOT=11, PLUS=12, TIMES=13, MINUS=14, DIV=15, MOD=16, IF=17, THEN=18, ELSE=19, 
		Skip=20, Stop=21, DIVERGE=22, TRUE=23, FALSE=24, GUARD=25, LPAREN=26, 
		RPAREN=27, LSYNC=28, RSYNC=29, INTL=30, ECHOICE=31, ICHOICE=32, COMMA=33, 
		COLLON=34, SEMICOL=35, ARROW=36, QUERY=37, PLING=38, CHANNEL=39, DOT=40, 
		LBRACE=41, RBRACE=42, EQUAL=43, BACKSLASH=44, TIMEOUT=45, INTR=46, ASSERT=47, 
		DEADLOCK=48, DETERMINISTIC=49, DIVERGENCE=50, FAILUREDIVE=51, FAILURE=52, 
		TRACE=53, FREE=54, DIGIT=55, ID=56, LINECOMMENT=57, WS=58;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "AND", "OR", "EQ", "NEQ", "LT", "GT", "LTEQ", "GTEQ", 
		"NOT", "PLUS", "TIMES", "MINUS", "DIV", "MOD", "IF", "THEN", "ELSE", "Skip", 
		"Stop", "DIVERGE", "TRUE", "FALSE", "GUARD", "LPAREN", "RPAREN", "LSYNC", 
		"RSYNC", "INTL", "ECHOICE", "ICHOICE", "COMMA", "COLLON", "SEMICOL", "ARROW", 
		"QUERY", "PLING", "CHANNEL", "DOT", "LBRACE", "RBRACE", "EQUAL", "BACKSLASH", 
		"TIMEOUT", "INTR", "ASSERT", "DEADLOCK", "DETERMINISTIC", "DIVERGENCE", 
		"FAILUREDIVE", "FAILURE", "TRACE", "FREE", "DIGIT", "ID", "LINECOMMENT", 
		"WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "' :['", "']'", "'and'", "'or'", "'=='", "'!='", "'<'", "'>'", "'<='", 
		"'>='", "'not'", "'+'", "'*'", "'-'", "'/'", "'%'", "'if'", "'then'", 
		"'else'", "'SKIP'", "'STOP'", "'div'", "'true'", "'false'", "'&'", "'('", 
		"')'", "'[|'", "'|]'", "'|||'", "'[]'", "'|~|'", "','", "':'", "';'", 
		"'->'", "'?'", "'!'", "'channel'", "'.'", "'{'", "'}'", "'='", "'\\'", 
		"'[>'", "'/\\'", "'assert'", "'deadlock'", "'deterministic'", "'divergence'", 
		"' [FD]'", "' [F]'", "' [T]'", "' free'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, "AND", "OR", "EQ", "NEQ", "LT", "GT", "LTEQ", "GTEQ", 
		"NOT", "PLUS", "TIMES", "MINUS", "DIV", "MOD", "IF", "THEN", "ELSE", "Skip", 
		"Stop", "DIVERGE", "TRUE", "FALSE", "GUARD", "LPAREN", "RPAREN", "LSYNC", 
		"RSYNC", "INTL", "ECHOICE", "ICHOICE", "COMMA", "COLLON", "SEMICOL", "ARROW", 
		"QUERY", "PLING", "CHANNEL", "DOT", "LBRACE", "RBRACE", "EQUAL", "BACKSLASH", 
		"TIMEOUT", "INTR", "ASSERT", "DEADLOCK", "DETERMINISTIC", "DIVERGENCE", 
		"FAILUREDIVE", "FAILURE", "TRACE", "FREE", "DIGIT", "ID", "LINECOMMENT", 
		"WS"
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


	public CspLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Csp.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2<\u015c\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\3\2\3\2\3\2"+
		"\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3"+
		"\b\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\16\3\16"+
		"\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23"+
		"\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26"+
		"\3\26\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31"+
		"\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\35\3\36\3\36\3\36"+
		"\3\37\3\37\3\37\3\37\3 \3 \3 \3!\3!\3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3"+
		"%\3&\3&\3\'\3\'\3(\3(\3(\3(\3(\3(\3(\3(\3)\3)\3*\3*\3+\3+\3,\3,\3-\3-"+
		"\3.\3.\3.\3/\3/\3/\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\61\3\61\3\61\3"+
		"\61\3\61\3\61\3\61\3\61\3\61\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3"+
		"\62\3\62\3\62\3\62\3\62\3\62\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3"+
		"\63\3\63\3\63\3\64\3\64\3\64\3\64\3\64\3\64\3\65\3\65\3\65\3\65\3\65\3"+
		"\66\3\66\3\66\3\66\3\66\3\67\3\67\3\67\3\67\3\67\3\67\38\38\39\69\u0147"+
		"\n9\r9\169\u0148\3:\3:\3:\3:\7:\u014f\n:\f:\16:\u0152\13:\3:\3:\3;\6;"+
		"\u0157\n;\r;\16;\u0158\3;\3;\2\2<\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23"+
		"\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31"+
		"\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60"+
		"_\61a\62c\63e\64g\65i\66k\67m8o9q:s;u<\3\2\5\4\2C\\c|\4\2\f\f\17\17\5"+
		"\2\13\f\17\17\"\"\2\u015e\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2"+
		"\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2"+
		"\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3"+
		"\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2"+
		"\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67"+
		"\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2"+
		"\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2"+
		"\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]"+
		"\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2"+
		"\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2"+
		"\3w\3\2\2\2\5{\3\2\2\2\7}\3\2\2\2\t\u0081\3\2\2\2\13\u0084\3\2\2\2\r\u0087"+
		"\3\2\2\2\17\u008a\3\2\2\2\21\u008c\3\2\2\2\23\u008e\3\2\2\2\25\u0091\3"+
		"\2\2\2\27\u0094\3\2\2\2\31\u0098\3\2\2\2\33\u009a\3\2\2\2\35\u009c\3\2"+
		"\2\2\37\u009e\3\2\2\2!\u00a0\3\2\2\2#\u00a2\3\2\2\2%\u00a5\3\2\2\2\'\u00aa"+
		"\3\2\2\2)\u00af\3\2\2\2+\u00b4\3\2\2\2-\u00b9\3\2\2\2/\u00bd\3\2\2\2\61"+
		"\u00c2\3\2\2\2\63\u00c8\3\2\2\2\65\u00ca\3\2\2\2\67\u00cc\3\2\2\29\u00ce"+
		"\3\2\2\2;\u00d1\3\2\2\2=\u00d4\3\2\2\2?\u00d8\3\2\2\2A\u00db\3\2\2\2C"+
		"\u00df\3\2\2\2E\u00e1\3\2\2\2G\u00e3\3\2\2\2I\u00e5\3\2\2\2K\u00e8\3\2"+
		"\2\2M\u00ea\3\2\2\2O\u00ec\3\2\2\2Q\u00f4\3\2\2\2S\u00f6\3\2\2\2U\u00f8"+
		"\3\2\2\2W\u00fa\3\2\2\2Y\u00fc\3\2\2\2[\u00fe\3\2\2\2]\u0101\3\2\2\2_"+
		"\u0104\3\2\2\2a\u010b\3\2\2\2c\u0114\3\2\2\2e\u0122\3\2\2\2g\u012d\3\2"+
		"\2\2i\u0133\3\2\2\2k\u0138\3\2\2\2m\u013d\3\2\2\2o\u0143\3\2\2\2q\u0146"+
		"\3\2\2\2s\u014a\3\2\2\2u\u0156\3\2\2\2wx\7\"\2\2xy\7<\2\2yz\7]\2\2z\4"+
		"\3\2\2\2{|\7_\2\2|\6\3\2\2\2}~\7c\2\2~\177\7p\2\2\177\u0080\7f\2\2\u0080"+
		"\b\3\2\2\2\u0081\u0082\7q\2\2\u0082\u0083\7t\2\2\u0083\n\3\2\2\2\u0084"+
		"\u0085\7?\2\2\u0085\u0086\7?\2\2\u0086\f\3\2\2\2\u0087\u0088\7#\2\2\u0088"+
		"\u0089\7?\2\2\u0089\16\3\2\2\2\u008a\u008b\7>\2\2\u008b\20\3\2\2\2\u008c"+
		"\u008d\7@\2\2\u008d\22\3\2\2\2\u008e\u008f\7>\2\2\u008f\u0090\7?\2\2\u0090"+
		"\24\3\2\2\2\u0091\u0092\7@\2\2\u0092\u0093\7?\2\2\u0093\26\3\2\2\2\u0094"+
		"\u0095\7p\2\2\u0095\u0096\7q\2\2\u0096\u0097\7v\2\2\u0097\30\3\2\2\2\u0098"+
		"\u0099\7-\2\2\u0099\32\3\2\2\2\u009a\u009b\7,\2\2\u009b\34\3\2\2\2\u009c"+
		"\u009d\7/\2\2\u009d\36\3\2\2\2\u009e\u009f\7\61\2\2\u009f \3\2\2\2\u00a0"+
		"\u00a1\7\'\2\2\u00a1\"\3\2\2\2\u00a2\u00a3\7k\2\2\u00a3\u00a4\7h\2\2\u00a4"+
		"$\3\2\2\2\u00a5\u00a6\7v\2\2\u00a6\u00a7\7j\2\2\u00a7\u00a8\7g\2\2\u00a8"+
		"\u00a9\7p\2\2\u00a9&\3\2\2\2\u00aa\u00ab\7g\2\2\u00ab\u00ac\7n\2\2\u00ac"+
		"\u00ad\7u\2\2\u00ad\u00ae\7g\2\2\u00ae(\3\2\2\2\u00af\u00b0\7U\2\2\u00b0"+
		"\u00b1\7M\2\2\u00b1\u00b2\7K\2\2\u00b2\u00b3\7R\2\2\u00b3*\3\2\2\2\u00b4"+
		"\u00b5\7U\2\2\u00b5\u00b6\7V\2\2\u00b6\u00b7\7Q\2\2\u00b7\u00b8\7R\2\2"+
		"\u00b8,\3\2\2\2\u00b9\u00ba\7f\2\2\u00ba\u00bb\7k\2\2\u00bb\u00bc\7x\2"+
		"\2\u00bc.\3\2\2\2\u00bd\u00be\7v\2\2\u00be\u00bf\7t\2\2\u00bf\u00c0\7"+
		"w\2\2\u00c0\u00c1\7g\2\2\u00c1\60\3\2\2\2\u00c2\u00c3\7h\2\2\u00c3\u00c4"+
		"\7c\2\2\u00c4\u00c5\7n\2\2\u00c5\u00c6\7u\2\2\u00c6\u00c7\7g\2\2\u00c7"+
		"\62\3\2\2\2\u00c8\u00c9\7(\2\2\u00c9\64\3\2\2\2\u00ca\u00cb\7*\2\2\u00cb"+
		"\66\3\2\2\2\u00cc\u00cd\7+\2\2\u00cd8\3\2\2\2\u00ce\u00cf\7]\2\2\u00cf"+
		"\u00d0\7~\2\2\u00d0:\3\2\2\2\u00d1\u00d2\7~\2\2\u00d2\u00d3\7_\2\2\u00d3"+
		"<\3\2\2\2\u00d4\u00d5\7~\2\2\u00d5\u00d6\7~\2\2\u00d6\u00d7\7~\2\2\u00d7"+
		">\3\2\2\2\u00d8\u00d9\7]\2\2\u00d9\u00da\7_\2\2\u00da@\3\2\2\2\u00db\u00dc"+
		"\7~\2\2\u00dc\u00dd\7\u0080\2\2\u00dd\u00de\7~\2\2\u00deB\3\2\2\2\u00df"+
		"\u00e0\7.\2\2\u00e0D\3\2\2\2\u00e1\u00e2\7<\2\2\u00e2F\3\2\2\2\u00e3\u00e4"+
		"\7=\2\2\u00e4H\3\2\2\2\u00e5\u00e6\7/\2\2\u00e6\u00e7\7@\2\2\u00e7J\3"+
		"\2\2\2\u00e8\u00e9\7A\2\2\u00e9L\3\2\2\2\u00ea\u00eb\7#\2\2\u00ebN\3\2"+
		"\2\2\u00ec\u00ed\7e\2\2\u00ed\u00ee\7j\2\2\u00ee\u00ef\7c\2\2\u00ef\u00f0"+
		"\7p\2\2\u00f0\u00f1\7p\2\2\u00f1\u00f2\7g\2\2\u00f2\u00f3\7n\2\2\u00f3"+
		"P\3\2\2\2\u00f4\u00f5\7\60\2\2\u00f5R\3\2\2\2\u00f6\u00f7\7}\2\2\u00f7"+
		"T\3\2\2\2\u00f8\u00f9\7\177\2\2\u00f9V\3\2\2\2\u00fa\u00fb\7?\2\2\u00fb"+
		"X\3\2\2\2\u00fc\u00fd\7^\2\2\u00fdZ\3\2\2\2\u00fe\u00ff\7]\2\2\u00ff\u0100"+
		"\7@\2\2\u0100\\\3\2\2\2\u0101\u0102\7\61\2\2\u0102\u0103\7^\2\2\u0103"+
		"^\3\2\2\2\u0104\u0105\7c\2\2\u0105\u0106\7u\2\2\u0106\u0107\7u\2\2\u0107"+
		"\u0108\7g\2\2\u0108\u0109\7t\2\2\u0109\u010a\7v\2\2\u010a`\3\2\2\2\u010b"+
		"\u010c\7f\2\2\u010c\u010d\7g\2\2\u010d\u010e\7c\2\2\u010e\u010f\7f\2\2"+
		"\u010f\u0110\7n\2\2\u0110\u0111\7q\2\2\u0111\u0112\7e\2\2\u0112\u0113"+
		"\7m\2\2\u0113b\3\2\2\2\u0114\u0115\7f\2\2\u0115\u0116\7g\2\2\u0116\u0117"+
		"\7v\2\2\u0117\u0118\7g\2\2\u0118\u0119\7t\2\2\u0119\u011a\7o\2\2\u011a"+
		"\u011b\7k\2\2\u011b\u011c\7p\2\2\u011c\u011d\7k\2\2\u011d\u011e\7u\2\2"+
		"\u011e\u011f\7v\2\2\u011f\u0120\7k\2\2\u0120\u0121\7e\2\2\u0121d\3\2\2"+
		"\2\u0122\u0123\7f\2\2\u0123\u0124\7k\2\2\u0124\u0125\7x\2\2\u0125\u0126"+
		"\7g\2\2\u0126\u0127\7t\2\2\u0127\u0128\7i\2\2\u0128\u0129\7g\2\2\u0129"+
		"\u012a\7p\2\2\u012a\u012b\7e\2\2\u012b\u012c\7g\2\2\u012cf\3\2\2\2\u012d"+
		"\u012e\7\"\2\2\u012e\u012f\7]\2\2\u012f\u0130\7H\2\2\u0130\u0131\7F\2"+
		"\2\u0131\u0132\7_\2\2\u0132h\3\2\2\2\u0133\u0134\7\"\2\2\u0134\u0135\7"+
		"]\2\2\u0135\u0136\7H\2\2\u0136\u0137\7_\2\2\u0137j\3\2\2\2\u0138\u0139"+
		"\7\"\2\2\u0139\u013a\7]\2\2\u013a\u013b\7V\2\2\u013b\u013c\7_\2\2\u013c"+
		"l\3\2\2\2\u013d\u013e\7\"\2\2\u013e\u013f\7h\2\2\u013f\u0140\7t\2\2\u0140"+
		"\u0141\7g\2\2\u0141\u0142\7g\2\2\u0142n\3\2\2\2\u0143\u0144\4\62;\2\u0144"+
		"p\3\2\2\2\u0145\u0147\t\2\2\2\u0146\u0145\3\2\2\2\u0147\u0148\3\2\2\2"+
		"\u0148\u0146\3\2\2\2\u0148\u0149\3\2\2\2\u0149r\3\2\2\2\u014a\u014b\7"+
		"/\2\2\u014b\u014c\7/\2\2\u014c\u0150\3\2\2\2\u014d\u014f\n\3\2\2\u014e"+
		"\u014d\3\2\2\2\u014f\u0152\3\2\2\2\u0150\u014e\3\2\2\2\u0150\u0151\3\2"+
		"\2\2\u0151\u0153\3\2\2\2\u0152\u0150\3\2\2\2\u0153\u0154\b:\2\2\u0154"+
		"t\3\2\2\2\u0155\u0157\t\4\2\2\u0156\u0155\3\2\2\2\u0157\u0158\3\2\2\2"+
		"\u0158\u0156\3\2\2\2\u0158\u0159\3\2\2\2\u0159\u015a\3\2\2\2\u015a\u015b"+
		"\b;\2\2\u015bv\3\2\2\2\6\2\u0148\u0150\u0158\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}