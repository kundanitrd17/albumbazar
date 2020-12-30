package com.albumbazaar.albumbazar.controller;

import com.albumbazaar.albumbazar.services.SMSService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SMSController {

    @Autowired
    private SMSService messageService;

    @GetMapping("/sms")
    @ResponseBody
    public String sendSms() {
        return messageService.sendSMS("Hii Man", "918918930270");

    }

}
