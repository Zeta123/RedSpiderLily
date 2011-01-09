package cn.com.redspiderlily.bms.system;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import cn.com.redspiderlily.bms.db.DbOperate;

public class ClassificationDialog extends Dialog implements ModifyListener{
	private Combo comboMainClassification,comboSubClassification;
	private String mainClassification,subClassification;
	
	public ClassificationDialog(Shell parentShell){
		super(parentShell);
	}
	
	protected Control createDialogArea(Composite parent){
		Composite topComp = new Composite(parent, SWT.NONE);
		topComp.setLayout(new GridLayout());
		
		Composite c = new Composite(topComp, SWT.NONE);
		c.setLayoutData(new GridData(GridData.CENTER,GridData.CENTER,true,true));
		c.setLayout(new RowLayout());
		
		new Label(c, SWT.NONE).setText("主分类：");
		comboMainClassification = SmsUtil.createMainClassificationCombo(c,SWT.BORDER|SWT.READ_ONLY);
		comboMainClassification.setLayoutData(new RowData(100,-1));
		comboMainClassification.select(0);
		comboMainClassification.addModifyListener(this);
		
		new Label(c, SWT.NONE).setText("次分类：");
		comboSubClassification = new Combo(c,SWT.READ_ONLY);
		comboSubClassification.setLayoutData(new RowData(100,-1));
		modifyText(null);
		
		return topComp;
	}
	
	private String oldMainClassification = "";
	public void modifyText(ModifyEvent e){
		String newMainClassification = comboMainClassification.getText();
		if(oldMainClassification.equals(newMainClassification)) 
			return;
		oldMainClassification = newMainClassification;
		comboSubClassification.removeAll();
		DbOperate db = SmsFactory.getDbOperate();
		for(String subClassification : db.getSearchedSubClassification(newMainClassification)){
			comboSubClassification.add(subClassification);
			comboSubClassification.setData(subClassification, subClassification);
		}
		comboSubClassification.select(0);
	}
	
	protected void buttonPressed(int buttonId){
		if(buttonId == IDialogConstants.OK_ID){
			String key = comboMainClassification.getText();
			mainClassification = (String)comboMainClassification.getData(key);
			key = comboSubClassification.getText();
			subClassification = (String)comboSubClassification.getData(key);
		}
		super.buttonPressed(buttonId);
	}

	public String getMainClassification() {
		return mainClassification;
	}

	public void setMainClassification(String mainClassification) {
		this.mainClassification = mainClassification;
	}

	public String getSubClassification() {
		return subClassification;
	}

	public void setSubClassification(String subClassification) {
		this.subClassification = subClassification;
	}
	


}
