package br.ufcg.edu.csp.utils;

import java.util.ArrayList;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;
import org.eclipse.jface.viewers.ITreeContentProvider;

import br.ufcg.edu.csp.counterexampleView.CheckerNodeDecorator;
import br.ufcg.edu.csp.outline.ExpressionNodeDecorator;
import br.ufcg.edu.csp.parser.CspParser;
import br.ufcg.edu.csp.parser.ParserUtil;

public class CSPViewsContentProvider<T> implements ITreeContentProvider {
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
		if(obj instanceof CspParser.DefinitionContext){
			result = ((ParseTree) obj).getChildCount() > 0;
		} else if(obj instanceof ExpressionNodeDecorator) {
			result =  ((ExpressionNodeDecorator)obj).hasChildren();
		}
		return result;
	}

	@Override
	public Object getParent(Object obj) {
		return ((INodeDecorator)obj).getNode().getParent();
	}

	@Override
	public Object[] getElements(Object obj) {
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
	@SuppressWarnings("unchecked")
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
		} else if (obj instanceof CspParser.AnyContext) {
			getElements(((ParseTree)obj).getChild(0), list);
		} else if(obj instanceof CspParser.ProcContext) {
			ParseTree node = (ParseTree) obj;
			for(int i = 0; i < node.getChildCount(); i++) {
				if(!(node.getChild(i) instanceof TerminalNodeImpl)) {
					INodeDecorator nodeDecorator = factory.createInstance();
					nodeDecorator.setNode(node.getChild(i));
					list.add((T) nodeDecorator);
				}
			}
		}
	}
	
	
	private boolean isOperator(ParseTree newNode) {
		return (newNode.getText().equals("[]")
		|| newNode.getText().equals("->")
		|| newNode.getText().equals("|~|"));
	}

	@Override
	public Object[] getChildren(Object obj) {
		ArrayList<T> elementos = new ArrayList<>();
		if (obj instanceof ExpressionNodeDecorator) {
			ParseTree node = ((ExpressionNodeDecorator) obj).getNode();
			if(node instanceof CspParser.SimpleDefinitionContext) {
				// pega regra any
				getElements(node.getChild(2), elementos);
			} else {
				for(int i = 0; i < node.getChildCount(); i++) {
					ParseTree newNode = node.getChild(i);
					if(!(newNode instanceof TerminalNodeImpl) || !isOperator(newNode)) {
						getElements(newNode, elementos);
					}
				}
				
			}
			
		}
		return elementos.toArray();
	}
}
