package com.milk.vivekas.enums;

public enum PaymentMethod {
    CASH("Cash Payment"),
    UPI("UPI / Online Payment"),
    BANK_TRANSFER("Bank Transfer"),
    CARD("Debit / Credit Card");

    private final String description;

    PaymentMethod(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
