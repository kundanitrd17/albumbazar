package com.albumbazaar.albumbazar.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.albumbazaar.albumbazar.form.LocationForm;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "address1")
@JsonIgnoreProperties(ignoreUnknown = true, value = { "address2" })
public class Address1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String landmark;
    private String street1;
    private String street2;

    @ManyToOne
    @JoinColumn(name = "address2_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Address2 address2;

    public Address1() {
    }

    public Address1(final LocationForm locationDetail) {
        this.landmark = locationDetail.getLandmark();
        this.street1 = locationDetail.getStreet();
        this.street2 = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public Address2 getAddress2() {
        return address2;
    }

    public void setAddress2(Address2 address2) {
        this.address2 = address2;
    }

    @Override
    public String toString() {
        return "Address1 [address2=" + address2 + ", id=" + id + ", landmark=" + landmark + ", street1=" + street1
                + ", street2=" + street2 + "]";
    }

}
