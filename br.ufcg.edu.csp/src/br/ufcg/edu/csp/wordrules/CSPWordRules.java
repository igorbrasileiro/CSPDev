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
		IToken wordToken = new Token(new TextAttribute(colorManager.getColor(ICSPColorConstants.CSP_RESERVED_WORDS),null, SWT.BOLD));
		
		WordRule wr = new WordRule(new CSPWordRuleDetector());

		for(String word : getReservedWords()) {
			wr.addWord(word, wordToken);
		}
		
		IRule[] rules = new IRule[1];
		rules[0] = wr;
		
		setRules(rules);
	}
	
	private String[] getReservedWords() {
		// falta [T=, FD, "div" 
		return new String[] { "channel", "assert", "if", "then", "else", "datatype", "let", "external", "within", "&"};
	}
}
