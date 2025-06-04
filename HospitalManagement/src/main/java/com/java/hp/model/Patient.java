package com.java.hp.model;

import java.util.Date;
import java.util.List;

public class Patient {
//	PatientId varchar(30),
//    PatientName varchar(30),
//    DoctorId varchar(30) REFERENCES Doctor(DoctorId),
//    DateOfBirth Date
	String patientId;
	public List<MedicalHistory> getMedicalHistory() {
		return medicalHistory;
	}
	public void setMedicalHistory(List<MedicalHistory> medicalHistory) {
		this.medicalHistory = medicalHistory;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	String patientName;
	Doctor doctor;
	Date dateOfBirth;
	List<MedicalHistory> medicalHistory;
	public String getPatientId() {
		return patientId;
	}
	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", patientName=" + patientName + ", Doctor=" + doctor
				+ ", DateOfBirth=" + dateOfBirth + "]";
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
	public Patient(String patientId, String patientName, com.java.hp.model.Doctor doctor, Date dateOfBirth,
			List<MedicalHistory> medicalHistory) {
		super();
		this.patientId = patientId;
		this.patientName = patientName;
		this.doctor = doctor;
		this.dateOfBirth = dateOfBirth;
		this.medicalHistory = medicalHistory;
	}
	public Patient() {
		this.doctor = new Doctor(); 
		
		// TODO Auto-generated constructor stub
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Doctor getDoctor() {
		return this.doctor;
	}
	

}
