package com.codecool.vizsgaremekvaccinedatabase.repository;

import com.codecool.vizsgaremekvaccinedatabase.model.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccineRepository extends JpaRepository<Vaccine, Long> {
}
