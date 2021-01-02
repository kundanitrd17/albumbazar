package com.albumbazaar.albumbazar.form.order;

import java.util.HashMap;

import lombok.Data;

@Data
public class OrderDetailFormDTO {

    private Long associationId;
    private String associationName;

    private String orientation;
    private String productCategory;
    private String productSize;

    private Long coverId;

    private Long paperId[];
    private Long numberOfSheet[];

    private String description;

    private Long branchId;

}
