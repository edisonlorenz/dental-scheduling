package com.lorenz.dental_scheduling.service;

import com.lorenz.dental_scheduling.dto.TimeSlotDto;
import com.lorenz.dental_scheduling.exception.AlreadyExistsException;
import com.lorenz.dental_scheduling.exception.ResourceNotFoundException;
import com.lorenz.dental_scheduling.model.TimeSlot;
import com.lorenz.dental_scheduling.repository.TimeSlotRepository;
import com.lorenz.dental_scheduling.request.AddTimeSlotRequest;
import com.lorenz.dental_scheduling.request.UpdateTimeSlotRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@Service
public class TimeSlotServiceImpl implements TimeSlotService{
    private  final TimeSlotRepository timeSlotRepository;
    private final ModelMapper modelMapper;

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm a");

    public TimeSlotServiceImpl(TimeSlotRepository timeSlotRepository, ModelMapper modelMapper) {
        this.timeSlotRepository = timeSlotRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TimeSlot addTimeSlot(AddTimeSlotRequest request) {
        // Convert time format
        LocalTime startTime = parseTime(request.getStartTime());
        LocalTime endTime =  parseTime(request.getEndTime());
        // check if the date and start/end time is existing
        boolean exists = timeSlotRepository.existsByDateAndStartTimeAndEndTime(request.getDate(), startTime, endTime);
        if (exists) {
            throw new AlreadyExistsException("Schedule is already exists");
        }

        TimeSlot newTimeSlot = new TimeSlot();
        newTimeSlot.setDate(request.getDate());
        newTimeSlot.setStartTime(startTime);
        newTimeSlot.setEndTime(endTime);
        newTimeSlot.setBooked(false);
        newTimeSlot.setCreatedBy(request.getCreatedBy());
        newTimeSlot.setCreatedAt(LocalDateTime.now());

        return timeSlotRepository.save(newTimeSlot);
    }

    @Override
    public TimeSlot updateTimeSlot(UpdateTimeSlotRequest request) {
        LocalTime startTime = parseTime(request.getStartTime());
        LocalTime endTime =  parseTime(request.getEndTime());
        // check if the date and start/end time is existing
        boolean exists = timeSlotRepository.existsByDateAndStartTimeAndEndTime(request.getDate(), startTime, endTime);
        if (exists) {
            throw new AlreadyExistsException("Schedule is already exists");
        }
        return timeSlotRepository.findById(request.getId())
                .map(existingTimeSlot -> {
                     existingTimeSlot.setDate(request.getDate());
                    existingTimeSlot.setStartTime(startTime);
                    existingTimeSlot.setEndTime(endTime);
                    return existingTimeSlot;
                })
                .map(timeSlotRepository :: save)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule not exists."));
    }


    @Override
    public void deleteTimeSlotById(Long id) {
        timeSlotRepository.findById(id)
                .ifPresentOrElse(timeSlotRepository::delete,
                        () -> {throw new ResourceNotFoundException("Schedule not exists.");});
    }

    @Override
    public List<TimeSlot> getAllTimeSlots() {
       return timeSlotRepository.findAll();
    }

    @Override
    public TimeSlot getTimeSlotById(Long id) {
        return null;
    }
    @Override
    public List<TimeSlotDto> convertToDtoList(List<TimeSlot> timeSlots) {
        return timeSlots.stream()
                .map(this::convertToDto)
                .toList();
    }
    @Override
    public TimeSlotDto convertToDto(TimeSlot timeSlot) {
        return modelMapper.map(timeSlot, TimeSlotDto.class);
    }

    public LocalTime parseTime(String time) {
        try {
            return LocalTime.parse(time, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Please enter valid time format.");
        }

    }
}
