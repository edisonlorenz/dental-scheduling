package com.lorenz.dental_scheduling.controller;

import com.lorenz.dental_scheduling.dto.TimeSlotDto;
import com.lorenz.dental_scheduling.exception.AlreadyExistsException;
import com.lorenz.dental_scheduling.exception.ResourceNotFoundException;
import com.lorenz.dental_scheduling.model.TimeSlot;
import com.lorenz.dental_scheduling.request.AddTimeSlotRequest;
import com.lorenz.dental_scheduling.request.UpdateTimeSlotRequest;
import com.lorenz.dental_scheduling.response.ApiResponse;
import com.lorenz.dental_scheduling.service.TimeSlotService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("${api.prefix}/time-slots")
public class TimeSlotController {
    private final TimeSlotService timeSlotService;

    public TimeSlotController(TimeSlotService timeSlotService) {
        this.timeSlotService = timeSlotService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllTimeSlots() {
        try {
            List<TimeSlot> timeSlotList = timeSlotService.getAllTimeSlots();
            List<TimeSlotDto> timeSlotDtos = timeSlotService.convertToDtoList(timeSlotList);
            return ResponseEntity.ok(new ApiResponse(true,"List of Time Slots successfully retrieve.", timeSlotDtos));
        } catch (RuntimeException e) {
           return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse(false, e.getMessage(),null));
        }
    }
    @PostMapping
    public ResponseEntity<ApiResponse> addTimeSlot(@Valid @RequestBody AddTimeSlotRequest request) {
        try {
            TimeSlot timeSlot = timeSlotService.addTimeSlot(request);
            return ResponseEntity.ok(new ApiResponse(true, "Time slot added successfully.", timeSlot));
        } catch (AlreadyExistsException e) {
           return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(false, e.getMessage(), request));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, e.getMessage(), request));
        }
    }
    @PutMapping
    public ResponseEntity<ApiResponse> updateTimeSlot(@Valid @RequestBody UpdateTimeSlotRequest request) {
        try {
            timeSlotService.updateTimeSlot(request);
            return ResponseEntity.ok(new ApiResponse(true, "Time slot updated successfully", request));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(false, e.getMessage(), request));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(false, e.getMessage(), request));
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteTimeSlot(@PathVariable Long id) {
        try {
            timeSlotService.deleteTimeSlotById(id);
            return ResponseEntity.ok(new ApiResponse(true, "Time slot deleted successfully.", id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(false, e.getMessage(), id));
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }

}
