package com.java.Carrental.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.Carrental.Exception.CarNotFoundException;
import com.java.Carrental.Exception.LeaseNotFoundException;
import com.java.Carrental.Exception.Uexception;
import com.java.Carrental.bal.Paymentsbal;
import com.java.Carrental.model.Payment;

/**
 * Servlet implementation class GetPaymentByLeaseId
 */

public class GetPaymentByLeaseId extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetPaymentByLeaseId() {
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
		int leaseId = Integer.parseInt(request.getParameter("leaseId"));
		 response.setContentType("text/html");
	     PrintWriter out = response.getWriter();
        Paymentsbal pay = new Paymentsbal();
        List<Payment> payments;
		try {
			payments = pay.getPaymentsByLeaseId(leaseId);
			 out.println("<html><body><h2>Payments for Lease ID: " + leaseId + "</h2>");

		        if (payments != null && !payments.isEmpty()) {
		            out.println("<table border='1' cellpadding='5'>");
		            out.println("<tr><th>Payment ID</th><th>Lease ID</th><th>Amount</th><th>Date</th></tr>");
		            for (Payment p : payments) {
		                out.println("<tr>");
		                out.println("<td>" + p.getPaymentId() + "</td>");
		                out.println("<td>" + p.getLeaseId() + "</td>");
		                out.println("<td>" + p.getAmount() + "</td>");
		                out.println("<td>" + p.getDate() + "</td>");
		                out.println("</tr>");
		            }
		            out.println("</table>");
			}
		} catch (ClassNotFoundException | SQLException | CarNotFoundException | LeaseNotFoundException | Uexception e) {
			// TODO Auto-generated catch block
			out.println(e.getMessage());
		}
}}
