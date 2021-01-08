package com.albumbazaar.albumbazar.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.albumbazaar.albumbazar.form.order.OrderDetailForm;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "order_detail")
@JsonIgnoreProperties(value = { "customer", "deliveryAddress", "sheets", "employee", "association",
        "cover" }, ignoreUnknown = true)
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

    private Float totalAmount;

    private Float discount;

    // Product details

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "association_id", nullable = false)
    private Association association;

    private Boolean hasAssociationAccepted;

    private Boolean isForwardedToAssociation;

    private String associationName;

    private String productName;

    private String productSize;

    private String coverName;

    private Float coverPrice;

    @Column(columnDefinition = "json")
    private String paperDetailsWithNumberOfSheetsList;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cover cover;

    // End of product details

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private List<SheetDetail> sheets;
    // end of finance

    // Tracking the current position of the order
    @Column(columnDefinition = "varchar(50) default 'pending'")
    private String orderStatus;

    private String orientation;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Customer customer;

    // The employee who handled this order
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
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
        this.orderStatus = OrderDetailStatus.PENDING.toString();
        this.paymentStatus = false;
        this.totalAmount = 1f;
        this.hasAssociationAccepted = false;
        this.isForwardedToAssociation = false;
    }

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

}
