package br.ufcg.edu.csp.counterexampleView;

import java.io.File;

import javax.inject.Inject;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocumentListener;
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
import br.ufcg.edu.csp.utils.CSPViewsContentProvider;
import br.ufcg.edu.csp.utils.CheckerNodeFactory;


public class ProcessCheckerListView extends ViewPart implements IDocumentListener {
	@Inject IWorkbench workbench;

	private TableViewer viewer;
	private Action divergenceChecker;
	private Action determinismChecker;
	private Action deadlockChecker;

	@SuppressWarnings("unchecked")
	@Override
	public void createPartControl(Composite parent) {
		// ISelectionChangedListner
		//TODO qual melhor? assim add document listner ou la no document provider?
		//addDocumentListner();

		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);

		viewer.setContentProvider(new CSPViewsContentProvider<CheckerNodeDecorator>(new CheckerNodeFactory()));
		viewer.setLabelProvider(new CSPViewLabelProvider());
		viewer.setInput((((CSPViewsContentProvider<CheckerNodeDecorator>) viewer.getContentProvider()).getTree()));	   

		// Create the help context id for the viewer's control
		//Control control = viewer.getControl();
		//workbench.getHelpSystem().setHelp(control, "br.ufcg.edu.csp.processcheckerview");
		getSite().setSelectionProvider(viewer);
		makeActions();
		hookContextMenu();
	}
	
	@SuppressWarnings("unchecked")
	public void updateContent() {
		viewer.setInput((((CSPViewsContentProvider<CheckerNodeDecorator>) viewer.getContentProvider()).getTree()));
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
					FDRChecker checker = new DeterministicChecker(cspFileName);
					checkNode(checker);
				}
			};

			determinismChecker.setText("Check Determinism");
			determinismChecker.setImageDescriptor(getImageDescriptor());

			deadlockChecker = new Action() {
				public void run() {
					FDRChecker checker = new DeadlockChecker(cspFileName);
					checkNode(checker);
				}
			};

			deadlockChecker.setText("Check Deadlock");
			deadlockChecker.setImageDescriptor(getImageDescriptor());

			divergenceChecker = new Action() {
				public void run() {
					FDRChecker checker = new DivergenceChecker(cspFileName);
					checkNode(checker);
				}
			};

			divergenceChecker.setText("Check Divergence");
			divergenceChecker.setImageDescriptor(getImageDescriptor());
		}
	}

	private void checkNode(FDRChecker checker) {
		IStructuredSelection selection = viewer.getStructuredSelection();
		Object obj = selection.getFirstElement();
		if(obj instanceof CheckerNodeDecorator) {
			// TODO: fazer verificação se nó tinha condicao falsa e agora é true e remover ele da lista
			boolean checkCondition = checker.checkProcess(obj.toString()); // capturar um boolean
			((CheckerNodeDecorator) obj).setCheckCondition(checkCondition);
			updateCheckerNodeList(checker,(CheckerNodeDecorator) obj);
		}
	}

	private void updateCheckerNodeList(FDRChecker checker, CheckerNodeDecorator node) {
		CheckerNodeListSingleton checkerNodeList = CheckerNodeListSingleton.getInstance();
		String[] nodeCounterexamples = checker.getCounterExamples(node.toString());	
		if(!node.getCheckCondition())
			node.setCounterexamples(nodeCounterexamples);
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

	/* CODIGO UTIL
	 * for(IWorkbenchWindow workbench : PlatformUI.getWorkbench().getWorkbenchWindows()) {
			for(IWorkbenchPage workbenchPage : workbench.getPages()) {
				workbenchPage.getPart(); //nome da viewpart
			}
		}
	 */

}
