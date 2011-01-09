package cn.com.redspiderlily.bms.system;

import java.util.ArrayList;
import java.util.List;

import cn.com.redspiderlily.bms.archive.ArchiveEditor;
import cn.com.redspiderlily.bms.archive.ArchiveEditorInput;
import cn.com.redspiderlily.bms.archive.ArchiveFavouriteEditor;
import cn.com.redspiderlily.bms.archive.ArchiveRecentReadEditor;
import cn.com.redspiderlily.bms.archive.ArchiveSyntheticalEditor;
import cn.com.redspiderlily.bms.classification.ClassificationBookEditor;
import cn.com.redspiderlily.bms.classification.ClassificationEditor;
import cn.com.redspiderlily.bms.db.DbOperate;
import cn.com.redspiderlily.bms.model.ITreeEntry;
import cn.com.redspiderlily.bms.navigator.NavigatorEntry;

public class SmsFactory {
	public static List<ITreeEntry> createNavigatorEntryTree(){
		ITreeEntry t1 = new NavigatorEntry("��ʾ���");
		t1.setImage(ImagesContext.getImage(ImagesContext.LIBRARY));
		ITreeEntry t2 = new NavigatorEntry("�������");
		t2.setImage(ImagesContext.getImage(ImagesContext.BOOK));
		ITreeEntry t3 = new NavigatorEntry("�����鼮");
		t3.setImage(ImagesContext.getImage(ImagesContext.REPORT));
		ITreeEntry t4 = new NavigatorEntry("ϵͳ����");
		t4.setImage(ImagesContext.getImage(ImagesContext.SYSCONFIG));
		{
			ITreeEntry c1 = new NavigatorEntry("�ۺ�");
			c1.setImage(ImagesContext.getImage(ImagesContext.LIBRARY));
			c1.setEditorInput(new ArchiveEditorInput());
			c1.setEditorId(ArchiveSyntheticalEditor.class.getName());
			t1.addChild(c1);
			ITreeEntry c2 = new NavigatorEntry("����Ķ�");
			c2.setImage(ImagesContext.getImage(ImagesContext.LIBRARY));
			c2.setEditorInput(new ArchiveEditorInput());
			c2.setEditorId(ArchiveRecentReadEditor.class.getName());
			t1.addChild(c2);
			ITreeEntry c3 = new NavigatorEntry("�����Ƽ�");
			c3.setImage(ImagesContext.getImage(ImagesContext.LIBRARY));
			c3.setEditorInput(new ArchiveEditorInput());
			c3.setEditorId(ArchiveFavouriteEditor.class.getName());
			t1.addChild(c3);
			ITreeEntry c4 = new NavigatorEntry("������ʾ");
			c4.setImage(ImagesContext.getImage(ImagesContext.LIBRARY));
			c4.setEditorInput(new ArchiveEditorInput());
			c4.setEditorId(ClassificationBookEditor.class.getName());
			t1.addChild(c4);

			ITreeEntry c5 = new NavigatorEntry("�鼮����");
			c5.setImage(ImagesContext.getImage(ImagesContext.BOOK));
			c5.setEditorInput(new ArchiveEditorInput());
			c5.setEditorId(ArchiveEditor.class.getName());
			t2.addChild(c5);
			ITreeEntry c6 = new NavigatorEntry("�������");
			c6.setImage(ImagesContext.getImage(ImagesContext.BOOK));
			c6.setEditorInput(new ArchiveEditorInput());
			c6.setEditorId(ClassificationEditor.class.getName());
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
