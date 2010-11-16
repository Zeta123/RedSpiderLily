package cn.com.chengang.sms.system;

import java.util.ArrayList;
import java.util.List;

import cn.com.chengang.sms.archive.ArchiveEditor;
import cn.com.chengang.sms.archive.ArchiveEditorInput;
import cn.com.chengang.sms.db.DbOperate;
import cn.com.chengang.sms.model.ITreeEntry;
import cn.com.chengang.sms.navigator.NavigatorEntry;

public class SmsFactory {
	public static List<ITreeEntry> createNavigatorEntryTree(){
		ITreeEntry t1 = new NavigatorEntry("显示书架");
		t1.setImage(ImagesContext.getImage(ImagesContext.LIBRARY));
		ITreeEntry t2 = new NavigatorEntry("管理书架");
		t2.setImage(ImagesContext.getImage(ImagesContext.BOOK));
		ITreeEntry t3 = new NavigatorEntry("搜索书籍");
		t3.setImage(ImagesContext.getImage(ImagesContext.REPORT));
		ITreeEntry t4 = new NavigatorEntry("系统配置");
		t4.setImage(ImagesContext.getImage(ImagesContext.SYSCONFIG));
		{
			ITreeEntry c1 = new NavigatorEntry("综合");
			c1.setImage(ImagesContext.getImage(ImagesContext.LIBRARY));
			t1.addChild(c1);
			ITreeEntry c2 = new NavigatorEntry("最近阅读");
			c2.setImage(ImagesContext.getImage(ImagesContext.LIBRARY));
			t1.addChild(c2);
			ITreeEntry c3 = new NavigatorEntry("个性推荐");
			c3.setImage(ImagesContext.getImage(ImagesContext.LIBRARY));
			t1.addChild(c3);
			ITreeEntry c4 = new NavigatorEntry("分类显示");
			c4.setImage(ImagesContext.getImage(ImagesContext.LIBRARY));
			t1.addChild(c4);

			ITreeEntry c5 = new NavigatorEntry("书籍管理");
			c5.setImage(ImagesContext.getImage(ImagesContext.BOOK));
			c5.setEditorInput(new ArchiveEditorInput());
			c5.setEditorId(ArchiveEditor.class.getName());
			t2.addChild(c5);
			ITreeEntry c6 = new NavigatorEntry("类别管理");
			c6.setImage(ImagesContext.getImage(ImagesContext.BOOK));
			t2.addChild(c6);
		}
		
		ArrayList<ITreeEntry> list = new ArrayList<ITreeEntry>();
		list.add(t1);
		list.add(t2);
		list.add(t3);
		list.add(t4);
		
		return list;
	}
	
	private static DbOperate db = new DbOperate();
	public static DbOperate getDbOperate(){
		return db;
	}

}
