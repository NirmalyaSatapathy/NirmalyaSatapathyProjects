package com.java.Carrental.bal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.java.Carrental.Exception.CarNotFoundException;
import com.java.Carrental.Exception.CustomerNotFoundException;
import com.java.Carrental.Exception.Uexception;
import com.java.Carrental.dao.CustomerImp;
import com.java.Carrental.model.Customer;
import com.java.Carrental.util.ConnectionHelper;

public class CustomerImpbal {
	Connection con;
	public String addCustomerbal(Customer cus) throws  ClassNotFoundException, SQLException, Uexception {
	    StringBuilder errors = new StringBuilder();
	    
	    if (cus.getName() == null || cus.getName().trim().isEmpty()) {
	        errors.append("Name cannot be null or empty.\n");
	    }
	    if (cus.getEmail() == null || !cus.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
	        errors.append("Invalid email format.");
	    }
	    if (cus.getPhone() == null || ((cus.getPhone().length()!=10))) {
	        errors.append("Phone must be a 10-digit number.");
	    }
	    if (errors.length() > 0) {
	        throw new Uexception(errors.toString());
	    }
	    return new CustomerImp().addCustomer(cus);
	}
	public String updateCustomerbal(Customer cus) throws  ClassNotFoundException, SQLException, CustomerNotFoundException, Uexception
	{
		StringBuilder errors = new StringBuilder();
	    if (cus.getId() <= 0) {
	        errors.append("Customer ID must be greater than zero.");
	    }
	    if (cus.getName() == null || cus.getName().trim().isEmpty()) {
	        errors.append("Name cannot be null or empty.\n");
	    }
	    if (cus.getEmail() == null || !cus.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
	        errors.append("Invalid email format.");
	    }
	    if (cus.getPhone() == null || ((cus.getPhone().length()!=10))) {
	        errors.append("Phone must be a 10-digit number.");
	    }
	    if(!customerExists(cus.getId()))
	    {
	    	throw new CustomerNotFoundException(cus.getId()+" doesnot exist");
	    }
	    if (errors.length() > 0) {
	        throw new Uexception(errors.toString());
	    }
	   
	   
	    return new CustomerImp().updateCustomer(cus);
	    
	}
	public Customer getCustomerByIdbal(int id) throws SQLException, ClassNotFoundException, CustomerNotFoundException {
		if(new CustomerImp().getCustomerById(id)==null)
		{
			throw new CustomerNotFoundException("customer with id "+ id+" not present");
		}
		else
		{
			return new CustomerImp().getCustomerById(id);
		}
	}
	public boolean customerExists(int customerId) throws ClassNotFoundException, SQLException
	{
		con=ConnectionHelper.getConnection();
		String cmd="select*from CUSTOMER where customerId=?";
		PreparedStatement pst=con.prepareStatement(cmd);
		pst.setInt(1, customerId);
		ResultSet rs=pst.executeQuery();
		return rs.next();
	}
}
