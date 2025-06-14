package com.java.Carrental.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.Carrental.dao.PaymentsaoImp;
import com.java.Carrental.model.Payment;

/**
 * Servlet implementation class GetAllPayments
 */

public class GetAllPayments extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllPayments() {
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
		PaymentsaoImp dao = new PaymentsaoImp();
        List<Payment> payments;
		try {
			payments = dao.getAllPayments();
			 out.println("<html><body>");
		        out.println("<h2>All Payments</h2>");

		        if (payments.isEmpty()) {
		            out.println("<p>No payment records found.</p>");
		        } else {
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
		        }}
		catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			out.println(e.getMessage());
		}}}
       
	


