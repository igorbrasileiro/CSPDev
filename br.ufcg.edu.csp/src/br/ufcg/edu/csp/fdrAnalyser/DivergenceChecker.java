package br.ufcg.edu.csp.fdrAnalyser;

import java.util.ArrayList;

import uk.ac.ox.cs.fdr.Assertion;

public class DivergenceChecker extends FDRServices implements FDRChecker {
	
	private static final String CHECKER_DECLARATION = " :[divergence free [FD]]";

	public DivergenceChecker(String fileName) {
		super(fileName);
	}

	@Override
	public String checkProcess(String processName) {
		return checkDivergenceFree(processName);
	}

	@Override
	public String[] getCounterExamples(String processName) {
		String assertString = processName + CHECKER_DECLARATION;

		Assertion assertion = getAssertion(assertString);
		
		ArrayList<String> listCounterExample = new ArrayList<>();
		
		describeCounterexample(assertion, listCounterExample);
		
		return listCounterExample.toArray(new String[1]);
	}
	
	private String checkDivergenceFree(String processName) {
		// :[divergence free [FD]]
		String assertString = processName + CHECKER_DECLARATION;
		
		Assertion divergenceAssert = getAssertion(assertString);
		
		String result = null;
		if(divergenceAssert != null) {
			result = "Divergence: " + (divergenceAssert.passed() ? "Passed" : "Failed");
		}
		
		return result;
	}
}
