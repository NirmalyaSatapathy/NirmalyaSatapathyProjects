package com.java.asset.model;

public class Employ {
//	EmployeeID INT PRIMARY KEY AUTO_INCREMENT,
//    Username VARCHAR(50) UNIQUE NOT NULL,
//    PasswordHash VARCHAR(255) NOT NULL,
//    Department VARCHAR(100),
//    Email VARCHAR(100)
	private String userName;
	private String password;
	private String dept;
	private String email;
	private String type;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Employ(String userName, String password, String dept, String email, String type) {
		super();
		this.userName = userName;
		this.password = password;
		this.dept = dept;
		this.email = email;
		this.type = type;
	}
	public Employ() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
