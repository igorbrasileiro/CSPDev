package br.ufcg.edu.csp.outline;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import br.ufcg.edu.csp.parser.CspParser;
import br.ufcg.edu.csp.utils.INodeDecorator;

public class ExpressionNodeDecorator implements INodeDecorator{

	private ParseTree node;

	public ExpressionNodeDecorator(ParseTree node) {
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
	
	public boolean hasChildren() {
		return this.node.getChildCount() > 0;
	}
	
	@Override
	public String toString() {
		if(node instanceof TerminalNodeImpl) {
			return node.getText();
		} else {
			return node.getChild(0).getText();
		}
	}
}
