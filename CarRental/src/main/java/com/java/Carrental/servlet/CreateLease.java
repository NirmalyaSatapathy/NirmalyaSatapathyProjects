package com.java.Carrental.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.Carrental.Exception.CarNotFoundException;
import com.java.Carrental.Exception.CustomerNotFoundException;
import com.java.Carrental.Exception.Uexception;
import com.java.Carrental.bal.Leaseimpbal;
import com.java.Carrental.model.Lease;

/**
 * Servlet implementation class CreateLease
 */

public class CreateLease extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateLease() {
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
	    // Get parameters from the request
	    int customerId = Integer.parseInt(request.getParameter("customerId"));
	    int carId = Integer.parseInt(request.getParameter("carId"));
	    String type = request.getParameter("type");
	    LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
	    LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));
	    double advance = Double.parseDouble(request.getParameter("advance"));  // Capture advance value

	    // Populate Lease object
	    Lease lease = new Lease();
	    lease.setCustomerId(customerId);
	    lease.setCarId(carId);
	    lease.setType(type);
	    lease.setStartDate(startDate);
	    lease.setEndDate(endDate);
	    lease.setAdvance(advance);  // Set the advance

	    // Call DAO to save lease and return response
	    Leaseimpbal leaseImpBal = new Leaseimpbal();
	    response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    try {
	        // Get the lease creation result (cost, advance, and lease ID)
	        String result = leaseImpBal.createLeasebal(lease);
	        out.println("<html><body>");
	        out.println("<h2>" + result + "</h2>");  // Show the returned message
	        out.println("</body></html>");
	    } catch (ClassNotFoundException | SQLException | CarNotFoundException | Uexception | CustomerNotFoundException e) {
	        // Handle exceptions properly
	        out.print("<html><body><h3>Error: " + e.getMessage() + "</h3></body></html>");
	    }
	}

	}

	


