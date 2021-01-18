package com.albumbazaar.albumbazar.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.albumbazaar.albumbazar.controller.FileUploadController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.albumbazaar.albumbazar.form.customer.BasicCustomerDetailForm;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@JsonIgnoreProperties(value = { "address", "allOrders" }, ignoreUnknown = true)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, updatable = false)
    private Long id;

    private String profilePhoto;

    private String name;

    @Email(message = "Invalid email")
    @Column(name = "email", unique = true)
    private String email;

    @Size(min = 6, max = 20, message = "Contact number is Invalid")
    @NotNull
    @Column(name = "contact_no", unique = true)
    private String contactNo;

    @Column(name = "referral_code", unique = true)
    private String referralCode;

    @Column(columnDefinition = "double default 0.0")
    private Double wallet;

    @Column(columnDefinition = "boolean default false")
    private Boolean active;

    @NotNull(message = "Password cannot be null")
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, message = "Password needs to be more stronger")
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(columnDefinition = "double default 0.0")
    private Double discount;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<AddressEntity> address;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Set<OrderDetail> allOrders;

    public Customer() {
    }

    @PrePersist
    void prePersist() {
        if (this.active == null) {
            this.active = false;
        }
        if (this.discount == null) {
            this.discount = 0.0;
        }
        if (this.wallet == null) {
            this.wallet = 0.0;
        }
    }

    public Customer(final BasicCustomerDetailForm customerDetail) {
        this.name = customerDetail.getName();
        this.email = customerDetail.getEmail();
        this.contactNo = customerDetail.getPhone();
        this.password = customerDetail.getPassword();
    }

    public String getGrofilePhoto() {

        if (profilePhoto != null && !profilePhoto.isBlank()) {
            return MvcUriComponentsBuilder.fromMethodName(FileUploadController.class, "serveFile", profilePhoto).build()
                    .toUri().toString();
        }

        return profilePhoto;

    }

}
