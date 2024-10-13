package com.lorenz.dental_scheduling.service;

import com.lorenz.dental_scheduling.dto.AppointmentDto;
import com.lorenz.dental_scheduling.dto.PatientDto;
import com.lorenz.dental_scheduling.model.Appointment;
import com.lorenz.dental_scheduling.model.Patient;
import com.lorenz.dental_scheduling.repository.AppointmentRepository;
import com.lorenz.dental_scheduling.repository.PatientRepository;
import com.lorenz.dental_scheduling.request.AddPatientRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class PatientServiceImpl implements  PatientService{
    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;
    private final AppointmentService appointmentService;
    private final ModelMapper modelMapper;

    public PatientServiceImpl(PatientRepository patientRepository, AppointmentRepository appointmentRepository, AppointmentService appointmentService, ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
        this.appointmentService = appointmentService;
        this.modelMapper = modelMapper;
    }


    @Override
    public Patient createPatient(AddPatientRequest request) {
        Patient newPatient = new Patient();
        newPatient.setFirstName(request.getFirstName());
        newPatient.setLastName(request.getLastName());
        newPatient.setGender(request.getGender());
        newPatient.setDateOfBirth(request.getDateOfBirth());
        newPatient.setAddress(request.getAddress());
        newPatient.setContact(request.getContact());
        newPatient.setRegistrationDate(LocalDate.now());
        return patientRepository.save(newPatient);
    }

    @Override
    public Patient updatePatient() {
        return null;
    }

    @Override
    public Patient getPatiendById(Long id) {
        return null;
    }

    @Override
    public Patient getPatiendByFirstName(String firstName) {
        return null;
    }

    @Override
    public Patient getPatiendByLastName(String lastName) {
        return null;
    }

    @Override
    public Patient getPatiendByGender(String gender) {
        return null;
    }

    @Override
    public List<Patient> getAllPatient() {
        return patientRepository.findAll();
    }

    @Override
    public void deletePatient(Long id) {

    }
    @Override
    public List<PatientDto> convertToDtoList(List<Patient> patients) {
        return patients.stream()
                .map(this::convertToDto)
                .toList();
    }
    @Override
    public PatientDto convertToDto(Patient patient) {
        PatientDto patientDto = modelMapper.map(patient, PatientDto.class);
        List<Appointment> appointmentList = appointmentRepository.findByPatientId(patient.getId());
        List<AppointmentDto> appointmentDtoList = appointmentService.convertToDtoList(appointmentList);
        patientDto.setAppointments(appointmentDtoList);
        return patientDto;
    }

}
