package br.ufcg.edu.csp.counterexampleView;

import org.eclipse.jface.viewers.ITreeContentProvider;

public class CounterexampleContentProvider implements ITreeContentProvider{

	@Override
	public Object[] getElements(Object inputElement) {
			
		return (Object[]) inputElement;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		// TODO Auto-generated method stub
		return false;
	}

}
