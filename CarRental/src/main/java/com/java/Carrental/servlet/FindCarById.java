package com.java.Carrental.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.Carrental.Exception.CarNotFoundException;
import com.java.Carrental.bal.carImpBal;
import com.java.Carrental.dao.CarImp;
import com.java.Carrental.model.Car;

/**
 * Servlet implementation class FindCarById
 */

public class FindCarById extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindCarById() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int carId = Integer.parseInt(request.getParameter("carId"));
		 PrintWriter out = response.getWriter();
		carImpBal c = new carImpBal();
        Car car;
		try {
			car = c.findCarByIdbal(carId);
			 response.setContentType("text/html");
		       

		        out.println("<html><head><title>Car Details</title></head><body>");
		        out.println("<h2>Car Details</h2>");

		        if (car != null) {
		            out.println("<div class='result'>");
		            out.println("<p><strong>ID:</strong> " + car.getId() + "</p>");
		            out.println("<p><strong>Model:</strong> " + car.getModel() + "</p>");
		            out.println("<p><strong>Brand:</strong> " + car.getBrand() + "</p>");
		            out.println("<p><strong>Price per Day:</strong> " + car.getPerDay() + "</p>");
		            out.println("<p><strong>Price per Day:</strong> " + car.getPerMonth() + "</p>");
		            out.println("</div>");
		        } else {
		            out.println("<p style='color:red;'>No car found with ID " + carId + "</p>");
		        }

		        out.println("<br><a href='findCarId.html'>Search Again</a>");
		        out.println("</body></html>");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			out.println(e.getMessage());
		} catch (CarNotFoundException e) {
			// TODO Auto-generated catch block
			out.println(e.getMessage());
		}

       
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
