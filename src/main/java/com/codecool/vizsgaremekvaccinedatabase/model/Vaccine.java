package com.codecool.vizsgaremekvaccinedatabase.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
public class Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank
    String name;

    @Min(1)
    int dosesNeeded;

    @Min(0)
    int minAge;

    public Vaccine(){}

    public Vaccine(String name, int dosesNeeded, int minAge) {
        this.name = name;
        this.dosesNeeded = dosesNeeded;
        this.minAge = minAge;
    }

    @ManyToOne()
    @JsonIgnore
    Patient patient;

    @ManyToOne()
    @JsonIgnore
    VaccinationPoint vaccinationPoint;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vaccine vaccine = (Vaccine) o;
        return dosesNeeded == vaccine.dosesNeeded && minAge == vaccine.minAge && name.equals(vaccine.name) && Objects.equals(patient, vaccine.patient) && Objects.equals(vaccinationPoint, vaccine.vaccinationPoint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dosesNeeded, minAge, patient, vaccinationPoint);
    }

    @Override
    public String toString() {
        return "Vaccine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dosesNeeded=" + dosesNeeded +
                ", minAge=" + minAge +
                ", patient=" + patient +
                ", vaccinationPoint=" + vaccinationPoint +
                '}';
    }
}
