package com.albumbazaar.albumbazar.form.employee;

import com.albumbazaar.albumbazar.form.api.BasicDetailForm;

public class BasicEmployeeDetailForm extends BasicDetailForm {
    private String branch;
    private String designation;
    private String salary;

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        super.toString();
        return "BasicEmployeeDetailForm [branch=" + branch + ", designation=" + designation + ", salary=" + salary
                + "]";
    }
    
    
}
