package com.jdbc.lmsdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jdbc.lms.util.ConnectionHelper;
import com.jdbc.lms.Leave;
import com.jdbc.lms.LeaveStatus;
import com.jdbc.lms.LeaveType;

public class LeaveDaoImpl implements LeaveDao{
	
	@Override
	public String applyALeaveDaoImpl(Leave leave) throws ClassNotFoundException, SQLException {
		
		String cmd="insert into LEAVE_HISTORY (LEAVE_ID,LEAVE_NO_OF_DAYS,EMP_ID,LEAVE_START_DATE,LEAVE_END_DATE,LEAVE_REASON) values(?,?,?,?,?,?)";
		
		Connection connection=ConnectionHelper.getConnection();
		PreparedStatement pst=connection.prepareStatement(cmd);
		pst.setInt(1,generateLeaveId());
		pst.setInt(2, leave.getNoofdays());
		pst.setInt(3,leave.getEmpid());
		pst.setDate(4, leave.getStartdate());
		pst.setDate(5, leave.getEnddate());
		pst.setString(6, leave.getLeavereason());
	
		pst.executeUpdate();
		return " leave applied successfully";
	}

	@Override
    public List<Leave> readAllLeaveTakenDaoImpl(int employId) throws ClassNotFoundException, SQLException {
        String cmd = "select * from leave_history where EMP_ID=?";
        Connection connection = ConnectionHelper.getConnection();
        PreparedStatement pst = connection.prepareStatement(cmd);
        pst.setInt(1, employId);
        ResultSet res = pst.executeQuery();
        List<Leave> leaves = new ArrayList<>();
        while (res.next()) {
            Leave leave = new Leave();
            leave.setLeaveid(res.getInt("LEAVE_ID"));
            leave.setNoofdays(res.getInt("LEAVE_NO_OF_DAYS"));
            leave.setEmpid(res.getInt("EMP_ID"));
            leave.setStartdate(res.getDate("LEAVE_START_DATE"));
            leave.setEnddate(res.getDate("LEAVE_END_DATE"));
            leave.setLeavereason(res.getString("LEAVE_REASON"));
            leave.setLeavetype(com.jdbc.lms.LeaveType.valueOf(res.getString("LEAVE_TYPE")));
            leave.setLeavestatus(com.jdbc.lms.LeaveStatus.valueOf(res.getString("LEAVE_STATUS")));
            leaves.add(leave);
        }
        return leaves;
    }

	

	@Override
	public List<Leave> showPendingLeaveDaoImpl(int managerId) throws ClassNotFoundException, SQLException {
		String cmd="select * from LEAVE_HISTORY a inner join EMPLOYEE e on e.emp_id=a.emp_id "
				+ "where e.emp_manager_id=? and a.LEAVE_STATUS='PENDING'";
		Connection connection= ConnectionHelper.getConnection();
		PreparedStatement pst=connection.prepareStatement(cmd);
		pst.setInt(1, managerId);
		ResultSet res=pst.executeQuery();
		List<Leave> requestedLeaves = new ArrayList<>();
        while (res.next()) {
            Leave leave = new Leave();
            leave.setLeaveid(res.getInt("leave_id"));
            leave.setNoofdays(res.getInt("leave_no_of_days"));
            leave.setEmpid(res.getInt("emp_id"));
            leave.setStartdate(res.getDate("leave_start_date"));
            leave.setEnddate(res.getDate("leave_end_date"));
            leave.setLeavetype(LeaveType.valueOf(res.getString("leave_type")));
            leave.setLeavestatus(LeaveStatus.valueOf(res.getString("leave_status")));
            leave.setLeavereason(res.getString("LEAVE_REASON"));
            requestedLeaves.add(leave);
        }
        return requestedLeaves;
	}
	

	@Override
	public String changeLeaveStatusDaoImple(int managerId, int leaveid, LeaveStatus status, String comments)
			throws ClassNotFoundException, SQLException {
		String cmd = "UPDATE leave_history  SET LEAVE_STATUS=?,LEAVE_MNGR_COMMENTS=? where leave_id=?";
        Connection connection = ConnectionHelper.getConnection();
        PreparedStatement pst = connection.prepareStatement(cmd);
        pst.setInt(3, leaveid);
        String stat=status.name();
        pst.setString(1,stat);
        pst.setString(2,comments);
		pst.executeUpdate();
	    return "leave history updated";
	}
	public static int generateLeaveId() throws ClassNotFoundException, SQLException {
		String cmd="select case when max(leave_id) is  null then  1 else max(leave_id)+1 end leaveId  from LEAVE_HISTORY";
		
		Connection connection=ConnectionHelper.getConnection();
		PreparedStatement pst=connection.prepareStatement(cmd);
		ResultSet res=pst.executeQuery();
		res.next();
		return res.getInt("leaveId");
	}

}
