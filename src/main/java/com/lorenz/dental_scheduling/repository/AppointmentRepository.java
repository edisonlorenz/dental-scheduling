package com.lorenz.dental_scheduling.repository;

import com.lorenz.dental_scheduling.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    boolean existsByTimeSlotId(Long id);
    Appointment findByIdAndTimeSlotId(Long id, Long timeSlotId);

    List<Appointment> findByPatientId(Long id);
}
