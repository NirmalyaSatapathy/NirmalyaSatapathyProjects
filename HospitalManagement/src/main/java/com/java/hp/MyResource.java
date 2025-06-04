package com.java.hp;

import java.util.ArrayList;
import java.util.List;

import com.java.hp.dao.DoctorDaoImpl;
import com.java.hp.model.Doctor;
import com.java.hp.model.Patient;
import com.java.hp.model.Spetialization;
import com.java.hp.model.Status;

public class MyResource {
    public static void main(String[] args) {
        DoctorDaoImpl dao = new DoctorDaoImpl();
 
        
 
        // Create a new Doctor object
        Doctor newDoctor = new Doctor(); // Set the generated doctor ID
        newDoctor.setDoctorId("D009");
        newDoctor.setDoctorName("Dr. John Doe");
        newDoctor.setLocation("City Hospital");
        newDoctor.setMobileNo("123-456-7890");
 
        newDoctor.setSpetialization(Spetialization.GENERAL);
 
        // Set the Status (assuming you have an instance of Status)
        Status status = Status.ACTIVE;  // Or whatever the status is
        newDoctor.setIsStatus(status);
 
        // Add some patients (if needed)
        List<Patient> patients = new ArrayList<>();  // You can add actual Patient objects here
        newDoctor.setPatients(patients);
 
        // Now add the Doctor to the database
        String result = dao.addDoctor(newDoctor);
        System.out.println(result);
    }
}