import static org.junit.Assert.*;

import org.junit.Test;

import cn.com.redspiderlily.bms.model.Book;



public class TESTClassificationNode {
	Book bk=new Book();
	@Test public void TESTsetMainClassification() {
		bk.setMainClassification("MainClass");
		assertEquals("MainClass",bk.getMainClassification());
	}
	@Test public void TESTsetSubClassification() {
		bk.setSubClassification("SubClass");
		assertEquals("SubClass",bk.getSubClassification());
	}
}
