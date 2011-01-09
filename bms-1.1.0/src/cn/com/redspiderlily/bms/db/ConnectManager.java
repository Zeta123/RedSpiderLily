package cn.com.redspiderlily.bms.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.eclipse.jface.preference.IPreferenceStore;

import cn.com.redspiderlily.bms.Activator;
import cn.com.redspiderlily.bms.preferences.DBPreferencePage;

public class ConnectManager {
	private static Connection con;
	private ConnectManager(){}
	
	public static Connection getConnection() throws SQLException{
		if(con != null && !con.isClosed())
			return con;
		
		IPreferenceStore ps = Activator.getDefault().getPreferenceStore();
		String className,url,username,password;
		if(DBPreferencePage.CLASSNAME_KEY != null)
			className = ps.getString(DBPreferencePage.CLASSNAME_KEY);
		else
			className = ps.getString(DBPreferencePage.getClassnameDefault());
		
		if(DBPreferencePage.URL_KEY != null)
			url = ps.getString(DBPreferencePage.URL_KEY);
		else
			url = ps.getString(DBPreferencePage.getUrlDefault());
		
		if(DBPreferencePage.USERNAME_KEY != null)
			username = ps.getString(DBPreferencePage.USERNAME_KEY);
		else
			username = ps.getString(DBPreferencePage.getUsernameDefault());
		
		if(DBPreferencePage.PASSWORD_KEY != null)
			password = ps.getString(DBPreferencePage.PASSWORD_KEY);
		else
			password = ps.getString(DBPreferencePage.getPasswordDefault());
		
		try{
			Class.forName(className);
			con = DriverManager.getConnection(url,username,password);
		}catch(ClassNotFoundException e){e.printStackTrace();}
		return con;
	}
	
	public static void closeConnection(){
		if(con == null) return;
		try{con.close();}catch(SQLException e){e.printStackTrace();}
		con = null;
	}
}