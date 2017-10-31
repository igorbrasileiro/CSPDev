package br.ufcg.edu.csp;

import org.eclipse.ui.editors.text.TextEditor;

import br.ufcg.edu.csp.wordrules.ColorManager;

public class CSPEditor extends TextEditor {

	private ColorManager colorManager;
	
	public CSPEditor() {
		super();
		colorManager = new ColorManager();
		setSourceViewerConfiguration(new CSPSourceViewerConfiguration());
		setDocumentProvider(new CSPDocumentProvider());
		loadLibs();
	}
	
	private void loadLibs() {
		System.loadLibrary("libfdr_java");
	}

	@Override
	public void dispose() {
		colorManager.dispose();
		super.dispose();
	}
}
