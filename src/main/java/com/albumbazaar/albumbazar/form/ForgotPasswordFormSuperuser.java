package com.albumbazaar.albumbazar.form;

import com.albumbazaar.albumbazar.form.api.ForgotPasswordAPI;

public class ForgotPasswordFormSuperuser extends ForgotPasswordAPI {


    @Override
    public String toString() {
        return "ForgotPassword{" +
                "password1='" + password1 + '\'' +
                ", password2='" + password2 + '\'' +
                '}';
    }
}
