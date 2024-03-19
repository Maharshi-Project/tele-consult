package com.teleconsulting.demo.controller;

import com.teleconsulting.demo.model.Doctor;
import com.teleconsulting.demo.service.DoctorService;
import com.teleconsulting.demo.service.RoomJoinRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/all")
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @PostMapping("/join-room")
    public ResponseEntity<?> joinRoom(@RequestBody RoomJoinRequest request) {
        Doctor doctor = doctorService.findByPhoneNumber(request.getDoctorPhoneNumber());
        if (doctor != null) {
            doctor.setIncomingCall(request.getPatientPhoneNumber());
            doctorService.saveDoctor(doctor);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor not found");
        }
    }

    @GetMapping("/{doctorId}/incoming-call")
    public ResponseEntity<?> getIncomingCall(@PathVariable Long doctorId) {
        Doctor doctor = doctorService.findById(doctorId);
        if (doctor != null) {
            return ResponseEntity.ok(doctor.getIncomingCall());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor not found");
        }
    }
    @GetMapping("/{doctorId}")
    public ResponseEntity<String> getDoctorById(@PathVariable Long doctorId) {
        Doctor doctor = doctorService.findById(doctorId);
        if (doctor != null) {
            return ResponseEntity.ok(doctor.getPhoneNumber());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{doctorId}/reject-call")
    public ResponseEntity<?> rejectCall(@PathVariable("doctorId") Long doctorId) {
        Doctor doctor = doctorService.findById(doctorId);
        if (doctor != null) {
            doctor.setIncomingCall(null);
            doctorService.saveDoctor(doctor);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor not found");
        }
    }


}
