package com.milk.vivekas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendRegistrationEmail(String toEmail, String userName) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Welcome to Vivekas!");
        message.setText("Hello " + userName + ",\n\nThank you for registering at Vivekas. We're glad to have you!\n\nRegards,\nVivekas Team");

        mailSender.send(message);
        System.out.println("Email Sent");


    }
}
