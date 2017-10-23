package br.ufcg.edu.csp.contentAssist;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistant;

public class CSPContentAssistant extends ContentAssistant {

	public CSPContentAssistant() {
		setContentAssistProcessor(new CSPCompletionProcessor(),
				 IDocument.DEFAULT_CONTENT_TYPE);
		
		enableAutoActivation(true);
		
		setContextInformationPopupOrientation(IContentAssistant.CONTEXT_INFO_ABOVE);
	}
}
