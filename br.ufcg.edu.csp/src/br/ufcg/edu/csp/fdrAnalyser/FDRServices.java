package br.ufcg.edu.csp.fdrAnalyser;

import java.util.ArrayList;

import uk.ac.ox.cs.fdr.Assertion;
import uk.ac.ox.cs.fdr.Session;

public class FDRServices {

	private Session session;

	public FDRServices(String fileName) {
		session = new Session();
		session.loadFile(fileName);
	}
	
	public ArrayList<String> checkProcessAssertions(String processName) {
		ArrayList<String> result = new ArrayList<>();
		
		// criar os 3 métodos
		
		
		
		return result;
	}
	/*
	 * Assertion assertTeste = session.parseAssertion("UpDownP [T= OPEN").result();
		assertTeste.execute(null);
		System.out.println(assertTeste.passed());
	 */
}
