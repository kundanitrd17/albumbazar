package com.albumbazaar.albumbazar.services;

public interface SMSService {

    public String sendSMS(String message, String number);

    public String sendSMSAsync(String message, String number);

}
