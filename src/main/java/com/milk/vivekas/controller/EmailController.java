package com.milk.vivekas.controller;

import com.milk.vivekas.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    // Endpoint to send a test email or registration confirmation
    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestParam String to,
                                            @RequestParam String name) {
        try {
            emailService.sendRegistrationEmail(to, name);
            return ResponseEntity.ok("Email sent successfully to " + to);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Failed to send email: " + e.getMessage());
        }
    }
}
