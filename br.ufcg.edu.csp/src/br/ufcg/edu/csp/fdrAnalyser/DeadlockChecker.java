package br.ufcg.edu.csp.fdrAnalyser;

public class DeadlockChecker implements FDRChecker {

	@Override
	public String checkProcess(FDRServices service, String processName) {
		return service.checkDeadlockFree(processName);
	}

}
