package com.codecool.vizsgaremekvaccinedatabase.repository;

import com.codecool.vizsgaremekvaccinedatabase.model.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface VaccineRepository extends JpaRepository<Vaccine, Long> {

    @Modifying
    @Query(value = "DELETE FROM Vaccine v WHERE v.vaccineData.name = :name")
    void deleteByName(String name);

}
