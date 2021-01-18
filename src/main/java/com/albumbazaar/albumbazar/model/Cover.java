package com.albumbazaar.albumbazar.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Getter
@Setter
@Entity
@JsonIgnoreProperties(value = { "association" }, ignoreUnknown = true)
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
    private Double coverPrice;

    private String image;

    @Column(columnDefinition = "double default 0.0")
    private Double GST;

    @Column(name = "active", columnDefinition = "boolean default true")
    private Boolean active;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Association association;

    @PrePersist
    void prePersist() {
        if (this.active == null) {
            this.active = true;
        }

    }

    @Override
    public String toString() {
        return "Cover{" + "id=" + id + ", coverName='" + coverName + '\'' + ", coverSize='" + coverSize + '\''
                + ", coverPrice=" + coverPrice + '}';
    }

}
