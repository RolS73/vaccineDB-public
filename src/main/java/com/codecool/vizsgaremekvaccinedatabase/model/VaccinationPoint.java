package com.codecool.vizsgaremekvaccinedatabase.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class VaccinationPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    String region;

    String city;

    String address;

    @OneToMany(cascade = CascadeType.ALL)
    List<Vaccine> vaccineStock;

    public VaccinationPoint() {}

    public VaccinationPoint(String name, String state, String address, List<Vaccine> vaccineStock) {
        this.name = name;
        this.region = state;
        this.address = address;
        this.vaccineStock = vaccineStock;
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String state) {
        this.region = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Vaccine> getVaccineStock() {
        return vaccineStock;
    }

    public void setVaccineStock(List<Vaccine> vaccineStock) {
        this.vaccineStock = vaccineStock;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "VaccinationPoint{" +
                "name='" + name + '\'' +
                ", state='" + region + '\'' +
                ", address='" + address + '\'' +
                ", vaccineStock=" + vaccineStock +
                '}';
    }
}
