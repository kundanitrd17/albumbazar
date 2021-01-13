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
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "paper_quality", "paper_size", "association_id" }))
@JsonIgnoreProperties(value = { "association" })
public class Paper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, updatable = false)
    private Long id;

    @NotNull
    @NotBlank
    @Column(name = "paper_quality")
    private String paperQuality;

    @NotNull
    @NotBlank
    @Column(name = "paper_size")
    private String paperSize;

    @NotNull
    @Column(name = "paper_price")
    private Float paperPrice;

    private Float GST;

    private String image;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "association_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Association association;

    @Override
    public String toString() {
        return "Paper{" + "id=" + id + ", paperQuality='" + paperQuality + '\'' + ", paperSize='" + paperSize + '\''
                + ", paperPrice=" + paperPrice + ", GST" + GST + '}';
    }
}
