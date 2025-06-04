package com.java.Carrental.dao;

import java.sql.SQLException;
import java.util.List;

import com.java.Carrental.model.Payment;

public interface Paymentdao {
	String makePayment(Payment payment) throws ClassNotFoundException, SQLException;
	List<Payment> getAllPayments() throws ClassNotFoundException, SQLException;
	List<Payment> getPaymentsByLeaseId(int leaseId) throws ClassNotFoundException, SQLException;
	public Payment getPaymentById(int paymentId) throws ClassNotFoundException, SQLException;
}
