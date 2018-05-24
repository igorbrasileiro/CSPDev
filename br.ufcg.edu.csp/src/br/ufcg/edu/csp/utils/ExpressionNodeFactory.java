package br.ufcg.edu.csp.utils;

import br.ufcg.edu.csp.outline.ExpressionNodeDecorator;

public class ExpressionNodeFactory implements INodeFactory{

	public INodeDecorator createInstance() {
		return new ExpressionNodeDecorator(null);
	}
}
