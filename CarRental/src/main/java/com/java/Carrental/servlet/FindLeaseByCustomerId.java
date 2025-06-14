package com.java.Carrental.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.Carrental.Exception.CustomerNotFoundException;
import com.java.Carrental.bal.Leaseimpbal;
import com.java.Carrental.model.Lease;

/**
 * Servlet implementation class FindLeaseByCustomerId
 */

public class FindLeaseByCustomerId extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindLeaseByCustomerId() {
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
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
		int customerId = Integer.parseInt(request.getParameter("customerId"));
        Leaseimpbal dao = new Leaseimpbal();
        List<Lease> leases;
		try {
			leases = dao.getLeaseByCustomerbal(customerId);
			out.println("<html><body><h2>Leases for Customer ID: " + customerId + "</h2>");
            out.println("<table border='1' cellpadding='5'>");
            out.println("<tr><th>Lease ID</th><th>Car ID</th><th>Type</th><th>Duration</th><th>Cost</th><th>Start Date</th><th>End Date</th><th>Returned</th></tr>");
            for (Lease lease : leases) {
                out.println("<tr>");
                out.println("<td>" + lease.getLeaseId() + "</td>");
                out.println("<td>" + lease.getCarId() + "</td>");
                out.println("<td>" + lease.getType() + "</td>");
                out.println("<td>" + lease.getDuration() + "</td>");
                out.println("<td>" + lease.getCost() + "</td>");
                out.println("<td>" + lease.getStartDate() + "</td>");
                out.println("<td>" + lease.getEndDate() + "</td>");
                out.println("<td>" + lease.isReturned() + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
		} catch (ClassNotFoundException | SQLException | CustomerNotFoundException e) {
			// TODO Auto-generated catch block
			out.println(e.getMessage());
		}
        
       
	}

}
