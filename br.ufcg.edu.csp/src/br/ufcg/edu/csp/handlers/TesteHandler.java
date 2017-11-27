package br.ufcg.edu.csp.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.jface.dialogs.MessageDialog;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class TesteHandler extends AbstractHandler {

	private static final String deterministic = "br.ufcg.edu.csp.commands.deterministic";
	private static final String deadlock = "br.ufcg.edu.csp.commands.deadlock";
	private static final String divergence ="br.ufcg.edu.csp.commands.divergence";
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		// verificar o tipo do botao - deadlock - divergencia ..
		// pesquisar todos os processos
		// listar todos os processos e criar botoes
		
		String eventId = event.getCommand().getId();
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		MessageDialog.openInformation(
				window.getShell(),
				"Csp", eventId.equals(deadlock) ? "Deadlock" : (eventId.equals(divergence) ? "Divergence" : (eventId.equals(deterministic) ? "Deterministic" : "Comand Failed"))
				);
		return null;
	}
}
