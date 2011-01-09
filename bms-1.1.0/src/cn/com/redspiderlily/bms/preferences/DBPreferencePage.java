package cn.com.redspiderlily.bms.preferences;


import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import cn.com.redspiderlily.bms.Activator;
import cn.com.redspiderlily.bms.db.ConnectManager;

public class DBPreferencePage extends PreferencePage implements IWorkbenchPreferencePage,ModifyListener{
	public static final String CLASSNAME_KEY = "$CLASSNAME_KEY";
	public static final String URL_KEY = "$URL_KEY";
	public static final String USERNAME_KEY = "$USERNAME_KEY";
	public static final String PASSWORD_KEY = "$PASSWORD_KEY";
	
	private static final String CLASSNAME_DEFAULT = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String URL_DEFAULT = "jdbc:sqlserver://localhost:1433;DatabaseName=sms";
	private static final String USERNAME_DEFAULT = "sa";
	private static final String PASSWORD_DEFAULT = "123";
	
	private Text classNameText,urlText,usernameText,passwordText,archiveText;
	private IPreferenceStore ps;
	private VerifyListener verifyListener = new VerifyListener(){
		public void verifyText(VerifyEvent e){
			e.doit = ("0123456789".indexOf(e.text) >= 0);
		}
	};
	
	public void init(IWorkbench workbench){
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}
	
	protected Control createContents(Composite parent){
		ps = getPreferenceStore();
		Composite topComp = new Composite(parent, SWT.NONE);
		topComp.setLayout(new GridLayout());
		createDbConnectGroup(topComp);
		return topComp;
	}
	
	private void createDbConnectGroup(Composite topComp){
		Group group = new Group(topComp,SWT.NONE);
		group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		group.setLayout(new GridLayout(2,false));
		group.setText("数据库连接");
		classNameText = createText(group,"ClassName:",CLASSNAME_KEY,CLASSNAME_DEFAULT);
		urlText = createText(group,"URL:",URL_KEY,URL_DEFAULT);
		usernameText = createText(group,"用户名",USERNAME_KEY,USERNAME_DEFAULT);
		passwordText = createText(group,"密码",PASSWORD_KEY,PASSWORD_DEFAULT);
		passwordText.setEchoChar('*');
	}
	
	private Text createText(Composite comp,String label,String saveKey,String defaultValue){
		new Label(comp,SWT.NONE).setText(label);
		Text text = new Text(comp,SWT.BORDER);
		text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		String value = ps.getString(saveKey);
		text.setText(isEmpty(value) ? defaultValue : value);
		
		text.addModifyListener(this);
		return text;
	}
	
	private boolean isEmpty(String str){
		return str == null || str.trim().equals("");
	}
	
	public void modifyText(ModifyEvent e){
		String errorStr = null;
		if(isEmpty(classNameText.getText())){
			errorStr = "ClassName 不能为空！";
		}else if(isEmpty(urlText.getText())){
			errorStr = "URL 不能为空！";
		}else if(isEmpty(usernameText.getText())){
			errorStr = "用户名不能为空！";
		}else if(isEmpty(passwordText.getText())){
			errorStr = "密码不能为空！";
		}else if(isEmpty(archiveText.getText())){
			errorStr = "书籍管理表格每页显示记录不能为空！";
		}
		setErrorMessage(errorStr);
		setValid(errorStr == null);
		getApplyButton().setEnabled(errorStr == null);
	}
	
	protected void performDefaults(){
		classNameText.setText(CLASSNAME_DEFAULT);
		urlText.setText(URL_DEFAULT);
		usernameText.setText(USERNAME_DEFAULT);
		passwordText.setText(PASSWORD_DEFAULT);
		archiveText.removeVerifyListener(verifyListener);
		archiveText.addVerifyListener(verifyListener);
	}
	
	protected void performApply(){
		doSave();
	}
	
	public boolean performOk(){
		doSave();
		ConnectManager.closeConnection();
		return true;
	}
	
	private void doSave(){
		ps.setValue(CLASSNAME_KEY, classNameText.getText());
		ps.setValue(URL_KEY, urlText.getText());
		ps.setValue(USERNAME_KEY, usernameText.getText());
		ps.setValue(PASSWORD_KEY, passwordText.getText());
	}

	public static String getClassnameDefault() {
		return CLASSNAME_DEFAULT;
	}

	public static String getUrlDefault() {
		return URL_DEFAULT;
	}

	public static String getUsernameDefault() {
		return USERNAME_DEFAULT;
	}

	public static String getPasswordDefault() {
		return PASSWORD_DEFAULT;
	}
}
