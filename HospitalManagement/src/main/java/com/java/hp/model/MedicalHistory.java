package com.java.hp.model;

public class MedicalHistory {
//	MedId varchar(30),
//    PatientId varchar(30) references Patient(patientId), 
//    Medicines varchar(30),
//    Tests varchar(30)
	String medId;
	String medicies;
	String tests;
	Patient patient;
	public String getMedId() {
		return medId;
	}
	public MedicalHistory() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setMedId(String medId) {
		this.medId = medId;
	}
	public MedicalHistory(String medId, String medicies, String tests, Patient patient) {
		super();
		this.medId = medId;
		this.medicies = medicies;
		this.tests = tests;
		this.patient = patient;
	}
	public String getMedicies() {
		return medicies;
	}
	public void setMedicies(String medicies) {
		this.medicies = medicies;
	}
	public String getTests() {
		return tests;
	}
	public void setTests(String tests) {
		this.tests = tests;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
}
