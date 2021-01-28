package com.albumbazaar.albumbazar.services;

import com.albumbazaar.albumbazar.form.api.ForgotPasswordFormAPI;
import com.albumbazaar.albumbazar.model.WebsiteGeneralInfoEntity;

import org.springframework.ui.ModelMap;

public interface SuperuserService {

    public abstract ModelMap resetPassword(ForgotPasswordFormAPI FormData);

    public void updateGlobalDiscount(double discount);

    public void updateReferallAmount(double referralAmount);

    public WebsiteGeneralInfoEntity getWebsiteInfoEntity();

}
