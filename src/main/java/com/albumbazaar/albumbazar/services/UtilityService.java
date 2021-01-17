package com.albumbazaar.albumbazar.services;

import com.albumbazaar.albumbazar.dto.ResetPasswordDTO;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UtilityService {

    void resetCustomerPassword(ResetPasswordDTO resetPasswordDTO) throws UsernameNotFoundException;

    void sendOTPToCustomer(String userIdentificationkey);

    void sendOTPToEmployee(String userIdentificationkey);

    public void resetEmployeePassword(ResetPasswordDTO resetPasswordDTO) throws UsernameNotFoundException;

}
