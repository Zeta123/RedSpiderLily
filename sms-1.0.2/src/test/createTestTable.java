package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class createTestTable {
	public static void main(String[] args){
		Connection con = null;
		Statement stmt = null;

		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=sms","sa","123");
			stmt = con.createStatement();

			stmt.addBatch("USE sms;");
			
			StringBuilder sb = new StringBuilder();
			sb.append("CREATE TABLE test_table(");
			sb.append("Id integer NOT NULL,");
			sb.append("name0 varchar(4) default NULL,");
			sb.append("name1 varchar(4) default NULL,");
			sb.append("name2 varchar(4) default NULL,");
			sb.append("name3 varchar(4) default NULL,");
			sb.append("PRIMARY KEY (Id)");
			sb.append(");");
			stmt.addBatch(sb.toString());
			stmt.executeBatch();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(stmt != null){
				try{stmt.close();}catch(SQLException e){e.printStackTrace();}
				stmt = null;
			}
			if(con != null){
				try{con.close();}catch(SQLException e){e.printStackTrace();}
				con = null;
			}
		}
	}
}
