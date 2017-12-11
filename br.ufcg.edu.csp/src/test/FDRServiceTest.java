package test;

import uk.ac.ox.cs.fdr.Assertion;
import uk.ac.ox.cs.fdr.Counterexample;
import uk.ac.ox.cs.fdr.CounterexampleList;
import uk.ac.ox.cs.fdr.Session;

public class FDRServiceTest {
	
	Session session;
	
	public FDRServiceTest() {
		session = new Session();
		
		session.loadFile("C:\\Users\\sixbd\\runtime-EclipseApplication\\Projeto.Teste.CSP\\arquivo_teste.csp");
	}
	
	public void getTrace() {
		String processName = "UpDown";
		
		String assertString = processName+ " " + ":[deadlock free [FD]]";
		
		Assertion deadlockAssert = getAssertion(assertString);
		
		CounterexampleList celist = deadlockAssert.counterexamples();
		
		for(Counterexample ce : celist) {
			ce.toString();
		}
	}

	private Assertion getAssertion(String assertion) {
		try {
			Assertion newAssertion = session.parseAssertion(assertion).result();
			newAssertion.execute(null);
			return newAssertion;
		} catch (RuntimeException re) {
			return null;
		}
		
	}
	
   public static void main(String[] args) {
	   
   }
}
