package test;

import uk.ac.ox.cs.fdr.Assertion;
import uk.ac.ox.cs.fdr.Behaviour;
import uk.ac.ox.cs.fdr.CompiledEventList;
import uk.ac.ox.cs.fdr.Counterexample;
import uk.ac.ox.cs.fdr.CounterexampleList;
import uk.ac.ox.cs.fdr.DeadlockCounterexample;
import uk.ac.ox.cs.fdr.DeterminismCounterexample;
import uk.ac.ox.cs.fdr.Session;

public class FDRServiceTest {
	
	Session session;
	
	public FDRServiceTest() {
		System.loadLibrary("libfdr_java");
		session = new Session();
		
		session.loadFile("C:\\Users\\sixbd\\runtime-EclipseApplication\\Projeto.Teste.CSP\\arquivo_teste.csp");
	}
	
	public void getTraceUpDownDeadlock() {
		String processName = "UpDown";
		
		String assertString = processName+ " " + ":[deadlock free [FD]]";
		
		Assertion deadlockAssert = getAssertion(assertString);
		
		CounterexampleList celist = deadlockAssert.counterexamples();
		
		for(Counterexample ce : celist) {
			DeadlockCounterexample dce = ((DeadlockCounterexample) ce);
			Behaviour counterexampleBehaviour = dce.behaviour();
			CompiledEventList cel = counterexampleBehaviour.trace();
			
			StringBuilder sb = new StringBuilder();
			sb.append(processName + " Deadlock counterexample trace: ");
			
			for(Long event: cel) {
				sb.append(session.uncompileEvent(event).toString());
				sb.append("->");
			}
			System.out.println(sb.toString());
		}
	}
	
	public void getTraceUpDownInternalDeterministic() {
		String processName = "P2";
		
		String assertString = processName+ " " + ":[deterministic [FD]]";
		
		Assertion deterministicAssert = getAssertion(assertString);
		
		CounterexampleList celist = deterministicAssert.counterexamples();
		
		for(Counterexample ce : celist) {
			DeterminismCounterexample dce = ((DeterminismCounterexample) ce);
			//dce.implementationBehaviour().machine().minimalAcceptances(dce.implementationBehaviour().machine().rootNode());
			Behaviour counterexampleBehaviour = dce.implementationBehaviour();
			CompiledEventList cel = counterexampleBehaviour.trace();
			
			StringBuilder sb = new StringBuilder();
			sb.append(processName + " Deterministic counterexample trace: ");
			
			for(Long event: cel) {
				sb.append(session.uncompileEvent(event).toString());
				sb.append("->");
			}
			System.out.println(sb.toString());
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
	   FDRServiceTest fdrteste = new FDRServiceTest();
	   //TODO Criar Debug Context com contra exemplo;
	   fdrteste.getTraceUpDownDeadlock();
	   fdrteste.getTraceUpDownInternalDeterministic();
	   fdrteste.getTraceUpDownInternalDeterministic();
   }
}
