package com.java.Carrental.dao;

import java.sql.SQLException;
import java.util.List;

import com.java.Carrental.model.Lease;

public interface Leasedao {
	public String createLease(Lease lease) throws ClassNotFoundException, SQLException;
	public String returnCar(int leaseId) throws ClassNotFoundException, SQLException;
	List<Lease> showActiveLease() throws ClassNotFoundException, SQLException;
	List<Lease> showLeaseHistory() throws ClassNotFoundException, SQLException;
	public Lease getLeasebyId(int leaseId) throws ClassNotFoundException, SQLException;
	public List<Lease> getLeasebyCustomer(int CustomerId) throws ClassNotFoundException, SQLException;
}
