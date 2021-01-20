package com.albumbazaar.albumbazar.services;

public interface MailService {

    void sendEmail(String from, String to, String subject, String body);

    void sendEmail(final String to, String subject, String body);

    String getCompanyEmail();

}
