package com.java.hp.model;

import java.util.List;

public class Doctor {
//	doctorId varchar(30),
//	   doctorName varchar(30),
//	   specialization ENUM('HEART','SKIN','GENERAL','KIDNEY'),
//	   location varchar(30),
//	   mobileNo varchar(20),
//	   isStatus ENUM('ACTIVE','INACTIVE')
	String doctorId;
	String doctorName;
	public List<Patient> getPatients() {
		return patients;
	}
	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}
	Spetialization spetialization;
	String location;
	String mobileNo;
	Status isStatus;
	List<Patient> patients;
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public Spetialization getSpetialization() {
		return spetialization;
	}
	public void setSpetialization(Spetialization spetialization) {
		this.spetialization = spetialization;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public Status getIsStatus() {
		return isStatus;
	}
	public void setIsStatus(Status isStatus) {
		this.isStatus = isStatus;
	}
	
	public Doctor(String doctorId, String doctorName, Spetialization spetialization, String location, String mobileNo,
			Status isStatus, List<Patient> patients) {
		super();
		this.doctorId = doctorId;
		this.doctorName = doctorName;
		this.spetialization = spetialization;
		this.location = location;
		this.mobileNo = mobileNo;
		this.isStatus = isStatus;
		this.patients = patients;
	}
	public Doctor() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Doctor [doctorId=" + doctorId + ", doctorName=" + doctorName + ", spetialization=" + spetialization
				+ ", location=" + location + ", mobileNo=" + mobileNo + ", isStatus=" + isStatus + "]";
	}
	
}
