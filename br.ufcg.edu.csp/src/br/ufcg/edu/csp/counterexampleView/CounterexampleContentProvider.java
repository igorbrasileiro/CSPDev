package br.ufcg.edu.csp.counterexampleView;

import org.eclipse.jface.viewers.ITreeContentProvider;

public class CounterexampleContentProvider implements ITreeContentProvider {
	@Override
	public Object[] getElements(Object inputElement) {
			
		return (Object[]) inputElement;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		return null;
	}

	@Override
	public Object getParent(Object element) {
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		return false;
	}
}
