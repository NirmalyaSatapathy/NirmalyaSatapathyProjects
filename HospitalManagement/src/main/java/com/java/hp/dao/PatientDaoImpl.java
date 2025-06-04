package com.java.hp.dao;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import com.java.hp.model.Doctor;
import com.java.hp.model.Patient;

public class PatientDaoImpl implements PatientDao{
	
	Session session;
	 private static SessionFactory sessionFactory;

	    static {
	        // Initialize the SessionFactory only once
	        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
	    }
	@Override
	public Patient showPatientById(String patientId) {
		session=sessionFactory.openSession();
		Query query=session.getNamedQuery("showPatientById");
		query.setParameter(0, patientId);
		Patient patient=(Patient) query.uniqueResult();
		return patient;
	}
	@Override
	public List<Patient> ShowPatientByDoctorId(String doctorId) {
		
		session=sessionFactory.openSession();
		Query query=session.getNamedQuery("showPatientByDoctorId");
		query.setParameter(0, doctorId);
		List<Patient> patients=query.list();
		return patients;
	}
	@Override
	public String addPatient(Patient patient) {
	    boolean isValid = true;
	    FacesContext context = FacesContext.getCurrentInstance();
	    if (patient.getPatientName() == null || patient.getPatientName().length() < 3) {
	        context.addMessage("patientName", new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                "Invalid Name", "Patient name must be at least 3 characters long."));
	        context.validationFailed();
	        isValid = false;
	    }
	    if (patient.getDateOfBirth() == null) {
	        context.addMessage("dateOfBirth", new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                "Invalid DOB", "Date of Birth is required."));
	        context.validationFailed();
	        isValid = false;
	    }
	    if (patient.getDoctor() == null || patient.getDoctor().getDoctorId() == null || patient.getDoctor().getDoctorId().trim().isEmpty()) {
	        context.addMessage("doctorId", new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                "Invalid Doctor", "Doctor ID is required."));
	        context.validationFailed();
	        isValid = false;
	    }
	    if (!isValid) {
	        return null; 
	    }
	    Session session = sessionFactory.openSession();
	    Transaction tx = session.beginTransaction();

	    Doctor doctor = (Doctor) session.get(Doctor.class, patient.getDoctor().getDoctorId());
	    if (doctor == null) {
	        context.addMessage("doctorId", new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                "Doctor Not Found", "No doctor found with the given ID."));
	        context.validationFailed();
	        session.close();
	        return null;
	    }
	    patient.setDoctor(doctor); 
	    patient.setPatientId(generateNewPatientId());
	    session.save(patient);
	    tx.commit();
	    session.close();
	    return "PatientHome.jsp?faces-redirect=true";
	}
	public String generateNewPatientId() {
	 session =sessionFactory.openSession();
		Query query = session.createQuery("SELECT p.patientId FROM Patient p ORDER BY p.patientId DESC");
		query.setMaxResults(1);
		List<String> patientIds = query.list();
		String newPatientId = "PTH001";
 
		if (patientIds != null && !patientIds.isEmpty()) {
			String latestPatientId = patientIds.get(0);
 
			if (latestPatientId.startsWith("PTH")) {
				String numericPart = latestPatientId.substring(3);
 
				try {
					int currentNum = Integer.parseInt(numericPart);
					currentNum++;
 
					if (currentNum <= 1000) {
						newPatientId = "PTH" + String.format("%03d", currentNum);
					} else {
						newPatientId = "PTH1000";
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}
		}
		return newPatientId;
	}
	@Override
	public List<Patient> showPatients() {
		session=sessionFactory.openSession();
		Criteria criteria=session.createCriteria(Patient.class);
		List<Patient> list=criteria.list();
		return list;
	}
}
