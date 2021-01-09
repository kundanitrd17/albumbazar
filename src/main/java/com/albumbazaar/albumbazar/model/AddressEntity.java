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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;


@Data
@Entity
@Table(name = "address")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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

    private String landmark;
    @NotNull
    @NotBlank
    private String line1;
    private String line2;
    @NotNull
    private String city;
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

}
