package com.lorenz.dental_scheduling.dto;

import java.time.LocalDate;
import java.util.List;

public class PatientDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String gender;
    private LocalDate dateOfBirth;
    private String address;
    private String contact;
    private LocalDate registrationDate;

    private List<AppointmentDto> appointments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public List<AppointmentDto> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<AppointmentDto> appointments) {
        this.appointments = appointments;
    }
}
