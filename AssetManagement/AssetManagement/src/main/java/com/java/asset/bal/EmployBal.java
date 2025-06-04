package com.java.asset.bal;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.java.asset.dao.EmployDao;
import com.java.asset.model.Employ;

public class EmployBal {
public EmployDao getEmployDao() {
		return employDao;
	}
	public void setEmployDao(EmployDao employDao) {
		this.employDao = employDao;
	}
EmployDao employDao;
private static final Logger log = Logger.getLogger("com.java.asset.bal.EmployBal");
public String addEmployBal(Employ employ) throws ClassNotFoundException, SQLException
{
	log.info("in bal layer for add employ");
	 employDao.addEmployDao(employ);
	 return"EmployDashBoard?faces-redirect=true";
}
public List<Employ> showEmployBal() throws ClassNotFoundException, SQLException
{
	log.info("in bal layer for showemploy");
	return employDao.showEmployDao();
}
}
