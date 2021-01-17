package com.albumbazaar.albumbazar.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.albumbazaar.albumbazar.form.order.OrderDetailForm;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "order_detail")
@JsonIgnoreProperties(value = { "customer", "deliveryAddress", "sheets", "employee", "association", "cover" })
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, updatable = false)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date orderTime;

    // Finance
    @NotNull
    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean paymentStatus;

    @Column(unique = true)
    private String razorpayOrderId;

    private String razorpayPaymentId;

    private String razorPaySignature;

    // Product details

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "association_id", nullable = false)
    private Association association;

    @Column(columnDefinition = "boolean default true")
    private Boolean hasAssociationAccepted;

    @Column(columnDefinition = "boolean default true")
    private Boolean isForwardedToAssociation;

    private String associationName;

    private String productName;

    private String productSize;

    private String coverName;

    private Float coverPrice;

    @NotNull
    @NotBlank
    @Column(columnDefinition = "json", nullable = false)
    private String paperDetailsWithNumberOfSheetsList;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cover_id", nullable = false)
    private Cover cover;

    // End of product details

    @Embedded
    private OrderBillEmbeddable orderBill;

    // @OneToMany(fetch = FetchType.LAZY)
    // @JoinColumn(name = "order_id")
    // private List<SheetDetail> sheets;
    // end of finance

    // Tracking the current position of the order
    @Column(columnDefinition = "varchar(50) default 'pending'")
    private String orderStatus;

    private String orientation;

    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Customer customer;

    // The employee who handled this order
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private Long branchId;

    // Photos regarding the project

    private String photoFolderGoogleDriveId;

    private String photoFolderGoogleDriveLink;

    // End of photos columns

    private Date deliveryDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_location_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private AddressEntity deliveryAddress;

    @PrePersist
    public void prePersist() {
        if (this.orderStatus == null)
            this.orderStatus = OrderDetailStatus.PENDING.toString();

        if (this.paymentStatus == null)
            this.paymentStatus = false;

        if (this.hasAssociationAccepted == null)
            this.hasAssociationAccepted = false;

        if (this.isForwardedToAssociation == null)
            this.isForwardedToAssociation = false;
    }

    public OrderDetail() {
    }

    public OrderDetail(final OrderDetailForm orderDetails) {// throws Exception {
        this.associationName = orderDetails.getCompanyName();
        this.coverName = orderDetails.getCoverQuality();
        this.productName = orderDetails.getAlbumType();
        this.productSize = orderDetails.getAlbumSize();
        if (orderDetails.getBranchId() != null) {
            try {
                this.branchId = Long.parseLong(orderDetails.getBranchId());
            } catch (Exception e) {
                System.out.println("branch Id is invalid");
            }

        }
        // this.coverPrice = Float.parseFloat(orderDetails.getCoverPrice());
    }

    public void setDescription(final String description) {
        if (description == null || description.isBlank()) {
            return;
        }

        // remove all '"' double quotes before a string so that it can be easily
        // inserted in a value tag
        int startCopyingPosition = 0, endCopyingPosition = description.length();

        for (int index = 0; index < description.length(); ++index) {
            if (description.charAt(index) == '"' || description.charAt(index) == '\'') {
                startCopyingPosition++;
            } else
                break;
        }
        for (int index = description.length() - 1; index >= 0; --index) {
            if (description.charAt(index) == '"' || description.charAt(index) == '\'') {
                endCopyingPosition--;
            } else
                break;
        }

        if (startCopyingPosition >= endCopyingPosition) {
            throw new RuntimeException("Description is Invalid");
        }

        this.description = description.substring(startCopyingPosition, endCopyingPosition);

    }

}
