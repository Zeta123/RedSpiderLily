package cn.com.redspiderlily.bms.system;

import java.util.Collection;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class SmsContentProvider implements IStructuredContentProvider{
	public Object[] getElements(Object element){
		if(element instanceof Collection)
			return ((Collection)element).toArray();
		else
			return new Object[0];
	}
	public void dispose(){}
	public void inputChanged(Viewer v, Object oldInput, Object newInput){}
}
