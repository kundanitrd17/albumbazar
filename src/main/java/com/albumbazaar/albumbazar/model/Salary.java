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
}
