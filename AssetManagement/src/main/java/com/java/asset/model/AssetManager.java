package com.java.asset.model;

import java.util.Date;

public class AssetManager {
//	 AssignmentID INT PRIMARY KEY AUTO_INCREMENT,
//	    AssetID INT NOT NULL,
//	    EmployeeID INT NOT NULL,
//	    AssignedQuantity INT NOT NULL,
//	    AssignedDate DATETIME DEFAULT CURRENT_TIMESTAMP,
//	    ReturnedQuantity INT DEFAULT 0,
//	    ReturnDate DATETIME,
	private String assetName;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	private String EmployName;
	private Date assignedDate;
	private Date returnDate;
	private String status;
	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	public String getEmployName() {
		return EmployName;
	}
	public void setEmployName(String employName) {
		EmployName = employName;
	}
	public Date getAssignedDate() {
		return assignedDate;
	}
	public void setAssignedDate(Date assignedDate) {
		this.assignedDate = assignedDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public AssetManager(String assetName, String employName, Date assignedDate,
			Date returnDate) {
		super();
		this.assetName = assetName;
		EmployName = employName;
		this.assignedDate = assignedDate;
		this.returnDate = returnDate;
	}
	public AssetManager() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
