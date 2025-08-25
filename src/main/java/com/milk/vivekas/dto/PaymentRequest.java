package com.milk.vivekas.dto;

import java.time.LocalDate;

public class PaymentRequest {
    private Long userId;
    private double amount;
    private String method; // CASH, UPI, CARD, etc.
    private LocalDate date;
    // Getters & Setters
}
