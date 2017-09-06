package br.ufcg.edu.csp.wordrules;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WordRule;
import org.eclipse.swt.SWT;


public class CSPWordRules extends RuleBasedScanner {

	public CSPWordRules(ColorManager colorManager) {
		IToken wordTokenBold = new Token(new TextAttribute(colorManager.getColor(ICSPColorConstants.CSP_RESERVED_WORDS_GREEN),null, SWT.BOLD));
		IToken wordTokenUnbold = new Token(new TextAttribute(colorManager.getColor(ICSPColorConstants.CSP_RESERVED_WORDS_GREEN)));
		
		WordRule wr = new WordRule(new CSPWordRuleDetector());

		for(String word : getBoldReservedWords()) {
			wr.addWord(word, wordTokenBold);
		}
		
		for(String word : getUnboldReservedWords()) {
			wr.addWord(word, wordTokenUnbold);
		}
		
		IRule[] rules = new IRule[1];
		rules[0] = wr;
		
		setRules(rules);
	}
	
	private String[] getBoldReservedWords() {
		// falta [T=, FD, "div" 
		return new String[] { "channel", "assert", "if", "then", "else", "datatype", "let", "external", "within", "&"};
	}
	
	private String[] getUnboldReservedWords() {
		// numeros , numeros,
		return new String[] { "STOP", "SKIP", "and", "or", "not", "[|", "|]", "|||", "[]", "|~|"};
	}
}
