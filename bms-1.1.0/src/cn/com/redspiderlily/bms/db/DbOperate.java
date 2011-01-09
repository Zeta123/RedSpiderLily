package cn.com.redspiderlily.bms.db;

import java.io.File;
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

import cn.com.redspiderlily.bms.model.Book;
import cn.com.redspiderlily.bms.model.ClassificationNode;

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
				book.setRecentDate(rs.getDate("recentDate"));
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
				book.setRecentDate(rs.getDate("recentDate"));
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
	
	public void deleteInvalidBook(){
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		try{
			con = ConnectManager.getConnection();
			sm = con.createStatement();
			String sql = "select * from book";
			rs = sm.executeQuery(sql);
			while(rs.next()){
				Book book = new Book();
				book.setBookName(rs.getString("bookName"));
				book.setBookAuthor(rs.getString("bookAuthor"));
				book.setMainClassification(rs.getString("mainClassification"));
				book.setSubClassification(rs.getString("subClassification"));
				book.setReadTimes(rs.getInt("readTimes"));
				book.setStorePath(rs.getString("storePath"));
				book.setRecentDate(rs.getDate("recentDate"));
				
				File file = new File(book.getStorePath());
				if(!file.exists()){
					sm = con.createStatement();
					sm.addBatch("delete from book where storePath = '" + book.getStorePath() + "' ");
					sm.executeBatch();
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);
			close(sm);
			close(con);
		}
	}
	
	public Set<Book> getFavouriteBook(){
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		try{
			con = ConnectManager.getConnection();
			sm = con.createStatement();
			String sql = "select * from book where readTimes IN(select MAX(readTimes) from book)";
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
				book.setRecentDate(rs.getDate("recentDate"));
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
	
	public Set<Book> getRecentReadBook(){
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		try{
			con = ConnectManager.getConnection();
			sm = con.createStatement();
			String sql = "select * from book where recentDate IN(select MAX(recentDate) from book)";
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
				book.setRecentDate(rs.getDate("recentDate"));
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

	public List<String> getSearchedSubClassification(String MainClassification){
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		
		try{
			con = ConnectManager.getConnection();
			sm = con.createStatement();
			String sql = "select distinct subClassification " +
							"from classification " +
							"where mainClassification = '" + MainClassification +"' ";
			rs = sm.executeQuery(sql);
			List<String> searchedSubClassification = new ArrayList<String>();
			while(rs.next())
				searchedSubClassification.add(rs.getString("subClassification"));
			return searchedSubClassification;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);
			close(sm);
			close(con);
		}
		return Collections.emptyList();
	}
	
	public List<ClassificationNode> getClassification(){
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		
		try{
			con = ConnectManager.getConnection();
			sm = con.createStatement();
			String sql = "select * from classification";
			rs = sm.executeQuery(sql);
			List<ClassificationNode> classificationList = new ArrayList<ClassificationNode>();
			while(rs.next())
			{
				ClassificationNode classification = new ClassificationNode();
				classification.setMainClassification((rs.getString("mainClassification")));
				classification.setSubClassification((rs.getString("subClassification")));
				classificationList.add(classification);
			}
			return classificationList;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);
			close(sm);
			close(con);
		}
		return Collections.emptyList();
	}
	
	public List<Book> getSyntheticalSearchedBookList(String searchKey){		
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		
		try{
			con = ConnectManager.getConnection();
			sm = con.createStatement();
			String sql = "select * from book " +
							"where bookName like '%" + searchKey + "%' " +
							"or bookAuthor like '%" + searchKey + "%'";
			rs = sm.executeQuery(sql);
			List<Book> searchedBookList = new ArrayList<Book>();
			while(rs.next())
			{
				Book book = new Book();
				book.setBookName(rs.getString("bookName"));
				book.setBookAuthor(rs.getString("bookAuthor"));
				book.setMainClassification(rs.getString("mainClassification"));
				book.setSubClassification(rs.getString("subClassification"));
				book.setReadTimes(rs.getInt("readTimes"));
				book.setStorePath(rs.getString("storePath"));
				book.setRecentDate(rs.getDate("recentDate"));
				searchedBookList.add(book);
			}
			return searchedBookList;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);
			close(sm);
			close(con);
		}
		return Collections.emptyList();
	}
	
	public List<Book> getBookNameSearchedBookList(String searchKeyBookName){
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		
		try{
			con = ConnectManager.getConnection();
			sm = con.createStatement();
			String sql = "select * from book " +
							"where bookName like '%" + searchKeyBookName + "%' ";
			rs = sm.executeQuery(sql);
			List<Book> searchedBookList = new ArrayList<Book>();
			while(rs.next())
			{
				Book book = new Book();
				book.setBookName(rs.getString("bookName"));
				book.setBookAuthor(rs.getString("bookAuthor"));
				book.setMainClassification(rs.getString("mainClassification"));
				book.setSubClassification(rs.getString("subClassification"));
				book.setReadTimes(rs.getInt("readTimes"));
				book.setStorePath(rs.getString("storePath"));
				book.setRecentDate(rs.getDate("recentDate"));
				searchedBookList.add(book);
			}
			return searchedBookList;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);
			close(sm);
			close(con);
		}
		return Collections.emptyList();
	}
	
	public List<Book> getAuthorSearchedBookList(String searchKeyAuthor){
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		
		try{
			con = ConnectManager.getConnection();
			sm = con.createStatement();
			String sql = "select * from book " +
							"where bookAuthor like '&" + searchKeyAuthor + "%'";
			rs = sm.executeQuery(sql);
			List<Book> searchedBookList = new ArrayList<Book>();
			while(rs.next())
			{
				Book book = new Book();
				book.setBookName(rs.getString("bookName"));
				book.setBookAuthor(rs.getString("bookAuthor"));
				book.setMainClassification(rs.getString("mainClassification"));
				book.setSubClassification(rs.getString("subClassification"));
				book.setReadTimes(rs.getInt("readTimes"));
				book.setStorePath(rs.getString("storePath"));
				book.setRecentDate(rs.getDate("recentDate"));
				searchedBookList.add(book);
			}
			return searchedBookList;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);
			close(sm);
			close(con);
		}
		return Collections.emptyList();
	}
	
	public List<Book> getClassifiedBookList(String mainClassification, String subClassification){
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		
		try{
			con = ConnectManager.getConnection();
			sm = con.createStatement();
			String sql = "select * from book " +
							"where mainClassification = '" + mainClassification + "' " +
							" and subClassification = '" + subClassification + "' ";
			rs = sm.executeQuery(sql);
			List<Book> searchedBookList = new ArrayList<Book>();
			while(rs.next())
			{
				Book book = new Book();
				book.setBookName(rs.getString("bookName"));
				book.setBookAuthor(rs.getString("bookAuthor"));
				book.setMainClassification(rs.getString("mainClassification"));
				book.setSubClassification(rs.getString("subClassification"));
				book.setReadTimes(rs.getInt("readTimes"));
				book.setStorePath(rs.getString("storePath"));
				book.setRecentDate(rs.getDate("recentDate"));
				searchedBookList.add(book);
			}
			return searchedBookList;
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
			
			String sql = "insert into book values (?,?,?,?,?,?,?);";
			sm = con.prepareStatement(sql);
			sm.setString(1, book.getBookName());
			sm.setString(2, book.getBookAuthor());
			sm.setString(3, book.getMainClassification());
			sm.setString(4, book.getSubClassification());
			sm.setInt(5, book.getReadTimes());
			sm.setString(6, book.getStorePath());
			sm.setDate(7, book.getRecentDate());
			
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

	public boolean modifyBook(Book book){
		Connection con = null;
		PreparedStatement sm = null;
		ResultSet rs = null;
		try{
			con = ConnectManager.getConnection();
			String sql = "update book set bookName=?,bookAuthor=?,mainClassification=?,subClassification=? where storePath = ?";
			sm = con.prepareStatement(sql);
			sm.setString(1, book.getBookName());
			sm.setString(2, book.getBookAuthor());
			sm.setString(3, book.getMainClassification());
			sm.setString(4, book.getSubClassification());
			sm.setString(5, book.getStorePath());
			sm.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
			try{con.rollback();}catch(Exception e2){e2.printStackTrace();}
			return false;
		}finally{
			close(rs);
			close(sm);
			close(con);
		}
		return true;
	}
	
	public boolean readBook(Book book){
		Connection con = null;
		PreparedStatement sm = null;
		ResultSet rs = null;
		try{
			con = ConnectManager.getConnection();
			String sql = "update book set readTimes = ?,recentDate = ? where storePath = ?";
			sm = con.prepareStatement(sql);
			sm.setInt(1, book.getReadTimes());
			sm.setDate(2, book.getRecentDate());
			sm.setString(3, book.getStorePath());
			sm.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
			try{con.rollback();}catch(Exception e2){e2.printStackTrace();}
			return false;
		}finally{
			close(rs);
			close(sm);
			close(con);
		}
		return true;
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
