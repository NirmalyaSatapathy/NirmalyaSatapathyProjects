package com.java.Carrental.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.Carrental.Exception.CarNotFoundException;
import com.java.Carrental.Exception.LeaseNotFoundException;
import com.java.Carrental.Exception.Uexception;
import com.java.Carrental.bal.Leaseimpbal;
import com.java.Carrental.bal.Paymentsbal;
import com.java.Carrental.dao.PaymentsaoImp;
import com.java.Carrental.model.Lease;
import com.java.Carrental.model.Payment;
import com.java.Carrental.util.ConnectionHelper;

/**
 * Servlet implementation class MakePayment
 */

public class MakePayment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MakePayment() {
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

	    int leaseId = Integer.parseInt(request.getParameter("leaseId"));
	    double amount = Double.parseDouble(request.getParameter("amount"));
	    LocalDate date = LocalDate.now();

	    Payment payment = new Payment();
	    payment.setLeaseId(leaseId);
	    payment.setAmount(amount);
	    payment.setDate(date);

	    try {
	        Lease lease = new Leaseimpbal().getLeasebyIdbal(leaseId);
	        Paymentsbal paymentsbal = new Paymentsbal();

	        // Get past payments
	        double pastPayments = new PaymentsaoImp().getTotalPaymentsByLeaseId(leaseId);
	        double remainingAmount = lease.getCost() - lease.getAdvance() - pastPayments;

	        // Log for debugging
	        System.out.println("Lease ID: " + leaseId);
	        System.out.println("Lease Cost: " + lease.getCost());
	        System.out.println("Advance: " + lease.getAdvance());
	        System.out.println("Past Payments: " + pastPayments);
	        System.out.println("Remaining: " + remainingAmount);
	        System.out.println("Entered Amount: " + amount);

	        // Floating point-safe check
	        if (Math.abs(amount - remainingAmount) > 0.01) {
	            out.println("<html><body><h3>Please enter the expected amount: " + String.format("%.2f", remainingAmount) + "</h3></body></html>");
	            return;
	        }

	        // Make the payment
	        try {
	            String result = paymentsbal.makePaymentbal(payment);

	            // After successful payment, show the required details:
	            out.println("<html><body>");
	            out.println("<h3>Payment Successful!</h3>");
	            out.println("<p>Lease ID: " + leaseId + "</p>");
	            out.println("<p>Amount Paid: " + String.format("%.2f", amount) + "</p>");
	            out.println("<p>Total Cost: " + String.format("%.2f", lease.getCost()) + "</p>");
	            out.println("<p>Advance Paid: " + String.format("%.2f", lease.getAdvance()) + "</p>");
	            out.println("<p>Remaining Balance after Payment: " + String.format("%.2f", remainingAmount - amount) + "</p>");
	            out.println("<p>" + result + "</p>");
	            out.println("</body></html>");
	        } catch (CarNotFoundException e) {
	            out.println("<html><body><h3>Error: " + e.getMessage() + "</h3></body></html>");
	        }

	    } catch (ClassNotFoundException | SQLException | LeaseNotFoundException | Uexception e) {
	        out.println("<html><body><h3>Error: " + e.getMessage() + "</h3></body></html>");
	    }
	}

	}

	

	    
	


