package com.albumbazaar.albumbazar.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "paper_quality", "paper_quality", "association_id" }))
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

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "association_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Association association;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaperQuality() {
        return paperQuality;
    }

    public void setPaperQuality(String paperQuality) {
        this.paperQuality = paperQuality;
    }

    public String getPaperSize() {
        return paperSize;
    }

    public void setPaperSize(String paperSize) {
        this.paperSize = paperSize;
    }

    public Float getPaperPrice() {
        return paperPrice;
    }

    public void setPaperPrice(Float paperPrice) {
        this.paperPrice = paperPrice;
    }

    public Association getAssociation() {
        return association;
    }

    public void setAssociation(Association association) {
        this.association = association;
    }

    @Override
    public String toString() {
        return "Paper{" + "id=" + id + ", paperQuality='" + paperQuality + '\'' + ", paperSize='" + paperSize + '\''
                + ", paperPrice=" + paperPrice + '}';
    }
}
