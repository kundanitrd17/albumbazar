package com.albumbazaar.albumbazar.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(value = { "association" })
public class Cover {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, updatable = false)
    private Long id;

    @NotNull
    @NotBlank
    private String coverName;

    @NotNull
    @NotBlank
    private String coverSize;

    @NotNull
    private Float coverPrice;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Association association;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCoverName() {
        return coverName;
    }

    public void setCoverName(String coverName) {
        this.coverName = coverName;
    }

    public String getCoverSize() {
        return coverSize;
    }

    public void setCoverSize(String coverSize) {
        this.coverSize = coverSize;
    }

    public Float getCoverPrice() {
        return coverPrice;
    }

    public void setCoverPrice(Float coverPrice) {
        this.coverPrice = coverPrice;
    }

    public Association getAssociation() {
        return association;
    }

    public void setAssociation(Association association) {
        this.association = association;
    }

    @Override
    public String toString() {
        return "Cover{" + "id=" + id + ", coverName='" + coverName + '\'' + ", coverSize='" + coverSize + '\''
                + ", coverPrice=" + coverPrice + '}';
    }
}
