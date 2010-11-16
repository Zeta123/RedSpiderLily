package cn.com.chengang.sms.model;

import java.util.List;

import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorInput;

public interface ITreeEntry {
	void setName(String name);
	String getName();
	
	void setChildren(List<ITreeEntry> children);
	List<ITreeEntry> getChildren();
	void addChild(ITreeEntry entry);
	boolean hasChild();
	
	void setParentEntry(ITreeEntry parentEntry);
	ITreeEntry getParentEntry();
	
	public Image getImage();
	public void setImage(Image image);
	
	public IEditorInput getEditorInput();
	public void setEditorInput(IEditorInput editorInput);
	
	public String getEditorId();
	public void setEditorId(String editorId);
}
