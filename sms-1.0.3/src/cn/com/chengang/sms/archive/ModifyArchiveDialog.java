package cn.com.chengang.sms.archive;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import cn.com.chengang.sms.model.Book;

public class ModifyArchiveDialog extends TitleAreaDialog{
	private Book book;
	private Text nameText,authorText;
	//修改分类
	
	public ModifyArchiveDialog(Shell parentShell){
		super(parentShell);
	}
	
	protected Control createDialogArea(Composite parent){
		getShell().setText("书籍信息");
		setTitle("修改书籍信息");
		setMessage("请输入新的书籍信息",IMessageProvider.INFORMATION);
		Composite topComp = new Composite(parent, SWT.NONE);
		
		topComp.setLayoutData(new GridData(GridData.CENTER,GridData.CENTER,true,true));
		topComp.setLayout(new GridLayout(2,false));
		
		Composite comp = new Composite(topComp,SWT.NONE);
		comp.setLayoutData(new GridData(GridData.FILL_BOTH));
		comp.setLayout(new GridLayout(2,false));
		
		new Label(comp,SWT.NONE).setText("书名：");
		nameText = createText(comp,SWT.BORDER);
		new Label(comp,SWT.NONE).setText("作者");
		authorText = createText(comp,SWT.BORDER);
		
//		classificationComp = 
		setValue(book);
		return topComp;
	}

	public void setValue(Book book){
		if(book == null) return;
		
		nameText.setText(book.getBookName());
		authorText.setText(book.getBookAuthor());
		
		//classification
	}
	
	private Text createText(Composite comp, int style){
		Text text = new Text(comp,style);
		text.setLayoutData(new GridData(100,-1));
		return text;
	}
	
	protected void buttonPressed(int buttonId){
		if(buttonId == IDialogConstants.OK_ID){
			if(!checkValue()) return;
			getValue(book);
		}
		super.buttonPressed(buttonId);
	}
	
	private boolean checkValue(){
		if(nameText.getText().trim().equals("")){
			setErrorMessage("书名不能为空");
			return false;
		}
		if(authorText.getText().trim().equals("")){
			setErrorMessage("作者不能为空");
			return false;
		}
		return true;
	}
	
	private void getValue(Book book){
		book.setBookName(nameText.getText());
		book.setBookAuthor(authorText.getText());
		//类别
	}
	
	protected Point getInitialSize(){
		Point p = super.getInitialSize();
		p.x = 400;
		return p;
	}
	
	public Book getBook(){return book;}
	public void setBook(Book book){this.book = book;}
}
