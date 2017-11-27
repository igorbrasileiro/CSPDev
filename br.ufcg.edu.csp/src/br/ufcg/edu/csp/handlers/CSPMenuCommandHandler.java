package br.ufcg.edu.csp.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

public class CSPMenuCommandHandler extends AbstractHandler {
	
	private static final String deterministic = "br.ufcg.edu.csp.commands.deterministic";
	private static final String deadlock = "br.ufcg.edu.csp.commands.deadlock";
	private static final String divergence ="br.ufcg.edu.csp.commands.divergence";
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		String eventId = event.getCommand().getId();
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		MessageDialog.openInformation(
				window.getShell(),
				"Csp", eventId.equals(deadlock) ? "Deadlock" : (eventId.equals(divergence) ? "Divergence" : (eventId.equals(deterministic) ? "Deterministic" : "Comand Failed"))
				);
		return null;
	}

}
