package br.ufcg.edu.csp;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.editors.text.TextEditor;

import br.ufcg.edu.csp.wordrules.ColorManager;

public class CSPEditor extends TextEditor {

	private ColorManager colorManager;
	
	public CSPEditor() {
		super();
		colorManager = new ColorManager();
		setSourceViewerConfiguration(new CSPSourceViewerConfiguration());
		setDocumentProvider(new CSPDocumentProvider());
	}
	
	@Override
	public void dispose() {
		colorManager.dispose();
		super.dispose();
	}
}
