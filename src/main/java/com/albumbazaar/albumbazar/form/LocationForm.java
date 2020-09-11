package com.albumbazaar.albumbazar.form;

public class LocationForm {
    
    private String landmark;
    private String street;
    private String town;
    private String postOffice;
    private String city;
    private String district;  
    private String pin;
    private String state;

    

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

    public String getPostOffice() {
        return postOffice;
    }

    public void setPostOffice(String postOffice) {
        this.postOffice = postOffice;
    }

    @Override
    public String toString() {
        return "LocationForm [city=" + city + ", district=" + district + ", landmark=" + landmark + ", pin=" + pin
                + ", postOffice=" + postOffice + ", state=" + state + ", street=" + street + ", town=" + town + "]";
    }


    

}
