package br.ufcg.edu.csp.wordrules;

import java.util.ArrayList;

import org.antlr.v4.runtime.Vocabulary;

import br.ufcg.edu.csp.parser.CspLexer;

public class ITokensParserCSP {
	public static String[] TOKENS;

	static{
		ArrayList<String> tokens = new ArrayList<String>(); 
		Vocabulary v = CspLexer.VOCABULARY;
		int i = 1;
		String literal = v.getLiteralName(i);
		while(literal != null){
			literal = literal.replaceAll("'", "");
			tokens.add(literal);
			literal = v.getLiteralName(++i);
		}
		TOKENS = tokens.toArray(new String[0]);
	}
}
