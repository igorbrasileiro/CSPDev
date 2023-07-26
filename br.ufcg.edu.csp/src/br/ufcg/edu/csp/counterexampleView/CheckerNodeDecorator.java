package br.ufcg.edu.csp.counterexampleView;

import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.tree.ParseTree;

import br.ufcg.edu.csp.parser.CspLexer;
import br.ufcg.edu.csp.parser.CspParser;
import br.ufcg.edu.csp.utils.INodeDecorator;

public class CheckerNodeDecorator implements INodeDecorator {
	
	private boolean isCounterexampleNode;
	private boolean isDeadlock;
	private ParseTree node;
	private boolean checkCondition;
	private String[] counterexamples;
	private String assertionText;	
	private String collon;
	private String lbracket;
	private String rbracket;

	public CheckerNodeDecorator(ParseTree node) {
		this.node = node;
		this.checkCondition = false;
		this.isCounterexampleNode = false;
		this.assertionText = "";
		this.isDeadlock = false;
		createEspecialChars();
	}

	private void createEspecialChars() {
		Vocabulary vocabulary = CspLexer.VOCABULARY;
		collon = vocabulary.getDisplayName(CspLexer.COLLON);
		collon = collon.replaceAll("'", "");
		lbracket = vocabulary.getDisplayName(CspLexer.LBRACKET);
		lbracket = lbracket.replaceAll("'", "");
		rbracket = vocabulary.getDisplayName(CspLexer.RBRACKET);
		rbracket = rbracket.replaceAll("'", "");
	}
	
	public ParseTree getNode() {
		return this.node;
	}
	
	public boolean getCheckCondition() {
		return this.checkCondition;
	}
	
	public void setCheckCondition(boolean value) {
		this.checkCondition = value;
	}

	public String[] getCounterexamples() {
		return this.counterexamples;
	}
	
	public void setCounterexamples(String[] nodeCounterexamples) {
		this.counterexamples = nodeCounterexamples;
	}

	@Override
	public boolean equals (Object o) {
		return this.getAssertionText().equals(((CheckerNodeDecorator)o).getAssertionText());
	}

	@Override
	public void setNode(ParseTree node) {
		this.node = node;
	}

	public String getAssertionText() {
		return assertionText;
	}

	public void setAssertionText(String assertionText) {
		this.assertionText = assertionText;
	}
	
	public void setNodeAsCounterexampleNode() {
		this.isCounterexampleNode = true;
	}
	
	public boolean getIsCounterexampleNode() {
		return this.isCounterexampleNode;
	}
	
	public String getNodeName() { 
		return node.getChild(0).getText();
	}
	
	@Override
	public String toString() {
		String text;
		if(node instanceof CspParser.AssertDefinitionContext) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < node.getChildCount(); i++) {
				String substring = node.getChild(i).getText();
				if(substring.equals(collon) || substring.equals(lbracket)) {
					sb.append(substring);
				} else if(substring.equals(rbracket)) {
					sb.insert(sb.length()-1, substring);
				}else {
					sb.append(substring + " ");
				}
			}
			text = sb.toString();
		} else if(isCounterexampleNode) {
			text = getAssertionText();
		} else {
			text =  getNodeName();
		}
		
		return text;
	}

	@Override
	public INodeDecorator getParent() {
		return new CheckerNodeDecorator(this.node.getParent());
	}
	
	public void setIsDeadlock(boolean isDeadlock) {
		this.isDeadlock = isDeadlock;
	}
	
	protected boolean isDeadlockCounterexample() {
		return isDeadlock;
	}
}
