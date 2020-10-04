package com.albumbazaar.albumbazar.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

import com.albumbazaar.albumbazar.form.order.OrderDetailForm;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "order_detail")
@JsonIgnoreProperties(value = { "customer", "deliveryAddress", "sheets" })
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date orderTime;
    private Date deliveryDate;
    private Boolean paymentStatus;
    private Float amount;
    private String associationName;
    private String productName;
    private String productSize;
    private String coverName;
    private Float coverPrice;
    private Integer noOfSheets;
    private String orderStatus;
    private String orientation;
    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Customer customer;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_location_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Address1 deliveryAddress;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private List<SheetDetail> sheets;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private Long branchId;

    public OrderDetail() {
    }

    public OrderDetail(final OrderDetailForm orderDetails) {// throws Exception {
        this.associationName = orderDetails.getCompanyName();
        this.coverName = orderDetails.getCoverQuality();
        this.productName = orderDetails.getAlbumType();
        this.productSize = orderDetails.getAlbumSize();
        this.description = orderDetails.getDescription();
        if (orderDetails.getBranchId() != null) {
            try {
                this.branchId = Long.parseLong(orderDetails.getBranchId());
            } catch (Exception e) {
                System.out.println("branch Id is invalid");
            }

        }
        // this.coverPrice = Float.parseFloat(orderDetails.getCoverPrice());
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(final Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(final Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(final Customer customer) {
        this.customer = customer;
    }

    public Boolean getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(final Boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Address1 getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(final Address1 deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(final Float amount) {
        this.amount = amount;
    }

    public String getAssociationName() {
        return associationName;
    }

    public void setAssociationName(final String associationName) {
        this.associationName = associationName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(final String productName) {
        this.productName = productName;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(final String productSize) {
        this.productSize = productSize;
    }

    public String getCoverName() {
        return coverName;
    }

    public void setCoverName(final String coverName) {
        this.coverName = coverName;
    }

    public Float getCoverPrice() {
        return coverPrice;
    }

    public void setCoverPrice(final Float coverPrice) {
        this.coverPrice = coverPrice;
    }

    public Integer getNoOfSheets() {
        return noOfSheets;
    }

    public void setNoOfSheets(final Integer noOfSheets) {
        this.noOfSheets = noOfSheets;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(final String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(final String orientation) {
        this.orientation = orientation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public List<SheetDetail> getSheets() {
        return sheets;
    }

    public void setSheets(final List<SheetDetail> sheets) {
        this.sheets = sheets;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    @Override
    public String toString() {
        return "OrderDetail [amount=" + amount + ", associationName=" + associationName + ", branchId=" + branchId
                + ", coverName=" + coverName + ", coverPrice=" + coverPrice + ", deliveryDate=" + deliveryDate
                + ", description=" + description + ", id=" + id + ", noOfSheets=" + noOfSheets + ", orderStatus="
                + orderStatus + ", orderTime=" + orderTime + ", orientation=" + orientation + ", paymentStatus="
                + paymentStatus + ", productName=" + productName + ", productSize=" + productSize + "]";
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}
