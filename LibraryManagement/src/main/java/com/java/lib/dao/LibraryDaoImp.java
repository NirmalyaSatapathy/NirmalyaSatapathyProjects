package com.java.lib.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import com.java.lib.model.Books;
import com.java.lib.model.Defaulter;
import com.java.lib.model.LibAdmins;
import com.java.lib.model.LibUsers;
import com.java.lib.model.TranBook;
import com.java.lib.model.TransReturn;
import com.java.lib.util.ConnectionHelper;
import com.java.lib.util.EncryptPassword;

public class LibraryDaoImp implements LibraryDao{
	Connection con;
	PreparedStatement psmt;
	public String issuedOrNot(String userName, int bookId) throws ClassNotFoundException, SQLException {
	    con = ConnectionHelper.getConnection();
	    String checkIssued = "SELECT COUNT(*) AS cnt FROM TranBook WHERE UserName = ? AND BookId = ?";
	    PreparedStatement pst = con.prepareStatement(checkIssued);
	    pst.setString(1, userName);
	    pst.setInt(2, bookId);
	    ResultSet rs = pst.executeQuery();
	    rs.next();
	    if (rs.getInt("cnt") > 0) {
	        return "Book with ID " + bookId + " is already issued.";
	    }
	    String countIssued = "SELECT COUNT(*) AS total FROM TranBook WHERE UserName = ?";
	    pst = con.prepareStatement(countIssued);
	    pst.setString(1, userName);
	    rs = pst.executeQuery();
	    rs.next();
	    if (rs.getInt("total") >= 4) {
	        return "You cannot issue more than 4 books.";
	    }

	    return "OK";  // Allowed to issue
	}

	@Override
	public String createUser(LibUsers libUsers) throws ClassNotFoundException, SQLException {
		String encr=EncryptPassword.getCode(libUsers.getPassWord());
		con=ConnectionHelper.getConnection();
		String cmd="Insert into LibUsers(UserName,Password) values (?,?);";
		psmt=con.prepareStatement(cmd);
		psmt.setString(1,libUsers.getUserName());
		psmt.setString(2,encr);
		psmt.executeUpdate();
		return "User Account created successfully";
		}
	@Override
	public int logIn(LibUsers libUsers) throws ClassNotFoundException, SQLException {
		String encr=EncryptPassword.getCode(libUsers.getPassWord());
		con=ConnectionHelper.getConnection();
		String cmd="select count(*) cnt from LibUsers where UserName=? and "+ "Password=?";
		psmt=con.prepareStatement(cmd);
		psmt.setString(1,libUsers.getUserName());
		psmt.setString(2,encr);
		ResultSet rs=psmt.executeQuery();
		rs.next();
		int count=rs.getInt("cnt");
		return count;
	}
	@Override
	public List<Books> searchBooks(String searchType, String searchValue) throws ClassNotFoundException, SQLException {
		String cmd;
		boolean isValid=true;
		if(searchType.equals("id")) {
			cmd = " SELECT * FROM Books WHERE Id = ? " ;
		} else if (searchType.equals("bookname")) {
			cmd = " SELECT * FROM Books Where Name = ?";
		} else if (searchType.equals("authorname")) {
			cmd = "SELECT * FROM Books where Author = ?";
		} else if (searchType.equals("dept")) {
			cmd = "select * from Books where Dept = ?";
		} else {
			isValid = false;
			cmd = "select * from Books";
		}
		con = ConnectionHelper.getConnection();
		psmt = con.prepareStatement(cmd);
		if (isValid == true) {
			psmt.setString(1, searchValue);
		}
		ResultSet rs = psmt.executeQuery();
		Books books = null;
		List<Books> booksList = new ArrayList<Books>();
		while(rs.next()) {
			books = new Books();
			books.setId(rs.getInt("id"));
			books.setName(rs.getString("name"));
			books.setAuthor(rs.getString("author"));
			books.setEdition(rs.getString("edition"));
			books.setDept(rs.getString("dept"));
			books.setNoOfBooks(rs.getInt("TotalBooks"));
			booksList.add(books);
		}
		return booksList;
	}
	@Override
	public String issueBook(String userName, int bookId, Date date) throws ClassNotFoundException, SQLException {
	    String status = issuedOrNot(userName, bookId);
	    if (!"OK".equals(status)) {
	        return status; 
	    }
	    con = ConnectionHelper.getConnection();
	    String checkCopies = "SELECT TotalBooks FROM Books WHERE Id = ?";
	    psmt = con.prepareStatement(checkCopies);
	    psmt.setInt(1, bookId);
	    ResultSet rs = psmt.executeQuery();
	    if (rs.next()) {
	        int totalBooks = rs.getInt("TotalBooks");
	        if (totalBooks <= 1) {
	            return "This book is not available for issue (only 1 copy left).";
	        }
	    } else {
	        return "Book not found.";
	    }
	    String insert = "INSERT INTO TranBook(UserName, BookId, FromDate) VALUES (?, ?, ?)";
	    psmt = con.prepareStatement(insert);
	    psmt.setString(1, userName);
	    psmt.setInt(2, bookId);
	    psmt.setDate(3, date);
	    psmt.executeUpdate();
	    String update = "UPDATE Books SET TotalBooks = TotalBooks - 1 WHERE Id = ?";
	    psmt = con.prepareStatement(update);
	    psmt.setInt(1, bookId);
	    psmt.executeUpdate();
	    return "Book with ID " + bookId + " issued successfully.";
	}

