package com.teleconsulting.demo.controller;

import com.teleconsulting.demo.model.Doctor;
import com.teleconsulting.demo.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @PostMapping("/add")
    public String add(@RequestBody Doctor doctor)
    {
        doctorService.saveDoctor(doctor);
        return "New Doctor Added";
    }
}
