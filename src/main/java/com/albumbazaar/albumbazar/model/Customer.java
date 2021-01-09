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

    private String name;

    @Email(message = "Invalid email")
    @Column(name = "email", unique = true)
    private String email;

    @Size(min = 6, max = 20, message = "Contact number is Invalid")
    private String contactNo;

    private String referralCode;

    @Column(columnDefinition = "float default 0.0")
    private Float wallet;

    @Column(columnDefinition = "boolean default true")
    private Boolean active;

    @NotNull(message = "Password cannot be null")
    @NotBlank(message = "Password cannot be blank")
    // @Size(min = 8, message = "Password needs to be more stronger")
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(columnDefinition = "float default 0.0")
    private Float discount;

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
            this.active = true;
        }
        if (this.discount == null) {
            this.discount = 0f;
        }
        if (this.wallet == null) {
            this.wallet = 0f;
        }
    }

    public Customer(final BasicCustomerDetailForm customerDetail) {
        this.name = customerDetail.getName();
        this.email = customerDetail.getEmail();
        this.contactNo = customerDetail.getPhone();
        this.password = customerDetail.getPassword();
    }

}
