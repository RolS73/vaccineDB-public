package com.codecool.vizsgaremekvaccinedatabase.service;

import com.codecool.vizsgaremekvaccinedatabase.model.Vaccine;
import com.codecool.vizsgaremekvaccinedatabase.repository.VaccineRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VaccineService {

    private final VaccineRepository repository;

    public VaccineService(VaccineRepository repository) {
        this.repository = repository;
    }

    public List<Vaccine> findAll() {
        return repository.findAll();
    }

    public Vaccine save(Vaccine s) {
        return repository.save(s);
    }

    public Vaccine update(Vaccine s) {
        return repository.save(s);
    }

    public Optional<Vaccine> findById(Long aLong) {
        return repository.findById(aLong);
    }

    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }
}
