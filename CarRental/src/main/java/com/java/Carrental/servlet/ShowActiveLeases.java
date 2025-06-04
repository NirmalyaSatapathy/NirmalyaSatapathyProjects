package com.java.Carrental.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.Carrental.dao.LeaseImp;
import com.java.Carrental.model.Lease;

/**
 * Servlet implementation class ShowActiveLeases
 */

public class ShowActiveLeases extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowActiveLeases() {
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
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			LeaseImp l=new LeaseImp();
	        List<Lease> activeLeases;
			try {
				activeLeases = l.showActiveLease();
				out.println("<html><head><title>Active Leases</title></head><body>");
		        out.println("<h2>Currently Active Leases</h2>");

		        if (activeLeases.isEmpty()) {
		            out.println("<p>No active leases found.</p>");
		        } else {
		            out.println("<table border='1' cellpadding='8'>");
		            out.println("<tr><th>ID</th><th>Customer</th><th>Car</th><th>Type</th><th>Duration</th><th>Cost</th><th>Start</th><th>End</th></tr>");
		            for (Lease lease : activeLeases) {
		                out.println("<tr>");
		                out.println("<td>" + lease.getLeaseId() + "</td>");
		                out.println("<td>" + lease.getCustomerId() + "</td>");
		                out.println("<td>" + lease.getCarId() + "</td>");
		                out.println("<td>" + lease.getType() + "</td>");
		                out.println("<td>" + lease.getDuration() + "</td>");
		                out.println("<td>" + lease.getCost() + "</td>");
		                out.println("<td>" + lease.getStartDate() + "</td>");
		                out.println("<td>" + lease.getEndDate() + "</td>");
		                out.println("</tr>");
		            }
		            out.println("</table>");
		        }} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	

			}}
