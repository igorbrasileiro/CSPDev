package br.ufcg.edu.csp.wordrules;

import org.eclipse.jface.text.rules.IWordDetector;

public class CSPWordRuleDetector implements IWordDetector {

	@Override
	public boolean isWordStart(char c) {
		return c == 'c' || c == 'a' || c == 'i' || c == 't' || c == 'e' || c == 'f' || c == 'd' || c == 'l' || c == 'w'
				|| c == '&' || c == 'S' || c == 'o' || c == 'n' || c == '[' || c == '|';
	}

	@Override
	public boolean isWordPart(char c) {
		return identifyCharacter(c);
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
