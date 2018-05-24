package br.ufcg.edu.csp.wordrules;

import java.util.ArrayList;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.NumberRule;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WordRule;
import org.eclipse.swt.SWT;


public class CSPWordRules extends RuleBasedScanner {
	private IToken wordTokenBold;
	private IToken wordTokenUnbold;
	
	
	public CSPWordRules(ColorManager colorManager) {
		startTokens(colorManager);
		
		ArrayList<IRule> rulesList = new ArrayList<>();
		
		addWords(rulesList);
		//addMultipartWordRule(rulesList);
		addNumberRuleToList(rulesList);
		
		IRule[] rules = new IRule[1];
		rules = rulesList.toArray(rules);
		
		//setDefaultReturnToken(defaultToken);
		setRules(rules);
	}

	private void startTokens(ColorManager colorManager) {
		this.wordTokenBold = new Token(new TextAttribute(colorManager.getColor(ICSPColorConstants.CSP_RESERVED_WORDS_GREEN),null, SWT.BOLD));
		this.wordTokenUnbold = new Token(new TextAttribute(colorManager.getColor(ICSPColorConstants.CSP_RESERVED_WORDS_GREEN)));
	}
	
	private void addWords(ArrayList<IRule> list) {
		//WordRule wr = new WordRule(new CSPWordRuleDetector(), defaultToken, false);
		addBoldWordsToList(list);
		addUnboldWordsToList(list);
	}
	
	private void addBoldWordsToList(ArrayList<IRule> list) {
		WordRule wordRule;
		for(String word : getBoldReservedWords()) {
			wordRule = new WordRule(new CSPWordRuleDetector(word));
			wordRule.addWord(word, wordTokenBold);
			list.add(wordRule);
		}
	}
	
	private void addUnboldWordsToList(ArrayList<IRule> list) {
		WordRule wordRule;
		for(String word : getUnboldReservedWords()) {
			wordRule = new WordRule(new CSPWordRuleDetector(word));
			wordRule.addWord(word, wordTokenUnbold);
			list.add(wordRule);
		}
	}
	
	/*
	private void addMultipartWordRule(ArrayList<IRule> list) {
		WordPatternRule wpr = new WordPatternRule(new CSPMultipartWordRuleDectector("[|", "|]"), "[|", "|]", wordTokenUnbold);
		list.add(wpr);
	}
	*/
	
	private void addNumberRuleToList(ArrayList<IRule> list) {
		NumberRule nr = new NumberRule(wordTokenUnbold);
		list.add(nr);
	}
	
	private String[] getBoldReservedWords() {
		return new String[] { "channel", "assert", "if", "then", "else", "datatype", "let", "external", "within", "&"};
	}
	
	private String[] getUnboldReservedWords() {
		return new String[] { "STOP", "SKIP", "and", "or", "not", "|||", "[]", "|~|", "->", "[|", "|]", "[(", ")]"}; // 
	}
}
