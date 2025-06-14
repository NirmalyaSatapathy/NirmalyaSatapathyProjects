package com.java.Carrental.dao;

import java.sql.SQLException;
import java.util.List;

import com.java.Carrental.model.Customer;

public interface CustomerDao {
	public String addCustomer(Customer customer) throws ClassNotFoundException, SQLException;
	public String updateCustomer(Customer customer) throws SQLException,ClassNotFoundException;
	public Customer getCustomerById(int id) throws SQLException, ClassNotFoundException;
	public List<Customer> getAllCustomer() throws ClassNotFoundException, SQLException; 
}
