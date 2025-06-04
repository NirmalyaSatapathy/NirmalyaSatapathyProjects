package com.jdbc.lmsdao;
import com.jdbc.lms.Leave;
import com.jdbc.lms.LeaveStatus;

import java.sql.SQLException;
import java.util.List;



public interface LeaveDao {
	
//	user methods
	public String applyALeaveDaoImpl(Leave leave) throws ClassNotFoundException, SQLException;
	
	public List<Leave> readAllLeaveTakenDaoImpl(int employId) throws ClassNotFoundException, SQLException;
	
	
//	all manager methods
	public List<Leave>  showPendingLeaveDaoImpl(int empAsManagerId) throws ClassNotFoundException, SQLException;
	
	public String changeLeaveStatusDaoImple(int empAsManagerId, int leaveId, LeaveStatus status, String comments)
			throws ClassNotFoundException, SQLException;	
	
}
