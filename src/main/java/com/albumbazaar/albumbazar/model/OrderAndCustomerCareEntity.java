package com.albumbazaar.albumbazar.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "order_associated_customer_care", uniqueConstraints = { @UniqueConstraint(columnNames = { "order_id" }) })
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderAndCustomerCareEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, updatable = false)
    private Long id;

    @NotNull(message = "There should be a customer care to accept a order")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_care_employee")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Employee customerCareEmployee;

    @NotNull(message = "Order is absent")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private OrderDetail order;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date acceptedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getAcceptedAt() {
        return acceptedAt;
    }

    public void setAcceptedAt(Date acceptedAt) {
        this.acceptedAt = acceptedAt;
    }

    public Employee getCustomerCareEmployee() {
        return customerCareEmployee;
    }

    public void setCustomerCareEmployee(Employee customerCareEmployee) {
        this.customerCareEmployee = customerCareEmployee;
    }

    public OrderDetail getOrder() {
        return order;
    }

    public void setOrder(OrderDetail order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderAndCustomerCareEntity [acceptedAt=" + acceptedAt + ", customerCareEmployee="
                + customerCareEmployee.getName() + "customer-care: " + customerCareEmployee.getEmail() + ", id=" + id
                + ", order=" + order.getId() + "]";
    }

}
