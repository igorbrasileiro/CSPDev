package br.ufcg.edu.csp.parser;

import org.antlr.v4.runtime.DefaultErrorStrategy;
import org.antlr.v4.runtime.InputMismatchException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.IntervalSet;

import br.ufcg.edu.csp.errorReport.ReportErrorMarker;


public class ExceptionErrorStrategy extends DefaultErrorStrategy {
	
	
	@Override
	public void recover(Parser recognizer, RecognitionException e) {

		super.recover(recognizer, e);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void reportInputMismatch(Parser recognizer, InputMismatchException e) throws RecognitionException {
		String msg = "mismatched input " + getTokenErrorDisplay(e.getOffendingToken());
		msg += " expecting one of "+e.getExpectedTokens().toString(recognizer.getTokenNames());

		// mostrar linha
		Token t = recognizer.getCurrentToken();
		msg += " linha " + t.getLine() + " coluna " + t.getCharPositionInLine();
		// mostrar linha
		RecognitionException ex = new RecognitionException(msg, recognizer, recognizer.getInputStream(), recognizer.getContext());
		ex.initCause(e);
		throw ex;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void reportMissingToken(Parser recognizer) {
		beginErrorCondition(recognizer);
		Token t = recognizer.getCurrentToken();
		IntervalSet expecting = getExpectedTokens(recognizer);
		String msg = "missing "+expecting.toString(recognizer.getTokenNames()) + " at " + getTokenErrorDisplay(t);
		throw new RecognitionException(msg, recognizer, recognizer.getInputStream(), recognizer.getContext());
	}

	/**
	 * This method send a recognizer, that contains a mensager and a line from syntax erro, to ReportErrorMarker
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void reportError(Parser recognizer, RecognitionException e) {
		ReportErrorMarker.reportError(recognizer);
	}

	/*
    @Override
    public void reportMatch(Parser recognizer) {
    	beginErrorCondition(recognizer);
    	Token t = recognizer.getCurrentToken();
    	String msg = "Error report match " + getTokenErrorDisplay(t);
    	//throw new RecognitionException(msg, recognizer, recognizer.getInputStream(), recognizer.getContext());
    	System.out.println(msg);
    }
	 */


}
