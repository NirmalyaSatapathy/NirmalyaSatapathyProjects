package com.jdbc.lms;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Leave {
//	LEAVE_ID INT PRIMARY KEY AUTO_INCREMENT,
//	LEAVE_NO_OF_DAYS INT,
//	LEAVE_MNGR_COMMENTS CHAR(200),
//	EMP_ID INT,
//	LEAVE_START_DATE DATE NOT NULL, 
//	LEAVE_END_DATE DATE NOT NULL,
//	LEAVE_TYPE ENUM ('EL' ) DEFAULT 'EL',
//	LEAVE_STATUS ENUM ('PENDING','APPROVED','DENIED') DEFAULT 'PENDING',
//	LEAVE_REASON  CHAR(50) NOT NULL,
	
	int leaveid ;
	int noofdays;
	String leavemanagercomments;
	int empid;
	Date startdate;
	Date enddate;
	LeaveType leavetype;
	LeaveStatus leavestatus=LeaveStatus.PENDING;
	String leavereason;
	
}
