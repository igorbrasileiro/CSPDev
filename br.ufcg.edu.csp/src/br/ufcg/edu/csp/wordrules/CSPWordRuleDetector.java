package br.ufcg.edu.csp.wordrules;

import org.eclipse.jface.text.rules.IWordDetector;

public class CSPWordRuleDetector implements IWordDetector {

	@Override
	public boolean isWordStart(char c) {
		return identifyCharacter(c);
	}

	@Override
	public boolean isWordPart(char c) {
		return identifyCharacter(c);
	}
	
	// TODO completar char das palavras reservadas
	private boolean identifyCharacter(char c) {
		return  c == '-' || c == '+'
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
				|| (c >= 'a' && c <= 'z')
				|| (c >= 'A' && c <= 'Z')
				|| c == '|' || c == '~';
		
		// "'\"'", "'#'", "'$'", "'%'", "'&'", "'''", "'('", "')'", "'*'", "'+'", "','", "'-'", "'.'", "'/'"
	}
}
