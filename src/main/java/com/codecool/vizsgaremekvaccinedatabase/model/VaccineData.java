package com.codecool.vizsgaremekvaccinedatabase.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class VaccineData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    Long id;
    String name;
    int dosesNeeded;
    int minAge;

    @OneToMany(mappedBy = "vaccine", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Patient> patients = new ArrayList<>();

    public VaccineData() {}

    public VaccineData(String name, int doses_needed, int min_age) {
        this.name = name;
        this.dosesNeeded = doses_needed;
        this.minAge = min_age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public int getDosesNeeded() {
        return dosesNeeded;
    }

    public void setDosesNeeded(int dosesNeeded) {
        this.dosesNeeded = dosesNeeded;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }
}
