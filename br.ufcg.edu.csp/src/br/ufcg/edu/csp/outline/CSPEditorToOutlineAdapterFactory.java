package br.ufcg.edu.csp.outline;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

public class CSPEditorToOutlineAdapterFactory implements IAdapterFactory {

	private CSPOutlinePage myPage;

	@Override
	public Class<?>[] getAdapterList() {
		return new Class<?>[] { IContentOutlinePage.class };
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getAdapter(Object adaptableObject, Class<T> adapterType) {
		if (IContentOutlinePage.class.equals(adapterType)) {
			if(myPage == null) {
				myPage= new CSPOutlinePage();
			}
			
			return (T) myPage;
		} 

		return null;
	}

}
