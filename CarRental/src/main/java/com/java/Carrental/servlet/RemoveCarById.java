package com.java.Carrental.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.Carrental.Exception.CarNotFoundException;
import com.java.Carrental.bal.carImpBal;
import com.java.Carrental.dao.CarImp;

/**
 * Servlet implementation class RemoveCarById
 */

public class RemoveCarById extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveCarById() {
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
		int carId = Integer.parseInt(request.getParameter("carId"));

       

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        carImpBal carDao = new carImpBal();
        try {
			 
			 out.println(carDao.removeCarbal(carId));
		       
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			out.println(e.getMessage());
		} catch (CarNotFoundException e) {
			// TODO Auto-generated catch block
			out.println(e.getMessage());
		}

       

        
	}

}
