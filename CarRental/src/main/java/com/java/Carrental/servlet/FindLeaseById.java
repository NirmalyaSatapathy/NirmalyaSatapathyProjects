package com.java.Carrental.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.Carrental.Exception.LeaseNotFoundException;
import com.java.Carrental.bal.Leaseimpbal;
import com.java.Carrental.model.Lease;

/**
 * Servlet implementation class FindLeaseById
 */

public class FindLeaseById extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindLeaseById() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int leaseId = Integer.parseInt(request.getParameter("leaseId"));
		Leaseimpbal l=new Leaseimpbal();
        Lease lease;
        PrintWriter out = response.getWriter();
		try {
			lease = l.getLeasebyIdbal(leaseId);
			response.setContentType("text/html");
	        out.println("<html><head><title>Lease Details</title></head><body>");
	        out.println("<h2>Lease Details</h2>");
	            out.println("<table border='1' cellpadding='10'>");
	            out.println("<tr><th>Field</th><th>Value</th></tr>");
	            out.println("<tr><td>Lease ID</td><td>" + lease.getLeaseId() + "</td></tr>");
	            out.println("<tr><td>Customer ID</td><td>" + lease.getCustomerId() + "</td></tr>");
	            out.println("<tr><td>Car ID</td><td>" + lease.getCarId() + "</td></tr>");
	            out.println("<tr><td>Type</td><td>" + lease.getType() + "</td></tr>");
	            out.println("<tr><td>Duration</td><td>" + lease.getDuration() + " days</td></tr>");
	            out.println("<tr><td>Cost</td><td>" + lease.getCost() + "</td></tr>");
	            out.println("<tr><td>Start Date</td><td>" + lease.getStartDate() + "</td></tr>");
	            out.println("<tr><td>End Date</td><td>" + lease.getEndDate() + "</td></tr>");
	            out.println("<tr><td>Is Returned?</td><td>" + lease.isReturned() + "</td></tr>");
	            out.println("</table>");     
		} catch (ClassNotFoundException | SQLException | LeaseNotFoundException e) {
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
