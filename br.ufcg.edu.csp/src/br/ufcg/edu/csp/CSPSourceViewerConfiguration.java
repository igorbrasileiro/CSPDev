package br.ufcg.edu.csp;

import org.eclipse.jface.text.DefaultTextHover;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextHover;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.ITokenScanner;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.DefaultAnnotationHover;
import org.eclipse.jface.text.source.IAnnotationHover;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

import br.ufcg.edu.csp.wordrules.CSPWordRules;
import br.ufcg.edu.csp.wordrules.ColorManager;
import br.ufcg.edu.csp.wordrules.ICSPColorConstants;


public class CSPSourceViewerConfiguration extends SourceViewerConfiguration {
	
	private ColorManager colorManager;
	private CSPWordRules wordRules;
	
	public CSPSourceViewerConfiguration() {
		colorManager = new ColorManager();
	}
	
	// se precisar
	//COLOCAR AQUI TODAS AS STRINGS REFERENTES A TODOS DAMAGER E REPAIRER 
	@Override
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return new String[] {CSPPartitionScanner.CSP_COMMENT};
	}
	
	@Override
	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();
		
		DefaultDamagerRepairer dr = new DefaultDamagerRepairer(getCSPWordRules());
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);
		
		dr = new DefaultDamagerRepairer(getCSPComment());
		reconciler.setDamager(dr, CSPPartitionScanner.CSP_COMMENT);
		reconciler.setRepairer(dr, CSPPartitionScanner.CSP_COMMENT);
		
		return reconciler;
	}
	
	// - METODOS PARA MARCADOR DE ERRO NA LINHA
	@Override
	public ITextHover getTextHover(ISourceViewer sourceViewer, 
			String contentType) {
		return new DefaultTextHover(sourceViewer);
	}
	
	@Override
	public IAnnotationHover getAnnotationHover(ISourceViewer sourceViewer) {
	    return new DefaultAnnotationHover(true);
	}
	
	
	private ITokenScanner getCSPWordRules() {
		if(wordRules == null) {
			wordRules = new CSPWordRules(colorManager);
		}
		
		return wordRules;
	}
	
	private ITokenScanner getCSPComment() {
		// CRIAR UMA CLASSE DEIXE DE PREGUICA
		ITokenScanner scanner = new RuleBasedScanner();
		
		IRule[] rules = new IRule[1];
		
		rules[0] = new EndOfLineRule("--", 
				new Token(
						new TextAttribute(
								colorManager.getColor(ICSPColorConstants.CSP_COMMENT))));
	
		((RuleBasedScanner) scanner).setRules(rules);
		
		return scanner;
	}
	
}
