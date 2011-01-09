package cn.com.redspiderlily.bms.system;

import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ViewerSortSelectionListener extends SelectionAdapter{
	private boolean ascFlag = true;
	private ViewerSorter asc,desc;
	private StructuredViewer viewer;
	
	public ViewerSortSelectionListener(StructuredViewer v, ViewerSorter asc, ViewerSorter desc){
		this.asc = asc;
		this.desc = desc;
		this.viewer = v;
	}
	
	public void widgetSelected(SelectionEvent e){
		viewer.setSorter(ascFlag ? asc : desc);
		ascFlag = !ascFlag;
	}
}
