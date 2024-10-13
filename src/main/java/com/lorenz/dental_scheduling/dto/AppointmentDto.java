package com.lorenz.dental_scheduling.dto;

import com.lorenz.dental_scheduling.enums.AppointmentStatus;
import com.lorenz.dental_scheduling.model.Appointment;

import java.util.ArrayList;
import java.util.List;

public class AppointmentDto {
    private Long id;
    private String remarks;
    private AppointmentStatus status;
    private TimeSlotDto timeSlot;
//    private PatientDto patient;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public TimeSlotDto getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlotDto timeSlot) {
        this.timeSlot = timeSlot;
    }

//    public PatientDto getPatient() {
//        return patient;
//    }
//
//    public void setPatient(PatientDto patient) {
//        this.patient = patient;
//    }
}
