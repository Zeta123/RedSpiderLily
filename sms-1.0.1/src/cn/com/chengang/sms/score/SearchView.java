package cn.com.chengang.sms.score;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class SearchView extends ViewPart{
	public void createPartControl(Composite parent){
		Composite topComp = new Composite(parent, SWT.NONE);
		topComp.setLayout(new RowLayout());
	}
	
	public void setFocus(){}
}
