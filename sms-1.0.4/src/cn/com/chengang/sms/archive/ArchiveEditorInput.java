package cn.com.chengang.sms.archive;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

public class ArchiveEditorInput implements IEditorInput{
	public boolean exists(){return true;}
	public ImageDescriptor getImageDescriptor(){return null;}
	public String getName(){return "";}
	public String getToolTipText(){return "";}
	public IPersistableElement getPersistable(){return null;}
	public Object getAdapter(Class adapter){return null;}
}
