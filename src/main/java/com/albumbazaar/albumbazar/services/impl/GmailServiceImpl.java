package com.albumbazaar.albumbazar.services.impl;

import com.albumbazaar.albumbazar.services.MailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Qualifier("gmailService")
public class GmailServiceImpl implements MailService {

    private String COMPANY_EMAIL = "princewillz2013@gmail.com";

    private JavaMailSender mailSender;

    @Autowired
    protected GmailServiceImpl(final JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(String from, String to, String subject, String body) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(body);

        mailSender.send(mailMessage);

    }

    @Override
    public void sendEmail(final String to, String subject, String body) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(COMPANY_EMAIL);
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(body);

        mailSender.send(mailMessage);
    }

    @Override
    public String getCompanyEmail() {
        return this.COMPANY_EMAIL;
    }

}
