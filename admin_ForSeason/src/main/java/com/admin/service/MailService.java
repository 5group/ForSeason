package com.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMailToMultipleRecipients(List<String> toEmails, String subject, String textMessage) {
        for (String toEmail : toEmails) {
            SimpleMailMessage simpleMessage = new SimpleMailMessage();
            // set recipient email address
            simpleMessage.setTo(toEmail);
            // set subject of the email
            simpleMessage.setSubject(subject);
            // set text content of the email
            simpleMessage.setText(textMessage);
            // send the email
            javaMailSender.send(simpleMessage);
        }
    }
}