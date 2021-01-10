package com.albumbazaar.albumbazar.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    private Float coverPrice;

    private String image;

    private Float GST;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Association association;

    @Override
    public String toString() {
        return "Cover{" + "id=" + id + ", coverName='" + coverName + '\'' + ", coverSize='" + coverSize + '\''
                + ", coverPrice=" + coverPrice + '}';
    }

}
