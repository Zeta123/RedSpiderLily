package cn.com.redspiderlily.bms.model;

import java.sql.Date;

public class Book {
	private String bookName;
	private String bookAuthor;
	private int readTimes;
	private String mainClassification;
	private String subClassification;
	private String storePath;
	private Date recentDate;
	
	public Book(){}
	
	public Book(String bookName,String bookAuthor,String storePath){
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
		this.storePath = storePath;
		this.readTimes = 0;
		this.recentDate = new Date(System.currentTimeMillis());
	}
	
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	
	public int getReadTimes() {
		return readTimes;
	}
	public void setReadTimes(int readTimes) {
		this.readTimes = readTimes;
	}
	public void readBook(){
		++this.readTimes;
		this.setRecentDate(new Date(System.currentTimeMillis()));
	}
	
	public String getMainClassification() {
		return mainClassification;
	}
	public void setMainClassification(String mainClassification) {
		this.mainClassification = mainClassification;
	}
	
	public String getSubClassification() {
		return subClassification;
	}
	public void setSubClassification(String subClassification) {
		this.subClassification = subClassification;
	}
	
	public String getStorePath() {
		return storePath;
	}
	public void setStorePath(String storePath) {
		this.storePath = storePath;
	}

	public Date getRecentDate() {
		return recentDate;
	}

	public void setRecentDate(Date recentDate) {
		this.recentDate = recentDate;
	}

}
