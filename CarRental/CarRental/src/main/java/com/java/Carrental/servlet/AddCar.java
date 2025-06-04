package com.java.Carrental.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.Carrental.Exception.Uexception;
import com.java.Carrental.bal.carImpBal;
import com.java.Carrental.model.Car;

/**
 * Servlet implementation class AddCar
 */
public class AddCar extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public AddCar() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
	        String model = request.getParameter("model");
	        String brand = request.getParameter("brand");
	        double perDay = Double.parseDouble(request.getParameter("perDay"));
	        double perMonth = Double.parseDouble(request.getParameter("perMonth"));
	        boolean availability = request.getParameter("availability") != null;
	        String capacity=request.getParameter("capacity");
	        Car car = new Car();
	        car.setAvailability(availability);
	        car.setBrand(brand);
	        car.setEngine(capacity);
	        car.setModel(model);
	        car.setPerDay(perDay);
	        car.setPerMonth(perMonth);
	       
	        try {
				response.getWriter().println(new carImpBal().addCarbal(car));
			} catch (ClassNotFoundException | IOException | SQLException | Uexception e) {
				// TODO Auto-generated catch block
				response.getWriter().println(e.getMessage());
			}
	        
	}

}
