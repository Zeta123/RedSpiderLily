package cn.com.chengang.sms.archive;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

import cn.com.chengang.sms.model.Book;

public class ArchiveEditorSorter extends ViewerSorter{
	public static final int BOOKNAME = 1;
	public static final int BOOKAUTHOR = 2;
	public static final int MAINCLASSIFICATION = 3;
	
	public static final ViewerSorter BOOKNAME_ASC = new ArchiveEditorSorter(BOOKNAME);
	public static final ViewerSorter BOOKNAME_DESC = new ArchiveEditorSorter(-BOOKNAME);
	public static final ViewerSorter BOOKAUTHOR_ASC = new ArchiveEditorSorter(BOOKAUTHOR);
	public static final ViewerSorter BOOKAUTHOR_DESC = new ArchiveEditorSorter(-BOOKAUTHOR);
	public static final ViewerSorter MAINCLASSIFICATION_ASC = new ArchiveEditorSorter(MAINCLASSIFICATION);
	public static final ViewerSorter MAINCLASSIFICATION_DESC = new ArchiveEditorSorter(-MAINCLASSIFICATION);
	
	private int sortType;
	
	private ArchiveEditorSorter(int sortType){
		this.sortType = sortType;
	}
	
	public int compare(Viewer viewer, Object obj1, Object obj2){
		Book o1 = (Book)obj1;
		Book o2 = (Book)obj2;
		switch(sortType){
		case BOOKNAME:	return o1.getBookName().compareTo(o2.getBookName());
		case -BOOKNAME:	return o2.getBookName().compareTo(o1.getBookName());
		case BOOKAUTHOR:	return o1.getBookAuthor().compareTo(o2.getBookAuthor());
		case -BOOKAUTHOR:	return o2.getBookAuthor().compareTo(o1.getBookAuthor());
		case MAINCLASSIFICATION:	return o1.getMainClassification().compareTo(o2.getMainClassification());
		case -MAINCLASSIFICATION:	return o2.getMainClassification().compareTo(o1.getMainClassification());
		}
		return 0;
	}
}
