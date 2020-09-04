package com.albumbazaar.albumbazar.model;


import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(
        name = "location",
        uniqueConstraints =
                @UniqueConstraint(
                        name = "combined_address",
                        columnNames = {"landmark", "pin", "street"}
                )
)
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String landmark;
    private String street;
    private String town;
    private String city;
    private String district;
    @Size(max = 7)
    private String pin;
    private String state;

    public Integer getId() {
        return id;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Location [city=" + city + ", district=" + district + ", id=" + id + ", landmark=" + landmark + ", pin="
                + pin + ", state=" + state + ", street=" + street + ", town=" + town + "]";
    }

    
}
