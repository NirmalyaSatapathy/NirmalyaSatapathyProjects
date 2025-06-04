package com.java.Carrental.dao;

import java.sql.SQLException;
import java.util.List;

import com.java.Carrental.model.Car;

public interface Cardao {
	public String addCar(Car car) throws ClassNotFoundException, SQLException;
	public String remove(int carId) throws ClassNotFoundException, SQLException;
	public List<Car> showAvailableCars() throws ClassNotFoundException, SQLException;
	public List<Car> showRentedCars() throws SQLException,ClassNotFoundException;
	public Car findCarById(int carId) throws ClassNotFoundException, SQLException;
}
