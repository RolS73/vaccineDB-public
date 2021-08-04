package com.codecool.vizsgaremekvaccinedatabase.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    Long id;

    public Vaccine() {}

    @ManyToOne
    VaccineData vaccineData;

    @ManyToOne
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
}
