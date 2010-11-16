package cn.com.chengang.sms.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectManager {
	private static Connection con;
	private ConnectManager(){}
	
	public static Connection getConnection() throws SQLException{
		if(con != null && !con.isClosed())
			return con;
		
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=sms","sa","123");
		}catch(ClassNotFoundException e){e.printStackTrace();}
		return con;
	}
	
	public static void closeConnection(){
		try{con.close();}catch(SQLException e){e.printStackTrace();}
		con = null;
	}
}