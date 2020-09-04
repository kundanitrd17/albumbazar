package com.albumbazaar.albumbazar.model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;
import java.util.Optional;

@Entity
@Table(
        name = "branch",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name"),
                @UniqueConstraint(columnNames = "contact_no")
        }
)
public class Branch {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(name = "contact_no")
    private String contactNo;
    @Nullable
    private Date date;
    private boolean active;

    @OneToOne(optional = true)
    @JoinColumn(
            name = "address_id", unique = true, nullable = true
    )
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Location location;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
