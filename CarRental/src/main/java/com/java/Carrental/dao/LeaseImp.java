package com.java.Carrental.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import com.java.Carrental.model.Lease;
import com.java.Carrental.model.Payment;
import com.java.Carrental.util.ConnectionHelper;

public class LeaseImp implements Leasedao {
	 PreparedStatement psmt1;
	Connection con;
	double cost(long duration,int carId,String type) throws ClassNotFoundException, SQLException
	{
		String day="select perDay from Car where carId=?";
		String month="select perMonth from Car where carId=?";
		con=ConnectionHelper.getConnection();
	     PreparedStatement psmt1=con.prepareStatement(day);
	     PreparedStatement psmt2=con.prepareStatement(month);
		if(type.equalsIgnoreCase("daily"))
		{
			psmt1.setInt(1, carId);
			ResultSet rs=psmt1.executeQuery();
			rs.next();
			return duration*rs.getInt("perDay");
		}
		psmt2.setInt(1, carId);
		ResultSet rs=psmt2.executeQuery();
		rs.next();
		return duration*rs.getInt("perMonth");
	}
	
	public String createLease(Lease lease) throws ClassNotFoundException, SQLException {
	    // Calculate the duration and set it in the lease object
	    lease.setDuration(duration(lease.getStartDate(), lease.getEndDate(), lease.getType()));

	    // Prepare SQL commands
	    String cmd1 = "INSERT INTO Lease (customerId, carId, leaseType, duration, startDate, endDate, isReturned, cost, advance) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	    String cmd2 = "UPDATE Car SET available = false WHERE carId = ?";

	    // Database connection
	    con = ConnectionHelper.getConnection();
	    psmt1 = con.prepareStatement(cmd1, psmt1.RETURN_GENERATED_KEYS);
	    PreparedStatement psmt2 = con.prepareStatement(cmd2);

	    // Set parameters for Lease insert statement
	    psmt1.setInt(1, lease.getCustomerId());
	    psmt1.setInt(2, lease.getCarId());
	    psmt1.setString(3, lease.getType());
	    psmt1.setLong(4, lease.getDuration());
	    psmt1.setDate(5, Date.valueOf(lease.getStartDate()));
	    psmt1.setDate(6, Date.valueOf(lease.getEndDate()));
	    psmt1.setBoolean(7, false);  // isReturned initially false
	    psmt1.setDouble(8, cost(lease.getDuration(), lease.getCarId(), lease.getType()) ); // Total cost minus advance
	    psmt1.setDouble(9, lease.getAdvance());  // Set the advance

	    // Execute lease insertion
	    psmt1.executeUpdate();

	    // Update car availability
	    psmt2.setInt(1, lease.getCarId());
	    psmt2.executeUpdate();

	    // Get the generated lease ID
	    ResultSet rs = psmt1.getGeneratedKeys();
	    rs.next();
	    int leaseId = rs.getInt(1);
	    lease.setLeaseId(leaseId); // Set the lease ID

	    // Return a message with the lease details (cost and advance)
	    double totalCost = cost(lease.getDuration(), lease.getCarId(), lease.getType());
	    double advance = lease.getAdvance();
	    double remainingAmount = totalCost - advance;

	    return String.format("Lease created successfully! Lease ID: %d, Total Cost: %.2f, Advance Paid: %.2f, Remaining Amount: %.2f",
	                         leaseId, totalCost, advance, remainingAmount);
	}


