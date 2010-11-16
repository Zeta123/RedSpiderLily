package cn.com.chengang.sms.navigator;

import java.util.List;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import cn.com.chengang.sms.model.ITreeEntry;
import cn.com.chengang.sms.system.LabelProviderAdapter;
import cn.com.chengang.sms.system.SmsFactory;
import cn.com.chengang.sms.system.TreeContentProviderAdapter;

public class NavigatorView extends ViewPart{
	public void createPartControl(Composite parent){
		Composite topComp = new Composite(parent, SWT.NONE);
		topComp.setLayout(new FillLayout());
		TreeViewer tv = new TreeViewer(topComp,SWT.NONE);
		tv.setContentProvider(new MyContentProvider());
		tv.setLabelProvider(new MyLableProvider());
		
		tv.setInput(SmsFactory.createNavigatorEntryTree());
		
		tv.addDoubleClickListener(new MyDoubleClickListener());
	}
	
	public void setFocus(){}
	
	private static final class MyLableProvider extends LabelProviderAdapter{
		public String getText(Object element){
			return ((ITreeEntry)element).getName();
		}
		public Image getImage(Object element){
			return((NavigatorEntry)element).getImage();
		}
	}
	
	private static final class MyContentProvider extends TreeContentProviderAdapter{
		public Object[] getElements(Object inputElement){
			if(inputElement instanceof List){
				return ((List)inputElement).toArray();
			}else{
				return new Object[0];
			}
		}
		public boolean hasChildren(Object element){
			ITreeEntry entry = (ITreeEntry)element;
			return !entry.getChildren().isEmpty();
		}
		public Object[] getChildren(Object parentElement){
			ITreeEntry entry = (ITreeEntry)parentElement;
			return entry.getChildren().toArray();
		}
	}
	
	private class MyDoubleClickListener implements IDoubleClickListener{
		public void doubleClick(DoubleClickEvent event){
			IStructuredSelection sel = (IStructuredSelection)event.getSelection();
			NavigatorEntry entry = (NavigatorEntry)sel.getFirstElement();
			IEditorInput editorInput = entry.getEditorInput();
			String editorID = entry.getEditorId();
			
			if(editorInput == null || editorID == null) return;
			
			IWorkbenchPage workbenchPage = getViewSite().getPage();
			IEditorPart editor = workbenchPage.findEditor(editorInput);
			
			if(editor != null){
				workbenchPage.bringToTop(editor);
			}else{
				try{
					editor = workbenchPage.openEditor(editorInput, editorID);
				}catch(PartInitException e2){e2.printStackTrace();}
			}
		}
	}

}
