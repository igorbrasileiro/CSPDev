package br.ufcg.edu.csp;

import org.eclipse.ui.editors.text.TextEditor;

public class CSPEditor extends TextEditor {

	public CSPEditor() {
		// TODO color manager
		super();
		setSourceViewerConfiguration(new CSPSourceViewerConfiguration());
		setDocumentProvider(new CSPDocumentProvider());
	}
}
