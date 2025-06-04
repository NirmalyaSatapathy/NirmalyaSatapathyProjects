package com.java.Carrental.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.Carrental.dao.CarImp;
import com.java.Carrental.model.Car;

/**
 * Servlet implementation class RentedCars
 */
public class RentedCars extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RentedCars() {
        super();
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
		CarImp c=new CarImp();
        List<Car> cars;
        PrintWriter out = response.getWriter();
		try {
			cars = c.showRentedCars();
			
	        out.println("<html><body><h2>Car List</h2>");

	        if (cars.isEmpty()) {
	            out.println("<p>No cars available.</p>");
	        } else {
	            out.println("<table border='1'><tr><th>ID</th><th>Model</th><th>Brand</th><th>perDayPrice</th><th>perMonthPrice</th><th>Availability</th></tr>");
	            for (Car car : cars) {
	                out.println("<tr><td>" + car.getId() + "</td><td>" + car.getModel() + "</td><td>" + car.getBrand()
	                        + "</td><td>" + car.getPerDay() + "</td><td>" + car.getPerMonth()+"</td><td>" + car.isAvailability() + "</td></tr>");
	            }
	            out.println("</table>");
	        }

	        out.println("</body></html>");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			out.println(e.getMessage());
		}
	
		}
}
