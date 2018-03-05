package br.ufcg.edu.csp.fdrAnalyser;


import uk.ac.ox.cs.fdr.Assertion;

public class DeterministicChecker extends FDRServices implements FDRChecker {

	public DeterministicChecker(String fileName) {
		super(fileName);
	}

	@Override
	public String checkProcess(String processName) {
		return checkDeterministic(processName);
	}
	
	public String checkDeterministic(String processName) {
		//assert P :[deterministic]
		String assertString = processName + " " + ":[deterministic [FD]]";
		
		Assertion assertion = getAssertion(assertString);
		String result = null;
		if(assertion != null ) {
			result = "Deterministic: " + (assertion.passed() ? "Passed" : "Failed" + " add falha");
		}
		
		return result;
	}
}
