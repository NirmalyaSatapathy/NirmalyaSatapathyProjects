package com.java.Carrental.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.Carrental.Exception.CustomerNotFoundException;
import com.java.Carrental.Exception.Uexception;
import com.java.Carrental.bal.CustomerImpbal;
import com.java.Carrental.dao.CustomerImp;
import com.java.Carrental.model.Customer;

/**
 * Servlet implementation class UpdateCustomer
 */

public class UpdateCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCustomer() {
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
		int id=Integer.parseInt(request.getParameter("customerId"));
		String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        // Create Customer object
        Customer customer = new Customer();
        customer.setId(id);
        customer.setName(name);
        customer.setEmail(email);
        customer.setPhone(phone);

        // Call DAO to add customer
        CustomerImpbal c = new CustomerImpbal();
        try {
			response.getWriter().println(c.updateCustomerbal(customer));
		} catch (ClassNotFoundException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			response.getWriter().println(e.getMessage());
		} catch (CustomerNotFoundException e) {
			// TODO Auto-generated catch block
			response.getWriter().println(e.getMessage());
		} catch (Uexception e) {
			// TODO Auto-generated catch block
			response.getWriter().println(e.getMessage());
		}
	}

}
