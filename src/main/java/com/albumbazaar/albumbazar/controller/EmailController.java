package com.albumbazaar.albumbazar.controller;

import javax.mail.internet.MimeMessage;

import com.albumbazaar.albumbazar.services.MailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class EmailController {

    private JavaMailSender mailSender;
    @Autowired
    @Qualifier("gmailService")
    private MailService gmailService;

    private String from = "nishiganu22@gmail.com";
    private String to = "princewillz2013@gmail.com";

    @GetMapping("/email")
    public String sendEmail() {

        return "sendemail";
    }

    @PostMapping("/email")
    @ResponseBody
    public String sendEmailMsg() {

        gmailService.sendEmail(from, to, "subject test", "body of the message");

        return "sent";
    }

    @PostMapping("/email-rem")
    @ResponseBody
    public String sendEmailPost(@RequestParam("file") MultipartFile file) {
        System.out.println(file);
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        System.out.println(filename);

        // String toaddress = this.to;
        // String fromaddress = this.from;

        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(this.from);
            helper.setTo(this.to);
            helper.setSubject("subject");
            // FileSystemResource f = new FileSystemResource("");
            helper.addAttachment(filename, new ByteArrayResource(file.getBytes()));
        } catch (Exception e) {
            System.out.println("oops..., " + e);
        }
        mailSender.send(message);

        return "sent";
    }

}
