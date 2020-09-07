package com.albumbazaar.albumbazar.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(
        name = "order_detail"
)
public class OrderDetail {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date orderTime;
    private Date deliveryDate;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Customer customer;
    private Boolean paymentStatus;
    @OneToOne
    @JoinColumn(name = "delivery_location_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Address1 deliveryAddress;
    private Float amount;
    private String associationName;
    private String productName;
    private String productSize;
    private String coverName;
    private Float coverPrice;
    private Integer noOfSheets;
    private String orderStatus;
    private String orientation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Boolean getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Address1 getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address1 deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
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

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }
}
