package com.java.Carrental.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.Carrental.model.Car;
import com.java.Carrental.util.ConnectionHelper;

public class CarImp implements Cardao {
	
	
	Connection con;
	PreparedStatement psmt;
	@Override
	public String addCar(Car car) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		  
		  String cmd = "insert into CAR (brand, model, available,perDay,perMonth,engine) VALUES (?, ?, ?,?,?,?)";
		  con=ConnectionHelper.getConnection();
		  psmt=con.prepareStatement(cmd,psmt.RETURN_GENERATED_KEYS);
		  
          psmt.setString(1, car.getBrand());
          psmt.setString(2, car.getModel());
          psmt.setBoolean(3,car.isAvailability());
          psmt.setDouble(4, car.getPerDay());
          psmt.setDouble(5,car.getPerMonth());
          psmt.setString(6, car.getEngine());
          psmt.executeUpdate();
          ResultSet rs=psmt.getGeneratedKeys();
          rs.next();
          int id1=rs.getInt(1);
          car.setId(id1);
          return "successfully added the id is "+id1;
	}

	@Override
	public String remove(int carId) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		 String cmd = "DELETE FROM car WHERE carId = ?";
		 con=ConnectionHelper.getConnection();
		 psmt=con.prepareStatement(cmd);
		 psmt.setInt(1, carId);
         psmt.executeUpdate();
         return "successfully removed";
	}

	@Override
	public List<Car> showAvailableCars() throws ClassNotFoundException, SQLException {
		  List<Car> cars = new ArrayList<>();
	      String cmd = "SELECT * FROM car WHERE available = true";
	      con=ConnectionHelper.getConnection();
	      psmt=con.prepareStatement(cmd);
	      ResultSet rs = psmt.executeQuery();
          while (rs.next()) {
        	  Car car = new Car();
              car.setId(rs.getInt("carId"));
              car.setBrand(rs.getString("brand"));
              car.setModel(rs.getString("model"));
              car.setAvailability(rs.getBoolean("available"));//availabale=isAvailabale try
              car.setPerMonth(rs.getDouble("perMonth"));
              car.setPerDay(rs.getDouble("perDay"));
              car.setEngine(rs.getString("engine"));
              cars.add(car);
          }
          return cars;
	}

	@Override
	public List<Car> showRentedCars() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		  List<Car> cars = new ArrayList<>();
	      String cmd = "SELECT * FROM car WHERE available = false";
	      con=ConnectionHelper.getConnection();
	      psmt=con.prepareStatement(cmd);
	      ResultSet rs = psmt.executeQuery();
	      if(rs!=null)
	      {
          while (rs.next()) {
        	  Car car = new Car();
              car.setId(rs.getInt("carId"));
              car.setBrand(rs.getString("brand"));
              car.setModel(rs.getString("model"));
              car.setAvailability(rs.getBoolean("available"));//availabale=isAvailabale try
              car.setPerMonth(rs.getDouble("perMonth"));
              car.setPerDay(rs.getDouble("perDay"));
              car.setEngine(rs.getString("engine"));
              cars.add(car);
          }
          return cars;
	      }
	      return null;
	}

	@Override
	public Car findCarById(int carId) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		 String cmd = "SELECT * FROM car WHERE carId = ?";
		 con=ConnectionHelper.getConnection();
		 Car car = new Car();
	     psmt=con.prepareStatement(cmd);
	     psmt.setInt(1, carId);
         ResultSet rs = psmt.executeQuery();
         if (rs.next()) {
             car.setId(rs.getInt("carId"));
             car.setBrand(rs.getString("brand"));
             car.setModel(rs.getString("model"));
             car.setAvailability(rs.getBoolean("available"));
             car.setPerMonth(rs.getDouble("perMonth"));
             car.setPerDay(rs.getDouble("perDay"));
             car.setEngine(rs.getString("engine"));
         }
		return car;
	}

}
