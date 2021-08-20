package com.codecool.vizsgaremekvaccinedatabase.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class VaccinationPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank
    String name;

    @NotBlank
    String region;

    @NotBlank
    String city;

    @NotBlank
    String address;

    @OneToMany()
    List<Vaccine> vaccineStock;

    public VaccinationPoint() {}

    public VaccinationPoint(String name, String state, String city, String address) {
        this.name = name;
        this.region = state;
        this.city = city;
        this.address = address;
        this.vaccineStock = new ArrayList<>();
    }

    public VaccinationPoint(String name, String state, String city, String address, List<Vaccine> vaccineStock) {
        this.name = name;
        this.region = state;
        this.city = city;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VaccinationPoint that = (VaccinationPoint) o;
        return name.equals(that.name) && region.equals(that.region) && city.equals(that.city) && address.equals(that.address) && Objects.equals(vaccineStock, that.vaccineStock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, region, city, address, vaccineStock);
    }
}
