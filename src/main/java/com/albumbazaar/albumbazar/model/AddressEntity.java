package com.albumbazaar.albumbazar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.albumbazaar.albumbazar.form.LocationForm;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "address")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" }, ignoreUnknown = true)
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotNull
    @NotBlank
    @Size(min = 10, max = 15)
    @Column(nullable = false)
    private String contactNo;

    @NotNull
    @NotBlank
    private String landmark;

    @NotNull
    @NotBlank
    private String line1;

    private String line2;

    @NotNull
    @NotBlank
    private String city;

    @NotNull
    @NotBlank
    private String district;

    @NotNull
    @NotBlank
    @Size(min = 6, max = 6)
    private String pincode;

    @NotNull
    @NotBlank
    private String state;

    private String cityCode;

    private String stateCode;

    public AddressEntity() {
    }

    public AddressEntity(final LocationForm locationForm) {

        this.landmark = locationForm.getLandmark();
        this.line1 = locationForm.getLine1();
        this.line2 = locationForm.getLine2();
        this.city = locationForm.getCity();
        this.district = locationForm.getDistrict();
        this.pincode = locationForm.getPin();
        this.state = locationForm.getState();

    }

}
