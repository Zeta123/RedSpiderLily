

import cn.com.redspiderlily.bms.db.DbOperate;
import cn.com.redspiderlily.bms.model.Book;
import static org.junit.Assert.*;
import org.junit.Test;

public class TESTDBOperate {
	
	@Test public void TESTgetBook(){
		String target = ""; 
		Book result = DbOperate.getBook(target); 
		assertNull(result);
	}
	
}

