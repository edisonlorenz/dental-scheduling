package com.lorenz.dental_scheduling.repository;

import com.lorenz.dental_scheduling.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
