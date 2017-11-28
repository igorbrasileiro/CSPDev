package br.ufcg.edu.csp.fdrAnalyser;

public class DeterministicChecker implements FDRChecker {

	@Override
	public String checkProcess(FDRServices service, String processName) {
		return service.checkDeterministic(processName);
	}

}
