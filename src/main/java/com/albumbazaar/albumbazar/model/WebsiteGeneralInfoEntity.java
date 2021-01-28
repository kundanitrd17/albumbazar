package com.albumbazaar.albumbazar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "website_general_info")
@Data
public class WebsiteGeneralInfoEntity {

    @Id
    private Long id;

    @Column(columnDefinition = "double default 0.0")
    private Double REFERALL_AMOUNT;

    @Column(columnDefinition = "double default 0.0")
    private Double DISCOUNT_FOR_ALL;

    @PrePersist
    void prePersist() {
        if (this.REFERALL_AMOUNT == null) {
            this.REFERALL_AMOUNT = 0.0;
        }
        if (this.DISCOUNT_FOR_ALL == null) {
            this.DISCOUNT_FOR_ALL = 0.0;
        }
    }

}
