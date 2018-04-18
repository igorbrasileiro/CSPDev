package br.ufcg.edu.csp;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.editors.text.FileDocumentProvider;
import org.eclipse.ui.texteditor.AbstractTextEditor;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import br.ufcg.edu.csp.counterexampleView.ProcessCheckerListView;
import br.ufcg.edu.csp.errorReport.ReportErrorMarker;
import br.ufcg.edu.csp.outline.CSPOutlinePage;


public class CSPDocumentProvider extends FileDocumentProvider implements IDocumentListener {

	private ReportErrorMarker errorReport;
	
	public CSPDocumentProvider() {
		errorReport = ReportErrorMarker.getInstance();
		//TODO: remover isto aqui adicionar IDocumentListner a errorReport
	}
	
	@Override
	protected IDocument createDocument(Object element) throws CoreException {
		IDocument document = super.createDocument(element);
		if(document != null) {
			IDocumentPartitioner partitioner = new FastPartitioner(new CSPPartitionScanner(), 
					new String[] {CSPPartitionScanner.CSP_COMMENT});
			partitioner.connect(document);
			document.setDocumentPartitioner(partitioner);
			document.addDocumentListener(this);
		}
		
		return document;
	}
	
	@Override
	public void documentAboutToBeChanged(DocumentEvent event) {
		// nothing
	}

	@Override
	public void documentChanged(DocumentEvent event) {
		// limpar o array dos marcadores
		errorReport.cleanErrorList();
		
		updateOutlineView();
		updateProcessCheckerListView();
		
		errorReport.reportError();
		
	}
	
	private void updateProcessCheckerListView() {
		ProcessCheckerListView pclv = (ProcessCheckerListView) PlatformUI.getWorkbench().
				getActiveWorkbenchWindow().getActivePage().
					findView("br.ufcg.edu.csp.processcheckerview");
		if(pclv != null) {
			pclv.updateContent();
		}
	}

	public void updateOutlineView() {
		IWorkbenchPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart();
		CSPOutlinePage outlinePage = (CSPOutlinePage) Adapters.adapt(part, IContentOutlinePage.class);// aqui faz o parse 
		if(outlinePage != null)
			outlinePage.updateContent();
	}
	
	public static File getEditorFile() {
		IResource file = getEditorIFile();
		if(file != null) {
			return new File(file.getLocation().toString());
		}
		return null;
	}
	
	public static IResource getEditorIFile() {
		IWorkbench iworkbench = PlatformUI.getWorkbench();

		IWorkbenchWindow activeWorkbenchWindow = null;
		if(iworkbench != null) {
			activeWorkbenchWindow = iworkbench.getActiveWorkbenchWindow();
		}

		IWorkbenchPage activePage = null;
		if(activeWorkbenchWindow != null) {
			activePage = activeWorkbenchWindow.getActivePage();
		}

		IEditorPart activeEditor = null;
		if(activePage != null ) {
			activeEditor = activePage.getActiveEditor();
		}

		if(activeEditor != null) {
			return activeEditor.getEditorInput().getAdapter(IResource.class);
		}
		return null;
	}

	
	public static File[] findCSPFilesFromWorkspace() {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();  

		IProject[] workspaceDirectory = workspace.getRoot().getProjects();
		ArrayList<File> files = new ArrayList<>();
		for(IProject projeto : workspaceDirectory) {
			File[] filesArray = encontraFileEm(new File(projeto.getLocation().toString()));
			addFilesToListFromArray(files, filesArray);
		}

		File[] retorno = new File[1];
		return files.toArray(retorno);
	}


	private static File[] encontraFileEm(File file) {
		if(file.isDirectory()) {
			// melhorar essa parte toda usando filter
			File[] listaArquivos = file.listFiles();
			ArrayList<File> files = new ArrayList<>();
			
			int index = 0;
			while(index < listaArquivos.length) {
				File[] encontrados = encontraFileEm(listaArquivos[index]);
				addFilesToListFromArray(files, encontrados);
				index++;
			}
			
			
			File[] filtrados = filterFiles(file);
			files = addFilesToListFromArray(files, filtrados);
			
			return files.toArray(filtrados);
		}
		
		File[] NO_FILES = {};
		return NO_FILES;
	}

	private static ArrayList<File> addFilesToListFromArray(ArrayList<File> list, File[] files) {
		
		for(File f: files) {
			list.add(f);
		}
		
		return list;
	}
	
	private static File[] filterFiles(File file) {
		File[] files = file.listFiles(new FilenameFilter() {
			public boolean accept(File file, String name) {
				return name.toLowerCase().endsWith(".csp");
			}
		});
		return files;
	}

	public static String getTextFromEditor() {
		try{
			IEditorPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();

			AbstractTextEditor compEditor = (AbstractTextEditor)part;

			IEditorInput input = compEditor.getEditorInput();

			IDocumentProvider provider = compEditor.getDocumentProvider();

			IDocument document = provider.getDocument(input);


			return document.get();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "sem documento encontrado";
	}
	
}
