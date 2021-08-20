package com.codecool.vizsgaremekvaccinedatabase.service;

import com.codecool.vizsgaremekvaccinedatabase.model.VaccinationPoint;
import com.codecool.vizsgaremekvaccinedatabase.repository.VaccinationPointRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VaccinationPointService {

    private final VaccinationPointRepository repository;

    public VaccinationPointService(VaccinationPointRepository repository) {
        this.repository = repository;
    }

    public VaccinationPoint save(VaccinationPoint s) {
        return repository.save(s);
    }

    public VaccinationPoint update(VaccinationPoint s) {
        return repository.save(s);
    }

    public List<VaccinationPoint> findAll() {
        return repository.findAll();
    }

    public Optional<VaccinationPoint> findById(Long aLong) {
        return repository.findById(aLong);
    }

    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }
}
