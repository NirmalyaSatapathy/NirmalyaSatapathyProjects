package com.java.Carrental.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.Carrental.model.Customer;
import com.java.Carrental.util.ConnectionHelper;

public class CustomerImp implements CustomerDao{
	
		
		Connection con;
		PreparedStatement psmt;
	@Override
	public String addCustomer(Customer cus) throws ClassNotFoundException, SQLException {
		String cmd="insert into CUSTOMER(email,name,phone) values(?,?,?)";
		con=ConnectionHelper.getConnection();
		
		
		psmt=con.prepareStatement(cmd,psmt.RETURN_GENERATED_KEYS);
		
		psmt.setString(1,cus.getEmail() );
		psmt.setString(2,cus.getName());
		psmt.setString(3,cus.getPhone());
		psmt.executeUpdate();
		ResultSet rs=psmt.getGeneratedKeys();
        rs.next();
        int id1=rs.getInt(1);
        cus.setId(id1);
		return "new customer added with id "+id1;
		
	}

	@Override
	public String updateCustomer(Customer customer) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Customer cus=new Customer();
		cus=getCustomerById(customer.getId());
		String cmd="Update CUSTOMER set name = ?,email=?, phone = ? Where customerId = ?";
		con=ConnectionHelper.getConnection();
		PreparedStatement psmt=con.prepareStatement(cmd);
		psmt.setString(1,customer.getName());
		psmt.setString(2,customer.getEmail());
		psmt.setString(3,customer.getPhone());
		psmt.setInt(4,cus.getId());
		psmt.executeUpdate();
		return "customer data updated";
	}

	@Override
	public Customer getCustomerById(int id) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
		String cmd="select * from CUSTOMER where customerId=?";
		con=ConnectionHelper.getConnection();
		PreparedStatement psmt=con.prepareStatement(cmd);
		psmt.setInt(1,id);
		ResultSet rs=psmt.executeQuery();
		if(rs.next())
		{
			Customer cus=new Customer();
			cus.setEmail(rs.getString("email"));
			cus.setId(rs.getInt("customerId"));
			cus.setName(rs.getString("name"));
			cus.setPhone(rs.getString("phone"));
			return cus;
		}
		
			return null;
		
		
	}

	@Override
	public List<Customer> getAllCustomer() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		List<Customer> customers=new ArrayList<Customer>();
		
		String cmd="select*from CUSTOMER";
		con=ConnectionHelper.getConnection();
		PreparedStatement psmt=con.prepareStatement(cmd);
		ResultSet rs=psmt.executeQuery();
		while(rs.next())
		{	Customer cus=new Customer();
			cus.setEmail(rs.getString("email"));
			cus.setId(rs.getInt("customerId"));
			cus.setName(rs.getString("name"));
			cus.setPhone(rs.getString("phone"));
			customers.add(cus);
		}
		return customers;
	}
	
}
