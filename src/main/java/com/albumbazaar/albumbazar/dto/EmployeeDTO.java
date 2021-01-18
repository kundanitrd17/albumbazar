package com.albumbazaar.albumbazar.dto;

import java.util.Date;

import com.albumbazaar.albumbazar.model.AvailableRoles;

import lombok.Data;

@Data
public class EmployeeDTO {

    private Long id;

    private String name;

    private String father_name;

    private String experience;

    private String date_of_birth;

    private String qualification;

    private Double salary;

    private String home_contact;

    private String joining_date;

    private String leaving_date;

    private Boolean active = true;

    private String profile_pic;

    private String personal_contact;

    private String email;

    private String pan;

    private String aadhaar;

    private String voter;

    private String religion;

    private AvailableRoles role;

    private String password;

    private Long branchId;
}
