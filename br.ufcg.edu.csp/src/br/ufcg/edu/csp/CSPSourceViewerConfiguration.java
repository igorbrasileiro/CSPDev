package br.ufcg.edu.csp;

import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

public class CSPSourceViewerConfiguration extends SourceViewerConfiguration {
	
	
	public CSPSourceViewerConfiguration() {
		// TODO Iniciar colormanager e wordrule
	}
	
	// se precisar
	//COLOCAR AQUI TODAS AS STRINGS REFERENTES A TODOS DAMAGER E REPAIRER 
	@Override
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return new String[] { };
	}
}
