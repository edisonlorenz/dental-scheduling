package com.lorenz.dental_scheduling.service;

import com.lorenz.dental_scheduling.dto.TimeSlotDto;
import com.lorenz.dental_scheduling.model.TimeSlot;
import com.lorenz.dental_scheduling.request.AddTimeSlotRequest;
import com.lorenz.dental_scheduling.request.UpdateTimeSlotRequest;

import java.util.List;

public interface TimeSlotService {
    // add Time slot
    TimeSlot addTimeSlot(AddTimeSlotRequest request);
    // update Time slot
    TimeSlot updateTimeSlot(UpdateTimeSlotRequest request);
    // delete Time slot
    void deleteTimeSlotById(Long id);
    // get all Time slot
    List<TimeSlot> getAllTimeSlots();
    // get Time slot by id
    TimeSlot getTimeSlotById(Long id);

    List<TimeSlotDto> convertToDtoList(List<TimeSlot> timeSlots);

    TimeSlotDto convertToDto(TimeSlot timeSlot);
}
