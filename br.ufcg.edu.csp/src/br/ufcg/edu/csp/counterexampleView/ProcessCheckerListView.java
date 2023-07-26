package br.ufcg.edu.csp.counterexampleView;

import java.io.File;

import javax.inject.Inject;

import org.antlr.v4.runtime.tree.ParseTree;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import br.ufcg.edu.csp.CSPDocumentProvider;
import br.ufcg.edu.csp.fdrAnalyser.DeadlockChecker;
import br.ufcg.edu.csp.fdrAnalyser.DeterministicChecker;
import br.ufcg.edu.csp.fdrAnalyser.DivergenceChecker;
import br.ufcg.edu.csp.fdrAnalyser.FDRChecker;
import br.ufcg.edu.csp.fdrAnalyser.FDRServices;
import br.ufcg.edu.csp.parser.CspParser;
import br.ufcg.edu.csp.utils.CSPViewsContentProvider;
import br.ufcg.edu.csp.utils.CheckerNodeFactory;


public class ProcessCheckerListView extends ViewPart implements IDocumentListener {
	@Inject IWorkbench workbench;

	private TableViewer viewer;
	private Action divergenceChecker;
	private Action determinismChecker;
	private Action deadlockChecker;
	private Action doubleClickAction;

	@SuppressWarnings("unchecked")
	@Override
	public void createPartControl(Composite parent) {
		// ISelectionChangedListner
		//addDocumentListner();

		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);

		viewer.setContentProvider(new CSPCheckerContentProvider(new CheckerNodeFactory()));
		viewer.setLabelProvider(new CSPViewLabelProvider());
		viewer.setInput((((CSPCheckerContentProvider) viewer.getContentProvider()).getTree()));	   

		// Create the help context id for the viewer's control
		//Control control = viewer.getControl();
		//workbench.getHelpSystem().setHelp(control, "br.ufcg.edu.csp.processcheckerview");
		getSite().setSelectionProvider(viewer);
		makeActions();
		hookContextMenu();
	}
	
	@SuppressWarnings("unchecked")
	public void updateContent() {
		viewer.setInput((((CSPCheckerContentProvider) viewer.getContentProvider()).getTree()));
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

	private void fillContextMenu(IMenuManager manager) {
		if(determinismChecker != null && deadlockChecker != null && divergenceChecker != null) {
			manager.add(determinismChecker);
			manager.add(deadlockChecker);
			manager.add(divergenceChecker);
			manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
		}
	}

	private void makeActions() {
		String cspFileName = getEditorFileName();

		if(cspFileName != null) {
			determinismChecker = new Action() {
				public void run() {
					try{ 
						FDRChecker checker = new DeterministicChecker(cspFileName);
						checkNode(checker);
					} catch (RuntimeException re) {
						// TODO show dialog
					}
				}
			};

			determinismChecker.setText("Check Determinism");
			determinismChecker.setImageDescriptor(getImageDescriptor());

			deadlockChecker = new Action() {
				public void run() {
					try{
						FDRChecker checker = new DeadlockChecker(cspFileName);
						checkNode(checker);
					} catch (RuntimeException re) {
						// TODO show dialog
					}
					
				}
			};

			deadlockChecker.setText("Check Deadlock");
			deadlockChecker.setImageDescriptor(getImageDescriptor());

			divergenceChecker = new Action() {
				public void run() {
					try {
						FDRChecker checker = new DivergenceChecker(cspFileName);
						checkNode(checker);
					} catch (RuntimeException re) {
						// TODO show dialog
					}
				}
			};

			divergenceChecker.setText("Check Divergence");
			divergenceChecker.setImageDescriptor(getImageDescriptor());
			
			doubleClickAction = new Action() {
				public void run() {
					doubleClick();
				}
			};
			
			hookDoubleClickAction();
		}
	}

	private void checkNode(FDRChecker checker) {
		IStructuredSelection selection = viewer.getStructuredSelection();
		Object obj = selection.getFirstElement();
		if(obj instanceof CheckerNodeDecorator) {
			CheckerNodeDecorator node = (CheckerNodeDecorator) obj;
			if(checker instanceof DeadlockChecker) {
				((CheckerNodeDecorator) obj).setIsDeadlock(true);
			}
			try {
				boolean checkCondition = checker.checkProcess(node.getNodeName()); // capturar um boolean
				node.setAssertionText(checker.getAssertionText(node.getNodeName()));
				node.setCheckCondition(checkCondition);
				
				if(!node.getCheckCondition()) {
					String[] nodeCounterexamples = checker.getCounterExamples(node.getNodeName());
					node.setCounterexamples(nodeCounterexamples);
				}
				
				updateCheckerNodeList(node);
			} catch (NullPointerException e) {
				//TODO CAPTURAR O ERRO E IMPRIMIR
			}
		}
	}

	private void updateCheckerNodeList(CheckerNodeDecorator node) {
		CheckerNodeListSingleton checkerNodeList = CheckerNodeListSingleton.getInstance();
		checkerNodeList.updateList(node);
	}

	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	private String getEditorFileName() {
		File editorFile = CSPDocumentProvider.getEditorFile();
		if(editorFile != null) {
			return editorFile.getAbsolutePath();
		}
		return null;
	}

	private ImageDescriptor getImageDescriptor() {
		return PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK);
	}

	@Override
	public void documentAboutToBeChanged(DocumentEvent event) {

	}

	@Override
	public void documentChanged(DocumentEvent event) {
		updateContent();
	}

	public void doubleClick() {
		IStructuredSelection selection = viewer.getStructuredSelection();
		Object obj = selection.getFirstElement();
		
		if(obj instanceof CheckerNodeDecorator
				&& ((CheckerNodeDecorator) obj).getNode() instanceof CspParser.AssertDefinitionContext) {		
			CheckerNodeDecorator node = ((CheckerNodeDecorator) obj);
			
			if(node.getNode().getText().toLowerCase().contains("deadlock")) {
				node.setIsDeadlock(true);
			}
			try {
				FDRServices checker = new FDRServices(getEditorFileName());	
				
				String assertString = node.toString().substring(6); // length to remove assert token
				boolean checkCondition = checker.checkAssertion(assertString);
				
				node.setAssertionText(assertString);
				node.setCheckCondition(checkCondition);
				
				if(!node.getCheckCondition()) {
					String[] nodeCounterexamples = checker.getCounterExamples(assertString);
					node.setCounterexamples(nodeCounterexamples);
				}
				
				updateCheckerNodeList(node);
			} catch (NullPointerException e) {
				//TODO ABRIR UM DIALOG MANDANDO CORRIGIR
			}
		}
		
	}
	
	private void hookDoubleClickAction() {
		viewer.addDoubleClickListener(o -> doubleClickAction.run());
	}

}
