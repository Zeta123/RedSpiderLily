import static org.junit.Assert.*;
import java.sql.Date;
import org.junit.Test;
import cn.com.redspiderlily.bms.model.Book;

public class TESTBook {
	Book bk=new Book();
	
	@Test public void TESTsetBookName() {
		bk.setBookName("测试名");
		assertEquals("测试名",bk.getBookName());
	}
	@Test public void TESTsetBookAuthor() {
		bk.setBookAuthor("测试作者");
		assertEquals("测试作者",bk.getBookAuthor());
	}
	@Test public void TESTsetReadTimes() {
		bk.setReadTimes(100);
		assertEquals(100,bk.getReadTimes());
	}
	@Test public void TESTreadBook() {
		int results;
		results=bk.getReadTimes();
		bk.readBook();
		results++;
		assertEquals(results,bk.getReadTimes());
	}
	@Test public void TESTsetMainClassification() {
		bk.setMainClassification("MainClass");
		assertEquals("MainClass",bk.getMainClassification());
	}
	@Test public void TESTsetSubClassification() {
		bk.setSubClassification("SubClass");
		assertEquals("SubClass",bk.getSubClassification());
	}
	@Test public void TESTsetStorePath() {
		bk.setStorePath("D:\\STUDY\\BOOK");
		assertEquals("D:\\STUDY\\BOOK",bk.getStorePath());
	}
	@SuppressWarnings("deprecation")
	@Test public void TESTsetRecentDate() {
		Date dt;
		dt=new Date(2011, 1, 8);
		
		bk.setRecentDate(dt);
		assertEquals(dt,bk.getRecentDate());
	}
}
