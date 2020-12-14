package com.albumbazaar.albumbazar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "paper_index", "order_id" }))
public class SheetDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, updatable = false)
    private Long id;

    @Column(name = "paper_index")
    private Integer paperIndex;

    private String paperQuality;

    private String paperPrice;

    public SheetDetail(Integer paperIndex, String paperQuality, String paperPrice) {
        System.out.println("sheet1");
        this.paperIndex = paperIndex;
        System.out.println("sheet1");
        this.paperQuality = paperQuality;
        System.out.println("sheet1");
        this.paperPrice = paperPrice;
    }

    public SheetDetail() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPaperIndex() {
        return paperIndex;
    }

    public void setPaperIndex(Integer paperIndex) {
        this.paperIndex = paperIndex;
    }

    public String getPaperQuality() {
        return paperQuality;
    }

    public void setPaperQuality(String paperQuality) {
        this.paperQuality = paperQuality;
    }

    public String getPaperPrice() {
        return paperPrice;
    }

    public void setPaperPrice(String paperPrice) {
        this.paperPrice = paperPrice;
    }

    @Override
    public String toString() {
        return "SheetDetail [id=" + id + ", paperIndex=" + paperIndex + ", paperPrice=" + paperPrice + ", paperQuality="
                + paperQuality + "]";
    }

}
