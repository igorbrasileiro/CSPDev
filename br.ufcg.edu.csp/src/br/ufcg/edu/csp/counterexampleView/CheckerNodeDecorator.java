package br.ufcg.edu.csp.counterexampleView;

import org.antlr.v4.runtime.tree.ParseTree;

public class CheckerNodeDecorator {
	
	private ParseTree node;

	public CheckerNodeDecorator(ParseTree node) {
		this.node = node;
	}
	
	public ParseTree getNode() {
		return this.node;
	}
	
	@Override
	public String toString() {
		return node.getChild(0).getText();
	}
}
