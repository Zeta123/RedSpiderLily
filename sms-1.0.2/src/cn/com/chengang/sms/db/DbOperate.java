package cn.com.chengang.sms.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.com.chengang.sms.model.Book;

public class DbOperate {
	
	public Book getBook(String storePath){
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		try{
			con = ConnectManager.getConnection();
			sm = con.createStatement();
			String sql = "select * from book where storePath = '" + storePath + "' ";
			rs = sm.executeQuery(sql);
			while(rs.next()){
				Book book = new Book();
				book.setBookName(rs.getString("bookName"));
				book.setBookAuthor(rs.getString("bookAuthor"));
				book.setMainClassification(rs.getString("mainClassification"));
				book.setSubClassification(rs.getString("subClassification"));
				book.setReadTimes(rs.getInt("readTimes"));
				book.setStorePath(rs.getString("storePath"));
				return book;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);
			close(sm);
			close(con);
		}
		return null;
	}
	
	public Set<Book> getBook(){
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		try{
			con = ConnectManager.getConnection();
			sm = con.createStatement();
			String sql = "select * from book";
			rs = sm.executeQuery(sql);
			Set<Book> set = new HashSet<Book>();
			while(rs.next()){
				Book book = new Book();
				book.setBookName(rs.getString("bookName"));
				book.setBookAuthor(rs.getString("bookAuthor"));
				book.setMainClassification(rs.getString("mainClassification"));
				book.setSubClassification(rs.getString("subClassification"));
				book.setReadTimes(rs.getInt("readTimes"));
				book.setStorePath(rs.getString("storePath"));
				set.add(book);
			}
			return set;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);
			close(sm);
			close(con);
		}
		return Collections.emptySet();
	}
	
	public List<Book> getBook(QueryInfo qi){
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		try{
			con = ConnectManager.getConnection();
			sm = con.createStatement();
			
			rs = sm.executeQuery("select count(*) from book");
			rs.next();
			qi.rsCount = rs.getInt(1);
			if(qi.rsCount == 0)
				return Collections.emptyList();
			if(qi.rsCount % qi.pageSize == 0)
				qi.pageCount = qi.rsCount / qi.pageSize;
			else
				qi.pageCount = (qi.rsCount / qi.pageSize)+1;
			
			int start = (qi.currentPage - 1) * qi.pageSize;
			rs = sm.executeQuery("select * from book limit" + start + "," + qi.pageSize);
			List<Book> list = new ArrayList<Book>(qi.pageSize);
			while(rs.next()){
				Book book = new Book();
				book.setBookName(rs.getString("bookName"));
				book.setBookAuthor(rs.getString("bookAuthor"));
				book.setMainClassification(rs.getString("mainClassification"));
				book.setSubClassification(rs.getString("subClassification"));
				book.setReadTimes(rs.getInt("readTimes"));
				book.setStorePath(rs.getString("storePath"));
				list.add(book);
			}
			return list;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);
			close(sm);
			close(con);
		}
		return Collections.emptyList();
	}
	
	public boolean removeBook(Book book){
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		boolean result = true;
		try{
			con = ConnectManager.getConnection();
			con.setAutoCommit(false);
			sm = con.createStatement();
			sm.addBatch("delete from book where storePath = '" + book.getStorePath() + "' ");
			sm.executeBatch();
			con.commit();
		}catch(SQLException e){
			result = false;
			e.printStackTrace();
			
			try {con.rollback();}catch(Exception e2){e2.printStackTrace();}
		}finally{
			close(rs);
			close(sm);
			close(con);
		}
		return result;
	}
	
	public boolean insertBook(Book book){
		Connection con = null;
		PreparedStatement sm = null;
		ResultSet rs = null;
		try{
			con = ConnectManager.getConnection();
			//ºÏ≤È÷ÿ√˚
			if(getBook(book.getStorePath()) != null)
				return false;
			
			String sql = "insert into book values (?,?,?,?,?,?);";
			sm = con.prepareStatement(sql);
			sm.setString(1, book.getBookName());
			sm.setString(2, book.getBookAuthor());
			sm.setString(3, book.getMainClassification());
			sm.setString(4, book.getSubClassification());
			sm.setInt(5, book.getReadTimes());
			sm.setString(6, book.getStorePath());
			
			sm.execute();
			return true;
		}catch(SQLException e){
			e.printStackTrace();
			try{con.rollback();}catch(Exception e2){e2.printStackTrace();}
		}finally{
			close(rs);
			close(sm);
			close(con);
		}
		return false;
	}
	
	public List<String> getAllMainClassification(){
		Connection con = null;
		PreparedStatement sm = null;
		ResultSet rs = null;
		try{
			con = ConnectManager.getConnection();
			sm = con.prepareStatement("select distinct mainClassification from classification");
			rs = sm.executeQuery();
			
			List<String> list = new ArrayList<String>();
			while(rs.next())
				list.add(rs.getString("mainClassification"));

			return list;
		}catch(SQLException e){
			e.printStackTrace();
			try{con.rollback();}catch(Exception e2){e2.printStackTrace();}
		}finally{
			close(rs);
			close(sm);
			close(con);
		}
		return Collections.emptyList();
	}
	
	public List<String> getAllSubClassification(String mainClassification){
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		try{
			con = ConnectManager.getConnection();
			String sql = "select subClassification from classification where mainClassification = '" + mainClassification + "' ";
			sm = con.createStatement();
			rs = sm.executeQuery(sql);
			
			List<String> list = new ArrayList<String>();
			while(rs.next())
				list.add(rs.getString("subClassification"));

			return list;
		}catch(SQLException e){
			e.printStackTrace();
			try{con.rollback();}catch(Exception e2){e2.printStackTrace();}
		}finally{
			close(rs);
			close(sm);
			close(con);
		}
		return Collections.emptyList();	
	}

	
	void close(ResultSet rs){
		if(rs != null){
			try{rs.close();}catch(SQLException e){e.printStackTrace();}
			rs = null;
		}
	}
	
	void close(Statement sm){
		if(sm != null){
			try{sm.close();}catch(SQLException e){e.printStackTrace();}
			sm = null;
		}
	}
	
	void close(Connection con){}
}
