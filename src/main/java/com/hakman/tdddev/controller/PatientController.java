package com.hakman.tdddev.controller;

import com.hakman.tdddev.dto.PatientDto;
import com.hakman.tdddev.model.Patient;
import com.hakman.tdddev.service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {
    private final PatientService service;

    public PatientController(PatientService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<Patient> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Patient getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PostMapping("/create")
    public Patient create(@RequestBody PatientDto patientDto) {
        return service.create(patientDto);
    }

    @PutMapping("/update/{id}")
    public Patient update(@PathVariable String id, @RequestBody PatientDto patientDto) {
        if (!id.equals(patientDto.id())) {
            throw new IllegalArgumentException("Id don't match");
        }

        return service.update(patientDto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
