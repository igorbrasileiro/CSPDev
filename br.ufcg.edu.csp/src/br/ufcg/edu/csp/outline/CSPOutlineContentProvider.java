package br.ufcg.edu.csp.outline;

import java.util.ArrayList;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.AbstractTextEditor;
import org.eclipse.ui.texteditor.IDocumentProvider;

import br.ufcg.edu.csp.CSPDocumentProvider;
import br.ufcg.edu.csp.parser.CspParser;
import br.ufcg.edu.csp.parser.ParserUtil;


public class CSPOutlineContentProvider implements ITreeContentProvider {

	private ParserRuleContext rootContext;

	public CSPOutlineContentProvider() {
		this.rootContext = getTree();
	}


	public ParserRuleContext getRootContext() {
		return rootContext;
	}


	public void setRootContext(ParserRuleContext rootContext) {
		this.rootContext = rootContext;
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
		return ((ExpressionNodeDecorator)obj).getParent();
	}

	@Override
	public Object[] getElements(Object obj) {
		// TODO: regra do parser simpleDefinition
		ArrayList<ExpressionNodeDecorator> elementos = new ArrayList<>();
		if(obj instanceof CspParser.SpecContext){
			getElements(obj, elementos);
		} else if (obj instanceof ExpressionNodeDecorator){
			return getElements(((ExpressionNodeDecorator) obj).getNode());
		}
		
		return elementos.toArray();
	}

	/**
	 * Metodo recursivo para descer da regra Spec até SimpleDefinition
	 * @param obj
	 * @param list
	 */
	private void getElements(Object obj, ArrayList<ExpressionNodeDecorator> list) {
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
			list.add(new ExpressionNodeDecorator((ParseTree) obj));
		}
	}
	
	@Override
	public Object[] getChildren(Object obj) {
		ArrayList<ExpressionNodeDecorator> elementos = new ArrayList<>();
		if(obj instanceof CspParser.SpecContext){
			int children = ((ParseTree) obj).getChildCount();
			for (int i = 0; i < children; i++) {
				ParseTree node = ((ParseTree) obj).getChild(i);
				if(!(node instanceof TerminalNode)){
					elementos.add( new ExpressionNodeDecorator(node));
				}
			}
		} else if (obj instanceof ExpressionNodeDecorator){
			return getChildren(((ExpressionNodeDecorator) obj).getNode());
		}
		return elementos.toArray();
	}

}
