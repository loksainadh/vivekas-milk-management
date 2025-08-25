package com.milk.vivekas.controller;

import com.milk.vivekas.enums.PaymentStatus;
import com.milk.vivekas.model.Payment;
import com.milk.vivekas.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService svc;

    public PaymentController(PaymentService svc) { this.svc = svc; }

    @PostMapping("/user/{userId}")
    public ResponseEntity<Payment> create(@PathVariable Long userId, @RequestBody Payment p) {
        return ResponseEntity.ok(svc.create(userId, p));
    }

    @GetMapping("/user/{userId}")
    public List<Payment> byUserStatus(@PathVariable Long userId, @RequestParam PaymentStatus status) {
        return svc.byUserAndStatus(userId, status);
    }
}
