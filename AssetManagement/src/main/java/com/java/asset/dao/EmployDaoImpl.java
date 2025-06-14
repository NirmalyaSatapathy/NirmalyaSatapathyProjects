package com.java.asset.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.java.asset.model.Employ;
import com.java.asset.util.ConnectionHelper;
import com.java.asset.util.EncryptPassword;

public class EmployDaoImpl implements EmployDao{
	Connection con;
	PreparedStatement pst;
	private static final Logger log = Logger.getLogger("com.java.asset.dao.EmployDaoImpl");
	@Override
	public String addEmployDao(Employ employ) throws ClassNotFoundException, SQLException {
	    Connection con = ConnectionHelper.getConnection();
	    String empCmd = "INSERT INTO Employee (Username, PasswordHash, Department, Email, Type) VALUES (?, ?, ?, ?, ?)";
	    String adminCmd = "INSERT INTO Admin (Username, PasswordHash, Email) VALUES (?, ?, ?)";
	        PreparedStatement empPstmt = con.prepareStatement(empCmd);
	        PreparedStatement adminPstmt = "admin".equalsIgnoreCase(employ.getType())
	                                        ? con.prepareStatement(adminCmd)
	                                        : null;
	        String hashedPassword=EncryptPassword.getCode(employ.getPassword());
	        empPstmt.setString(1, employ.getUserName());
	        empPstmt.setString(2, hashedPassword); // Ideally hash this
	        empPstmt.setString(3, employ.getDept());
	        empPstmt.setString(4, employ.getEmail());
	        empPstmt.setString(5, employ.getType());
	        int empInserted = empPstmt.executeUpdate();
	        // If admin, insert into Admin table
	        if ("admin".equalsIgnoreCase(employ.getType()) && adminPstmt != null) {
	            adminPstmt.setString(1, employ.getUserName());
	            adminPstmt.setString(2,hashedPassword); // Same here: hash recommended
	            adminPstmt.setString(3, employ.getEmail());
	            adminPstmt.executeUpdate();
	        }
	        con.close();
	        if (empInserted > 0) {
	        	log.info("employ added");
	            return "Employee added successfully" + 
	                   ("admin".equalsIgnoreCase(employ.getType()) ? " as Admin." : ".");
	        } else {
	            return "Failed to add employee.";
	        }   
	}
	@Override
	public List<Employ> showEmployDao() throws ClassNotFoundException, SQLException {
	    Connection con = ConnectionHelper.getConnection();
	    String cmd = "SELECT * FROM Employee";
	    List<Employ> employList = new ArrayList<>();
	    PreparedStatement pstmt = con.prepareStatement(cmd);
	         ResultSet rs = pstmt.executeQuery();
	        while (rs.next()) {
	            Employ emp = new Employ();
	            emp.setUserName(rs.getString("Username"));
	            emp.setPassword(rs.getString("PasswordHash")); // Be cautious exposing passwords
	            emp.setDept(rs.getString("Department"));
	            emp.setEmail(rs.getString("Email"));
	            emp.setType(rs.getString("Type"));
	            employList.add(emp);
	        }
	        con.close();
	        log.info("employ record returned");
	    return employList;
	}


}
