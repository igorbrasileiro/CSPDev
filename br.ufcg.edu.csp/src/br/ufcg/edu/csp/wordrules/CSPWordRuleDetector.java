package br.ufcg.edu.csp.wordrules;

import org.eclipse.jface.text.rules.IWordDetector;

public class CSPWordRuleDetector implements IWordDetector {
	
	private String startChar;
	private String charPart;
	
	public CSPWordRuleDetector(String word) {
		this.startChar = word.substring(0, 1);
		this.charPart = word.substring(1);
	}

	@Override
	public boolean isWordStart(char c) {
		return startChar.startsWith(String.valueOf(c));
	}

	@Override
	public boolean isWordPart(char c) {
		return charPart.contains(String.valueOf(c));
	}
	
	// TODO completar char das palavras reservadas
	private boolean identifyCharacter(char c) {
		return  
				Character.isJavaIdentifierPart(c)
				|| c == '-' || c == '+'
				|| c == '$' || c == '*'
				|| c == '%' || c == '&'
				|| c == "'".toCharArray()[0] // nao sei outra forma
				|| c == ',' || c == '.'
				|| c == '/' || c == '\\' 
				|| c == '!' || c == '#'
				|| c == '>' || c == '<'
				|| c == '(' || c == ')' 
				|| c == '[' || c == ']'
				|| c == '{' || c == '}'
				|| c == '|' || c == '~';
		
		// "'\"'", "'#'", "'$'", "'%'", "'&'", "'''", "'('", "')'", "'*'", "'+'", "','", "'-'", "'.'", "'/'"
	}
}
