package com.teleconsulting.demo.controller;

import com.teleconsulting.demo.model.Doctor;
import com.teleconsulting.demo.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    // "localhost:8081/doctor/"
    @GetMapping("/doctors")
    public List<Doctor> getDoctors(){
        return doctorService.getDoctors();
    }
    @GetMapping("/{id}")
    public Doctor getDoctor(@PathVariable Long id){
        return doctorService.getSingleDoctor(id);
    }
    @PostMapping("/add")
    public String add(@RequestBody Doctor doctor)
    {
        doctorService.saveDoctor(doctor);
        return "New Doctor Added";
    }
    @PutMapping("/update/{id}")
    public Doctor updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor){
        doctor.setId(id);
        return doctorService.updateDoctor(doctor);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteDoctor(@PathVariable Long id){
        doctorService.deleteDoctor(id);
    }
}
