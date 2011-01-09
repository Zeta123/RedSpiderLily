import static org.junit.Assert.*;

import org.junit.Test;

import cn.com.redspiderlily.bms.archive.ArchiveEditorSorter;
import cn.com.redspiderlily.bms.model.Book;



public class TESTArchive {
	@Test public void TESTcompare() {
		int result = 0;
		Book b1 = new Book(),b2 = new Book();
		b1.setBookName("测试书名1");
		b2.setBookName("测试书名2");
		//result=ArchiveEditorSorter.compare(null, b1,b2);
		assertTrue(result>0);
	}
}
