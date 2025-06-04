package com.jdbc.lmsdao;

import java.sql.SQLException;

import com.jdbc.lms.Employ;

public interface EmployDao {
	
	public Employ searchEmployDaoImpl(int empId) throws ClassNotFoundException, SQLException;
	public void showAllEmployDaoImpl() throws SQLException, ClassNotFoundException;
	public int getEmployLeaveById(int empId) throws ClassNotFoundException, SQLException;
	public String setEmployLeaveDayDaoImpl(int empId,int noOfLeaveDays) throws ClassNotFoundException, SQLException;
	
}
