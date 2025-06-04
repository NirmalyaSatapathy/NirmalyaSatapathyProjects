package com.jdbc.lms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Employ {
//	EMP_ID INT PRIMARY KEY ,
//	EMP_NAME CHAR(20) NOT NULL,
//	EMP_MAIL CHAR(30) NOT NULL,
//	EMP_MOB_NO varchar(15) NOT NULL,
//	EMP_DT_JOIN DATE NOT NULL,
//	EMP_DEPT CHAR(25),
//	EMP_MANAGER_ID INT,
//	EMP_AVAIL_LEAVE_BAL INT,
//	FOREIGN KEY (EMP_MANAGER_ID)
//	REFERENCES EMPLOYEE(EMP_ID)
//	);
	
	int empId;
	String empName;
	String empEmail;
	String empMobileNumber;
	String empDepartment;
	int empManagerId;
	int empAvailLeave;
	
}
