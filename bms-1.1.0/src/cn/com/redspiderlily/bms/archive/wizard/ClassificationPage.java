package cn.com.redspiderlily.bms.archive.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import cn.com.redspiderlily.bms.db.DbOperate;
import cn.com.redspiderlily.bms.model.Book;
import cn.com.redspiderlily.bms.system.SmsFactory;
import cn.com.redspiderlily.bms.system.SmsUtil;

public class ClassificationPage extends WizardPage implements ModifyListener{
	private Combo comboMainClassification,comboSubClassification;
	
	protected ClassificationPage(String pageName){
		super(pageName);
	}
	
	public void createControl(Composite parent){		
		setTitle("添加书籍");
		setMessage("设置书籍分类",INFORMATION);
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
		
		setControl(topComp);
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

	public void getValue(Book book){
		String key = comboMainClassification.getText();
		String mainClassification = (String)comboMainClassification.getData(key);
		book.setMainClassification(mainClassification);
		
		key = comboSubClassification.getText();
		String subClassification = (String)comboSubClassification.getData(key);
		book.setSubClassification(subClassification);
	}
}


