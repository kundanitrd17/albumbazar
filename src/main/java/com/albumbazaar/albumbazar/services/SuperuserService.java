package com.albumbazaar.albumbazar.services;

import com.albumbazaar.albumbazar.form.api.ForgotPasswordFormAPI;

import org.springframework.ui.ModelMap;

public interface SuperuserService {

    public abstract ModelMap resetPassword(ForgotPasswordFormAPI FormData);

}
