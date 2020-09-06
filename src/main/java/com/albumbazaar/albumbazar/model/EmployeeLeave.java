package com.albumbazaar.albumbazar.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
    name = "employee_leave",
    uniqueConstraints =
        @UniqueConstraint(
                name = "employee_from",
                columnNames = {"employee_id", "from_date"}
        )
)
public class EmployeeLeave {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date from_date;
    private Date to_date;
    @Column(columnDefinition = "Text")
    private String reason;
    @Column(columnDefinition = "Text")
    private String description;
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(
        name = "employee_id", nullable = false
    )
    private Employee employee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFrom_date() {
        return from_date;
    }

    public void setFrom_date(Date from_date) {
        this.from_date = from_date;
    }

    public Date getTo_date() {
        return to_date;
    }

    public void setTo_date(Date to_date) {
        this.to_date = to_date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Leave [description=" + description + ", employee=" + employee + ", from_date=" + from_date + ", id="
                + id + ", reason=" + reason + ", to_date=" + to_date + "]";
    }

    
}
