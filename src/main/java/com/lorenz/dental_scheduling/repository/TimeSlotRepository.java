package com.lorenz.dental_scheduling.repository;

import com.lorenz.dental_scheduling.model.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {
//    @Query("SELECT slot FROM TimeSlot slot WHERE slot.date = ?1 AND slot.startTime = ?2 AND slot.endTime = ?3")
//    List<TimeSlot> findTimeSlotsByDateStartTimeEndTime(LocalDate date, LocalTime startTime, LocalTime endTime);
    @Query("SELECT COUNT(t) > 0 FROM TimeSlot t WHERE t.date = :date AND t.startTime = :startTime AND t.endTime = :endTime")
    boolean existsByDateAndStartTimeAndEndTime(LocalDate date, LocalTime startTime, LocalTime endTime);
    @Query("SELECT t FROM TimeSlot t WHERE t.id = :id AND isBooked = :isBooked")
    TimeSlot findByIdAndIsBooked(Long id, boolean isBooked);
}
