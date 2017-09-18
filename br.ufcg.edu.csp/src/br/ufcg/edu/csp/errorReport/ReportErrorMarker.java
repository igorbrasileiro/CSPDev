package br.ufcg.edu.csp.errorReport;


import java.util.ArrayList;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.Token;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

import br.ufcg.edu.csp.CSPDocumentProvider;

public class ReportErrorMarker {

	private static ReportErrorMarker instance;
	private static ArrayList<SyntaxErrorInfo> errorList;
	
	public static ReportErrorMarker getInstance() {
		if(instance == null) {
			instance = new ReportErrorMarker();	
		}
		return instance;
	}
		
	public void reportError() {
		verifyListAndInitiate();
		deleteMarkers();
		
		IMarker marker;
		try {
			IResource file = CSPDocumentProvider.getEditorIFile();
			//if(Math.abs(lastTime - System.currentTimeMillis()) > UPDATE_DIFERENCE_TIME) {
			//	lastTime = System.currentTimeMillis();
			//}
			for(SyntaxErrorInfo error : errorList) {
				
				marker = file.createMarker(IMarker.PROBLEM);
				marker.setAttribute(IMarker.MESSAGE, error.errorMessage);
				marker.setAttribute(IMarker.LINE_NUMBER, error.lineNumber);
				marker.setAttribute(IMarker.TRANSIENT, true);
				marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
				//marker.getId();
				file.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);
			}
		} catch (CoreException ee) {
			ee.printStackTrace();
		}
	}
	//file.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_ZERO);

	public void cleanErrorList() {
		errorList = new ArrayList<>();
	}
	
	public void addError(Parser recognizer) {
		verifyListAndInitiate();
		SyntaxErrorInfo error = new SyntaxErrorInfo();
		error.errorMessage = getTokenMessage(recognizer);
		error.lineNumber = getTokenLineNumber(recognizer);
		
		errorList.add(error);
	}

	private int getTokenLineNumber(Parser recognizer) {
		Token currentToken = recognizer.getCurrentToken();
		int lineNumber = currentToken.getLine();
		return lineNumber;
	}
	
	private void deleteMarkers(IResource file){
		try {
			file.deleteMarkers(IMarker.PROBLEM, false, IResource.DEPTH_ONE);
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}
	public void deleteMarkers(){
		IResource file = CSPDocumentProvider.getEditorIFile();
		try {
			file.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_ZERO);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		deleteMarkers(file);
	}
	
	private void verifyListAndInitiate() {
		if(errorList == null) {
			errorList = new ArrayList<>();
		}
	}
	
	private String getTokenMessage(Parser recognizer) {
		Token currentToken = recognizer.getCurrentToken();
		String msg = "Report error. CurrentToken " + currentToken.getText() 
		+ " linha: " + currentToken.getLine()+":"+ currentToken.getCharPositionInLine();
		//+ ". expected " + recognizer.getExpectedTokens().toString(recognizer.getTokenNames());
		
		return msg;
	}
}
class SyntaxErrorInfo {
	public String errorMessage;
	public int lineNumber;
}