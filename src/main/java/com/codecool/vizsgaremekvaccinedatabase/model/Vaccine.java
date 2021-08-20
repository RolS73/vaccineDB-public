package com.codecool.vizsgaremekvaccinedatabase.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String uuid = UUID.randomUUID().toString();

    public Vaccine(){}

    public Vaccine(VaccineData vaccineData) {
        this.vaccineData = vaccineData;
        this.uuid = UUID.randomUUID().toString();
    }

    @ManyToOne()
    VaccineData vaccineData;

    @ManyToOne()
    @JsonIgnore
    VaccinationPoint vaccinationPoint;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VaccineData getVaccineData() {
        return vaccineData;
    }

    public void setVaccineData(VaccineData vaccineData) {
        this.vaccineData = vaccineData;
    }

    public VaccinationPoint getVaccinationPoint() {
        return vaccinationPoint;
    }

    public void setVaccinationPoint(VaccinationPoint vaccinationPoint) {
        this.vaccinationPoint = vaccinationPoint;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "Vaccine{" +
                "id=" + id +
                ", vaccineData=" + vaccineData +
                ", vaccinationPoint=" + vaccinationPoint +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vaccine vaccine = (Vaccine) o;
        return Objects.equals(vaccineData, vaccine.vaccineData) && Objects.equals(vaccinationPoint, vaccine.vaccinationPoint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vaccineData, vaccinationPoint);
    }
}
