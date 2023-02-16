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
            simpleMessage.setTo(toEmail);
            simpleMessage.setSubject(subject);
            simpleMessage.setText(textMessage);
            javaMailSender.send(simpleMessage);
        }
    }
}