package com.albumbazaar.albumbazar.form.customer;

import com.albumbazaar.albumbazar.form.api.BasicDetailForm;

public class BasicCustomerDetailForm extends BasicDetailForm {

    private String referralCode;

    @Override
    public String toString() {
        return "BasicCustomerDetailForm{" + "name='" + name + '\'' + ", gender='" + gender + '\'' + ", dateOfBirth='"
                + dateOfBirth + '\'' + ", monthOfBirth='" + monthOfBirth + '\'' + ", yearOfBirth='" + yearOfBirth + '\''
                + ", maritalStatus='" + maritalStatus + '\'' + ", username='" + username + '\'' + ", rePassword='"
                + rePassword + '\'' + ", password='" + password + '\'' + ", email='" + email + '\'' + ", phone='"
                + phone + '\'' + '}';
    }

    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }
}
