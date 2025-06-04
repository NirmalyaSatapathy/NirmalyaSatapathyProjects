package com.java.hp.dao;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import com.java.hp.model.Doctor;
import com.java.hp.model.Spetialization;

public class DoctorDaoImpl implements DoctorDao {

    private static SessionFactory sessionFactory;
    static {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
    }

    @Override
    public String addDoctor(Doctor doctor) {
        boolean isValid = true;
        FacesContext context = FacesContext.getCurrentInstance();
        if (doctor.getDoctorName() == null || doctor.getDoctorName().length() < 5 || doctor.getDoctorName().length() > 30) {
            context.addMessage("doctorName", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Doctor Name Invalid", "Doctor Name must be between 5 and 30 characters."));
            context.validationFailed();
            isValid = false;
        }
        if (doctor.getSpetialization() == null) {
            context.addMessage("spetialization", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Specialization Missing", "Please select a specialization."));
            context.validationFailed();
            isValid = false;
        }
        if (doctor.getLocation() == null || doctor.getLocation().trim().isEmpty()) {
            context.addMessage("location", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Location Invalid", "Location cannot be empty."));
            context.validationFailed();
            isValid = false;
        }
        if (doctor.getMobileNo() == null || !doctor.getMobileNo().matches("\\d{10}")) {
            context.addMessage("mobileNo", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Mobile Number Invalid", "Mobile number must be exactly 10 digits."));
            context.validationFailed();
            isValid = false;
        }
        if (doctor.getIsStatus() == null) {
            context.addMessage("isStatus", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Status Missing", "Doctor status (ACTIVE/INACTIVE) is required."));
            context.validationFailed();
            isValid = false;
        }
        if (!isValid) {
            return null;  
        }
        Session session = sessionFactory.openSession();
        Transaction trans = session.beginTransaction();
        doctor.setDoctorId(generateNewDoctorId());
        session.save(doctor);
        trans.commit();
        session.close();
        return "Home.jsp?faces-redirect=true";
    }
    @Override
    public List<Doctor> showDoctor() {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("showAllDoctors");
        List<Doctor> doctorList = query.list();
        session.close();
        return doctorList;
    }
    @Override
    public List<Doctor> showDoctorByCategory(Spetialization category) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("findDoctorsBySpecialization");
        query.setParameter(0, category);
        List<Doctor> doctors = query.list();
        session.close();
        return doctors;
    }

    @Override
    public Doctor searchByDoctorId(String doctorId) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("findDoctorById");
        query.setParameter(0, doctorId);
        Doctor doctor = (Doctor) query.uniqueResult();
        session.close();
        return doctor;
    }

    public String generateNewDoctorId() {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("SELECT d.doctorId FROM Doctor d ORDER BY d.doctorId DESC");
		query.setMaxResults(1);
		List<String> doctorIds = query.list();
		String newDoctorId = "HSkDS001";
 
		if (doctorIds != null && !doctorIds.isEmpty()) {
			String latestDoctorId = doctorIds.get(0);
 
			if (latestDoctorId.startsWith("HSkDS")) {
				String numericPart = latestDoctorId.substring(5);
 
				try {
					int currentNum = Integer.parseInt(numericPart);
					currentNum++;
 
					if (currentNum <= 1000) {
						newDoctorId = "HSkDS" + String.format("%03d", currentNum);
					} else {
						newDoctorId = "HSkDS1000";
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}
		}
 
		return newDoctorId;
	}
}
