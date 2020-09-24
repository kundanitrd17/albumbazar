package com.albumbazaar.albumbazar.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.albumbazaar.albumbazar.form.association.AssociationDetailForm;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "association")
@JsonIgnoreProperties(value = { "address" })
public class Association {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Size(max = 15)
    private String contact1;
    @Size(max = 15)
    private String contact2;
    @Email
    private String email;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Address1 address;
    private String password;
    private Boolean active;

    public Association() {
    }

    public Association(final AssociationDetailForm associationDetail) {
        this.name = associationDetail.getName();
        this.contact1 = associationDetail.getContact1();
        this.contact2 = associationDetail.getContact2();
        this.email = associationDetail.getEmail();
        this.password = associationDetail.getPassword();
        this.active = true;
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

    public String getContact1() {
        return contact1;
    }

    public void setContact1(String contact1) {
        this.contact1 = contact1;
    }

    public String getContact2() {
        return contact2;
    }

    public void setContact2(String contact2) {
        this.contact2 = contact2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address1 getAddress() {
        return address;
    }

    public void setAddress(Address1 address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Association [active=" + active + ", contact1=" + contact1 + ", contact2=" + contact2 + ", email="
                + email + ", id=" + id + ", name=" + name + ", password=" + password + "]";
    }

}
