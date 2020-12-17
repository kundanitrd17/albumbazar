package com.albumbazaar.albumbazar.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OrderDetailDTO {

    @NotNull
    @NotBlank
    private Long id;

    private Date deliveryDate;

    private String associationName;

    private String productName;

    private String productSize;

    private String coverName;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getAssociationName() {
        return associationName;
    }

    public void setAssociationName(String associationName) {
        this.associationName = associationName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public String getCoverName() {
        return coverName;
    }

    public void setCoverName(String coverName) {
        this.coverName = coverName;
    }

    public Float getCoverPrice() {
        return coverPrice;
    }

    public void setCoverPrice(Float coverPrice) {
        this.coverPrice = coverPrice;
    }

    public Integer getNoOfSheets() {
        return noOfSheets;
    }

    public void setNoOfSheets(Integer noOfSheets) {
        this.noOfSheets = noOfSheets;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoFolderGoogleDriveId() {
        return photoFolderGoogleDriveId;
    }

    public void setPhotoFolderGoogleDriveId(String photoFolderGoogleDriveId) {
        this.photoFolderGoogleDriveId = photoFolderGoogleDriveId;
    }

    public String getPhotoFolderGoogleDriveLink() {
        return photoFolderGoogleDriveLink;
    }

    public void setPhotoFolderGoogleDriveLink(String photoFolderGoogleDriveLink) {
        this.photoFolderGoogleDriveLink = photoFolderGoogleDriveLink;
    }

    // End of photos columns

}
