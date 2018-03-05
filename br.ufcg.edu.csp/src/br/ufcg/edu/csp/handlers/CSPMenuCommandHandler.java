package br.ufcg.edu.csp.handlers;

import java.io.File;
import java.util.ArrayList;

import org.antlr.v4.runtime.tree.ParseTree;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import br.ufcg.edu.csp.CSPDocumentProvider;
import br.ufcg.edu.csp.fdrAnalyser.DeadlockChecker;
import br.ufcg.edu.csp.fdrAnalyser.DeterministicChecker;
import br.ufcg.edu.csp.fdrAnalyser.DivergenceChecker;
import br.ufcg.edu.csp.fdrAnalyser.FDRChecker;
import br.ufcg.edu.csp.fdrAnalyser.FDRServices;
import br.ufcg.edu.csp.parser.CspParser;
import br.ufcg.edu.csp.parser.ParserUtil;

public class CSPMenuCommandHandler extends AbstractHandler {

	private static final String deterministic = "br.ufcg.edu.csp.commands.deterministic";
	private static final String deadlock = "br.ufcg.edu.csp.commands.deadlock";
	private static final String divergence ="br.ufcg.edu.csp.commands.divergence";
	private FDRServices fdrService;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		ArrayList<String> processList = getProcessName();

		String eventId = event.getCommand().getId();

		final Display display = Display.getCurrent();
		final Shell shell = new Shell (display, SWT.DIALOG_TRIM); 
		FDRChecker checker = createChecker(eventId);
		
		if(checker != null) {
			createProcessButtons(checker, shell, processList);
		}
		
		createShell(shell);
		
		return null;
	}

	private void createShell(Shell shell) {
		shell.layout(true, true);
		final Point newSize = shell.computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
		newSize.x += 90;
		newSize.y += 10;// adicionar a posição a esquerda
		shell.setSize(newSize);
		shell.open ();
	}
	
	private ArrayList<String> getProcessName() {
		// get root
		CspParser.SpecContext root = ParserUtil.getRootFromTextEditor();
		ArrayList<String> result = new ArrayList<>();

		getProcessName(root, result);
		return result;
	}

	private void getProcessName(ParseTree node, ArrayList<String> list) {
		if(node instanceof CspParser.SpecContext){
			// regra de muitos filhos
			int children = ( node).getChildCount();
			for (int i = 0; i < children; i++) {
				ParseTree newNode = ( node).getChild(i);
				getProcessName(newNode, list);
			}
		} else if(node instanceof CspParser.DefinitionContext) {
			// regra de filho unico
			ParseTree newNode = ( node).getChild(0);
			getProcessName(newNode, list);
		} else if(node instanceof CspParser.SimpleDefinitionContext) {
			// pegar somente o nome do processo.
			ParseTree newNode = ( node).getChild(0);
			getProcessName(newNode, list);
		} else if(node instanceof CspParser.DefinitionLeftContext) {
			if(node.getChildCount() > 1) {
				// no dividido em 4 parte = nome + ( + valor + ). Estou setando VALOR = 0 igual ao que o FDR faz
				list.add(node.getChild(0).getText()+"(0)");
			} else {
				list.add(node.getText());
			}
		}
	}

	private FDRChecker createChecker(String eventId) {
		String fileEditorName = getEditorFileName();
		FDRChecker checker = null;
		
		if(eventId.equals(deadlock)) {
			checker = new DeadlockChecker(fileEditorName);
		} else if(eventId.equals(deterministic)) {
			checker = new DeterministicChecker(fileEditorName);
		} else if(eventId.equals(divergence)) {
			checker = new DivergenceChecker(fileEditorName);
		}
		
		return checker;
	}
	
	private void createProcessButtons(FDRChecker checker, Shell shell, ArrayList<String> processList) {
		int startY = 10;
		if(checker != null) {
			for(String processName : processList) {
				Button btn = new Button( shell , SWT.PUSH );
				btn.setText(processName);
				btn.setBounds(90, startY, 200, 30);
				startY += 40; // 10 from top + 30 height from top button

				btn.addSelectionListener( new SelectionAdapter() {
					public void widgetSelected(SelectionEvent se) {
						//You can set the sixe of the Rectangle
						MessageDialog.openInformation(shell, "Process Information", checker.checkProcess(processName));
					}
				});

			}
		}
	}
	
	private String getEditorFileName() {
		File editorFile = CSPDocumentProvider.getEditorFile();
		return editorFile.getAbsolutePath();
	}
}
