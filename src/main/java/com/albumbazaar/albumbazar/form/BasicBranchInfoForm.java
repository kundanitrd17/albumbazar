package com.albumbazaar.albumbazar.form;

import java.util.Date;

public class BasicBranchInfoForm {
    private String code;
    private String name;
    private String phone;
    private String email;
    private String admin;
    private String inaugrationDate;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getInaugrationDate() {
        return inaugrationDate;
    }

    public void setInaugrationDate(String inaugrationDate) {
        this.inaugrationDate = inaugrationDate;
    }

    @Override
    public String toString() {
        return "AddNewBranch [admin=" + admin + ", code=" + code + ", email=" + email + ", inaugrationDate="
                + inaugrationDate + ", name=" + name + ", phone=" + phone + "]";
    }

    
   
}
