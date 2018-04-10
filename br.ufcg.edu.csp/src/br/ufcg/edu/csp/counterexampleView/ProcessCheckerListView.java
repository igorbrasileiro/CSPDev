package br.ufcg.edu.csp.counterexampleView;

import java.io.File;
import java.util.ArrayList;

import javax.inject.Inject;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
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
import br.ufcg.edu.csp.outline.ExpressionNodeDecorator;
import br.ufcg.edu.csp.parser.CspParser;
import br.ufcg.edu.csp.parser.ParserUtil;


public class ProcessCheckerListView extends ViewPart {
@Inject IWorkbench workbench;
	
	private TableViewer viewer;
	private Action divergenceChecker;
	private Action determinismChecker;
	private Action deadlockChecker;
	private Action doubleClickAction;
	private ExpressionNodeDecorator[] allViewContent;
	 

	class ViewLabelProvider extends LabelProvider implements ITableLabelProvider {
		@Override
		public String getColumnText(Object obj, int index) {
			return getText(obj);
		}
		@Override
		public Image getColumnImage(Object obj, int index) {
			return getImage(obj);
		}
		@Override
		public Image getImage(Object obj) {
			return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
		}
	}

	@Override
	public void createPartControl(Composite parent) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
				
		viewer.setContentProvider(ArrayContentProvider.getInstance());
		viewer.setInput(allViewContent);
		updateContent();
	    viewer.setLabelProvider(new ViewLabelProvider());
	    
		// Create the help context id for the viewer's control
		//workbench.getHelpSystem().setHelp(viewer.getControl(), "br.ufcg.edu.csp.graphviz.viewer");
		getSite().setSelectionProvider(viewer);
		makeActions();
		hookContextMenu();
		//hookDoubleClickAction();
		//contributeToActionBars(); criar os botões
	}

	public void updateContent() {
		if(this.allViewContent == null) {
			this.allViewContent = new ExpressionNodeDecorator[1];
		}
		ParserRuleContext tree = ParserUtil.getRootFromTextEditor();
		ArrayList<ExpressionNodeDecorator> listContent = new ArrayList<>();
		
		addProcessToList(tree, listContent);
		//addAsserrtsToList
		// add assert rule a definition na gramatica
		
		this.allViewContent = listContent.toArray(this.allViewContent);
		
		viewer.setInput(this.allViewContent);
	}
	
	private void addProcessToList(ParseTree node, ArrayList<ExpressionNodeDecorator> list) {
		if(node instanceof CspParser.SpecContext) {
			int children = ((ParseTree) node).getChildCount();
			for (int i = 0; i < children; i++) {
				ParseTree newNode = ((ParseTree) node).getChild(i);
				addProcessToList(newNode, list);
			}
		} else if(node instanceof CspParser.DefinitionContext) {
			// regra de filho unico
			ParseTree newNode = ((ParseTree) node).getChild(0);
			addProcessToList(newNode, list);
		} else if(node instanceof CspParser.SimpleDefinitionContext) {
			list.add(new ExpressionNodeDecorator(node));
		}
	}
	
	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				ProcessCheckerListView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(determinismChecker);
		manager.add(new Separator());
		manager.add(deadlockChecker);
		manager.add(new Separator());
		manager.add(divergenceChecker);
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(determinismChecker);
		manager.add(deadlockChecker);
		manager.add(divergenceChecker);
		
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	private void makeActions() {
		String cspFileName = getEditorFileName();
		FDRChecker checker;
		
		determinismChecker = new Action() {
			public void run() {
				FDRChecker checker = new DeterministicChecker(cspFileName);
				IStructuredSelection selection = viewer.getStructuredSelection();
				Object obj = selection.getFirstElement();
				showMessage("Teste Determinism "+obj.toString());
			}
		};
		
		determinismChecker.setText("Check Determinism");
		determinismChecker.setImageDescriptor(getImageDescriptor());
		
		deadlockChecker = new Action() {
			public void run() {
				FDRChecker checker = new DeadlockChecker(cspFileName);
				IStructuredSelection selection = viewer.getStructuredSelection();
				Object obj = selection.getFirstElement();
				showMessage("Teste deadlock "+obj.toString());
			}
		};
		
		deadlockChecker.setText("Check Deadlock");
		deadlockChecker.setImageDescriptor(getImageDescriptor());
		
		divergenceChecker = new Action() {
			public void run() {
				FDRChecker checker = new DivergenceChecker(cspFileName);
				IStructuredSelection selection = viewer.getStructuredSelection();
				Object obj = selection.getFirstElement();
				showMessage("Teste divergence "+obj.toString());
			}
		};
		
		divergenceChecker.setText("Check Divergence");
		divergenceChecker.setImageDescriptor(getImageDescriptor());
		
		/*doubleClickAction = new Action() {
			public void run() {
				IStructuredSelection selection = viewer.getStructuredSelection();
				Object obj = selection.getFirstElement();
				showMessage("Double-click detected on "+obj.toString());
			}
		};
		*/
	}

	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}
	
	private String getEditorFileName() {
		File editorFile = CSPDocumentProvider.getEditorFile();
		return editorFile.getAbsolutePath();
	}
	
	private ImageDescriptor getImageDescriptor() {
		return PlatformUI.getWorkbench().getSharedImages().
		getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK);
	}
	
	// dead

	private void showMessage(String message) {
		MessageDialog.openInformation(
			viewer.getControl().getShell(),
			"Sample View",
			message);
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}
	
	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(determinismChecker);
		manager.add(deadlockChecker);
	}
	
	private void hookDoubleClickAction() {
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				doubleClickAction.run();
			}
		});
	}
}
