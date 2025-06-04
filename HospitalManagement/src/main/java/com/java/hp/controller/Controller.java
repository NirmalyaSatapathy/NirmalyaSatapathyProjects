package com.java.hp.controller;

import java.util.List;

import com.java.hp.dao.DoctorDao;
import com.java.hp.dao.MedicalHistoryDao;
import com.java.hp.dao.PatientDao;
import com.java.hp.model.Doctor;
import com.java.hp.model.MedicalHistory;
import com.java.hp.model.Patient;
import com.java.hp.model.Spetialization;

public class Controller {
    private DoctorDao doctorDao;
    private MedicalHistoryDao medicalHistoryDao;
    private PatientDao patientDao;
    public DoctorDao getDoctorDao() {
        return doctorDao;
    }
    public void setDoctorDao(DoctorDao doctorDao) {
        this.doctorDao = doctorDao;
    }
    public MedicalHistoryDao getMedicalHistoryDao() {
        return medicalHistoryDao;
    }
    public void setMedicalHistoryDao(MedicalHistoryDao medicalHistoryDao) {
        this.medicalHistoryDao = medicalHistoryDao;
    }
    public PatientDao getPatientDao() {
        return patientDao;
    }
    public void setPatientDao(PatientDao patientDao) {
        this.patientDao = patientDao;
    }
    public String addDoctor(Doctor doctor) {
        System.out.println("Adding Doctor...");
        return doctorDao.addDoctor(doctor);
    }
    public List<Doctor> showDoctorByCategory(Spetialization category) {
        return doctorDao.showDoctorByCategory(category);
}
    public Spetialization[] getSpecializations() {
        return Spetialization.values(); 
    }
    public Doctor searchByDoctorId(String doctorId)
    {
    	return doctorDao.searchByDoctorId(doctorId);
    }
    public String searchByDoctorId1()
    {
    	return null;
    }
    public String addPatient(Patient patient)
    {
    	return patientDao.addPatient(patient);
    }
    public Patient showPatientById(String patientId)
    {
    	return patientDao.showPatientById(patientId);
    }
    public List<Patient> ShowPatientByDoctorId(String doctorId)
    {
    	return patientDao.ShowPatientByDoctorId(doctorId);
    }
    public List<MedicalHistory> showPatientMedicalHistory(String patientId)
    {
    	return medicalHistoryDao.ShowPatientMedicalHistory(patientId);
    }
    public List<String> ShowPatientTests(String patientId)
    {
    	return medicalHistoryDao.ShowPatientTests(patientId);
    }
    public String addMedicalHistory(MedicalHistory medicalHistory)
    {
    	return medicalHistoryDao.addMedicalHistory(medicalHistory);
    }
    public List<Patient> showPatients()
    {
    	return patientDao.showPatients();
    }
}
