package com.lorenz.dental_scheduling.controller;

import com.lorenz.dental_scheduling.dto.PatientDto;
import com.lorenz.dental_scheduling.model.Patient;
import com.lorenz.dental_scheduling.request.AddPatientRequest;
import com.lorenz.dental_scheduling.response.ApiResponse;
import com.lorenz.dental_scheduling.service.PatientService;
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
@RequestMapping("${api.prefix}/patients")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createPatient(@Valid @RequestBody  AddPatientRequest request) {
        Patient newPatient = patientService.createPatient(request);
        return ResponseEntity.ok(new ApiResponse(true, "Patient added successfully", newPatient));
    }
    @GetMapping
    public ResponseEntity<ApiResponse> getAllPatient() {
        List<Patient> patientList = patientService.getAllPatient();
        List<PatientDto> patiendDtoList = patientService.convertToDtoList(patientList);
        return ResponseEntity.ok(new ApiResponse(true, "List of patients retrieve successfully", patiendDtoList));
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
