package br.ufcg.edu.csp;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import br.ufcg.edu.csp.CSPEditor;

public class CSPEditorActivator extends AbstractUIPlugin {

	public CSPEditorActivator() {
	}

	
	@Override
	protected void initializeImageRegistry(final ImageRegistry reg) {
	    reg.put("icons/sample.gif", imageDescriptorFromPlugin(CSPEditor.PLUGIN_ID, "icons/sample.gif" ));
	}
}
