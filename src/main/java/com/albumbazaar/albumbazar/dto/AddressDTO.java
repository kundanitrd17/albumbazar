package com.albumbazaar.albumbazar.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class AddressDTO {

    private Long id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    @Size(min = 10, max = 15)
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
