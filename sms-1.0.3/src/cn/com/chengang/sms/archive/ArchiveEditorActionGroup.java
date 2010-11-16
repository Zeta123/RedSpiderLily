package cn.com.chengang.sms.archive;

import static cn.com.chengang.sms.system.ImagesContext.FIRST;
import static cn.com.chengang.sms.system.ImagesContext.LAST;
import static cn.com.chengang.sms.system.ImagesContext.NEXT;
import static cn.com.chengang.sms.system.ImagesContext.NOTE;
import static cn.com.chengang.sms.system.ImagesContext.PREV;
import static cn.com.chengang.sms.system.ImagesContext.REMOVE;
import static cn.com.chengang.sms.system.ImagesContext.REPORT;

import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionGroup;

import cn.com.chengang.sms.archive.wizard.ArchiveWizard;
import cn.com.chengang.sms.db.DbOperate;
import cn.com.chengang.sms.db.QueryInfo;
import cn.com.chengang.sms.model.Book;
import cn.com.chengang.sms.system.ImagesContext;
import cn.com.chengang.sms.system.SmsFactory;

public class ArchiveEditorActionGroup extends ActionGroup{
	private TableViewer tv;
	private DbOperate db = SmsFactory.getDbOperate();
	private QueryInfo queryInfo = new QueryInfo();
//	private Action openAction = new OpenAction();	打开书籍，书架中的方法
	private Action addAction = new AddAction();
	private Action modifyAction = new ModifyAction();
	private Action removeAction = new RemoveAction();
	private Action firstAction = new FirstAction();
	private Action prevAction = new PrevAction();
	private Action nextAction = new NextAction();
	private Action lastAction = new LastAction();
	
	public ArchiveEditorActionGroup(TableViewer tv){
		this.tv = tv;
		queryInfo.currentPage = 1;
		//queryInfo.pageSize = Constants.ARCHIVE_EDITOR_RS_NUM;
	}
	
	public void fillActionToolBars(ToolBarManager toolBarManager){
		toolBarManager.add(createActionContrItem(addAction));
		toolBarManager.add(createActionContrItem(modifyAction));
		toolBarManager.add(createActionContrItem(removeAction));
		toolBarManager.add(createActionContrItem(firstAction));
		toolBarManager.add(createActionContrItem(prevAction));
		toolBarManager.add(createActionContrItem(nextAction));
		toolBarManager.add(createActionContrItem(lastAction));
		
		toolBarManager.update(true);
	}
	
	private ActionContributionItem createActionContrItem(IAction action){
		ActionContributionItem aci = new ActionContributionItem(action);
		aci.setMode(ActionContributionItem.MODE_FORCE_TEXT);
		return aci;
	}
	
	private class AddAction extends Action{
		public AddAction(){
			setText("新增");
			setHoverImageDescriptor(ImagesContext.getImageDescriptor(REPORT));
		}
		public void run(){
			ArchiveWizard wizard = new ArchiveWizard();
			WizardDialog dialog = new WizardDialog(null,wizard);
			dialog.setPageSize(-1, 120);
			
			if(dialog.open() == IDialogConstants.OK_ID){
				Book book = wizard.getBook();
				if(db.insertBook(book)){
					MessageDialog.openInformation(null, "", "成功插入");
					Book o = db.getBook(book.getStorePath());
					tv.add(o);
					((List)tv.getInput()).add(o);
				}else{
					MessageDialog.openError(null, "", "记录插入失败");
				}
			}
		}
	}

	private class ModifyAction extends Action{
		public ModifyAction(){
			setText("修改");
			setHoverImageDescriptor(ImagesContext.getImageDescriptor(NOTE));
		}
		public void run(){
			IStructuredSelection sel = (IStructuredSelection)tv.getSelection();
			Book book = (Book)sel.getFirstElement();
			if(book == null) return;
			ModifyArchiveDialog dialog = new ModifyArchiveDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
			dialog.setBook(book);
			if(dialog.open() == IDialogConstants.OK_ID){
				db.modifyBook(book);
				tv.refresh(book);
			}
		}
	}
	
	private class RemoveAction extends Action{
		public RemoveAction(){
			setText("删除");
			setHoverImageDescriptor(ImagesContext.getImageDescriptor(REMOVE));
		}
		public void run(){
			IStructuredSelection sel = (IStructuredSelection)tv.getSelection();
			Book book = (Book)sel.getFirstElement();
			if(book == null) return;
			if(MessageDialog.openConfirm(null, null, "真的删除吗？")){
				if(db.removeBook(book)){
					tv.remove(book);
					List list = (List)tv.getInput();
					list.remove(book);
				}else{
					MessageDialog.openConfirm(null, null, "删除失败！");
				}
			}
		}
	}
	
	private class FirstAction extends Action{
		public FirstAction(){
			setText("首页");
			setHoverImageDescriptor(ImagesContext.getImageDescriptor(FIRST));
		}
		public void run(){
			queryInfo.currentPage = 1;
			//tv.setInput(db.getBook(queryInfo));
			tv.setInput(db.getBook());
			refreshActionsState();
		}
	}
	
	private class PrevAction extends Action{
		public PrevAction(){
			setText("上一页");
			setHoverImageDescriptor(ImagesContext.getImageDescriptor(PREV));
		}
		public void run(){
			queryInfo.currentPage = queryInfo.currentPage - 1;
			tv.setInput(db.getBook(queryInfo));
			refreshActionsState();
		}
	}
	
	private class NextAction extends Action{
		public NextAction(){
			setText("下一页");
			setHoverImageDescriptor(ImagesContext.getImageDescriptor(NEXT));
		}
		public void run(){
			queryInfo.currentPage = queryInfo.currentPage + 1;
			tv.setInput(db.getBook(queryInfo));
			refreshActionsState();
		}
	}
	
	private class LastAction extends Action{
		public LastAction(){
			setText("末页");
			setHoverImageDescriptor(ImagesContext.getImageDescriptor(LAST));
		}
		public void run(){
			queryInfo.currentPage = queryInfo.pageCount;
			tv.setInput(db.getBook(queryInfo));
			refreshActionsState();
		}
	}
	
	private void refreshActionsState(){
		if(queryInfo.pageCount == 0){
			firstAction.setEnabled(false);
			prevAction.setEnabled(false);
			nextAction.setEnabled(false);
			lastAction.setEnabled(false);
		}else{
			boolean b = (queryInfo.currentPage == 1);
			firstAction.setEnabled(!b);
			prevAction.setEnabled(!b);
			
			b = (queryInfo.currentPage == queryInfo.pageCount);
			lastAction.setEnabled(!b);
			nextAction.setEnabled(!b);
		}
	}
	
	public void fireFirstAction(){
		firstAction.run();
	}
}
