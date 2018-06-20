package br.ufcg.edu.csp.counterexampleView;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

public class CSPViewLabelProvider  extends LabelProvider implements ITableLabelProvider{
	
	private Image tick;
	private Image crossTick;
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
		if(tick == null || crossTick == null) {
			try {
				File file = new File("CSPDev\\br.ufcg.edu.csp\\icons\\tick.png");
				ImageDescriptor imageDescriptor = ImageDescriptor.createFromURL(file.toURI().toURL());
				tick = imageDescriptor.createImage();
				
				file = new File("CSPDev\\br.ufcg.edu.csp\\icons\\cross_tick.png");
				imageDescriptor = ImageDescriptor.createFromURL(file.toURI().toURL());
				crossTick = imageDescriptor.createImage();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		
		if(((CheckerNodeDecorator)obj).getIsCounterexampleNode()) {
			if(((CheckerNodeDecorator)obj).getCheckCondition()) {
				return this.tick;
			} else {
				return this.crossTick;
			}
		} else {
			return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_INFO_TSK);
		}
	}
}
