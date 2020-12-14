package com.albumbazaar.albumbazar.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "order_id", "customer_id" }))
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, updatable = false)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private OrderDetail orders;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Customer customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderDetail getOrders() {
        return orders;
    }

    public void setOrders(OrderDetail orders) {
        this.orders = orders;
    }

    public Customer getAddress() {
        return customer;
    }

    public void setAddress(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "CartItem{" + "id=" + id + ", orders=" + orders + ", address=" + customer + '}';
    }
}
