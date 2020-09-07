package com.albumbazaar.albumbazar.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(
        uniqueConstraints =
                @UniqueConstraint(columnNames = {"employee_id", "added_time"})
)
public class Inventory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itemName;
    private Float price;
    private String itemNo;
    private Integer noOfItems;
    @Column(name = "added_time", nullable = false)
    private Date addedTime;
    @ManyToOne
    @JoinColumn(name = "branch_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Branch branch;
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Employee employee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public Integer getNoOfItems() {
        return noOfItems;
    }

    public void setNoOfItems(Integer noOfItems) {
        this.noOfItems = noOfItems;
    }

    public Date getAddedTime() {
        return addedTime;
    }

    public void setAddedTime(Date addedTime) {
        this.addedTime = addedTime;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", price=" + price +
                ", itemNo='" + itemNo + '\'' +
                ", noOfItems=" + noOfItems +
                ", addedTime=" + addedTime +
                ", branch=" + branch +
                ", employee=" + employee +
                '}';
    }
}
