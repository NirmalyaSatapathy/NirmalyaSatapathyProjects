package com.java.Carrental.bal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.java.Carrental.Exception.CarNotFoundException;
import com.java.Carrental.Exception.LeaseNotFoundException;
import com.java.Carrental.Exception.Uexception;
import com.java.Carrental.dao.LeaseImp;
import com.java.Carrental.dao.PaymentsaoImp;
import com.java.Carrental.model.Lease;
import com.java.Carrental.model.Payment;
import com.java.Carrental.util.ConnectionHelper;

public class Paymentsbal {
	Connection con;
	public String makePaymentbal(Payment payment) throws ClassNotFoundException, SQLException, CarNotFoundException, LeaseNotFoundException, Uexception {
	    StringBuilder errors = new StringBuilder();

	    int leaseId = payment.getLeaseId();
	    double amount = payment.getAmount();
	    LocalDate date = payment.getDate();

	    if (leaseId <= 0) {
	        errors.append("Lease ID must be greater than 0.\n");
	    }

	    if (!leaseExists(leaseId)) {
	        throw new LeaseNotFoundException("Lease with ID " + leaseId + " does not exist.");
	    }

	    if (amount <= 0) {
	        errors.append("Payment amount must be greater than 0.\n");
	    }

	    if (date == null) {
	        errors.append("Payment date cannot be null.\n");
	    }

	    // Get lease details (total cost and advance)
	    Lease lease = new LeaseImp().getLeasebyId(leaseId);
	    double totalCost = lease.getCost();
	    double advance = lease.getAdvance();

	    // Calculate total past payments made
	    double pastPayments = 0.0;
	    try (Connection con = ConnectionHelper.getConnection();
	         PreparedStatement psmt = con.prepareStatement("SELECT COALESCE(SUM(amount), 0) FROM PAYMENT WHERE leaseId = ?")) {
	        psmt.setInt(1, leaseId);
	        try (ResultSet rs = psmt.executeQuery()) {
	            if (rs.next()) {
	                pastPayments = rs.getDouble(1);
	            }
	        }
	    }

	    // Calculate remaining balance (total cost - advance - past payments)
	    double remaining = totalCost - advance - pastPayments;

	    // Log for debugging
	    System.out.println("Total Cost: " + totalCost);
	    System.out.println("Advance Paid: " + advance);
	    System.out.println("Past Payments: " + pastPayments);
	    System.out.println("Remaining Amount: " + remaining);
	    
	    // Check if the entered amount is greater than or less than the remaining amount
	    if (amount != remaining) {
	        // If the entered amount is greater or less than the expected remaining balance
	        errors.append("Please enter the expected amount: " + String.format("%.2f", remaining));
	    }

	    if (errors.length() > 0) {
	        throw new Uexception(errors.toString());
	    }

	    return new PaymentsaoImp().makePayment(payment);
	}


	public List<Payment> getPaymentsByLeaseId(int leaseId) throws ClassNotFoundException, SQLException, CarNotFoundException, LeaseNotFoundException, Uexception
	{
		 
		if(!leaseExists(leaseId))
		{
			throw new LeaseNotFoundException("Lease with ID " + leaseId + " does not exist.");
		}
		return new PaymentsaoImp().getPaymentsByLeaseId(leaseId);
	}
	private double getCost(int leaseId) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
			con = ConnectionHelper.getConnection();
		    String cmd = "SELECT cost FROM Lease WHERE leaseId = ?";
		    PreparedStatement ps = con.prepareStatement(cmd);
		    ps.setInt(1, leaseId);
		    ResultSet rs = ps.executeQuery();
		    rs.next();
		    return rs.getDouble("cost");
		    
	}
	public boolean leaseExists(int leaseId) throws ClassNotFoundException, SQLException
	{
		con=ConnectionHelper.getConnection();
		String cmd="select*from Lease where leaseId=?";
		PreparedStatement pst=con.prepareStatement(cmd);
		pst.setInt(1, leaseId);
		ResultSet rs=pst.executeQuery();
		return rs.next();
	}
}
