package br.ufcg.edu.csp.counterexampleView;

import org.antlr.v4.runtime.tree.ParseTree;

import br.ufcg.edu.csp.utils.INodeDecorator;

public class CheckerNodeDecorator implements INodeDecorator {
	
	private ParseTree node;
	private boolean checkCondition;
	private String[] counterexamples;

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
		return this.toString().equals(o.toString());
	}

	@Override
	public void setNode(ParseTree node) {
		this.node = node;
	}
}
