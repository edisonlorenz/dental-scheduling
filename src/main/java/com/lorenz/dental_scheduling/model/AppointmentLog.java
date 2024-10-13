package com.lorenz.dental_scheduling.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "appointment_logs")
public class AppointmentLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate rescheduledAt;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    @ManyToOne()
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getRescheduledAt() {
        return rescheduledAt;
    }

    public void setRescheduledAt(LocalDate rescheduledAt) {
        this.rescheduledAt = rescheduledAt;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
}
