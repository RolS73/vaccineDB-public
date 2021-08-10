package com.codecool.vizsgaremekvaccinedatabase.service;

import com.codecool.vizsgaremekvaccinedatabase.model.VaccineData;
import com.codecool.vizsgaremekvaccinedatabase.repository.VaccinationPointRepository;
import com.codecool.vizsgaremekvaccinedatabase.repository.VaccineDataRepository;
import com.codecool.vizsgaremekvaccinedatabase.repository.VaccineRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VaccineDataService {

    VaccineDataRepository repository;
    VaccineRepository vaccineRepository;
    VaccinationPointRepository vaccinationPointRepository;

    public VaccineDataService(VaccineDataRepository repository, VaccineRepository vaccineRepository, VaccinationPointRepository vaccinationPointRepository) {
        this.repository = repository;
        this.vaccineRepository = vaccineRepository;
        this.vaccinationPointRepository = vaccinationPointRepository;
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

    public Optional<VaccineData> findByName(String vaccineName) {
        return repository.findById(vaccineName);
    }

    public void deleteByName(String name) {
        vaccinationPointRepository.deleteByName(name);
        vaccineRepository.deleteByName(name);
        repository.deleteById(name);
    }
}
