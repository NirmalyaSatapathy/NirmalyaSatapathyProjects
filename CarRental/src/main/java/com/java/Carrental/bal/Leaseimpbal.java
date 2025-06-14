package com.java.Carrental.bal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.java.Carrental.Exception.CarNotFoundException;
import com.java.Carrental.Exception.CustomerNotFoundException;
import com.java.Carrental.Exception.LeaseNotFoundException;
import com.java.Carrental.Exception.Uexception;
import com.java.Carrental.dao.CarImp;
import com.java.Carrental.dao.LeaseImp;
import com.java.Carrental.model.Lease;
import com.java.Carrental.util.ConnectionHelper;

public class Leaseimpbal {
	Connection con;
	public String createLeasebal(Lease lease) throws ClassNotFoundException, SQLException, CarNotFoundException, Uexception, CustomerNotFoundException
	{
		StringBuilder errors = new StringBuilder();
		    

		    if (lease.getCustomerId() <= 0)
		       errors.append("Customer ID must be positive.");

		    if (lease.getCarId() <= 0)
		       errors.append("Car ID must be positive.");

		    if (lease.getType() == null || lease.getType().trim().isEmpty())
		        errors.append("Lease type cannot be empty.");

		    if (lease.getStartDate() == null || lease.getEndDate() == null)
		       errors.append("Start date and end date cannot be null.");

		    if (lease.getEndDate().isBefore(lease.getStartDate()))
		        errors.append("End date must be after start date.");

		    if(!available(lease.getCarId()))
	        {
	        	throw new CarNotFoundException("car is either not available for rent/the car is not present");
	        }
	        if(!customerExists(lease.getCustomerId()))
		    {
		    	throw new CustomerNotFoundException(lease.getCustomerId()+" doesnot exist");
		    }
	        if (errors.length() > 0) {
	            throw new Uexception(errors.toString());
	        }
	       
	        else
	        {
	        	return new LeaseImp().createLease( lease);
	        }
		}
	public Lease getLeasebyIdbal(int leaseId) throws ClassNotFoundException, SQLException, LeaseNotFoundException
	{
		 	if(new LeaseImp().getLeasebyId(leaseId)!=null)
		 	{
		 		return new LeaseImp().getLeasebyId(leaseId);
		 	}
		 	else
		 	{
		 		throw new LeaseNotFoundException("lease id "+leaseId+" not in database");
		 	}
	}
	public List<Lease> getLeaseByCustomerbal(int customerId) throws ClassNotFoundException, SQLException, CustomerNotFoundException
	{
		if(!customerExists(customerId))
		{
			throw new CustomerNotFoundException(customerId+" is not present");
		}
		return new LeaseImp().getLeasebyCustomer(customerId);
		
	}
	private boolean available(int carId) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		String sql = "SELECT available FROM Car WHERE carId = ?";
		con=ConnectionHelper.getConnection();
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setInt(1, carId);
	    ResultSet rs = ps.executeQuery();
	    if (rs.next()) {
	        return String.valueOf(rs.getBoolean("available")).equalsIgnoreCase("True");
	    }
	    return false;
	}
	public String returnCarbal(int leaseId) throws SQLException, CarNotFoundException, ClassNotFoundException, Uexception, LeaseNotFoundException  {
	    StringBuilder errors = new StringBuilder();
	    con=ConnectionHelper.getConnection();
	    if (leaseId <= 0) {
	        errors.append("Lease ID must be greater than zero.\n");
	    }
	    String checkQuery = "SELECT isReturned FROM lease WHERE leaseId = ?";
	    PreparedStatement checkStmt = con.prepareStatement(checkQuery);
	    checkStmt.setInt(1, leaseId);
	    ResultSet rs = checkStmt.executeQuery();
	    if (!rs.next()) {
	       throw new LeaseNotFoundException("Lease with ID " + leaseId + " does not exist.\n");
	    } else if (rs.getBoolean("isReturned")) {
	        errors.append("This car has already been returned.\n");
	    }
	    if (errors.length() > 0) {
	        throw new Uexception(errors.toString());
	    }
	    return new LeaseImp().returnCar(leaseId);
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

