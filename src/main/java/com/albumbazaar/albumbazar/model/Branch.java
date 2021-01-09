package com.albumbazaar.albumbazar.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.albumbazaar.albumbazar.form.BasicBranchInfoForm;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "branch", uniqueConstraints = { @UniqueConstraint(columnNames = "name"),
        @UniqueConstraint(columnNames = "contact_no"), @UniqueConstraint(columnNames = "address_id") })
@JsonIgnoreProperties(value = { "admin", "address" }, ignoreUnknown = true)
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, updatable = false)
    private Long id;
    private String name;
    @Column(name = "contact_no")
    private String contactNo;
    private Date date;
    private boolean active = true;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private AddressEntity address;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Employee admin;

    public Branch(final BasicBranchInfoForm branchDetail) {
        this.name = branchDetail.getName();
        this.contactNo = branchDetail.getPhone();
        this.date = null;
        this.active = true;
    }

    public Branch() {
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

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

   
}
