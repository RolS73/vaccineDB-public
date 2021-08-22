package com.codecool.vizsgaremekvaccinedatabase.service;

import com.codecool.vizsgaremekvaccinedatabase.model.VaccinationPoint;
import com.codecool.vizsgaremekvaccinedatabase.repository.VaccinationPointRepository;
import com.codecool.vizsgaremekvaccinedatabase.repository.VaccineRepository;
import com.codecool.vizsgaremekvaccinedatabase.util.PatientUnsafeVaccinationException;
import com.codecool.vizsgaremekvaccinedatabase.util.VaccinationPointException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VaccinationPointService {

    private final VaccinationPointRepository vaccinationPointRepository;
    private final VaccineRepository vaccineRepository;
    private final PatientService patientService;

    public VaccinationPointService(VaccinationPointRepository vaccinationPointRepository, VaccineRepository vaccineRepository, PatientService patientService) {
        this.vaccinationPointRepository = vaccinationPointRepository;
        this.vaccineRepository = vaccineRepository;
        this.patientService = patientService;
    }

    public VaccinationPoint save(VaccinationPoint s) {
        return vaccinationPointRepository.save(s);
    }

    public VaccinationPoint update(VaccinationPoint s) {
        return vaccinationPointRepository.save(s);
    }

    public List<VaccinationPoint> findAll() {
        return vaccinationPointRepository.findAll();
    }

    public Optional<VaccinationPoint> findById(Long aLong) {
        return vaccinationPointRepository.findById(aLong);
    }

    public void deleteById(Long aLong) {
        vaccinationPointRepository.deleteById(aLong);
    }

    public void injectPatientWithVaccineFromStock(Long vaccinationPointId, String vaccineName, Long patientId) {
        Long availableVaccineId = vaccinationPointRepository.selectVaccineIdFromStockByVaccineName(vaccineName, vaccinationPointId);

        if (availableVaccineId != null) {
            try {
                patientService.injectWithVaccine(availableVaccineId, patientId);
                vaccinationPointRepository.deleteFromStockWithId(availableVaccineId);
            } catch (PatientUnsafeVaccinationException e) {
                e.printStackTrace();
            }
        } else {
            throw new VaccinationPointException(vaccineName);
        }
    }
}
