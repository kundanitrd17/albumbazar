package com.albumbazaar.albumbazar.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Salary {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Employee employee;
    private Float amount;
    private Date fromDate;
    private Date toDate;
    private Integer totalWorkingDays;
    private Integer totalWorkedDays;
    private Date paymentDateTime;
    private String receiverSign;
    @OneToOne
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Expense expense;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Integer getTotalWorkingDays() {
        return totalWorkingDays;
    }

    public void setTotalWorkingDays(Integer totalWorkingDays) {
        this.totalWorkingDays = totalWorkingDays;
    }

    public Integer getTotalWorkedDays() {
        return totalWorkedDays;
    }

    public void setTotalWorkedDays(Integer totalWorkedDays) {
        this.totalWorkedDays = totalWorkedDays;
    }

    public Date getPaymentDateTime() {
        return paymentDateTime;
    }

    public void setPaymentDateTime(Date paymentDateTime) {
        this.paymentDateTime = paymentDateTime;
    }

    public String getReceiverSign() {
        return receiverSign;
    }

    public void setReceiverSign(String receiverSign) {
        this.receiverSign = receiverSign;
    }

    public Expense getExpense() {
        return expense;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }

    @Override
    public String toString() {
        return "Salary [amount=" + amount + ", employee=" + employee + ", expense=" + expense + ", fromDate=" + fromDate
                + ", id=" + id + ", paymentDateTime=" + paymentDateTime + ", receiverSign=" + receiverSign + ", toDate="
                + toDate + ", totalWorkedDays=" + totalWorkedDays + ", totalWorkingDays=" + totalWorkingDays + "]";
    }

    
}
