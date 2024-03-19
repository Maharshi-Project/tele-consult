package com.teleconsulting.demo.controller;

import com.teleconsulting.demo.model.Appointment;
import com.teleconsulting.demo.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

     @PostMapping("/add")
    public String add(@RequestBody Appointment appointment)
     {
         appointmentService.saveAppointment(appointment);
         return "New Appointment added";
     }

     @GetMapping("/today")
     public List<Appointment> getAppointmentsForToday() {
         return appointmentService.getAppointmentsForToday();
     }
}
