package br.ufcg.edu.csp.fdrAnalyser;

import java.util.ArrayList;

import uk.ac.ox.cs.fdr.Assertion;

public class DeadlockChecker extends FDRServices implements FDRChecker {

	private static final String CHECKER_DECLARATION = " :[deadlock free [FD]]";

	public DeadlockChecker(String fileName) {
		super(fileName);
	}

	@Override
	public Boolean checkProcess(String processName) {
		return checkDeadlockFree(processName);
	}
	
	@Override
	public String[] getCounterExamples(String processName) {
		String assertString = getAssertionText(processName);

		Assertion assertion = getAssertion(assertString);
		
		ArrayList<String> listCounterExample = new ArrayList<>();
		
		describeCounterexample(assertion, listCounterExample);
		
		return listCounterExample.toArray(new String[1]);
	}

	public Boolean checkDeadlockFree(String processName) {
		// :[deadlock free [FD]]
		String assertString = getAssertionText(processName);

		Assertion assertion = getAssertion(assertString);
		Boolean result = null;
		if(assertion != null ) {
			result = assertion.passed();
		}
		
		return result;
	}

	@Override
	public String getAssertionText(String processName) {
		return processName + CHECKER_DECLARATION;
	}
}
