package br.ufcg.edu.csp.counterexampleView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;
import org.eclipse.ui.part.ViewPart;

import br.ufcg.edu.csp.CSPDocumentProvider;
import br.ufcg.edu.csp.jetty.FileServer;
import graphvizJavaPloting.GraphvizJava;


public class CounterexampleListView extends ViewPart {

	private static final String directory = "/Outputs";
	private static final String htmlPage ="/counterExamplePage.html";
	
	private TableViewer viewer;
	private CheckerNodeListSingleton cnls;
	private Action doubleClickAction;
	private String projectFilePath;
	private GraphvizJava cgs;
	private FileServer fileServer;
	
	@Override
	public void createPartControl(Composite parent) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		viewer.setContentProvider(new CounterexampleContentProvider());
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
				//System.out.println("Double-click detected on "+obj.toString());
				if(obj instanceof CheckerNodeDecorator 
						&& !((CheckerNodeDecorator)obj).getCheckCondition()) {
					openCounterexampleBrowser(((CheckerNodeDecorator) obj).getCounterexamples());
				}
				
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
			cgs.ploting(nodes);
			
			startServer(projectFilePath+directory, htmlPage);
			//System.out.println(file);
			//System.out.println(urlFile);
			
			URL localHost = new URL("http://localhost:8080");
			
			browser.openURL(localHost);
			
		} catch (NullPointerException ceb) {
			ceb.printStackTrace();
			System.out.println("erro counterexample list abrir pagina web");
		} catch (PartInitException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void startServer(String directory, String htmlPagePath) {
		if(fileServer == null) {
			fileServer = new FileServer(directory,htmlPagePath);
			fileServer.startServer();
		}
	}
	
	private void createCounterExamplesFiles() throws IOException {
		if(projectFilePath == null) {
			projectFilePath = CSPDocumentProvider.getEditorFile().getParentFile().getAbsolutePath();
		}
		
		createOutputDirectory();
		
		if(cgs == null) {
			cgs = new GraphvizJava(projectFilePath+directory, htmlPage);
		}
	}

	private void createOutputDirectory() {
		File f = new File(projectFilePath+directory);
		if(!f.exists()) {
			f.mkdir();
		}
		
		//return f.getPath();
	}
}
