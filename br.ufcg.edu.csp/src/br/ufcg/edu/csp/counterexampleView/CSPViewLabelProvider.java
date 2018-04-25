package br.ufcg.edu.csp.counterexampleView;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

public class CSPViewLabelProvider  extends LabelProvider implements ITableLabelProvider{
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
		if(((CheckerNodeDecorator)obj).getIsCounterexampleNode()) {
			if(((CheckerNodeDecorator)obj).getCheckCondition()) {
				return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_INFO_TSK);
			} else {
				return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_ERROR_TSK);
			}
		} else {
			return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_INFO_TSK);
		}
	}
}
