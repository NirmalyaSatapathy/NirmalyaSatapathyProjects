package com.jdbc.lms.bal;
import com.jdbc.lmsdao.LeaveDao;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import com.jdbc.lms.Leave;
import com.jdbc.lms.LeaveStatus;
import com.jdbc.lmsdao.EmpdaoImp;
import com.jdbc.lmsdao.EmployDao;
import com.jdbc.lmsdao.LeaveDao ;
import com.jdbc.lmsdao.LeaveDaoImpl;
public class LeaveBal {
	static EmployDao empDaoImp;
	static LeaveDao leaveDaoImpl;
	static StringBuilder sb;
	
	static {
		empDaoImp=new EmpdaoImp();
		leaveDaoImpl=new LeaveDaoImpl();
		sb=new StringBuilder();
	}
	public String changeLeaveStatusDaoBal(int managerId, int leaveId,LeaveStatus status,String comments) throws ClassNotFoundException, SQLException
	{
		return leaveDaoImpl.changeLeaveStatusDaoImple(managerId, leaveId, status, comments);
	}
	
	public String applyALeaveBal(Leave leave) throws ClassNotFoundException, SQLException {
			
		if(validateLeave(leave)) {
			empDaoImp.setEmployLeaveDayDaoImpl(leave.getEmpid(),empDaoImp.getEmployLeaveById(leave.getEmpid())-leave.getNoofdays());
			return  leaveDaoImpl.applyALeaveDaoImpl(leave);
		}
		return sb.toString();
	}
	
	public List<Leave> allLeaveTakenByEmpBal(int empId) throws ClassNotFoundException, SQLException{
		return leaveDaoImpl.readAllLeaveTakenDaoImpl(empId);
	}
	
	public List<Leave> showPendingLeaves(int managerId) throws ClassNotFoundException, SQLException{
		return leaveDaoImpl.showPendingLeaveDaoImpl(managerId);
	}
	
	
	public static boolean validateLeave(Leave leave) throws ClassNotFoundException, SQLException {
		
		
		LocalDate fst=leave.getStartdate().toLocalDate();
		LocalDate lst=leave.getEnddate().toLocalDate();
		leave.setNoofdays((int)ChronoUnit.DAYS.between( fst,lst));
		boolean check =true;
		
		sb.setLength(0);
		if(lst.isBefore(LocalDate.now()) || fst.isBefore(LocalDate.now())) {
			sb.append("start date should not be past ");
			check=false;
		}
		if(lst.isBefore(LocalDate.now()) ) {
			sb.append("end date should not be past");
			check=false;
		}
		if(lst.isBefore(fst)) {
			sb.append("start date should not be greater than  end date ");
			check=false;
		}
		if(leave.getNoofdays()>=empDaoImp.getEmployLeaveById(leave.getEmpid())) {
			sb.append(" insufficient no of leave day");
			check=false;
		}
		
		
		return check;
	}
	
}
