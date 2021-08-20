package com.codecool.vizsgaremekvaccinedatabase.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class VaccineData {

    @Id
    String name;

    @Min(1)
    int dosesNeeded;

    @Min(0)
    int minAge;

    @OneToMany(mappedBy = "vaccine", cascade = { CascadeType.DETACH, CascadeType.PERSIST})
    @JsonIgnore
    private List<Patient> patients = new ArrayList<>();

    @OneToMany(mappedBy = "vaccineData", cascade = { CascadeType.REMOVE, CascadeType.PERSIST})
    @JsonIgnore
    private List<Vaccine> vaccines = new ArrayList<>();

    public VaccineData() {}

    public VaccineData(String name, int doses_needed, int min_age) {
        this.name = name;
        this.dosesNeeded = doses_needed;
        this.minAge = min_age;
    }

    public List<Vaccine> getVaccines() {
        return vaccines;
    }

    public void setVaccines(List<Vaccine> vaccines) {
        this.vaccines = vaccines;
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

    @Override
    public String toString() {
        return "VaccineData{" +
                "name='" + name + '\'' +
                ", dosesNeeded=" + dosesNeeded +
                ", minAge=" + minAge +
                ", patients=" + patients +
                ", vaccines=" + vaccines +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VaccineData that = (VaccineData) o;
        return dosesNeeded == that.dosesNeeded && minAge == that.minAge && name.equals(that.name) && Objects.equals(patients, that.patients) && Objects.equals(vaccines, that.vaccines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dosesNeeded, minAge, patients, vaccines);
    }
}
