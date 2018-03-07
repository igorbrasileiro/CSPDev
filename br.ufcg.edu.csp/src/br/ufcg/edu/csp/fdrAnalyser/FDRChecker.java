package br.ufcg.edu.csp.fdrAnalyser;

public interface FDRChecker {

	public String checkProcess(String processName);
	
	public String[] getCounterExamples(String processName);
	
}
