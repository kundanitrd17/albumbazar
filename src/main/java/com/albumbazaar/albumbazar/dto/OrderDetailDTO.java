package com.albumbazaar.albumbazar.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.albumbazaar.albumbazar.model.OrderDetailStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

    public OrderDetailStatus getStatus() {
        return status;
    }

    public void setStatus(OrderDetailStatus status) {
        this.status = status;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getTaxableAmount() {
        return taxableAmount;
    }

    public void setTaxableAmount(String taxableAmount) {
        this.taxableAmount = taxableAmount;
    }

    public String getDeliveryCharge() {
        return deliveryCharge;
    }

    public void setDeliveryCharge(String deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    public String getOrderCreationTime() {
        return orderCreationTime;
    }

    public void setOrderCreationTime(String orderCreationTime) {
        this.orderCreationTime = orderCreationTime;
    }

    public Boolean getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    // End of photos columns

}
