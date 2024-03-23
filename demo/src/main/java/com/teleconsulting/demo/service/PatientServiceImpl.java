package com.teleconsulting.demo.service;

import com.teleconsulting.demo.handler.NotFoundException;
import com.teleconsulting.demo.model.Patient;
import com.teleconsulting.demo.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService{
    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient getSinglePatient(Long id) {
        Optional<Patient> pat =  patientRepository.findById(id);
        if(pat.isPresent()){
            return pat.get();
        }
        throw new NotFoundException("Doctor is not found for the id :"+id);
    }

    @Override
    public void deletePatient(Long id) {
        Optional<Patient> existingPatient = patientRepository.findById(id);
        if (existingPatient.isEmpty()) {
            throw new NotFoundException("Patient doesn't exists !!\n");
        }
        patientRepository.deleteById(id);
    }

    @Override
    public Patient updatePatient(Patient patient) {
        Optional<Patient> existingPatient = patientRepository.findById(patient.getId());
        if (existingPatient.isEmpty()) {
            throw new NotFoundException("Patient doesn't exists !!\n");
        }
        return patientRepository.save(patient);
    }

    @Override
    public Patient savePatient(Patient patient) {
        Optional<Patient> existingPatient = patientRepository.findByPhoneNumber(patient.getPhoneNumber());
        if (existingPatient.isPresent()) {
            throw new NotFoundException("Phone Number already exists: " + patient.getPhoneNumber());
        }
        return patientRepository.save(patient);
    }
}
