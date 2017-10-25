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

import br.ufcg.edu.csp.CSPDocumentProvider;
import br.ufcg.edu.csp.fdrAnalyser.FDRServices;

public class CSPCompletionProcessor implements IContentAssistProcessor {

	//private static final char[] autoActivationChars = new char[] {'.', ' '};
	private FDRServices fdrService;
	
	// e � o m�todo invocado para gerar o conteudo
	@Override
	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer, int offset) {
		// TODO Auto-generated method stub
		//loadFile();
		
		// PARA PEGAR O TOKEN
		//Token t1 = tokens.LT(4);
		//Token t2 = tokens.LT(5);
		//t2.getStopIndex();
		// preciso pegar o contexto do token para ver se é processo
		
		IDocument doc = viewer.getDocument();
		
		return null;
	}

	private void loadFile() {
		File editorFile = CSPDocumentProvider.getEditorFile();
		String fileName = editorFile.getAbsolutePath();
		if(fdrService == null) {
			fdrService = new FDRServices();
		}
		fdrService.loadFile(fileName);
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
