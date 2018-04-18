package br.ufcg.edu.csp.counterexampleView;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;


public class CounterexampleListView extends ViewPart {

	private ListViewer viewer;
	private CheckerNodeListSingleton cnls;
	private Action doubleClickAction;
	
	@Override
	public void createPartControl(Composite parent) {
		viewer = new ListViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		viewer.setContentProvider(ArrayContentProvider.getInstance());
		viewer.setLabelProvider(new CSPViewLabelProvider());
		
		cnls = CheckerNodeListSingleton.getInstance();
		Object[] content = cnls.getListContent();
		if(content != null)
			viewer.setInput(content);
		
		createDoubleClickAction();
		hookDoubleClickAction();
	}

	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}
	
	public void updateContent(Object[] content) {
		viewer.setInput(content);
	}

	private void createDoubleClickAction() {
		doubleClickAction = new Action() {
			public void run() {
				IStructuredSelection selection = viewer.getStructuredSelection();
				Object obj = selection.getFirstElement();
				// chamar o contra exemplo
				System.out.println("Double-click detected on "+obj.toString());
			}
		};
	}

	private void hookDoubleClickAction() {
		viewer.addDoubleClickListener(o -> doubleClickAction.run());
	}
	
}
