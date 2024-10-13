package com.lorenz.dental_scheduling.model;

import com.lorenz.dental_scheduling.enums.AppointmentStatus;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String remarks;
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @OneToOne()
    @JoinColumn(name = "time_slot_id")
    private TimeSlot timeSlot;

    @OneToMany(mappedBy = "appointment")
    private List<AppointmentLog> appointmentLog = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

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

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public List<AppointmentLog> getAppointmentLog() {
        return appointmentLog;
    }

    public void setAppointmentLog(List<AppointmentLog> appointmentLog) {
        this.appointmentLog = appointmentLog;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
