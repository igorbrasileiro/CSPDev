package br.ufcg.edu.csp.utils;

import br.ufcg.edu.csp.counterexampleView.CheckerNodeDecorator;

public class CheckerNodeFactory implements INodeFactory {

	@Override
	public INodeDecorator createInstance() {
		return new CheckerNodeDecorator(null);
	}

}
