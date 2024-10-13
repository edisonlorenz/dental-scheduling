package com.lorenz.dental_scheduling.controller;

import com.lorenz.dental_scheduling.dto.AppointmentDto;
import com.lorenz.dental_scheduling.exception.AlreadyExistsException;
import com.lorenz.dental_scheduling.exception.ResourceNotFoundException;
import com.lorenz.dental_scheduling.model.Appointment;
import com.lorenz.dental_scheduling.request.AddAppointmentRequest;
import com.lorenz.dental_scheduling.request.UpdateAppointmentRequest;
import com.lorenz.dental_scheduling.response.ApiResponse;
import com.lorenz.dental_scheduling.service.AppointmentService;
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
@RequestMapping("${api.prefix}/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }
    @GetMapping
    public ResponseEntity<ApiResponse> getAllAppointment() {
        List<Appointment> appointmentList = appointmentService.getAllAppointment();
        List<AppointmentDto> appointmentDtos = appointmentService.convertToDtoList(appointmentList);
        return  ResponseEntity.ok(new ApiResponse(true, "List of Appointments successfully retrieve.", appointmentDtos));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getAppointmentById(Long id) {
        Appointment appointment = appointmentService.getAppointmentById(id);
        return  ResponseEntity.ok(new ApiResponse(true, "List of Appointments successfully retrieve.", appointment));
    }
    @PostMapping
    public ResponseEntity<ApiResponse> addAppointment(@Valid @RequestBody AddAppointmentRequest request) {
       try {
           appointmentService.addAppointment(request);
           return  ResponseEntity.ok(new ApiResponse(true, "Appointment added successfully", request));
       } catch (ResourceNotFoundException e) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(false, e.getMessage(), request));
       } catch (AlreadyExistsException e) {
           return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(false, e.getMessage(), request));
       }
    }
    @PutMapping
    public ResponseEntity<ApiResponse> updateAppointment(@Valid @RequestBody UpdateAppointmentRequest request) {
        try {
            appointmentService.updateAppointment(request);
            return  ResponseEntity.ok(new ApiResponse(true, "Appointment updated successfully", request));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(false, e.getMessage(), request));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(false, e.getMessage(), request));
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteAppointment(@PathVariable Long id) {
        try {
            appointmentService.deleteAppointmentById(id);
            return ResponseEntity.ok(new ApiResponse(true, "Appointment deleted successfully", id));
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
