package com.milk.vivekas.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private EmailService emailService;

    @Test
    void sendRegistrationEmail_sendsEmail() {
        String to = "user@example.com";
        String userName = "Alice";

        emailService.sendRegistrationEmail(to, userName);

        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mailSender).send(captor.capture());

        SimpleMailMessage sent = captor.getValue();
        assertNotNull(sent);
        assertArrayEquals(new String[]{to}, sent.getTo());
        assertEquals("Welcome to Vivekas!", sent.getSubject());
        assertTrue(sent.getText().contains("Hello " + userName));
        assertTrue(sent.getText().contains("Thank you for registering at Vivekas."));
    }
}