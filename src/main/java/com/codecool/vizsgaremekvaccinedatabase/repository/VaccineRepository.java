package com.codecool.vizsgaremekvaccinedatabase.repository;

import com.codecool.vizsgaremekvaccinedatabase.model.VaccineData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccineRepository extends JpaRepository<VaccineData, Long> {
}
