package com.albumbazaar.albumbazar.model;

import javax.annotation.PostConstruct;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.albumbazaar.albumbazar.form.association.AssociationDetailForm;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.PrePersist;

import lombok.Setter;
import lombok.Getter;

@Setter
@Getter
@Entity
@Table(name = "association")
@JsonIgnoreProperties(value = { "address" }, ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Association {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, updatable = false)
    private Long id;
    private String name;
    @Size(max = 15)
    private String contact1;
    @Size(max = 15)
    private String contact2;
    @Email
    private String email;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private Boolean active;

    @PrePersist
    void prePersist() {
        if (this.active == null) {
            this.active = true;
        }

    }

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

    @Override
    public String toString() {
        return "Association [active=" + active + ", contact1=" + contact1 + ", contact2=" + contact2 + ", email="
                + email + ", id=" + id + ", name=" + name + ", password=" + password + "]";
    }

}
