package br.ufcg.edu.csp.utils;

import org.antlr.v4.runtime.tree.ParseTree;

public interface INodeDecorator {
	
	public ParseTree getNode();
	
	public void setNode(ParseTree node);
	
	public INodeDecorator getParent();
}
