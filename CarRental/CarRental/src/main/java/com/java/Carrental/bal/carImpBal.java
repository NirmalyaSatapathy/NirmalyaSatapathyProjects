package com.java.Carrental.bal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.java.Carrental.Exception.CarNotFoundException;
import com.java.Carrental.Exception.Uexception;
import com.java.Carrental.dao.CarImp;
import com.java.Carrental.model.Car;
import com.java.Carrental.util.ConnectionHelper;

public class carImpBal {
	public String addCarbal(Car car) throws  ClassNotFoundException, SQLException, Uexception
	{
		 StringBuilder errors = new StringBuilder();
	      
	        if (car.getBrand() == null || car.getBrand().trim().isEmpty()) {
	            errors.append("Brand cannot be empty");
	        }
	        if (car.getModel() == null || car.getModel().trim().isEmpty()) {
	            errors.append("Model cannot be empty.\n");
	        }
	        if (car.getPerDay() <= 0) {
	            errors.append("Per day rent must be a positive number.\n");
	        }
	        if (car.getPerMonth() <= 0) {
	           errors.append("Per month rent must be a positive number.\n");
	        }
	        if (errors.length() > 0) {
	            throw new Uexception(errors.toString());
	        }
	        else
	        {
	        	return new CarImp().addCar(car);
	        }
	}
	public String removeCarbal(int carId) throws CarNotFoundException, ClassNotFoundException, SQLException
	{
		 StringBuilder errors = new StringBuilder();
	        if (carId <= 0) {
	            errors.append("Car ID must be a positive integer.\n");
	        } else {
	            Connection con = ConnectionHelper.getConnection();
	            String query = "SELECT COUNT(*) FROM car WHERE carId = ?";
	            PreparedStatement ps = con.prepareStatement(query);
	            ps.setInt(1, carId);
	            ResultSet rs = ps.executeQuery();
	            rs.next();
	            int count = rs.getInt(1);
	            if (count == 0) {
	                errors.append("Car with ID " + carId + " does not exist in the database.\n");
	            }
	            rs.close();
	            ps.close();
	            con.close();
	        }
	        if (errors.length() > 0) {
	            throw new CarNotFoundException(errors.toString());
	        }
	        return new CarImp().remove( carId);
	}
	public Car findCarByIdbal(int carId) throws ClassNotFoundException, SQLException, CarNotFoundException
	{
		 StringBuilder errors = new StringBuilder();
	        if (carId <= 0) {
	            errors.append("Car ID must be a positive number");
	        } else {
	            Connection con = ConnectionHelper.getConnection();
	            String query = "SELECT COUNT(*) FROM car WHERE carId = ?";
	            PreparedStatement ps = con.prepareStatement(query);
	            ps.setInt(1, carId);
	            ResultSet rs = ps.executeQuery();
	            rs.next();
	            int count = rs.getInt(1);
	            if (count == 0) {
	                errors.append("Car with ID ").append(carId).append(" does not exist");
	            }
	            rs.close();
	            ps.close();
	            con.close();
	        }
	        if (errors.length() > 0) {
	            throw new CarNotFoundException(errors.toString());
	        }
	        return new CarImp().findCarById(carId);
	}
}
