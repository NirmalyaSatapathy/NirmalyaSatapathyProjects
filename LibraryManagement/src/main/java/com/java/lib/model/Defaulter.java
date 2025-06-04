package com.java.lib.model;
import java.sql.Date;

public class Defaulter {

    private String userName;      
    private int bookId;           
    private String bookName;      
    private Date fromDate;        
    private Date toDate;          
    private long daysOverdue;     
    private long fine;            
    private String status;        

    // Getter and Setter methods
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

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public long getDaysOverdue() {
        return daysOverdue;
    }

    public void setDaysOverdue(long daysOverdue) {
        this.daysOverdue = daysOverdue;
    }

    public long getFine() {
        return fine;
    }

    public void setFine(long fine) {
        this.fine = fine;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
