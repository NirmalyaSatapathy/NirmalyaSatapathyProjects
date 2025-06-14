package com.java.Carrental.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.java.Carrental.model.Payment;
import com.java.Carrental.util.ConnectionHelper;


public class PaymentsaoImp implements Paymentdao{
	
	Connection con;
	PreparedStatement psmt;
	@Override
	public String makePayment(Payment payment) throws ClassNotFoundException, SQLException {
	    String cmd = "INSERT INTO PAYMENT(leaseId, amount, paymentDate) VALUES (?, ?, ?)";
	    con = ConnectionHelper.getConnection();
	    psmt = con.prepareStatement(cmd, Statement.RETURN_GENERATED_KEYS);

	    psmt.setInt(1, payment.getLeaseId());
	    psmt.setDouble(2, payment.getAmount());
	    psmt.setDate(3, Date.valueOf(payment.getDate()));
	    
	    psmt.executeUpdate();

	    ResultSet rs = psmt.getGeneratedKeys();
	    if (rs.next()) {
	        int paymentId = rs.getInt(1);
	        payment.setPaymentId(paymentId);
	        return "Payment added with ID " + paymentId;
	    } else {
	        return "Payment was added, but no ID returned.";
	    }
	}
	public double getTotalPaymentsByLeaseId(int leaseId) throws SQLException, ClassNotFoundException {
	    String query = "SELECT SUM(amount) FROM PAYMENT WHERE leaseId = ?";
	    double total = 0.0;

	    Connection con = ConnectionHelper.getConnection();
	    PreparedStatement psmt = con.prepareStatement(query);
	    psmt.setInt(1, leaseId);
	    ResultSet rs = psmt.executeQuery();

	    if (rs.next()) {
	        total = rs.getDouble(1);
	    }

	    return total;
	}



	@Override
	public List<Payment> getAllPayments() throws SQLException, ClassNotFoundException {
		String cmd="select * from PAYMENT";
		List<Payment> pay=new ArrayList<Payment>();
		con=ConnectionHelper.getConnection();
		PreparedStatement psmt=con.prepareStatement(cmd);
		ResultSet rs=psmt.executeQuery();
		while(rs.next())
		{
			Payment p=new Payment();
			p.setAmount(rs.getDouble("amount"));
			p.setDate(rs.getDate("paymentDate").toLocalDate());
			p.setLeaseId(rs.getInt("leaseId"));
			p.setPaymentId(rs.getInt("paymentId"));
			
			pay.add(p);
		}
		return pay;
	}

	@Override
	public List<Payment> getPaymentsByLeaseId(int leaseId) throws ClassNotFoundException, SQLException {
		String cmd="select * from PAYMENT where leaseId=?";
		List<Payment> pay=new ArrayList<Payment>();
		con=ConnectionHelper.getConnection();
		PreparedStatement psmt=con.prepareStatement(cmd);
		psmt.setInt(1, leaseId);
		ResultSet rs=psmt.executeQuery();
		while(rs.next())
		{
			Payment p=new Payment();
			p.setAmount(rs.getDouble("amount"));
			p.setDate(rs.getDate("paymentDate").toLocalDate());
			p.setLeaseId(rs.getInt("leaseId"));
			p.setPaymentId(rs.getInt("paymentId"));
			pay.add(p);
		}
		return pay;
	}

	@Override
	public Payment getPaymentById(int paymentId) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		String cmd="select * from PAYMENT where paymentId=?";
		con=ConnectionHelper.getConnection();
		PreparedStatement psmt=con.prepareStatement(cmd);
		psmt.setInt(1, paymentId);
		ResultSet rs=psmt.executeQuery();
		Payment p=new Payment();
		if(rs.next())
		{		
			p.setAmount(rs.getDouble("amount"));
			p.setDate(rs.getDate("paymentDate").toLocalDate());
			p.setLeaseId(rs.getInt("leaseId"));
			p.setPaymentId(rs.getInt("paymentId"));
			return p;
		}
		return null;
	}

}
