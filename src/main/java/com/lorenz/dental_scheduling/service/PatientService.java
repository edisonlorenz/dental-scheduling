package com.lorenz.dental_scheduling.service;

import com.lorenz.dental_scheduling.dto.PatientDto;
import com.lorenz.dental_scheduling.model.Patient;
import com.lorenz.dental_scheduling.request.AddPatientRequest;

import java.util.List;

public interface PatientService {
    Patient createPatient(AddPatientRequest request);
    Patient updatePatient();
    Patient getPatiendById(Long id);
    Patient getPatiendByFirstName(String firstName);
    Patient getPatiendByLastName(String lastName);
    Patient getPatiendByGender(String gender);
    List<Patient> getAllPatient();

    void deletePatient(Long id);

    List<PatientDto> convertToDtoList(List<Patient> patients);

    PatientDto convertToDto(Patient patient);
}
