package cn.com.redspiderlily.bms.score;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

public class ScoreEditorInput implements IEditorInput{
	private String name = "ËÑË÷Êé¼®";
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public boolean exists(){
		return true;
	}
	
	public ImageDescriptor getImageDescriptor(){
		return null;
	}
	
	public String getToolTipText(){
		return "";
	}
	
	public IPersistableElement getPersistable(){
		return null;
	}
	
	public Object getAdapter(Class adapter){
		return null;
	}
}
