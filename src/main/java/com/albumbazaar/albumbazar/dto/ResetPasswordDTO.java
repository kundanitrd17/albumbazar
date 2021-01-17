package com.albumbazaar.albumbazar.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResetPasswordDTO {

    private String email;

    private String OTP;

    private String password;

    private String rePassword;

}
