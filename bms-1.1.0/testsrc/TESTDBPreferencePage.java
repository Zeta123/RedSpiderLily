
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import cn.com.redspiderlily.bms.preferences.DBPreferencePage;

public class TESTDBPreferencePage {

	@Test public void TESTgetClassnameDefault() {
		assertEquals("com.microsoft.sqlserver.jdbc.SQLServerDriver",DBPreferencePage.getClassnameDefault());
	}
	
	@Test public void TESTgetUrlDefault() {
		assertEquals("jdbc:sqlserver://localhost:1433;DatabaseName=sms",DBPreferencePage.getUrlDefault());
	}
	
	@Test public void TESTgetUsernameDefault() {
		assertEquals("sa",DBPreferencePage.getUsernameDefault());
	}
	
	@Test public void TESTgetPasswordDefault() {
		assertEquals("123",DBPreferencePage.getPasswordDefault());
	}
}
