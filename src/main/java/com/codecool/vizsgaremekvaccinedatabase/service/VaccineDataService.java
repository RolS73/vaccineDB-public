package com.codecool.vizsgaremekvaccinedatabase.service;

import com.codecool.vizsgaremekvaccinedatabase.model.VaccineData;
import com.codecool.vizsgaremekvaccinedatabase.repository.VaccineDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VaccineDataService {

    VaccineDataRepository repository;

    public VaccineDataService(VaccineDataRepository repository) {
        this.repository = repository;
    }

    public List<VaccineData> findAll() {
        return repository.findAll();
    }

    public VaccineData save(VaccineData s) {
        return repository.save(s);
    }

    public VaccineData update(VaccineData s) {
        return repository.save(s);
    }

    public Optional<VaccineData> findById(Long aLong) {
        return repository.findById(aLong);
    }

    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }
}
