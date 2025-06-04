package com.java.hp.dao;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.java.hp.model.MedicalHistory;
import com.java.hp.model.Patient;

public class MedicalHistoryDaoImpl  implements MedicalHistoryDao{
	private static SessionFactory sessionFactory;

    static {
        // Initialize the SessionFactory only once
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
    }

	Session session;
	@Override
	public List<MedicalHistory> ShowPatientMedicalHistory(String patientId) {
		session=sessionFactory.openSession();
	    Transaction tx = session.beginTransaction();
	    Criteria criteria = session.createCriteria(MedicalHistory.class);
	    criteria.add(Restrictions.eq("patient.patientId", patientId));
	    List<MedicalHistory> list = criteria.list();
	    tx.commit();
	    session.close();
	    return list;
	}

	@Override
	public List<String> ShowPatientTests(String patientId) {
		session=sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
	    Criteria criteria = session.createCriteria(MedicalHistory.class);
	    criteria.add(Restrictions.eq("patient.patientId", patientId));
	    criteria.setProjection(Projections.property("tests"));
	    List<String> tests = criteria.list();
	    tx.commit();
	    session.close();
	    return tests;
	}

	@Override
	public String addMedicalHistory(MedicalHistory medicalHistory) {
	    boolean isValid = true;
	    FacesContext context = FacesContext.getCurrentInstance();
	    if (medicalHistory.getMedicies() == null || 
	        medicalHistory.getMedicies().trim().length() < 3 || 
	        medicalHistory.getMedicies().trim().length() > 30) {
	        context.addMessage("medicies", new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                "Invalid Medicines", "Medicines must be between 3 and 30 characters."));
	        context.validationFailed();
	        isValid = false;
	    }
	    if (medicalHistory.getTests() == null || 
	        medicalHistory.getTests().trim().length() < 3 || 
	        medicalHistory.getTests().trim().length() > 30) {
	        context.addMessage("tests", new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                "Invalid Tests", "Tests must be between 3 and 30 characters."));
	        context.validationFailed();
	        isValid = false;
	    }
	    if (medicalHistory.getPatient() == null || 
	        medicalHistory.getPatient().getPatientId() == null || 
	        medicalHistory.getPatient().getPatientId().trim().isEmpty()) {
	        context.addMessage("patientId", new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                "Invalid Patient", "Patient ID is required."));
	        context.validationFailed();
	        isValid = false;
	    }

	    if (!isValid) {
	        return null; 
	    }
	    session = sessionFactory.openSession();
	    Transaction tx = session.beginTransaction();
	    Patient patient = (Patient) session.get(Patient.class, medicalHistory.getPatient().getPatientId());
	    if (patient == null) {
	        context.addMessage("patientId", new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                "Patient Not Found", "No patient exists with the given ID."));
	        context.validationFailed();
	        session.close();
	        return null;
	    }
	    medicalHistory.setPatient(patient);
	    medicalHistory.setMedId(generateNewMedId());
	    session.save(medicalHistory);
	    tx.commit();
	    session.close();
	    return "MedicalHistoryHome?faces-redirect=true";
	}

	public String generateNewMedId() {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("SELECT mh.medId FROM MedicalHistory mh ORDER BY mh.medId DESC");
		query.setMaxResults(1);
		List<String> medIds = query.list();
		String newMedId = "MHD001";
		if (medIds != null && !medIds.isEmpty()) {
			String latestMedId = medIds.get(0);
			if (latestMedId.startsWith("MHD")) {
				String numericPart = latestMedId.substring(3);
 
				try {
					int currentNum = Integer.parseInt(numericPart);
					currentNum++;
					if (currentNum <= 10000) {
						newMedId = "MHD" + String.format("%03d", currentNum);
					} else {
						newMedId = "MHD1000";
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}
		}
 
		// Return the new MedId
		return newMedId;
	}
 
}
