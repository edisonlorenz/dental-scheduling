package com.lorenz.dental_scheduling.request;

import com.lorenz.dental_scheduling.enums.AppointmentStatus;
import com.lorenz.dental_scheduling.model.TimeSlot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

public class AddAppointmentRequest {
    @NotNull(message = "Remark is required")
    private String remarks;
    @NotNull(message = "Status is required")
    private AppointmentStatus status;
    @NotNull(message = "Time Slot is required")
    private Long timeSlotId;

    @NotNull(message = "Patient is requireed")
    private Long patientId;

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

    public Long getTimeSlotId() {
        return timeSlotId;
    }

    public void setTimeSlotId(Long timeSlotId) {
        this.timeSlotId = timeSlotId;
    }

    public @NotNull(message = "Patient is requireed") Long getPatientId() {
        return patientId;
    }

    public void setPatientId(@NotNull(message = "Patient is requireed") Long patientId) {
        this.patientId = patientId;
    }
}
