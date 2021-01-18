package com.albumbazaar.albumbazar.dto;

import java.util.Date;

import lombok.Data;


@Data
public class BranchDTO {

    private Long id;
    private String name;
    private String contactNo;
    private Date date;
    private boolean active;


}
