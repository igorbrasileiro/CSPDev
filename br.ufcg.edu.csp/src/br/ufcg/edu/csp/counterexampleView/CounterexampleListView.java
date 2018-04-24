package br.ufcg.edu.csp.counterexampleView;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;
import org.eclipse.ui.part.ViewPart;

import br.ufcg.edu.csp.CSPDocumentProvider;
import br.ufcg.edu.csp.fdrAnalyser.CSPGraphServices;


public class CounterexampleListView extends ViewPart {

	private ListViewer viewer;
	private CheckerNodeListSingleton cnls;
	private Action doubleClickAction;
	private String projectFilePath;
	
	@Override
	public void createPartControl(Composite parent) {
		viewer = new ListViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		viewer.setContentProvider(ArrayContentProvider.getInstance());
		viewer.setLabelProvider(new CSPViewLabelProvider());
		
		cnls = CheckerNodeListSingleton.getInstance();
		Object[] content = cnls.getListContent();
		if(content != null)
			viewer.setInput(content);
		
		createDoubleClickAction();
		hookDoubleClickAction();
	}

	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}
	
	public void updateContent(Object[] content) {
		viewer.setInput(content);
	}

	private void createDoubleClickAction() {
		doubleClickAction = new Action() {
			public void run() {
				IStructuredSelection selection = viewer.getStructuredSelection();
				Object obj = selection.getFirstElement();
				// chamar o contra exemplo
				System.out.println("Double-click detected on "+obj.toString());
				openCounterexampleBrowser(((CheckerNodeDecorator) obj).getCounterexamples());
			}
		};
	}

	private void hookDoubleClickAction() {
		viewer.addDoubleClickListener(o -> doubleClickAction.run());
	}
	
	private void openCounterexampleBrowser(String[] nodes) {
		try{
			IWorkbenchBrowserSupport support = PlatformUI.getWorkbench().getBrowserSupport();
			// trocar instancia caso queira um único browser
			IWebBrowser browser = support.createBrowser(IWorkbenchBrowserSupport.AS_EDITOR, null, "CSP Editor", "Checker counterexample");
			
			createCounterExamplesFiles();
			
			CSPGraphServices cgs = new CSPGraphServices();
			cgs.plotGraph(nodes);
			
			File file = new File(projectFilePath+"/Outputs/counterExamplePage.html");
			
			URL urlFile = file.toURI().toURL();
			
			System.out.println(file);
			System.out.println(urlFile);
			
			browser.openURL(urlFile);
			
		} catch (Exception ceb) {
			ceb.printStackTrace();
			System.out.println("erro counterexample list abrir pagina web");
		}
		
	}
	
	private void createCounterExamplesFiles() throws IOException {
		projectFilePath = CSPDocumentProvider.getEditorFile().getParentFile().getAbsolutePath();

		File f = new File(projectFilePath+"/Outputs");
		f.mkdir();
		
		//System.out.println(f.getAbsolutePath());
		//FileWriter fw = new FileWriter(f.getAbsoluteFile()+"/counterExampleGraph.json")
	}
}
