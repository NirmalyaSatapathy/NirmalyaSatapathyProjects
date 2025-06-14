package com.java.Carrental.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.Carrental.dao.CarImp;
import com.java.Carrental.dao.CustomerImp;
import com.java.Carrental.model.Car;
import com.java.Carrental.model.Customer;

/**
 * Servlet implementation class Customers
 */

public class Customers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Customers() {
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
		// TODO Auto-generated method stub
		CustomerImp c=new CustomerImp();
		PrintWriter out = response.getWriter();
        List<Customer> customers;
		try {
			customers = c.getAllCustomer();
			
	        out.println("<html><body><h2>Car List</h2>");

	        if (customers.isEmpty()) {
	            out.println("<p>No cars available.</p>");
	        } else {
	            out.println("<table border='1'><tr><th>ID</th><th>Name</th><th>Email</th><th>Phone</th>");
	            for (Customer customer : customers) {
	                out.println("<tr><td>" + customer.getId() + "</td><td>" + customer.getName() + "</td><td>" + customer.getEmail()
	                        + "</td><td>" + customer.getPhone());
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
