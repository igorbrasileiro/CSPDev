package br.ufcg.edu.csp.fdrAnalyser;

import uk.ac.ox.cs.fdr.Assertion;

public class DivergenceChecker extends FDRServices implements FDRChecker {

	public DivergenceChecker(String fileName) {
		super(fileName);
	}

	@Override
	public String checkProcess(String processName) {
		return checkDivergenceFree(processName);
	}

	private String checkDivergenceFree(String processName) {
		// :[divergence free [FD]]
		String assertString = processName+ " " + ":[divergence free [FD]]";
		
		Assertion divergenceAssert = getAssertion(assertString);
		
		String result = null;
		if(divergenceAssert != null) {
			result = "Divergence: " + (divergenceAssert.passed() ? "Passed" : "Failed");
		}
		
		
		return result;
	}
}
