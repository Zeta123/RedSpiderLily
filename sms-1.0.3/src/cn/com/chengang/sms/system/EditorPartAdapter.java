package cn.com.chengang.sms.system;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

public abstract class EditorPartAdapter extends EditorPart{
	public void init(IEditorSite site, IEditorInput input)throws PartInitException{
		setSite(site);
		setInput(input);
	}
	public void doSave(IProgressMonitor monitor){}
	public void doSaveAs(){}
	public boolean isDirty(){return false;}
	public boolean isSaveAsAllowed(){return false;}
	public void setFocus(){}
}
