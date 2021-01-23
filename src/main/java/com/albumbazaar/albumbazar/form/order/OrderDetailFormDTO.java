package com.albumbazaar.albumbazar.form.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDetailFormDTO {

    private Long selectedAssociationId;
    private String associationName;

    private String orientation;
    private String productCategory;
    private String productSize;

    private Long coverId;

    private Long paperId[];
    private Long numberOfSheet[];

    private String description;

    private Long employeeId;
    private Long branchId;

    private String customerIdentifier;

}
