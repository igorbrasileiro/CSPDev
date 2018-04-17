package br.ufcg.edu.csp.fdrAnalyser;

import java.io.PrintStream;
import java.util.ArrayList;

import uk.ac.ox.cs.fdr.Assertion;
import uk.ac.ox.cs.fdr.Behaviour;
import uk.ac.ox.cs.fdr.Counterexample;
import uk.ac.ox.cs.fdr.DebugContext;
import uk.ac.ox.cs.fdr.PropertyCounterexample;
import uk.ac.ox.cs.fdr.RefinementCounterexample;
import uk.ac.ox.cs.fdr.Session;
import uk.ac.ox.cs.fdr.fdr;

public class FDRServices {

	protected Session session;

	public FDRServices(String fileName) {
		session = new Session();
		
		loadFile(fileName);
		//System.loadLibrary("");
	}
	
	private void loadFile(String fileName) {
		if(session == null ) {
			session = new Session();
		}
		try {
			session.loadFile(fileName);
		} catch(RuntimeException re) {
			re.printStackTrace();
		}
	}
	
	// Dead Method
	/**
	 * This method return a list with assertions strings from process given. 
	 * First string is a Deadlock assertion. Second string is a Divergence assertion.
	 * @param processName - the name of process that will be checked
	 * @return ArrayList<String> 
	 */
	/*private ArrayList<String> checkProcessAssertions(String processName) {
		ArrayList<String> result = new ArrayList<>();
		
		// criar os 3 métodos
		result.add(checkDeadlockFree(processName));
		result.add(checkDeterministic(processName));
		result.add(checkDivergenceFree(processName));
		
		return result;
	}
	*/
	
	// necessários deadlock, livelock e deterministic 
	
	protected Assertion getAssertion(String assertion) {
		try {
			Assertion newAssertion = session.parseAssertion(assertion).result();
			newAssertion.execute(null);
			return newAssertion;
		} catch (RuntimeException re) {
			return null;
		}
		
	}
	
	protected void describeCounterexample(Assertion assertion,ArrayList<String> list) {
		for (Counterexample counterexample : assertion.counterexamples()) {
			
			DebugContext debugContext = null;

			if (counterexample instanceof RefinementCounterexample)
				debugContext = new DebugContext((RefinementCounterexample) counterexample, false);
			else if (counterexample instanceof PropertyCounterexample)
				debugContext = new DebugContext((PropertyCounterexample) counterexample, false);
			
			debugContext.initialise(null);
			Behaviour root = debugContext.rootBehaviours().get(0);
			describeBehaviour(debugContext, root, list);
		}
	}
	
	private void describeBehaviour(DebugContext debugContext,
			Behaviour behaviour, ArrayList<String> list) {
	
		for (Long event : behaviour.trace()) {
			// INVALIDEVENT indiciates that this machine did not perform an event at
			// the specified index (i.e. it was not synchronised with the machines
			// that actually did perform the event).
			if (event == fdr.INVALIDEVENT) {
				list.add("-");
			} else if(session.uncompileEvent(event).toString().equals("?")) {
				list.add("?");
			} else {
				list.add(session.uncompileEvent(event).toString());
			}
				
		}
		
		/*
		 if (recurse) {
			for (Behaviour child : debugContext.behaviourChildren(behaviour))
				describeBehaviour(debugContext, child, indent + 2, true);
		} 
		*/
	}
}
