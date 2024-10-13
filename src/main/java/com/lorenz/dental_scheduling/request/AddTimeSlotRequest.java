package com.lorenz.dental_scheduling.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class AddTimeSlotRequest {
    @Valid
    @NotNull(message = "Date is required")
    private LocalDate date;

    @NotNull(message = "Start time is required")
    private String startTime;

    @NotNull(message = "End time is required")
    private String endTime;

    private Boolean isBooked;

    @NotNull(message = "Create by is required")
    private String createdBy;

    private LocalDateTime createdAt;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(@NotNull(message = "End time is required") String endTime) {
        this.endTime = endTime;
    }

    public Boolean getBooked() {
        return isBooked;
    }

    public void setBooked(Boolean booked) {
        isBooked = booked;
    }

    public @NotNull(message = "Create by is required") String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(@NotNull(message = "Create by is required") String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
