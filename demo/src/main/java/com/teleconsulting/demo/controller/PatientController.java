// PatientController.java
package com.teleconsulting.demo.controller;

import com.teleconsulting.demo.model.Patient;
import com.teleconsulting.demo.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
public class PatientController {
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        Patient createdPatient = patientService.createPatient(patient);
        return new ResponseEntity<>(createdPatient, HttpStatus.CREATED);
    }

    @GetMapping(params = "phoneNumber")
    public ResponseEntity<Patient> getPatientByPhoneNumber(@RequestParam String phoneNumber) {
        Patient patient = patientService.getPatientByPhoneNumber(phoneNumber);
        return ResponseEntity.ok(patient);
    }
    @GetMapping("/id")
    public ResponseEntity<Patient> getPatientById(@RequestParam String phoneNumber) {
        Patient patient = patientService.getPatientByPhoneNumber(phoneNumber);
        return ResponseEntity.ok(patient);
    }

}