	@Override
	public String returnCar(int leaseId) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		 String cmd1 = "UPDATE lease SET isReturned = true, endDate = ? WHERE leaseId = ?";
		 String cmd2 = "UPDATE car SET available = true WHERE carId = (SELECT carId FROM lease WHERE leaseId = ?)";
	     con=ConnectionHelper.getConnection();
	     PreparedStatement psmt1=con.prepareStatement(cmd1);
	     PreparedStatement psmt2=con.prepareStatement(cmd2);
	     psmt1.setDate(1, Date.valueOf(LocalDate.now()));
         psmt1.setInt(2, leaseId);
         psmt1.executeUpdate();
         psmt2.setInt(1, leaseId);
         psmt2.executeUpdate();
         return "UPDATED";
	}

	@Override
	public List<Lease> showActiveLease() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		  String cmd = "SELECT * FROM lease WHERE isReturned = false";
		  con=ConnectionHelper.getConnection();
		  PreparedStatement psmt1=con.prepareStatement(cmd);
		  ResultSet rs=psmt1.executeQuery();
		  List<Lease> leases = new ArrayList<>();
		  while(rs.next())
		  {
		  Lease lease = new Lease();
          lease.setLeaseId(rs.getInt("leaseId"));
          lease.setCustomerId(rs.getInt("customerId"));
          lease.setCarId(rs.getInt("carId"));
          lease.setType(rs.getString("leaseType"));
          lease.setDuration(rs.getInt("duration"));//calculate duration automatic
          lease.setStartDate(rs.getDate("startDate").toLocalDate());
          lease.setEndDate(rs.getDate("endDate").toLocalDate());
          lease.setReturned(rs.getBoolean("isReturned"));
          lease.setCost(rs.getDouble("cost"));//calculate cost dynamically
          lease.setAdvance(rs.getDouble("advance"));
          leases.add(lease);
		  }
          return leases;
	}

	@Override
	public List<Lease> showLeaseHistory() throws ClassNotFoundException, SQLException {
		 String cmd = "SELECT * FROM lease";
		 con=ConnectionHelper.getConnection();
		 PreparedStatement psmt1=con.prepareStatement(cmd);
		 ResultSet rs=psmt1.executeQuery();
		 List<Lease> leases = new ArrayList<>();
		 while(rs.next())
		 {
		 Lease lease = new Lease();
         lease.setLeaseId(rs.getInt("leaseId"));
         lease.setCustomerId(rs.getInt("customerId"));
         lease.setCarId(rs.getInt("carId"));
         lease.setType(rs.getString("leaseType"));
         lease.setDuration(rs.getInt("duration"));//calculate duration automatic
         lease.setStartDate(rs.getDate("startDate").toLocalDate());
         lease.setEndDate(rs.getDate("endDate").toLocalDate());
         lease.setReturned(rs.getBoolean("isReturned"));
         lease.setCost(rs.getDouble("cost"));
         lease.setAdvance(rs.getDouble("advance"));
         leases.add(lease);
		 }
		return leases;
	}

	@Override
	public Lease getLeasebyId(int leaseId) throws ClassNotFoundException, SQLException {
	    String cmd = "SELECT * FROM Lease WHERE leaseId = ?";
	    con = ConnectionHelper.getConnection();
	    PreparedStatement pst = con.prepareStatement(cmd);
	    pst.setInt(1, leaseId);

	    ResultSet rs = pst.executeQuery();
	    if (rs.next()) {
	        Lease lease = new Lease();
	        lease.setLeaseId(rs.getInt("leaseId"));
	        lease.setCustomerId(rs.getInt("customerId"));
	        lease.setCarId(rs.getInt("carId"));
	        lease.setType(rs.getString("leaseType"));
	        lease.setDuration(rs.getInt("duration"));
	        lease.setStartDate(rs.getDate("startDate").toLocalDate());
	        lease.setEndDate(rs.getDate("endDate").toLocalDate());
	        lease.setReturned(rs.getBoolean("isReturned"));

	        // ✅ Add these lines
	        lease.setCost(rs.getDouble("cost"));
	        lease.setAdvance(rs.getDouble("advance"));

	        return lease;
	    }
	    return null;
	}

	

	public long duration(LocalDate d1, LocalDate d2,String type) { 
		if(type.equalsIgnoreCase("daily"))
		{
	        long duration = ChronoUnit.DAYS.between(d1, d2);
	        System.out.println("Total days between: " + duration);
			return duration;
		}
		if(type.equalsIgnoreCase("monthly"))
		{
			Period period=Period.between(d1, d2);
			int months=period.getYears()*12+period.getMonths();
			if(period.getDays()>0)
			{
				months++;
			}
			return months;
		}
		return 1;
	}

	@Override
	public List<Lease> getLeasebyCustomer(int CustomerId) throws ClassNotFoundException, SQLException {
		String cmd="select * from Lease where customerId=?";
		List<Lease> lease=new ArrayList<Lease>();
		
		con=ConnectionHelper.getConnection();
		PreparedStatement psmt=con.prepareStatement(cmd);
		psmt.setInt(1, CustomerId);
		ResultSet rs=psmt.executeQuery();
		while(rs.next())
		{
			Lease l=new Lease();
			l.setLeaseId(rs.getInt("leaseId"));
			l.setCarId(rs.getInt("carId"));
			l.setCost(rs.getDouble("cost"));
			l.setAdvance(rs.getDouble("advance"));
			l.setCustomerId(rs.getInt("customerId"));
			l.setDuration(rs.getLong("duration"));
			l.setEndDate(rs.getDate("endDate").toLocalDate());
			l.setStartDate(rs.getDate("startDate").toLocalDate());
			l.setReturned(rs.getBoolean("isReturned"));
			l.setType(rs.getString("leaseType"));
			lease.add(l);
		}
		return lease;
	}


}