	@Override
	public List<TranBook> accountDetails(String userName) throws ClassNotFoundException, SQLException {
		con=ConnectionHelper.getConnection();
		String cmd="select * from tranbook where username=?";
		psmt=con.prepareStatement(cmd);
		psmt.setString(1,userName);
		ResultSet rs=psmt.executeQuery();
		List<TranBook> booksIssued=new ArrayList<TranBook>();
		TranBook tranBook=null;
		while(rs.next())
		{
			tranBook=new TranBook();
			tranBook.setBookId(rs.getInt("BookId"));
			tranBook.setUserName(rs.getString("UserName"));
			tranBook.setFromDate(rs.getDate("FromDate"));
			booksIssued.add(tranBook);
		}
		System.out.println("User Name " +userName);
		System.out.println(booksIssued);
		return booksIssued;
	}
	@Override
	public String returnBook(String userName, int bookId) throws ClassNotFoundException, SQLException {
	    String cmd = "SELECT * FROM TranBook WHERE Username = ? and BookId = ?";
	    con = ConnectionHelper.getConnection();
	    psmt = con.prepareStatement(cmd);
	    psmt.setString(1, userName);
	    psmt.setInt(2, bookId);
	    ResultSet rs = psmt.executeQuery();

	    if (!rs.next()) {
	        return "No such book issued to this user.";
	    }

	    java.sql.Date fromDate = rs.getDate("FromDate");
	    long days = ChronoUnit.DAYS.between(fromDate.toLocalDate(), LocalDate.now());
	    long fine = (days > 21) ? (days - 21) * 5 : 0;
	    String sql2 = "INSERT INTO TransReturn(UserName, BookId, FromDate,fine) VALUES (?, ?, ?,?)";
	    psmt = con.prepareStatement(sql2);
	    psmt.setString(1, userName);
	    psmt.setInt(2, bookId);
	    psmt.setDate(3, fromDate);
	    psmt.setLong(4, fine);
	    psmt.executeUpdate();
	    String sql1 = "DELETE FROM TranBook WHERE BookId = ? AND Username = ?";
	    psmt = con.prepareStatement(sql1);
	    psmt.setInt(1, bookId);
	    psmt.setString(2, userName);
	    psmt.executeUpdate();
	    String sql3 = "UPDATE Books SET TotalBooks = TotalBooks + 1 WHERE Id = ?";
	    psmt = con.prepareStatement(sql3);
	    psmt.setInt(1, bookId);
	    psmt.executeUpdate();

	    return "Book with ID " + bookId + " returned by " + userName + ". " +
	           (fine > 0 ? "Fine: ₹" + fine : "No fine.");
	}


