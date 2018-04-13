package br.ufcg.edu.csp.utils;

import java.util.ArrayList;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.eclipse.jface.viewers.ITreeContentProvider;

import br.ufcg.edu.csp.counterexampleView.CheckerNodeDecorator;
import br.ufcg.edu.csp.parser.CspParser;
import br.ufcg.edu.csp.parser.ParserUtil;

public class CSPViewsContentProvider<T> implements ITreeContentProvider{
	private INodeFactory factory;

	public CSPViewsContentProvider(INodeFactory factory) {
		this.factory = factory;
	}

	public ParserRuleContext getTree() {
		return ParserUtil.getRootFromTextEditor(); // nao pode retornar null, impede de criar a tree;
	}

	@Override
	public boolean hasChildren(Object obj) {

		boolean result = false;
		if(obj instanceof CspParser.SpecContext){
			result = ((ParseTree) obj).getChildCount() > 0;
		}
		return result;
	}

	@Override
	public Object getParent(Object obj) {
		return ((INodeDecorator)obj).getNode().getParent();
	}

	@Override
	public Object[] getElements(Object obj) {
		// TODO: regra do parser simpleDefinition
		ArrayList<T> elementos = new ArrayList<>();
		if(obj instanceof CspParser.SpecContext){
			getElements(obj, elementos);
		} else if (obj instanceof INodeDecorator){
			return getElements(((INodeDecorator) obj).getNode());
		}
		
		return elementos.toArray();
	}

	/**
	 * Metodo recursivo para descer da regra Spec até SimpleDefinition
	 * @param obj
	 * @param list
	 */
	private void getElements(Object obj, ArrayList<T> list) {
		if(obj instanceof CspParser.SpecContext){
			// regra de muitos filhos
			int children = ((ParseTree) obj).getChildCount();
			for (int i = 0; i < children; i++) {
				ParseTree node = ((ParseTree) obj).getChild(i);
				getElements(node, list);
			}
		} else if(obj instanceof CspParser.DefinitionContext) {
			// regra de filho unico
			ParseTree node = ((ParseTree) obj).getChild(0);
			getElements(node, list);
		} else if(obj instanceof CspParser.SimpleDefinitionContext) {
			// TODO assert entrando na regra de simple definition, falar com professor.
			
			// regra terminal para imprimir os processos
			// debug String text = ((ParseTree) obj).getText();
			INodeDecorator nodeDecorator = factory.createInstance();
			nodeDecorator.setNode((ParseTree) obj);
			list.add((T) nodeDecorator);
		}
	}
	
	@Override
	public Object[] getChildren(Object obj) {
		ArrayList<INodeDecorator> elementos = new ArrayList<>();
		if(obj instanceof CspParser.SpecContext){
			int children = ((ParseTree) obj).getChildCount();
			for (int i = 0; i < children; i++) {
				ParseTree node = ((ParseTree) obj).getChild(i);
				if(!(node instanceof TerminalNode)){
					elementos.add( new CheckerNodeDecorator(node));
				}
			}
		} else if (obj instanceof CheckerNodeDecorator){
			return getChildren(((INodeDecorator) obj).getNode());
		}
		return elementos.toArray();
	}
}
