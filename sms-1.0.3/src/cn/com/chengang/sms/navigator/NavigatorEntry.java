package cn.com.chengang.sms.navigator;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorInput;


import cn.com.chengang.sms.model.ITreeEntry;

public class NavigatorEntry implements ITreeEntry{
	private String name;
	private ITreeEntry parentEntry;
	private List<ITreeEntry> children = new ArrayList<ITreeEntry>();
	private Image image;
	private IEditorInput editorInput;
	private String editorId;
	
	public NavigatorEntry(){}
	public NavigatorEntry(String name){this.name=name;}
	
	public String getName(){return name;}
	public void setName(String name){this.name=name;}
	
	public ITreeEntry getParentEntry(){return parentEntry;}
	public void setParentEntry(ITreeEntry parentEntry){this.parentEntry=parentEntry;}
	
	public void setChildren(List<ITreeEntry> children){this.children=children;}
	public List<ITreeEntry> getChildren(){return children;}
	public void addChild(ITreeEntry entry){children.add(entry);}
	public boolean hasChild(){return children.size()>0;}
	
	public Image getImage(){return image;}
	public void setImage(Image image){this.image=image;}
	
	public IEditorInput getEditorInput() {
		return editorInput;
	}
	public void setEditorInput(IEditorInput editorInput) {
		this.editorInput = editorInput;
	}
	
	public String getEditorId() {
		return editorId;
	}
	public void setEditorId(String editorId) {
		this.editorId = editorId;
	}

}
