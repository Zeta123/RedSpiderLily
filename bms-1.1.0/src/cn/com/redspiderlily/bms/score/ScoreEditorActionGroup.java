package cn.com.redspiderlily.bms.score;

import static cn.com.redspiderlily.bms.system.ImagesContext.REPORT;

import java.io.IOException;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.ui.actions.ActionGroup;

import cn.com.redspiderlily.bms.db.DbOperate;
import cn.com.redspiderlily.bms.model.Book;
import cn.com.redspiderlily.bms.system.ImagesContext;
import cn.com.redspiderlily.bms.system.SmsFactory;

public class ScoreEditorActionGroup extends ActionGroup{
	private TableViewer tv;
	private DbOperate db = SmsFactory.getDbOperate();
	private Action openAction = new OpenAction();	//打开书籍，书架中的方法
	
	public ScoreEditorActionGroup(TableViewer tv){
		this.tv = tv;
	}
	
	public void fillActionToolBars(ToolBarManager toolBarManager){
		toolBarManager.add(createActionContrItem(openAction));
		toolBarManager.update(true);
	}
	
	private ActionContributionItem createActionContrItem(IAction action){
		ActionContributionItem aci = new ActionContributionItem(action);
		aci.setMode(ActionContributionItem.MODE_FORCE_TEXT);
		return aci;
	}
	
	private class OpenAction extends Action{
		public OpenAction(){
			setText("打开文件");
			setHoverImageDescriptor(ImagesContext.getImageDescriptor(REPORT));
		}
		public void run(){
			IStructuredSelection sel = (IStructuredSelection)tv.getSelection();
			Book book = (Book)sel.getFirstElement();
			if(book == null) return;
			String cmd="rundll32 url.dll FileProtocolHandler file://" + book.getStorePath(); 
			try {
				Process p = Runtime.getRuntime().exec(cmd);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			book.readBook();
			db.readBook(book);
		}
	}
}
