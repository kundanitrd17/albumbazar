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
@JsonIgnoreProperties(value = { "association" }, ignoreUnknown = true)
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
    private Double paperPrice;

    @Column(name = "gst", columnDefinition = "double default 0.0")
    private Double GST;

    private String image;

    @Column(name = "active", columnDefinition = "boolean default true")
    private Boolean active;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "association_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Association association;

    @PrePersist
    void prePersist() {
        if (this.active == null) {
            this.active = true;
        }

        if (this.GST == null) {
            this.GST = 0.0;
        }
    }

    @Override
    public String toString() {
        return "Paper{" + "id=" + id + ", paperQuality='" + paperQuality + '\'' + ", paperSize='" + paperSize + '\''
                + ", paperPrice=" + paperPrice + ", GST" + GST + '}';
    }
}
