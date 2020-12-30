package com.albumbazaar.albumbazar.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerDTO {

    private Long id;

    private String firstName;
    private String lastName;

    private String name;

    @Email(message = "Email is Invalid")
    private String email;

    @Size(min = 5, max = 20, message = "Contact number is not valid")
    private String contactNo;

    private String referralCode;

    private Float wallet;

    private Boolean active;

    private Float discount;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String rePassword;

}
