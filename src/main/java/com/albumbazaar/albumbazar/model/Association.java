package com.albumbazaar.albumbazar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.albumbazaar.albumbazar.form.association.AssociationDetailForm;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import com.albumbazaar.albumbazar.controller.FileUploadController;

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

    @NotNull
    @NotBlank
    @Column(unique = true, nullable = false)
    private String name;

    @NotNull
    @Size(max = 15)
    @Column(unique = true, nullable = false)
    private String contact1;

    @Size(max = 15)
    private String contact2;

    @Email
    @Column(unique = true)
    private String email;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    @NotNull
    @NotBlank
    @Size(min = 8, message = "Password needs to be more stronger")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false)
    private String password;

    @Column(columnDefinition = "boolean default true")
    private Boolean active;

    private String profilePhoto;

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

    public String getProfilePhoto() {
        if (profilePhoto == null || profilePhoto.isBlank())
            return null;

        return MvcUriComponentsBuilder.fromMethodName(FileUploadController.class, "serveFile", profilePhoto).build()
                .toUri().toString();
    }

}
