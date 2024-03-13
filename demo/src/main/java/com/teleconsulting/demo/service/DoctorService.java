package com.teleconsulting.demo.service;
import com.teleconsulting.demo.model.Doctor;

import javax.print.Doc;
import java.util.List;

public interface DoctorService{
    List<Doctor> getDoctors();
    Doctor getSingleDoctor(Long id);
    void deleteDoctor(Long id);
    Doctor updateDoctor(Doctor doctor);
    Doctor saveDoctor(Doctor doctor);
}
