package br.ufcg.edu.csp.wordrules;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.NumberRule;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WordRule;
import org.eclipse.swt.SWT;


public class CSPWordRules extends RuleBasedScanner {
	ColorManager colorManager;
	
	public CSPWordRules(ColorManager colorManager) {
		this.colorManager = colorManager;
		
		WordRule wr = new WordRule(new CSPWordRuleDetector());

		addBoldWords(wr);
		addUnboldWords(wr);
		
		IToken nrToken = new Token(new TextAttribute(colorManager.getColor(ICSPColorConstants.CSP_RESERVED_WORDS_GREEN)));
		NumberRule nr = new NumberRule(nrToken);
		
		IRule[] rules = new IRule[2];
		rules[0] = wr;
		rules[1] = nr;
		
		setRules(rules);
	}
	
	private void addBoldWords(WordRule wr) {
		IToken wordTokenBold = new Token(new TextAttribute(colorManager.getColor(ICSPColorConstants.CSP_RESERVED_WORDS_GREEN),null, SWT.BOLD));
		
		for(String word : getBoldReservedWords()) {
			wr.addWord(word, wordTokenBold);
		}
	}
	
	private void addUnboldWords(WordRule wr) {
		IToken wordTokenUnbold = new Token(new TextAttribute(colorManager.getColor(ICSPColorConstants.CSP_RESERVED_WORDS_GREEN)));
		
		for(String word : getUnboldReservedWords()) {
			wr.addWord(word, wordTokenUnbold);
		}
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
