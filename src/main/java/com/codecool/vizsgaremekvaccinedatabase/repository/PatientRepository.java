package com.codecool.vizsgaremekvaccinedatabase.repository;

import com.codecool.vizsgaremekvaccinedatabase.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Modifying
    @Query(value =
            "UPDATE PATIENT " +
            "SET vaccine_id = :vaccineId, " +
            "VACCINE_DOSES_RECEIVED = VACCINE_DOSES_RECEIVED + 1, " +
            "is_vaccinated = true, " +
            "vaccination_date = CURRENT_TIMESTAMP " +
            "WHERE id = :patientId", nativeQuery = true)
    void injectWithVaccine(@Param("vaccineId") Long availableVaccineId, @Param("patientId") Long patientId);
}
