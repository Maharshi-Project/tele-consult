package com.teleconsulting.demo.service;

import com.teleconsulting.demo.model.Doctor;
import com.teleconsulting.demo.model.Patient;

import java.util.List;

public interface PatientService {

    List<Patient> getPatients();
    Patient getSinglePatient(Long id);
    void deletePatient(Long id);
    Patient updatePatient(Patient patient);
    public Patient savePatient(Patient patient);
}
