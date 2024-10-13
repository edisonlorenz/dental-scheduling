package com.lorenz.dental_scheduling.service;

import com.lorenz.dental_scheduling.dto.AppointmentDto;
import com.lorenz.dental_scheduling.model.Appointment;
import com.lorenz.dental_scheduling.request.AddAppointmentRequest;
import com.lorenz.dental_scheduling.request.UpdateAppointmentRequest;
import com.lorenz.dental_scheduling.request.UpdateTimeSlotRequest;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AppointmentService {
    Appointment addAppointment(AddAppointmentRequest request);
    Appointment updateAppointment(UpdateAppointmentRequest request);
    Appointment getAppointmentById(Long id);
    Appointment getAppointmentByTimeSlotId(Long id);
    void deleteAppointmentById(Long id);
    List<Appointment> getAllAppointment();

    List<AppointmentDto> convertToDtoList(List<Appointment> appointments);

    AppointmentDto convertToDto(Appointment appointment);
}
