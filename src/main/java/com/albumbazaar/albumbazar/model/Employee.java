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
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.albumbazaar.albumbazar.form.employee.BasicEmployeeDetailForm;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "employee", uniqueConstraints = { @UniqueConstraint(columnNames = { "email" }),
        @UniqueConstraint(columnNames = { "pan" }), @UniqueConstraint(columnNames = { "voter" }),
        @UniqueConstraint(columnNames = { "aadhaar" }), @UniqueConstraint(columnNames = { "personal_contact" }) })
@JsonIgnoreProperties(value = { "address" }, ignoreUnknown = true)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, updatable = false)
    private Long id;

    @Size(max = 100)
    private String name;

    @Size(max = 100)
    private String father_name;

    private String experience;

    private Date date_of_birth;

    private String qualification;

    private Float salary;

    @Size(max = 15)
    private String home_contact;

    private Date joining_date;

    private Date leaving_date;

    @Column(columnDefinition = "boolean default true")
    private Boolean active = true;

    private String profile_pic;

    @Size(max = 15)
    private String personal_contact;

    @Size(max = 50)
    private String email;

    @Size(max = 20)
    private String pan;

    @Size(max = 20)
    private String aadhaar;

    @Size(max = 20)
    private String voter;

    @Size(max = 20)
    private String religion;

    @Size(max = 40)
    private String role;

    @NotBlank
    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Branch branch;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Address1 address;

    @PrePersist
    void prePersist() {
        if (this.active == null) {
            this.active = true;
        }

    }

    public Employee() {
    }

    public Employee(final BasicEmployeeDetailForm employeeDetail) {
        this.name = employeeDetail.getName();
        this.email = employeeDetail.getEmail();
        this.active = true;
        this.date_of_birth = null; // employeeDetail.getDateOfBirth();
        this.personal_contact = employeeDetail.getPhone();
        this.salary = Float.parseFloat(employeeDetail.getSalary());
        this.role = employeeDetail.getDesignation();
        this.password = "password";
    }

    @Override
    public String toString() {
        return "Employee [aadhaar=" + aadhaar + ", active=" + active + ", date_of_birth=" + date_of_birth + ", email="
                + email + ", experience=" + experience + ", father_name=" + father_name + ", home_contact="
                + home_contact + ", id=" + id + ", joining_date=" + joining_date + ", leaving_date=" + leaving_date
                + ", name=" + name + ", pan=" + pan + ", password=" + password + ", personal_contact="
                + personal_contact + ", profile_pic=" + profile_pic + ", qualification=" + qualification + ", religion="
                + religion + ", role=" + role + ", salary=" + salary + ", voter=" + voter + "]";
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

    public String getFather_name() {
        return father_name;
    }

    public void setFather_name(String father_name) {
        this.father_name = father_name;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public String getHome_contact() {
        return home_contact;
    }

    public void setHome_contact(String home_contact) {
        this.home_contact = home_contact;
    }

    public Date getJoining_date() {
        return joining_date;
    }

    public void setJoining_date(Date joining_date) {
        this.joining_date = joining_date;
    }

    public Date getLeaving_date() {
        return leaving_date;
    }

    public void setLeaving_date(Date leaving_date) {
        this.leaving_date = leaving_date;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getPersonal_contact() {
        return personal_contact;
    }

    public void setPersonal_contact(String personal_contact) {
        this.personal_contact = personal_contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getAadhaar() {
        return aadhaar;
    }

    public void setAadhaar(String aadhaar) {
        this.aadhaar = aadhaar;
    }

    public String getVoter() {
        return voter;
    }

    public void setVoter(String voter) {
        this.voter = voter;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Address1 getAddress() {
        return address;
    }

    public void setAddress(Address1 address) {
        this.address = address;
    }

}
