package br.ufcg.edu.csp;

import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.Token;

public class CSPPartitionScanner extends RuleBasedPartitionScanner {

	public static final String CSP_COMMENT = "__CSP_COMMENT_";
	
	public CSPPartitionScanner() { 
		IToken cspComment = new Token(CSP_COMMENT);
		
		IPredicateRule[] rules = new IPredicateRule[1];
		
		rules[0] = new EndOfLineRule("--", cspComment);
		
		setPredicateRules(rules);
	}
}
