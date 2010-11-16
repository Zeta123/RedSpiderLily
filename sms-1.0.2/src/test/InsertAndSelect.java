/*package firstproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertAndSelect {
	public static void main(String[] args){
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=sms","sa","123");
			sm = con.createStatement();
			sm.executeUpdate("delete from test_table");
			
			
			sm.executeUpdate("insert into test_table(Id,name0) values(1,'∞Ÿ')");
			sm.executeUpdate("insert into test_table(Id,name1) values(2,'¿Ô')");
			sm.executeUpdate("insert into test_table(Id,name2) values(3,'Õ¿')");
			sm.executeUpdate("insert into test_table(Id,name3) values(4,'À’')");
			
			rs = sm.executeQuery("select * from test_table");
			while(rs.next())
				System.out.println(rs.getInt("id")+"_"+rs.getString("name0")+"_"+rs.getString("name1")+"_"+rs.getString("name2")+"_"+rs.getString("name3"));
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs != null){
				try{rs.close();}catch(SQLException e){e.printStackTrace();}
				rs = null;
			}
			if(sm != null){
				try{sm.close();}catch(SQLException e){e.printStackTrace();}
				sm = null;
			}
			if(con != null){
				try{con.close();}catch(SQLException e){e.printStackTrace();}
				con = null;
			}
		}
	}
}*/
