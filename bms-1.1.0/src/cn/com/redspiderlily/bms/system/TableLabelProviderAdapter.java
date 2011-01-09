package cn.com.redspiderlily.bms.system;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

public abstract class TableLabelProviderAdapter implements ITableLabelProvider{
	public Image getColumnImage(Object element, int columnIndex){return null;}
	public void addListener(ILabelProviderListener listener){}
	public void dispose(){}
	public boolean isLabelProperty(Object element, String property){return false;}
	public void removeListener(ILabelProviderListener listener){}
}
