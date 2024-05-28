package com.example.backend_uppgift.Services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

    @Autowired
    JavaMailSender javaMailSender;

    public void sendSimpleEmail(String to, String subject, String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("jakob@example.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        javaMailSender.send(message);
    }

}
