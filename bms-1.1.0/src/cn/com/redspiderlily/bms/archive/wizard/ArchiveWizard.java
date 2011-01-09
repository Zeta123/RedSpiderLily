package cn.com.redspiderlily.bms.archive.wizard;

import java.sql.Date;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;

import cn.com.redspiderlily.bms.db.DbOperate;
import cn.com.redspiderlily.bms.model.Book;
import cn.com.redspiderlily.bms.system.SmsFactory;

public class ArchiveWizard extends Wizard{
	private BookSelectPage bookSelectPage;	//选择书籍
	private ClassificationPage classificationPage;	//设置主分类
	private Book book = new Book();
	
	public void addPages(){
		bookSelectPage = new BookSelectPage("bookSelectPage");
		classificationPage = new ClassificationPage("classificationPage");
		addPage(bookSelectPage);
		addPage(classificationPage);
	}
	
	public boolean canFinish(){
		IWizardPage page = getContainer().getCurrentPage();
		if(page != classificationPage)
			return false;
		return super.canFinish();
	}
	
	public IWizardPage getNextPage(IWizardPage page){
		if(page == bookSelectPage)
			return classificationPage;
		if(page == classificationPage)
			return null;
		
		return super.getNextPage(page);
	}
	
	public boolean performFinish(){
		book = new Book();
		bookSelectPage.getValue(book);	
		classificationPage.getValue(book);
		
		DbOperate db = SmsFactory.getDbOperate();
		Book o = db.getBook(book.getStorePath().toString());
		
		if(o != null){
			MessageDialog.openError(null, "", "书籍已存在，不允许重复插入");
			return false;
		}
		
		book.setReadTimes(0);
		book.setRecentDate(new Date(System.currentTimeMillis()));
		return true;
	}
	
	public Book getBook(){
		return book;
	}

}