	@Override
	public List<TransReturn> showHistoryById(String name) throws ClassNotFoundException, SQLException {
		String cmd="select*from TransReturn where Username=?";
		con=ConnectionHelper.getConnection();
		psmt=con.prepareStatement(cmd);
		psmt.setString(1, name);
		ResultSet rs=psmt.executeQuery();
		
		List<TransReturn> trList=new ArrayList<TransReturn>();
		while(rs.next())
		{
			TransReturn tr = new TransReturn();
			tr.setBookId(rs.getInt("BookId"));
			tr.setFromDate(rs.getTimestamp("Fromdate"));
			tr.setToDate(rs.getTimestamp("Todate"));
			tr.setUserName(rs.getString("Username"));
			tr.setFineAmount(rs.getLong("fine"));
			trList.add(tr);
		}
		return trList;
	}
	@Override
	public int adminLogin(LibAdmins libAdmins) throws ClassNotFoundException, SQLException {
		String encr=EncryptPassword.getCode(libAdmins.getPassWord());
		con=ConnectionHelper.getConnection();
		String cmd="select count(*) cnt from LibAdmins where AdminName=? and "+ "Password=?";
		psmt=con.prepareStatement(cmd);
		psmt.setString(1,libAdmins.getAdminName());
		psmt.setString(2,encr);
		ResultSet rs=psmt.executeQuery();
		rs.next();
		int count=rs.getInt("cnt");
		return count;
	}
	@Override
	public String createAdmin(LibAdmins libAdmins) throws ClassNotFoundException, SQLException {
		String encr=EncryptPassword.getCode(libAdmins.getPassWord());
		con=ConnectionHelper.getConnection();
		String cmd="Insert into LibAdmins (AdminName,Password) values (?,?);";
		psmt=con.prepareStatement(cmd);
		psmt.setString(1,libAdmins.getAdminName());
		psmt.setString(2,encr);
		psmt.executeUpdate();
		return "Admin Account created successfully";
	}
	@Override
	public String addBook(Books newBook) throws SQLException, ClassNotFoundException {
		con=ConnectionHelper.getConnection();
		String checkQuery = "SELECT TotalBooks FROM books WHERE Name = ?";
		psmt = con.prepareStatement(checkQuery);
		psmt.setString(1,newBook.getName());
		ResultSet rs = psmt.executeQuery();
		if (rs.next()) {
		String cmdUpdate = "UPDATE books SET TotalBooks = ? WHERE Name = ?";
		psmt = con.prepareStatement(cmdUpdate);
        psmt.setInt(1, newBook.getNoOfBooks());
        psmt.setString(2, newBook.getName());
        psmt.executeUpdate();
        return "Book quantity updated successfully.";
        } else {
//        	Id INT primary key,
//        	Name varchar(50) NULL,
//        	Author varchar(50) NULL,
//        	Edition varchar(50) NULL,
//        	Dept varchar(20) NULL,
//        	TotalBooks INT NULL
        	String cmdInsert = "INSERT INTO books (Name,Author,Edition,Dept,TotalBooks) VALUES (?,?, ?, ?, ?)";
        	psmt = con.prepareStatement(cmdInsert);
		    psmt.setString(1, newBook.getName());
		    psmt.setString(2, newBook.getAuthor());
		    psmt.setString(3, newBook.getEdition());
		    psmt.setString(4, newBook.getDept());
		    psmt.setInt(5, newBook.getNoOfBooks());
		    psmt.executeUpdate();
	        return"New book added successfully. ";
		}

	}
	@Override
	public List<Defaulter> getDefaulters() throws ClassNotFoundException, SQLException {
	    List<Defaulter> defaulterList = new ArrayList<>();
	    con = ConnectionHelper.getConnection();
	    String query1 = "SELECT UserName, BookId, FromDate FROM TranBook ";
	    psmt = con.prepareStatement(query1);
	    ResultSet rs1 = psmt.executeQuery();
	    while (rs1.next()) {
	        String userName = rs1.getString("UserName");
	        int bookId = rs1.getInt("BookId");
	        Date fromDate = rs1.getDate("FromDate");
	        long daysOverdue = ChronoUnit.DAYS.between(fromDate.toLocalDate(), LocalDate.now());
	        if (daysOverdue > 21) {
	            Defaulter defaulter = new Defaulter();
	            defaulter.setUserName(userName);
	            defaulter.setBookId(bookId);
	            defaulter.setFromDate(fromDate);
	            defaulter.setDaysOverdue(daysOverdue);
	            defaulter.setFine((daysOverdue - 21) * 5);
	            defaulter.setStatus("Not Returned");
	            String bookQuery = "SELECT Name FROM Books WHERE Id = ?";
	            PreparedStatement psBook = con.prepareStatement(bookQuery);
	            psBook.setInt(1, bookId);
	            ResultSet rsBook = psBook.executeQuery();
	            if (rsBook.next()) {
	                defaulter.setBookName(rsBook.getString("Name"));
	            }
	            defaulterList.add(defaulter);
	        }
	    }
	    String query2 = "SELECT tr.UserName, tr.BookId, tr.FromDate, tr.ToDate FROM TransReturn tr WHERE DATEDIFF(tr.ToDate, tr.FromDate) > 21";
	    psmt = con.prepareStatement(query2);
	    ResultSet rs2 = psmt.executeQuery();
	    while (rs2.next()) {
	        String userName = rs2.getString("UserName");
	        int bookId = rs2.getInt("BookId");
	        Date fromDate = rs2.getDate("FromDate");
	        Date toDate = rs2.getDate("ToDate");
	        long daysOverdue = ChronoUnit.DAYS.between(fromDate.toLocalDate(), toDate.toLocalDate());
	        long fine = (daysOverdue - 21) * 5;
	        Defaulter defaulter = new Defaulter();
	        defaulter.setUserName(userName);
	        defaulter.setBookId(bookId);
	        defaulter.setFromDate(fromDate);
	        defaulter.setToDate(toDate);
	        defaulter.setDaysOverdue(daysOverdue);
	        defaulter.setFine(fine);
	        defaulter.setStatus("Returned Late");
	        String bookQuery = "SELECT Name FROM Books WHERE Id = ?";
	        PreparedStatement psBook = con.prepareStatement(bookQuery);
	        psBook.setInt(1, bookId);
	        ResultSet rsBook = psBook.executeQuery();
	        if (rsBook.next()) {
	            defaulter.setBookName(rsBook.getString("Name"));
	        }
	        defaulterList.add(defaulter);
	    }

	    return defaulterList;
	}


}
