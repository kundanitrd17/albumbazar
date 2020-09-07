package com.albumbazaar.albumbazar.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(
        uniqueConstraints =
                @UniqueConstraint(columnNames = {"added_time", "inventory_id"})
)
public class DeletedInventory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "added_time")
    private Date addedTime;
    @Column(columnDefinition = "TEXT")
    private String reason;
    @ManyToOne
    @JoinColumn(name = "inventory_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Inventory inventory;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getAddedTime() {
        return addedTime;
    }

    public void setAddedTime(Date addedTime) {
        this.addedTime = addedTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "DeletedInventory{" +
                "id=" + id +
                ", addedTime=" + addedTime +
                ", reason='" + reason + '\'' +
                ", inventory=" + inventory +
                '}';
    }
}
