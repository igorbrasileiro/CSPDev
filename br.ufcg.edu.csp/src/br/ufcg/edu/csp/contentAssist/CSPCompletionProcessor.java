package br.ufcg.edu.csp.contentAssist;

import java.io.File;

import org.antlr.v4.runtime.Token;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Shell;

import br.ufcg.edu.csp.CSPDocumentProvider;
import br.ufcg.edu.csp.fdrAnalyser.FDRServices;

public class CSPCompletionProcessor implements IContentAssistProcessor {

	//private static final char[] autoActivationChars = new char[] {'.', ' '};
	private FDRServices fdrService;
	
	// e � o m�todo invocado para gerar o conteudo
	@Override
	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer, int offset) {
		// TODO Auto-generated method stub
		loadFile();
		
		IDocument doc = viewer.getDocument();
		
		try {
			String txt = doc.get(offset, -7);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Rectangle rect = new Rectangle(110,220,200,110);
		InfoPopup pop = new InfoPopup( new Shell() , rect ,"Process " + " Information","Select and press ESC to close");
		pop.setText("Exemplo de Teste");
		pop.open();
		
		return new ICompletionProposal[0];
	}

	private void loadFile() {
		File editorFile = CSPDocumentProvider.getEditorFile();
		String fileName = editorFile.getAbsolutePath();
		fdrService = new FDRServices(fileName);
	}

	@Override
	public IContextInformation[] computeContextInformation(ITextViewer viewer, int offset) {
		// TODO Auto-generated method stub
		return null;
	}

	// ativado quando a tecla autoActivate � acionada
	@Override
	public char[] getCompletionProposalAutoActivationCharacters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public char[] getContextInformationAutoActivationCharacters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getErrorMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IContextInformationValidator getContextInformationValidator() {
		// TODO Auto-generated method stub
		return null;
	}

}
