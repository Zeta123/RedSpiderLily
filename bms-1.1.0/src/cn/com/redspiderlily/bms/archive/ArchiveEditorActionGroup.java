package cn.com.redspiderlily.bms.archive;

import static cn.com.redspiderlily.bms.system.ImagesContext.NOTE;
import static cn.com.redspiderlily.bms.system.ImagesContext.REMOVE;
import static cn.com.redspiderlily.bms.system.ImagesContext.REPORT;

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

import cn.com.redspiderlily.bms.archive.wizard.ArchiveWizard;
import cn.com.redspiderlily.bms.db.DbOperate;
import cn.com.redspiderlily.bms.model.Book;
import cn.com.redspiderlily.bms.system.ImagesContext;
import cn.com.redspiderlily.bms.system.SmsFactory;

public class ArchiveEditorActionGroup extends ActionGroup{
	private TableViewer tv;
	private DbOperate db = SmsFactory.getDbOperate();
	private Action addAction = new AddAction();
	private Action modifyAction = new ModifyAction();
	private Action removeAction = new RemoveAction();
	
	public ArchiveEditorActionGroup(TableViewer tv){
		this.tv = tv;
	}
	
	public void fillActionToolBars(ToolBarManager toolBarManager){
		toolBarManager.add(createActionContrItem(addAction));
		toolBarManager.add(createActionContrItem(modifyAction));
		toolBarManager.add(createActionContrItem(removeAction));
		
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
	
	public void fireFirstAction(){
		db.deleteInvalidBook();
		tv.setInput(db.getBook());
	}
}
