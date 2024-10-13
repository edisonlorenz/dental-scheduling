package com.lorenz.dental_scheduling.request;

import com.lorenz.dental_scheduling.enums.AppointmentStatus;
import jakarta.validation.constraints.NotNull;

public class UpdateAppointmentRequest {
    private Long id;

    @NotNull(message = "Remark is required")
    private String remarks;

    @NotNull(message = "Status is required")
    private AppointmentStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "Remark is required") String getRemarks() {
        return remarks;
    }

    public void setRemarks(@NotNull(message = "Remark is required") String remarks) {
        this.remarks = remarks;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

}
