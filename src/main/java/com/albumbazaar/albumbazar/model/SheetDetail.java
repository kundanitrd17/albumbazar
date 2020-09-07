package com.albumbazaar.albumbazar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
        uniqueConstraints =
                @UniqueConstraint(columnNames = {"paper_index", "order_id"})
)
public class SheetDetail {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "paper_index")
    private Long paperIndex;
    private String paperQuality;
    private String paperPrice;
    @Column(name = "order_id")
    private Long orderId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPaperIndex() {
        return paperIndex;
    }

    public void setPaperIndex(Long paperIndex) {
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

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "SheetDetail [id=" + id + ", orderId=" + orderId + ", paperIndex=" + paperIndex + ", paperPrice="
                + paperPrice + ", paperQuality=" + paperQuality + "]";
    }

    
}
