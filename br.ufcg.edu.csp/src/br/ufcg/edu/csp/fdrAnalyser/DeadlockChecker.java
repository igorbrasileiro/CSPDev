package br.ufcg.edu.csp.fdrAnalyser;

import uk.ac.ox.cs.fdr.Assertion;

public class DeadlockChecker extends FDRServices implements FDRChecker {

	
	public DeadlockChecker(String fileName) {
		super(fileName);
	}

	@Override
	public String checkProcess(String processName) {
		return checkDeadlockFree(processName);
	}

	public String checkDeadlockFree(String processName) {
		// :[deadlock free [FD]]
		String assertString = processName+ " " + ":[deadlock free [FD]]";
		
		Assertion assertion = getAssertion(assertString);
		String result = null;
		if(assertion != null) {
			 result = "Deadlock: " + (assertion.passed() ? "Passed" : "Failed");
		}
		
		describeCounterexample(assertion);
		
		return result;
	}
}
