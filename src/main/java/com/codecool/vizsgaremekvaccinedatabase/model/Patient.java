package com.codecool.vizsgaremekvaccinedatabase.model;

import javax.persistence.*;

@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String fullName;

    private int age;

    private String gender;

    private boolean isVaccinated;

    private String vaccinationDate;

    @ManyToOne()
    private VaccineData vaccine;

    public Patient() {}

    public Patient(String fullName, int age, String gender) {
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
    }

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
}
