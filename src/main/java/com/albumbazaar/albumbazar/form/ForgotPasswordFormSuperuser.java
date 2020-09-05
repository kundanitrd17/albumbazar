package com.albumbazaar.albumbazar.form;

import com.albumbazaar.albumbazar.form.api.ForgotPasswordFormAPI;

public class ForgotPasswordFormSuperuser extends ForgotPasswordFormAPI {


    @Override
    public String toString() {
        return "ForgotPassword{" +
                "password1='" + password1 + '\'' +
                ", password2='" + password2 + '\'' +
                '}';
    }
}
