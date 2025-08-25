package com.milk.vivekas.enums;

public enum PaymentStatus {
    PENDING("Payment Pending"),
    PAID("Payment Completed"),
    CANCELLED("Payment Cancelled"),
    FAILED("Payment Failed");

    private final String description;

    PaymentStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
