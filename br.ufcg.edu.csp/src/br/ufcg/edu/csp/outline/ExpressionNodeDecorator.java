package br.ufcg.edu.csp.outline;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import br.ufcg.edu.csp.parser.CspParser;

public class ExpressionNodeDecorator {

	private ParseTree node;

	public ExpressionNodeDecorator(ParseTree node) {
		super();
		this.node = node;
	}

	public ParseTree getNode() {
		return node;
	}

	public void setNode(ParseTree node) {
		this.node = node;
	}
	
	public ExpressionNodeDecorator getParent() {
		ParseTree parent = getNode().getParent();
		if(parent != null) {
			return new ExpressionNodeDecorator(getNode().getParent());
		}
		return null;
	}
	
	// TODO: FAZER METODO TOSTRING
	@Override
	public String toString() {
		StringBuilder retorno = new StringBuilder();
		
		retorno.append(node.getText());
		return retorno.toString();
	}
}