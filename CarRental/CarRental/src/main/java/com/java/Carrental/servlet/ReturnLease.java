package com.java.Carrental.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.Carrental.Exception.CarNotFoundException;
import com.java.Carrental.Exception.LeaseNotFoundException;
import com.java.Carrental.Exception.Uexception;
import com.java.Carrental.bal.Leaseimpbal;

/**
 * Servlet implementation class ReturnLease
 */
public class ReturnLease extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReturnLease() {
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
		int leaseId = Integer.parseInt(request.getParameter("leaseId"));
        Leaseimpbal l=new Leaseimpbal();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try {
			out.println(l.returnCarbal(leaseId));
		} catch (ClassNotFoundException | SQLException | CarNotFoundException | Uexception | LeaseNotFoundException e) {
			// TODO Auto-generated catch block
			out.print(e.getMessage());
		}
       

       
	}

}
