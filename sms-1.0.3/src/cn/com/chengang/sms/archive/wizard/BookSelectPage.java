package cn.com.chengang.sms.archive.wizard;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

import cn.com.chengang.sms.model.Book;

public class BookSelectPage extends WizardPage implements ModifyListener{
	private Text bookNameText, bookAuthorText;
	private String storePath;
	
	protected BookSelectPage(String pageName){
		super(pageName);
	}
	
	public void createControl(Composite parent){
		setTitle("添加书籍");
		setMessage("输入书籍基本信息",IMessageProvider.INFORMATION);
		Composite topComp = new Composite(parent, SWT.NONE);
		topComp.setLayoutData(new GridData(GridData.CENTER,GridData.CENTER,true,true));
		
		topComp.setLayout(new GridLayout(2,false));
		new Label(topComp,SWT.NONE).setText("基本信息：");
		Button selectButton = new Button(topComp, SWT.NONE);
		selectButton.setText("选择书籍");
		selectButton.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e){
				FileDialog dialog = new FileDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),SWT.OPEN);
				storePath = dialog.open();
				bookNameText.setText(dialog.getFileName());
				bookAuthorText.setText("未知");
			}
		});
		
		new Label(topComp, SWT.NONE).setText("书名：");
		bookNameText = createText(topComp,SWT.BORDER);
		bookNameText.addModifyListener(this);
		
		new Label(topComp,SWT.NONE).setText("作者：");
		bookAuthorText = createText(topComp,SWT.BORDER);
		bookAuthorText.addModifyListener(this);

		setPageComplete(false);
		setControl(topComp);
	}

	private Text createText(Composite comp,int style){
		Text text = new Text(comp,style);
		text.setLayoutData(new GridData(100,-1));
		return text;
	}
	
	public void modifyText(ModifyEvent e){
		setPageComplete(false);
		if(storePath.equals("")){
			setErrorMessage("必须先选择一本书");
			return;
		}
		if(bookNameText.getText().trim().equals("")){
			setErrorMessage("必须填写书名");
			return;
		}
		if(bookAuthorText.getText().trim().equals("")){
			setErrorMessage("必须填写作者");
			return;
		}
		setErrorMessage(null);
		setPageComplete(true);
	}
	
	public void getValue(Book book){	
		book.setBookAuthor(bookAuthorText.getText());
		book.setBookName(bookNameText.getText());
		book.setStorePath(storePath);
	}
}
