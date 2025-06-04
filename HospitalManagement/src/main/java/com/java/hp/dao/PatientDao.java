package com.java.hp.dao;

import java.util.List;

import com.java.hp.model.Patient;

public interface PatientDao {
	// ShowPatientById, ShowPatientByDoctorId
	public String addPatient(Patient patient);
	public Patient showPatientById(String patientId);
	public List<Patient> ShowPatientByDoctorId(String doctorId);
	public List<Patient> showPatients();
}
