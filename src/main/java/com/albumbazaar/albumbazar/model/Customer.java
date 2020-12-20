package com.albumbazaar.albumbazar.model;

import java.util.ArrayList;
import java.util.List;
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

    @Size(min = 8)
    private String referralCode;

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
    private List<Address1> address = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Set<OrderDetail> allOrders;

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

    public Customer() {
    }

    public Customer(final BasicCustomerDetailForm customerDetail) {
        this.name = customerDetail.getName();
        this.email = customerDetail.getEmail();
        this.contactNo = customerDetail.getPhone();
        this.password = customerDetail.getPassword();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Address1> getAddress() {
        return address;
    }

    public void setAddress(Address1 address) {
        this.address.add(address);
    }

    @Override
    public String toString() {
        return "Customer [contactNo=" + contactNo + "referralCode=" + referralCode + ", discount=" + discount
                + ", email=" + email + ", id=" + id + ", name=" + name + ", active=" + active + ", password=" + password
                + "]";
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    public Set<OrderDetail> getAllOrders() {
        return allOrders;
    }

    public void setAllOrders(Set<OrderDetail> allOrders) {
        this.allOrders = allOrders;
    }

    public Float getWallet() {
        return wallet;
    }

    public void setWallet(Float wallet) {
        this.wallet = wallet;
    }

}
