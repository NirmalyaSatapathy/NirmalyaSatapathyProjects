package com.java.lib.model;

public class LibAdmins {
	private String adminName;
	private String passWord;
	public LibAdmins(String userName, String passWord) {
		super();
		this.adminName = userName;
		this.passWord = passWord;
	}
	public LibAdmins() {
		
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String userName) {
		this.adminName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
}
