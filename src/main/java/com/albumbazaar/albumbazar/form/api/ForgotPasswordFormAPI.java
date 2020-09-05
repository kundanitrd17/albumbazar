package com.albumbazaar.albumbazar.form.api;

public abstract class ForgotPasswordFormAPI {

    public String password1;
    public String password2;

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public boolean isValid() {
        return !(password1 == null || password2 == null) && !password1.isBlank() && this.password1.equals(this.password2);
    }
    
}
