package br.ufcg.edu.csp.fdrAnalyser;


import java.util.ArrayList;

import uk.ac.ox.cs.fdr.Assertion;

public class DeterministicChecker extends FDRServices implements FDRChecker {
	
	private static final String CHECKER_DECLARATION = " :[deterministic [FD]]";

	public DeterministicChecker(String fileName) {
		super(fileName);
	}

	@Override
	public Boolean checkProcess(String processName) {
		return checkDeterministic(processName);
	}
	
	@Override
	public String[] getCounterExamples(String processName) {
		String assertString = getAssertionText(processName);

		Assertion assertion = getAssertion(assertString);
		
		ArrayList<String> listCounterExample = new ArrayList<>();
		
		describeCounterexample(assertion, listCounterExample);
		
		return listCounterExample.toArray(new String[1]);
	}
	
	public Boolean checkDeterministic(String processName) {
		//assert P :[deterministic]
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
