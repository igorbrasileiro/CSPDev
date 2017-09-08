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


	public String getTextFromEditor() {
		try{
			IEditorPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();

			AbstractTextEditor compEditor = (AbstractTextEditor)part;

			IEditorInput input = compEditor.getEditorInput();

			IDocumentProvider provider = compEditor.getDocumentProvider();

			IDocument document = provider.getDocument(input);


			return document.get();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "sem documento encontrado";
	}

	public ParserRuleContext getTree() {
		CspParser parser = (CspParser) ParserUtil.getParserFromText(getTextFromEditor());
		CspParser.SpecContext tree = parser.spec();
		
		//System.out.println("em getTree. Numero de erros sintaticos" + parser.getNumberOfSyntaxErrors());

		return tree; // nao pode retornar null, impede de criar a tree;
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
		ArrayList elementos = new ArrayList();
		if(obj instanceof CspParser.SpecContext){
			int children = ((ParseTree) obj).getChildCount();
			for (int i = 0; i < children; i++) {
				ParseTree node = ((ParseTree) obj).getChild(i);
				if(!(node instanceof TerminalNode)){
					elementos.add( new ExpressionNodeDecorator(node));
				}
			}
		} else if (obj instanceof ExpressionNodeDecorator){
			return getElements(((ExpressionNodeDecorator) obj).getNode());
		}
		//System.out.println("%%%%%%%%%%%%%%%%%" + obj.getClass() + "%%%%%%%%%%%" + obj);
		//Date nova = new Date();
		//Object[] retorno = {1,"Dois",nova};
		//System.out.println("%%%%%%%%%%%% RETORNO: " + elementos.toArray());

		//se colcoar esse return abaixo ele nao pinta. Isso deve ser porque precisamos adaptar 
		//os objetos do array que tem um tipo especifico ParseTree para que eles mostrem algum texto deles
		//baseado no tipo do paragrafo. Por exemplo, quando for um ModContext ele pinta a expressao com o modulo.

		return elementos.toArray();
	}

	@Override
	public Object[] getChildren(Object obj) {
		ArrayList elementos = new ArrayList();
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
