package com.albumbazaar.albumbazar.form;

public class EmployeeDetailForm {
    private String name;
    
    private String landmark;
    private String city;

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "EmployeeDetailForm [city=" + city + ", landmark=" + landmark + ", name=" + name + "]";
    }

    // public LocationForm getAddress() {
    //     return address;
    // }

    // public void setAddress(LocationForm address) {
    //     this.address = address;
    // }

    // @Override
    // public String toString() {
    //     return "EmployeeDetailForm [address=" + address + ", name=" + name + "]";
    // }
    
}
