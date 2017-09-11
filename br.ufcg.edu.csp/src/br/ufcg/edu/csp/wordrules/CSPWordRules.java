package br.ufcg.edu.csp.wordrules;

import java.util.ArrayList;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.NumberRule;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WordPatternRule;
import org.eclipse.jface.text.rules.WordRule;
import org.eclipse.swt.SWT;


public class CSPWordRules extends RuleBasedScanner {
	private IToken wordTokenBold;
	private IToken wordTokenUnbold;
	private Token defaultToken;
	
	
	public CSPWordRules(ColorManager colorManager) {
		startTokens(colorManager);
		
		ArrayList<IRule> rulesList = new ArrayList<>();
		
		addWords(rulesList);
		
		addNumberRule(rulesList);
		
		
		// tem que ser o ultimo
		//addMultilineWords(rulesList);
		
		IRule[] rules = new IRule[1];
		rules = rulesList.toArray(rules);
		
		//setDefaultReturnToken(defaultToken);
		setRules(rules);
	}

	private void startTokens(ColorManager colorManager) {
		this.wordTokenBold = new Token(new TextAttribute(colorManager.getColor(ICSPColorConstants.CSP_RESERVED_WORDS_GREEN),null, SWT.BOLD));
		this.wordTokenUnbold = new Token(new TextAttribute(colorManager.getColor(ICSPColorConstants.CSP_RESERVED_WORDS_GREEN)));
		this.defaultToken = new Token(new TextAttribute(colorManager.getColor(ICSPColorConstants.DEFAULT)));
	}
	
	private void addWords(ArrayList<IRule> list) {
		WordRule wr = new WordRule(new CSPWordRuleDetector(), defaultToken, false);
		addBoldWords(wr);
		addUnboldWords(wr);
		list.add(wr);
	}
	
	private void addBoldWords(WordRule wr) {
		for(String word : getBoldReservedWords()) {
			wr.addWord(word, wordTokenBold);
		}
	}
	
	private void addUnboldWords(WordRule wr) {
		for(String word : getUnboldReservedWords()) {
			wr.addWord(word, wordTokenUnbold);
		}
	}
	
	/*private void addMultilineWords(ArrayList<IRule> list) {
		// TODO: "[|", "|]" resolver com XMLTag do editor de xml
		WordPatternRule op = new WordPatternRule(new CSPWordRuleDetector(), "[|", "|]", wordTokenUnbold);
		list.add(op);
	}
	*/
	private void addNumberRule(ArrayList<IRule> list) {
		NumberRule nr = new NumberRule(wordTokenUnbold);
		list.add(nr);
	}
	
	private String[] getBoldReservedWords() {
		return new String[] { "channel", "assert", "if", "then", "else", "datatype", "let", "external", "within", "&"};
	}
	
	private String[] getUnboldReservedWords() {
		return new String[] { "STOP", "SKIP", "and", "or", "not", "|||", "[]", "|~|", "->", "[|", "|]" };
	}
}
