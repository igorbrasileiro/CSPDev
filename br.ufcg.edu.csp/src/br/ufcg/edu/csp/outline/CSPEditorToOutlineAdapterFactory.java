package br.ufcg.edu.csp.outline;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import br.ufcg.edu.csp.counterexampleView.ProcessCheckerListView;

public class CSPEditorToOutlineAdapterFactory implements IAdapterFactory {

	private CSPOutlinePage myPage;
	private ProcessCheckerListView pclsPage;

	@Override
	public Class<?>[] getAdapterList() {
		return new Class<?>[] { IContentOutlinePage.class, ProcessCheckerListView.class };
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getAdapter(Object adaptableObject, Class<T> adapterType) {
		if (IContentOutlinePage.class.equals(adapterType)) {
			if(myPage == null) {
				myPage= new CSPOutlinePage();
			}
			
			return (T) myPage;
		} else if(ProcessCheckerListView.class.equals(adapterType)) {
			if(pclsPage == null) {
				pclsPage = new ProcessCheckerListView();
			}
			
			return (T) pclsPage;
		}

		return null;
	}

}
