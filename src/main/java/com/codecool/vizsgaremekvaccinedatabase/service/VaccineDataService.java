package com.codecool.vizsgaremekvaccinedatabase.service;

import com.codecool.vizsgaremekvaccinedatabase.model.VaccineData;
import com.codecool.vizsgaremekvaccinedatabase.repository.PatientRepository;
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

    private final VaccineDataRepository repository;
    private final VaccineRepository vaccineRepository;
    private final VaccinationPointRepository vaccinationPointRepository;
    private final PatientRepository patientRepository;

    public VaccineDataService(VaccineDataRepository repository, VaccineRepository vaccineRepository, VaccinationPointRepository vaccinationPointRepository, PatientRepository patientRepository) {
        this.repository = repository;
        this.vaccineRepository = vaccineRepository;
        this.vaccinationPointRepository = vaccinationPointRepository;
        this.patientRepository = patientRepository;
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
        vaccinationPointRepository.deleteFromStockByName(name);
        vaccineRepository.deleteByName(name);
        patientRepository.findAll()
                .stream()
                .filter(patient -> patient.getVaccine() != null && patient.getVaccine().getName().equals(name))
                .forEach(patient -> patient.setVaccine(null));
        repository.deleteById(name);
    }
}
