package com.codecool.vizsgaremekvaccinedatabase.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank
    @NotNull
    private String fullName;

    @Min(0)
    private int age;

    @NotBlank
    private String gender;

    private boolean isVaccinated;

    private String vaccinationDate;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private VaccineData vaccine;

    public Patient() {}

    public Patient(String fullName, int age, String gender) {
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
    }

    /*public Patient(Long id, String fullName, int age, String gender) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isVaccinated() {
        return isVaccinated;
    }

    public void setVaccinated(boolean vaccinated) {
        isVaccinated = vaccinated;
    }

    public String getVaccinationDate() {
        return vaccinationDate;
    }

    public void setVaccinationDate(String vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
    }

    public VaccineData getVaccine() {
        return vaccine;
    }

    public void setVaccine(VaccineData vaccine) {
        this.vaccine = vaccine;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "fullName='" + fullName + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", isVaccinated=" + isVaccinated +
                ", vaccinationDate='" + vaccinationDate + '\'' +
                ", vaccine=" + vaccine +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return age == patient.age && isVaccinated == patient.isVaccinated && fullName.equals(patient.fullName) && gender.equals(patient.gender) && Objects.equals(vaccinationDate, patient.vaccinationDate) && Objects.equals(vaccine, patient.vaccine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, age, gender, isVaccinated, vaccinationDate, vaccine);
    }
}
