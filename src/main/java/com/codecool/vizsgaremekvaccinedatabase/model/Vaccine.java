package com.codecool.vizsgaremekvaccinedatabase.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Vaccine extends VaccineData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Vaccine() {}

    @ManyToOne()
    @JsonIgnore
    VaccinationPoint vaccinationPoint;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VaccinationPoint getVaccinationPoint() {
        return vaccinationPoint;
    }

    public void setVaccinationPoint(VaccinationPoint vaccinationPoint) {
        this.vaccinationPoint = vaccinationPoint;
    }
}
