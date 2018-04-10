package br.ufcg.edu.csp.outline;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;


public class CSPOutlinePage extends ContentOutlinePage {

	private StructuredViewer viewer;

	public CSPOutlinePage() { }

	@Override
	public void createControl(Composite parent) {
		super.createControl(parent);
		viewer= getTreeViewer();
		viewer.setContentProvider(new CSPOutlineContentProvider());
		viewer.setLabelProvider(new LabelProvider());
		viewer.addSelectionChangedListener(this);
		viewer.setInput((((CSPOutlineContentProvider) viewer.getContentProvider()).getRootContext()));
	}

	public void updateContent(){
		viewer.setInput((((CSPOutlineContentProvider) viewer.getContentProvider()).getRootContext()));
	}
}
