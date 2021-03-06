package com.codecool.vizsgaremekvaccinedatabase.service;

import com.codecool.vizsgaremekvaccinedatabase.model.Patient;
import com.codecool.vizsgaremekvaccinedatabase.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository repository;

    public PatientService(PatientRepository repository) {
        this.repository = repository;
    }

    public Patient save(Patient s) {
        return repository.save(s);
    }

    public Patient update(Patient s) {
        return repository.save(s);
    }

    public List<Patient> findAll() {
        return repository.findAll();
    }

    public Optional<Patient> findById(Long aLong) {
        return repository.findById(aLong);
    }

    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }
}
