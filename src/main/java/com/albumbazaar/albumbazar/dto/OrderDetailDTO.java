package com.albumbazaar.albumbazar.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.albumbazaar.albumbazar.model.OrderDetailStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDetailDTO {

    @NotNull
    @NotBlank
    private Long id;

    private OrderDetailStatus status;

    private Date deliveryDate;

    private String associationName;

    private String productName;

    private String productSize;

    private String coverName;

    private String paperDetailsWithNumberOfSheetsList;

    private String orderCreationTime;

    private String totalAmount;
    private String discount;
    private String taxableAmount;
    private String deliveryCharge;

    private Boolean paymentStatus;
    // Change it to just the ID of the cover and get the pricing of the album from
    // the database
    private Float coverPrice;

    private Integer noOfSheets;

    // Tracking the current position of the order

    private String orientation;

    @Size(min = 10)
    private String description;

    // Photos regarding the project

    private String photoFolderGoogleDriveId;

    private String photoFolderGoogleDriveLink;

}
