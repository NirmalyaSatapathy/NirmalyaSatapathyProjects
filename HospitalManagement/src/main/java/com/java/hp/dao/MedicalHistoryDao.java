package com.java.hp.dao;

import java.util.List;

import com.java.hp.model.MedicalHistory;

public interface MedicalHistoryDao {
	//ShowPatientMedicalHistory, ShowPatientTests, AddMedicalHistory
	public List<MedicalHistory> ShowPatientMedicalHistory(String patientId);
	public List<String> ShowPatientTests(String patientId);
	public String addMedicalHistory(MedicalHistory medicalHistory);
}
