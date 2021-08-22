package com.codecool.vizsgaremekvaccinedatabase.repository;

import com.codecool.vizsgaremekvaccinedatabase.model.VaccinationPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VaccinationPointRepository extends JpaRepository<VaccinationPoint, Long> {

    @Modifying
    @Query(value =
                    "DELETE FROM VACCINATION_POINT_VACCINE_STOCK vs " +
                    "WHERE EXISTS(" +
                    "SELECT * " +
                    "FROM VACCINE v " +
                    "JOIN VACCINE_DATA vd " +
                    "ON vs.Vaccine_Stock_Id = v.id " +
                    "AND v.VACCINE_DATA_NAME = :name" +
            ")"
            ,nativeQuery = true)
    void deleteFromStockByName(@Param("name") String name);

    @Query(value =
            "SELECT Vaccine_STOCK_ID vsi " +
            "FROM VACCINATION_POINT_VACCINE_STOCK vs " +
            "INNER JOIN Vaccine v " +
            "ON v.id = vs.vaccine_stock_id " +
            "AND v.name = :vaccineName " +
            "AND vs.VACCINATION_POINT_ID = :vaccinationPointId " +
            "LIMIT 1"
            ,nativeQuery = true)
    Long selectVaccineIdFromStockByVaccineName(@Param("vaccineName") String vaccineName, @Param("vaccinationPointId") Long id);

    @Modifying
    @Query(value =
            "DELETE FROM VACCINATION_POINT_VACCINE_STOCK " +
            "WHERE vaccine_stock_id = :id", nativeQuery = true)
    void deleteFromStockWithId(@Param("id") Long id);
}
