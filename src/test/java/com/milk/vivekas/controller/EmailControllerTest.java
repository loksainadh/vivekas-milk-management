package com.milk.vivekas.controller;

import com.milk.vivekas.service.EmailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmailControllerTest {

    @Mock
    private EmailService emailService;

    @InjectMocks
    private EmailController emailController;

    @Test
    void sendEmail_success() {
        String to = "test@example.com";
        String name = "Alice";

        ResponseEntity<String> response = emailController.sendEmail(to, name);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Email sent successfully to " + to, response.getBody());
        verify(emailService).sendRegistrationEmail(to, name);
    }

    @Test
    void sendEmail_failure() {
        String to = "test@example.com";
        String name = "Bob";

        doThrow(new RuntimeException("SMTP error")).when(emailService).sendRegistrationEmail(to, name);

        ResponseEntity<String> response = emailController.sendEmail(to, name);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertTrue(response.getBody().contains("Failed to send email: SMTP error"));
        verify(emailService).sendRegistrationEmail(to, name);
    }
}