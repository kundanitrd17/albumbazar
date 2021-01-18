package com.albumbazaar.albumbazar.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, updatable = false)
    private Long id;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Employee employee;
    private Double amount;
    private Date fromDate;
    private Date toDate;
    private Integer totalWorkingDays;
    private Integer totalWorkedDays;
    private Date paymentDateTime;
    private String receiverSign;
    @OneToOne
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Expense expense;

}
