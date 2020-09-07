package com.albumbazaar.albumbazar.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
public class AssociationContactPerson {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String designation;
    @Size(max = 15)
    private String contact1;
    @Size(max = 15)
    private String contact2;
    @Email
    private String email;
    private Boolean active=true;
    private String password;
    @ManyToOne
    @JoinColumn(
            name = "association_id"
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Association association;

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

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Association getAssociation() {
        return association;
    }

    public void setAssociation(Association association) {
        this.association = association;
    }
    @Override
    public String toString() {
        return "AssociationContactPerson{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", designation='" + designation + '\'' +
                ", contact1='" + contact1 + '\'' +
                ", contact2='" + contact2 + '\'' +
                ", email='" + email + '\'' +
                ", active=" + active +
                ", password='" + password + '\'' +
                ", association=" + association +
                '}';
    }
}
