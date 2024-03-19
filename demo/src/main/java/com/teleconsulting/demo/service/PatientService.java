// PatientService.java
package com.teleconsulting.demo.service;

import com.teleconsulting.demo.model.Doctor;
import com.teleconsulting.demo.model.Patient;

public interface PatientService {
    Patient createPatient(Patient patient);
    Patient getPatientByPhoneNumber(String phoneNumber);

    Patient findById(Long id);
}
