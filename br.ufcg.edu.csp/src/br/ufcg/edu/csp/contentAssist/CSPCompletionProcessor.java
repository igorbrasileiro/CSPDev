package br.ufcg.edu.csp.contentAssist;

import java.io.File;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;

import br.ufcg.edu.csp.CSPDocumentProvider;
import br.ufcg.edu.csp.fdrAnalyser.FDRServices;
import uk.ac.ox.cs.fdr.fdr;

public class CSPCompletionProcessor implements IContentAssistProcessor {

	//private static final char[] autoActivationChars = new char[] {'.', ' '};
	private FDRServices fdrService;
	
	// e é o método invocado para gerar o conteudo
	@Override
	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer, int offset) {
		// TODO Auto-generated method stub
		loadFile();
		
		IDocument doc = viewer.getDocument();
		try {
			String processName = doc.get(offset, 5);
			
			
			
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
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

	// ativado quando a tecla autoActivate é acionada
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
