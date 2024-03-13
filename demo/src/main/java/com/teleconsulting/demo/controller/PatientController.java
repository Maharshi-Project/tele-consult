package com.teleconsulting.demo.controller;

import com.teleconsulting.demo.model.Doctor;
import com.teleconsulting.demo.model.Patient;
import com.teleconsulting.demo.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping("/patients")
    public List<Patient> getPatients(){
        return patientService.getPatients();
    }
    @GetMapping("/{id}")
    public Patient getPatient(@PathVariable Long id){
        return patientService.getSinglePatient(id);
    }
    @PostMapping("/add")
    public String add(@RequestBody Patient patient)
    {
        patientService.savePatient(patient);
        return "New Patient Added";
    }
    @PutMapping("/patients/{id}")
    public Patient updatePatient(@PathVariable Long id, @RequestBody Patient patient){
        patient.setId(id);
        return patientService.updatePatient(patient);
    }
    @DeleteMapping("/delete/{id}")
    public void deletePatient(@PathVariable Long id){
        patientService.deletePatient(id);
    }
}
