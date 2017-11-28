package br.ufcg.edu.csp.fdrAnalyser;

public class DivergenceChecker implements FDRChecker {

	@Override
	public String checkProcess(FDRServices service, String processName) {
		return service.checkDivergenceFree(processName);
	}

}
