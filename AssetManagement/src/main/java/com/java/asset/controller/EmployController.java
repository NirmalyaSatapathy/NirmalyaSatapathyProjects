package com.java.asset.controller;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.java.asset.bal.EmployBal;
import com.java.asset.model.Employ;

public class EmployController {
EmployBal employBal;

public EmployBal getEmployBal() {
	return employBal;
}
public void setEmployBal(EmployBal employBal) {
	this.employBal = employBal;
}
private static final Logger log = Logger.getLogger("com.java.asset.Controller.EmployController");
public String addEmployController(Employ employ) throws ClassNotFoundException, SQLException
{
	log.info("In add employcontroller");
	return employBal.addEmployBal(employ);
}
public List<Employ> showEmployController() throws ClassNotFoundException, SQLException
{
	log.info("in show employ controller");
	return employBal.showEmployBal();
}
}
