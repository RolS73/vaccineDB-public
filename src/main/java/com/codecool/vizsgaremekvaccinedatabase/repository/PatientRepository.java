package com.codecool.vizsgaremekvaccinedatabase.repository;

import com.codecool.vizsgaremekvaccinedatabase.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
