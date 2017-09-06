package br.ufcg.edu.csp;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.editors.text.FileDocumentProvider;


public class CSPDocumentProvider extends FileDocumentProvider implements IDocumentListener {

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
		
	}

	@Override
	public void documentChanged(DocumentEvent event) {
		// TODO OutlineView update
	
	}
	
	private static File getEditorFile() {
		IResource file = getEditorIFile();
		if(file != null) {
			return new File(file.getLocation().toString());
		}
		return null;
	}
	
	private static IResource getEditorIFile() {
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

	
	private static File[] findCSPFilesFromWorkspace() {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();  

		IProject[] workspaceDirectory = workspace.getRoot().getProjects();
		ArrayList<File> files = new ArrayList<>();
		for(IProject projeto : workspaceDirectory) {
			addFilesToListFromArray(files, encontraFileEm(new File(projeto.getLocation().toString())));
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

}
