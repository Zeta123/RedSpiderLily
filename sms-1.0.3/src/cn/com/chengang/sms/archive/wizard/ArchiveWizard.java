package cn.com.chengang.sms.archive.wizard;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;

import cn.com.chengang.sms.db.DbOperate;
import cn.com.chengang.sms.model.Book;
import cn.com.chengang.sms.system.SmsFactory;

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
//		System.out.println("in");
		
		book = new Book();
//		System.out.println("1 finish");
		
		bookSelectPage.getValue(book);	
//		System.out.println("2 finish");
		
		classificationPage.getValue(book);
//		System.out.println("3 finish");
		
/*		System.out.println(book.getBookName().toString());
		System.out.println(book.getBookAuthor().toString());
		System.out.println(book.getStorePath().toString());
		System.out.println(book.getMainClassification().toString());
		System.out.println(book.getSubClassification().toString());*/
		
		DbOperate db = SmsFactory.getDbOperate();
		
		Book o = db.getBook(book.getStorePath().toString());
		
		if(o != null){
			MessageDialog.openError(null, "", "书籍已存在，不允许重复插入");
			return false;
		}
		
		book.setMainClassification("");
		book.setSubClassification("");
		book.setReadTimes(0);
		return true;
	}
	
	public Book getBook(){
		return book;
	}

}
