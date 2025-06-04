package com.java.lib.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import com.java.lib.model.Books;
import com.java.lib.model.Defaulter;
import com.java.lib.model.LibAdmins;
import com.java.lib.model.LibUsers;
import com.java.lib.model.TranBook;
import com.java.lib.model.TransReturn;

public interface LibraryDao {
	public String createUser(LibUsers libUsers) throws ClassNotFoundException, SQLException;
	public int logIn(LibUsers libUsers) throws ClassNotFoundException, SQLException;
	public List<Books> searchBooks(String searchType,String searchValue) throws ClassNotFoundException, SQLException;
	public String issueBook(String userName,int bookId,Date date) throws ClassNotFoundException,SQLException;
	public List<TranBook> accountDetails(String userName) throws ClassNotFoundException, SQLException;
	public String returnBook(String userName,int bookId) throws ClassNotFoundException, SQLException;
	public List<TransReturn> showHistoryById(String name) throws ClassNotFoundException, SQLException;
	public int adminLogin(LibAdmins libUAdmins) throws ClassNotFoundException, SQLException;
	public String addBook(Books newBook) throws SQLException, ClassNotFoundException;
	public String createAdmin(LibAdmins libAdmins) throws ClassNotFoundException, SQLException;
	public List<Defaulter> getDefaulters() throws ClassNotFoundException, SQLException;
}
