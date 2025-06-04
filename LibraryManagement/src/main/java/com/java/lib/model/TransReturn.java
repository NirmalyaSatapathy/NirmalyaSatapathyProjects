package com.java.lib.model;


import java.sql.Date;
import java.sql.Timestamp;

public class TransReturn {
//	Username varchar(50) NOT NULL,
//	BookId INT NOT NULL,
//	Fromdate date,
//	Todate TIMESTAMP DEFAULT CURRENT_TIMESTAMP
	private String userName;
	private int bookId;
	private Timestamp fromDate;
	private Timestamp toDate;
	private long fineAmount;
	public long getFineAmount() {
		return fineAmount;
	}
	public void setFineAmount(long l) {
		this.fineAmount = l;
	}
	public TransReturn() {
		
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
	public TransReturn(String userName, int bookId, Timestamp fromDate, Timestamp toDate) {
		this.userName = userName;
		this.bookId = bookId;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public Timestamp getFromDate() {
		return fromDate;
	}
	public void setFromDate(Timestamp timestamp) {
		this.fromDate = timestamp;
	}
	public Timestamp getToDate() {
		return toDate;
	}
	public void setToDate(Timestamp toDate) {
		this.toDate = toDate;
	}
}
