package com.lorenz.dental_scheduling.service;

import com.lorenz.dental_scheduling.dto.AppointmentDto;
import com.lorenz.dental_scheduling.dto.PatientDto;
import com.lorenz.dental_scheduling.dto.TimeSlotDto;
import com.lorenz.dental_scheduling.enums.AppointmentStatus;
import com.lorenz.dental_scheduling.exception.AlreadyExistsException;
import com.lorenz.dental_scheduling.exception.ResourceNotFoundException;
import com.lorenz.dental_scheduling.model.Appointment;
import com.lorenz.dental_scheduling.model.Patient;
import com.lorenz.dental_scheduling.model.TimeSlot;
import com.lorenz.dental_scheduling.repository.AppointmentRepository;
import com.lorenz.dental_scheduling.repository.PatientRepository;
import com.lorenz.dental_scheduling.repository.TimeSlotRepository;
import com.lorenz.dental_scheduling.request.AddAppointmentRequest;
import com.lorenz.dental_scheduling.request.UpdateAppointmentRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final TimeSlotRepository timeSlotRepository;
    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, TimeSlotRepository timeSlotRepository, PatientRepository patientRepository, ModelMapper modelMapper) {
        this.appointmentRepository = appointmentRepository;
        this.timeSlotRepository = timeSlotRepository;
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Appointment addAppointment(AddAppointmentRequest request) {
        TimeSlot timeSlot = timeSlotRepository.findById(request.getTimeSlotId())
                .orElseThrow(() -> new ResourceNotFoundException("Time slot does not exists."));
        Patient patient = patientRepository.findById(request.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient does not exists."));

        boolean exist = appointmentRepository.existsByTimeSlotId(request.getTimeSlotId());
        if (exist) {
            throw new AlreadyExistsException("Appointment is already exists");
        }
        // set timeslot to booked
        timeSlot.setBooked(true);
        Appointment newAppointment = new Appointment();
        newAppointment.setRemarks(request.getRemarks());
        newAppointment.setStatus(request.getStatus());
        newAppointment.setTimeSlot(timeSlot);
        newAppointment.setPatient(patient);
        return appointmentRepository.save(newAppointment);
    }

    @Override
    public Appointment updateAppointment(UpdateAppointmentRequest request) {
        return  appointmentRepository.findById(request.getId())
                .map(existingAppointment -> {
                    existingAppointment.setStatus(request.getStatus());
                    existingAppointment.setRemarks(request.getRemarks());;
                    return existingAppointment;
                })
                .map(appointmentRepository::save)
                .orElseThrow(() -> new ResourceNotFoundException("No Appointment found."));
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return null;
    }

    @Override
    public Appointment getAppointmentByTimeSlotId(Long id) {
        return null;
    }

    @Override
    public void deleteAppointmentById(Long id) {
        appointmentRepository.findById(id)
                .ifPresentOrElse(appointmentRepository::delete, () -> {
                    throw new ResourceNotFoundException("No Appointment found.");
                });
    }

    @Override
    public List<Appointment> getAllAppointment() {
        return appointmentRepository.findAll();
    }

    @Override
    public List<AppointmentDto> convertToDtoList(List<Appointment> appointments) {
        return appointments.stream()
                .map(this::convertToDto)
                .toList();
    }

    @Override
    public AppointmentDto convertToDto(Appointment appointment) {
        AppointmentDto appointmentDto = modelMapper.map(appointment, AppointmentDto.class);
//        Patient patient = patientRepository.findById(appointment.getPatient().getId())
//                .orElseThrow(() -> new ResourceNotFoundException("No Appointment found."));
//        PatientDto patientDto = modelMapper.map(patient, PatientDto.class);
//        appointmentDto.setPatient(patientDto);
        return appointmentDto;
    }
}
