package cn.com.chengang.sms.system;


import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;

import cn.com.chengang.sms.Activator;


public class ImagesContext {
	private final static String ICONS_PATH = "icons/";
	
	//此处添加图标
	public final static String LIBRARY = "LIBRARY";
	public final static String BOOK = "BOOK";
	public final static String SYSCONFIG = "SYSCONFIG";
	public final static String REPORT = "REPORT";
	public final static String NOTE = "NOTE";
	public final static String REMOVE = "REMOVE";
	public final static String FIRST = "FIRST";
	public final static String PREV = "PREV";
	public final static String NEXT = "NEXT";
	public final static String LAST = "LAST";
	public final static String OPEN = "OPEN";
	
	private static ImageRegistry imageRegistry;
	
	public static ImageRegistry getImageRegistry(){
		if(imageRegistry == null){
			imageRegistry = new ImageRegistry();
			declareImages();
		}
		return imageRegistry;
	}
	
	//修改此处图标名称
	private final static void declareImages(){
		declareRegistryImage(LIBRARY,ICONS_PATH+"alt_window_16.gif");
		declareRegistryImage(BOOK,ICONS_PATH+"alt_window_16.gif");
		declareRegistryImage(SYSCONFIG,ICONS_PATH+"alt_window_16.gif");
		declareRegistryImage(REPORT,ICONS_PATH+"alt_window_16.gif");
		declareRegistryImage(NOTE,ICONS_PATH+"alt_window_16.gif");
		declareRegistryImage(REMOVE,ICONS_PATH+"alt_window_16.gif");
		declareRegistryImage(FIRST,ICONS_PATH+"alt_window_16.gif");
		declareRegistryImage(PREV,ICONS_PATH+"alt_window_16.gif");
		declareRegistryImage(NEXT,ICONS_PATH+"alt_window_16.gif");
		declareRegistryImage(LAST,ICONS_PATH+"alt_window_16.gif");
		declareRegistryImage(OPEN,ICONS_PATH+"alt_window_16.gif");
	}
	
	private final static void declareRegistryImage(String key,String path){
		try{
			URL url = FileLocator.find(Activator.getDefault().getBundle(),new Path(path),null);
			if(url != null){
				ImageDescriptor image = ImageDescriptor.createFromURL(url);
				getImageRegistry().put(key,image);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static Image getImage(String key){
		return getImageRegistry().get(key);
	}
	
	public static ImageDescriptor getImageDescriptor(String key){
		return getImageRegistry().getDescriptor(key);
	}
}
