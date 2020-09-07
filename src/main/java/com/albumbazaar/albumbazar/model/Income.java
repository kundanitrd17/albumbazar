package com.albumbazaar.albumbazar.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(
        uniqueConstraints =
                @UniqueConstraint(columnNames = {"order_id", "received_time"})
)
public class Income {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "order_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private OrderDetail order;
    @Column(name = "received_time")
    private Date receivedTime;
    private Float amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderDetail getOrder() {
        return order;
    }

    public void setOrder(OrderDetail order) {
        this.order = order;
    }

    public Date getReceivedTime() {
        return receivedTime;
    }

    public void setReceivedTime(Date receivedTime) {
        this.receivedTime = receivedTime;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Income{" +
                "id=" + id +
                ", order=" + order +
                ", receivedTime=" + receivedTime +
                ", amount=" + amount +
                '}';
    }
}
