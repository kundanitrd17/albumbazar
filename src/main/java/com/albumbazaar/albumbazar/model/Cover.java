package com.albumbazaar.albumbazar.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
public class Cover {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String coverName;
    private String coverSize;
    private Float coverPrice;
    @ManyToOne
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
        return "Cover{" +
                "id=" + id +
                ", coverName='" + coverName + '\'' +
                ", coverSize='" + coverSize + '\'' +
                ", coverPrice=" + coverPrice +
                ", association=" + association +
                '}';
    }
}
