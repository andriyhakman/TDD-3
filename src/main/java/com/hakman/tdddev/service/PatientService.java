package com.hakman.tdddev.service;

import com.hakman.tdddev.dto.PatientDto;
import com.hakman.tdddev.model.Patient;
import com.hakman.tdddev.repository.PatientRepository;
import com.hakman.tdddev.service.interfaces.IService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PatientService implements IService<Patient, PatientDto> {
    private final PatientRepository repository;

    public PatientService(PatientRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Patient> getAll() {
        return repository.findAll();
    }

    @Override
    public Patient getById(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Patient create(PatientDto patientDto) {
        var patient = new Patient();

        patient.setId(patientDto.id());
        patient.setName(patientDto.name());
        patient.setPhoneNumber(patientDto.phoneNumber());
        patient.setEMail(patientDto.eMail());
        patient.setCreatedAt(LocalDateTime.now());
        patient.setUpdatedAt(LocalDateTime.now());

        return repository.save(patient);
    }

    @Override
    public Patient update(PatientDto patientDto) {
        var patient = getById(patientDto.id());

        patient.setId(patientDto.id());
        patient.setName(patientDto.name());
        patient.setPhoneNumber(patientDto.phoneNumber());
        patient.setEMail(patientDto.eMail());
        patient.setUpdatedAt(LocalDateTime.now());

        return repository.save(patient);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}
