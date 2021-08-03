package com.codecool.vizsgaremekvaccinedatabase.repository;

import com.codecool.vizsgaremekvaccinedatabase.model.VaccinationPoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccinationPointRepository extends JpaRepository<VaccinationPoint, Long> {
}
