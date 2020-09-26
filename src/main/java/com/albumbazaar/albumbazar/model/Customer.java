
package com.albumbazaar.albumbazar.model;

import com.albumbazaar.albumbazar.form.customer.BasicCustomerDetailForm;

import javax.persistence.*;
import javax.validation.constraints.Email;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Email
    private String email;
    private String contactNo;
    private String password;
    @OneToMany
    private List<Address1> address = new ArrayList<>();

    public Customer() {
    }

    public Customer(final BasicCustomerDetailForm customerDetail) {
        this.name = customerDetail.getName();
        this.email = customerDetail.getEmail();
        this.contactNo = customerDetail.getPhone();
        this.password = customerDetail.getPassword();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Address1> getAddress() {
        return address;
    }

    public void setAddress(Address1 address) {
        this.address.add(address);
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", name='" + name + '\'' + ", email='" + email + '\'' + ", contactNo='"
                + contactNo + '\'' + ", password='" + password + '\'' + ", address=" + address + '}';
    }
}
