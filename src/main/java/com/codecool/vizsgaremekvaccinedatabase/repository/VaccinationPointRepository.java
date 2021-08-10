package com.codecool.vizsgaremekvaccinedatabase.repository;

import com.codecool.vizsgaremekvaccinedatabase.model.VaccinationPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VaccinationPointRepository extends JpaRepository<VaccinationPoint, Long> {

    @Modifying
    @Query(value = "SELECT FROM VACCINATION_POINT_VACCINE_STOCK vs " +
            "INNER JOIN VACCINE v " +
            "ON vs.Vaccine_Stock_Id = v.id " +
            "AND v.VACCINE_DATA_NAME = :name"
            ,nativeQuery = true)
    void deleteFromStockByName(@Param("name") String name);
}
