package com.teleconsulting.demo.controller;

import com.teleconsulting.demo.model.CallHistory;
import com.teleconsulting.demo.model.Doctor;
import com.teleconsulting.demo.model.Patient;
import com.teleconsulting.demo.repository.DoctorRepository;
import com.teleconsulting.demo.repository.PatientRepository;
import com.teleconsulting.demo.service.CallHistoryService;
import com.teleconsulting.demo.service.DoctorService;
import com.teleconsulting.demo.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/callhistory")
public class CallHistoryController {
    @Autowired
    private CallHistoryService callHistoryService;
    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;

    @PostMapping("/add")
    public String add(@RequestBody CallHistory callHistory) {
        callHistoryService.saveCallHistory(callHistory);
        return "New CallHistory added";
    }

    @GetMapping("/today")
    public ResponseEntity<List<CallHistory>> getCallHistoryForToday() {
        List<CallHistory> callHistoryList = callHistoryService.getCallHistoryForToday();
        return new ResponseEntity<>(callHistoryList, HttpStatus.OK);
    }

    @GetMapping("/today/search")
    public ResponseEntity<List<CallHistory>> searchCallHistory(
            @RequestParam("startTime") String startTime,
            @RequestParam("endTime") String endTime) {
        // Get today's date
        LocalTime start = LocalTime.parse(startTime);
        LocalTime end = LocalTime.parse(endTime);
        List<CallHistory> callHistoryList = callHistoryService.getCallHistoryForTodayWithinTimeRange(start, end);
        return ResponseEntity.ok(callHistoryList);
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<CallHistory>> getCallHistoryForDoctorToday(
            @PathVariable Long doctorId,
            @RequestParam(required = false) String startTime,
            @RequestParam(required = false) String endTime) {
        List<CallHistory> callHistoryList;
        if (startTime != null && endTime != null) {
            // If startTime and endTime are provided, filter call history within the specified time range
            LocalTime start = LocalTime.parse(startTime);
            LocalTime end = LocalTime.parse(endTime);
            callHistoryList = callHistoryService.getCallHistoryForDoctorTodayWithinTimeRange(doctorId, start, end);
        } else {
            // If startTime and endTime are not provided, fetch all call history for the doctor for today
            callHistoryList = callHistoryService.getCallHistoryForDoctorToday(doctorId);
        }
        return new ResponseEntity<>(callHistoryList, HttpStatus.OK);
    }

    @GetMapping("/doctor/{doctorId}/all")
    public ResponseEntity<List<CallHistory>> getAllCallHistoryForDoctor(
            @PathVariable Long doctorId) {
        List<CallHistory> callHistoryList = callHistoryService.getAllCallHistoryForDoctor(doctorId);
        return new ResponseEntity<>(callHistoryList, HttpStatus.OK);
    }

    @PostMapping("/schedule")
    public ResponseEntity<String> scheduleCall(@RequestBody CallHistory callHistory) {
        try {
            callHistoryService.saveCallHistory(callHistory);
            return ResponseEntity.ok("Call scheduled successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error scheduling call");
        }
    }



}
