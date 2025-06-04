package com.java.lib.model;


import java.sql.Date;
import java.sql.Timestamp;

public class TranBook {
private String userName;
private int bookId;
private Date fromDate;
public Date getFromDate() {
	return fromDate;
}
public void setFromDate(Date date) {
	this.fromDate = date;
}

public TranBook(String userName, int bookId) {
	
	this.userName = userName;
	this.bookId = bookId;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public int getBookId() {
	return bookId;
}
public void setBookId(int bookId) {
	this.bookId = bookId;
}
public TranBook() {
	
}

}
