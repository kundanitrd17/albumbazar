package com.albumbazaar.albumbazar.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerDTO {

    private Long id;

    private String name;

    @Email(message = "Email is Invalid")
    private String email;

    @Size(min = 5, max = 20, message = "Contact number is not valid")
    private String contactNo;

    private String referralCode;

    private Float wallet;

    private Boolean active;

    private Float discount;

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

    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    public Float getWallet() {
        return wallet;
    }

    public void setWallet(Float wallet) {
        this.wallet = wallet;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "CustomerDTO [active=" + active + ", contactNo=" + contactNo + ", discount=" + discount + ", email="
                + email + ", id=" + id + ", name=" + name + ", referralCode=" + referralCode + ", wallet=" + wallet
                + "]";
    }

}
