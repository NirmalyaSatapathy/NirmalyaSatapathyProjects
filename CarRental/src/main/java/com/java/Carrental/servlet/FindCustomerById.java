package com.java.Carrental.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.Carrental.Exception.CustomerNotFoundException;
import com.java.Carrental.bal.CustomerImpbal;
import com.java.Carrental.model.Customer;

/**
 * Servlet implementation class FindCustomerById
 */

public class FindCustomerById extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindCustomerById() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 int customerId = Integer.parseInt(request.getParameter("customerId"));

	        
	        CustomerImpbal c=new CustomerImpbal();
	        PrintWriter out = response.getWriter();
	        
	        Customer customer;
			try {
				customer = c.getCustomerByIdbal(customerId);
				 // Set content type
		        response.setContentType("text/html");
		        

		        out.println("<html><head><title>Customer Details</title></head><body>");
		        out.println("<h2>Customer Information</h2>");

		       
		            out.println("<p><strong>ID:</strong> " + customer.getId() + "</p>");
		            out.println("<p><strong>Name:</strong> " + customer.getName() + "</p>");
		            out.println("<p><strong>Email:</strong> " + customer.getEmail() + "</p>");
		            out.println("<p><strong>Phone:</strong> " + customer.getPhone() + "</p>");
		       

		        out.println("<br><a href='getCustomerById.html'>Search Again</a>");
		        out.println("</body></html>");
			} catch (ClassNotFoundException | SQLException | CustomerNotFoundException e) {
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
