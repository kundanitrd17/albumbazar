package com.albumbazaar.albumbazar.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "website_general_info")
@Data
public class WebsiteGeneralInfoEntity {

    private Long id;

    private Double REFERALL_AMOUNT;

    private Double DISCOUNT_FOR_ALL;

}
