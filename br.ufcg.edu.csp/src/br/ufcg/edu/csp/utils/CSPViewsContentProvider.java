package br.ufcg.edu.csp.utils;

import java.util.ArrayList;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;
import org.eclipse.jface.viewers.ITreeContentProvider;

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
			result = ((ParseTree) obj).getChildCount() > 1;
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
		} else if(obj instanceof Object[]) {
			return (Object[]) obj;
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
			addNodeInList((ParseTree) obj, list);
		} else if (obj instanceof CspParser.AnyContext) {
			getElements(((ParseTree)obj).getChild(0), list);
		} else if(obj instanceof CspParser.ProcContext) {
			ParseTree node = (ParseTree) obj;
			// if it comes from any, has left proc OPERATOR proc2, need get the second proc
			if(node.getChild(0) instanceof TerminalNodeImpl && node.getChild(1) instanceof TerminalNodeImpl) {
				//ID ARROW proc
				addNodeInList(node, list);
			} else if(node.getChild(0).getText().equals("(")) {
				// LPAREN proc RPAREN
				getElements(node.getChild(1), list);
			} else if(node.getChild(0) instanceof CspParser.ProcContext 
					&& node.getChild(2) instanceof CspParser.ProcContext) {
				// proc operator proc
				addNodeInList(node.getChild(0), list);
				addNodeInList(node.getChild(2), list);
			} else if(node.getChild(0) instanceof CspParser.ProcContext
					&& node.getChild(2) instanceof CspParser.SetContext) {
				// proc LSYNC set RSYNC proc
				addNodeInList(node.getChild(0), list);
				addNodeInList(node.getChild(4), list);
			} else if(node.getChild(0).getText().equals("if")) {
				//if bool then proc else proc
				addNodeInList(node.getChild(3), list);
				addNodeInList(node.getChild(5), list);
			} else if(node.getChild(0) instanceof CspParser.BoolExpContext) {
				// boolexp guard proc
				addNodeInList(node.getChild(2), list);
			} else {
				addNodeInList(node, list);
			}
		}
	}
	
	public void addNodeInList(ParseTree node, ArrayList<T> list) {
		INodeDecorator nodeDecorator = factory.createInstance();
		nodeDecorator.setNode(node);
		list.add((T) nodeDecorator);
	}
	
	@Override
	public Object[] getChildren(Object obj) {
		ArrayList<T> elementos = new ArrayList<>();
		if (obj instanceof ExpressionNodeDecorator) {
			ParseTree node = ((ExpressionNodeDecorator) obj).getNode();
			if(node instanceof CspParser.SimpleDefinitionContext) {
				// pega regra any
				getElements(node.getChild(2), elementos);
			} else if(node instanceof CspParser.ProcContext) {
				getElements(node.getChild(2), elementos);
			}
			
		}
		return elementos.toArray();
	}
	
}
