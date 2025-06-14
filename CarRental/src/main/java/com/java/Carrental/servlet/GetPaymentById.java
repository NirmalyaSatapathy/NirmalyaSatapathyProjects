package com.java.Carrental.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.Carrental.dao.PaymentsaoImp;
import com.java.Carrental.model.Payment;

/**
 * Servlet implementation class GetPaymentById
 */

public class GetPaymentById extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetPaymentById() {
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
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    int paymentId = Integer.parseInt(request.getParameter("paymentId"));
	    PaymentsaoImp dao = new PaymentsaoImp();
	    Payment p;
	    try {
	        p = dao.getPaymentById(paymentId);
	        out.println("<html><body><h2>Payment Details</h2>");
	        if (p == null) {
	            out.println("<p style='color:red;'>No payment found for ID: " + paymentId + "</p>");
	        } else {
	            out.println("<table border='1' cellpadding='5'>");
	            out.println("<tr><td>Payment ID</td><td>" + p.getPaymentId() + "</td></tr>");
	            out.println("<tr><td>Lease ID</td><td>" + p.getLeaseId() + "</td></tr>");
	            out.println("<tr><td>Amount</td><td>" + p.getAmount() + "</td></tr>");
	            out.println("<tr><td>Date</td><td>" + p.getDate() + "</td></tr>");
	            out.println("</table>");
	        }
	        out.println("</body></html>");
	    } catch (ClassNotFoundException | SQLException e) {
	        out.println( e.getMessage());
	    }
	}




        
       
	}


