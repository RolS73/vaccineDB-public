package com.codecool.vizsgaremekvaccinedatabase.service;

import com.codecool.vizsgaremekvaccinedatabase.model.Patient;
import com.codecool.vizsgaremekvaccinedatabase.model.Vaccine;
import com.codecool.vizsgaremekvaccinedatabase.repository.PatientRepository;
import com.codecool.vizsgaremekvaccinedatabase.util.PatientUnsafeVaccinationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository repository;
    private final VaccineService vaccineService;

    public PatientService(PatientRepository repository, VaccineService vaccineService) {
        this.repository = repository;
        this.vaccineService = vaccineService;
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

    public void injectWithVaccine(Long availableVaccineId, Long patientId) throws PatientUnsafeVaccinationException {
        Optional<Vaccine> vaccine = vaccineService.findById(availableVaccineId);
        Optional<Patient> patient = repository.findById(patientId);

            if (vaccine.isPresent() && patient.isPresent()
                    && vaccine.get().getDosesNeeded() > patient.get().getVaccineDosesReceived()
                    && (patient.get().getVaccine() == null || (patient.get().getVaccine() != null && vaccine.get().getName().equals(patient.get().getVaccine().getName())))) {
                repository.injectWithVaccine(availableVaccineId, patientId);
            } else if (vaccine.isPresent() && patient.isPresent() &&
                    (vaccine.get().getDosesNeeded() == patient.get().getVaccineDosesReceived()
                    || (patient.get().getVaccine() != null && !vaccine.get().getName().equals(patient.get().getVaccine().getName())))) {
                throw new PatientUnsafeVaccinationException(patient.get().getFullName());
            }
    }
}
