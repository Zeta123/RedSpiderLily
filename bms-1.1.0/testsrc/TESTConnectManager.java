import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import cn.com.redspiderlily.bms.db.ConnectManager;



public class TESTConnectManager {
	@Test public void TESTgetConnection() throws SQLException {
		Connection con;
		con=ConnectManager.getConnection();
		assertNotNull(con);
	}
}
