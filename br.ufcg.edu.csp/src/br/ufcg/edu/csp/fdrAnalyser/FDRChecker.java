package br.ufcg.edu.csp.fdrAnalyser;

public interface FDRChecker {

	public Boolean checkProcess(String processName);
	
	public String[] getCounterExamples(String processName);
	
	public String getAssertionText(String processName);
	
}
