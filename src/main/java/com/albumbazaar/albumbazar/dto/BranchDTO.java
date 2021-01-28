package com.albumbazaar.albumbazar.dto;

import javax.validation.constraints.Email;

import lombok.Data;

@Data
public class BranchDTO {

    private Long id;
    private String name;
    private String contactNo;
    private String date;
    private boolean active;

    private String branchCode;

    @Email
    private String email;

}
