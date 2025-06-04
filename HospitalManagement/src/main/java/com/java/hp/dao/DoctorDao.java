package com.java.hp.dao;



import java.util.List;

import com.java.hp.model.Doctor;
import com.java.hp.model.Spetialization;

public interface DoctorDao {
	//AddDoctor,ShowDoctor,ShowByCategory,SearchByDoctorID 
	public String addDoctor(Doctor doctor);
	public List<Doctor> showDoctor();
	public List<Doctor> showDoctorByCategory(Spetialization category);
	public Doctor searchByDoctorId(String doctorId);
}
