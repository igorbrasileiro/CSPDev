package br.ufcg.edu.csp.errorReport;


import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.Token;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

import br.ufcg.edu.csp.CSPDocumentProvider;

public class ReportErrorMarker {
	
	private static final int UPDATE_DIFERENCE_TIME = 15;
	private static long lastTime = -1;
	
	public static void reportError(Parser recognizer) {
		Token currentToken = recognizer.getCurrentToken();
		String msg = "Report error. CurrentToken " + currentToken.getText() 
			+ " linha: " + currentToken.getLine()+":"+ currentToken.getCharPositionInLine() 
			+ ". expected " + recognizer.getExpectedTokens().toString(recognizer.getTokenNames());

		int lineNumber = currentToken.getLine();

		IResource file = CSPDocumentProvider.getEditorIFile();
		IMarker marker;
		try {
			// TODO mudar esse currentTImeMillis por que fica consumindo memória demasiada
			//if(Math.abs(lastTime - System.currentTimeMillis()) > UPDATE_DIFERENCE_TIME) {
			//	lastTime = System.currentTimeMillis();
			file.deleteMarkers(IMarker.PROBLEM, false, IResource.DEPTH_ONE);
			//}
			marker = file.createMarker(IMarker.PROBLEM);
			marker.setAttribute(IMarker.MESSAGE, msg);
			marker.setAttribute(IMarker.LINE_NUMBER, lineNumber);
			marker.setAttribute(IMarker.TRANSIENT, true);
			marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
		} catch (CoreException ee) {
			ee.printStackTrace();
		}
	}

}
