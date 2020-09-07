package com.albumbazaar.albumbazar.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(
        name = "location",
        uniqueConstraints =
                @UniqueConstraint(
                        name = "landmark_pin_street",
                        columnNames = {"landmark", "pin", "street"}
                )
)
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 200)
    private String landmark;
    @Size(max = 200)
    private String street;
    @Size(max = 100)
    private String town;
    @Size(max = 100)
    private String city;
    @Size(max = 100)
    private String district;
    @Size(max = 7)
    private String pin;
    @Size(max = 100)
    private String state;


    public Location() {
    }
    public Location(String landmark, String street, String town, String city, String district,
            @Size(max = 7) String pin, String state) {
  
        this.landmark = landmark;
        this.street = street;
        this.town = town;
        this.city = city;
        this.district = district;
        this.pin = pin;
        this.state = state;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
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
