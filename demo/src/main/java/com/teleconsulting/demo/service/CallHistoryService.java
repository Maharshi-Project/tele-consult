// CallHistoryService.java
package com.teleconsulting.demo.service;

import com.teleconsulting.demo.model.CallHistory;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface CallHistoryService {
    CallHistory saveCallHistory(CallHistory callHistory);
    List<CallHistory> getCallHistoryForToday();
    List<CallHistory> getCallHistoryForTodayWithinTimeRange(LocalTime startTime, LocalTime endTime);
    List<CallHistory> getCallHistoryForDoctorToday(Long doctorId);
    List<CallHistory> getCallHistoryForDoctorTodayWithinTimeRange(Long doctorId, LocalTime startTime, LocalTime endTime); // New method

    List<CallHistory> getAllCallHistoryForDoctor(Long doctorId);

}
