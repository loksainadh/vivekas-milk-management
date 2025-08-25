package com.milk.vivekas.dto;

import java.time.LocalDate;

public class MilkRecordRequest {
    private Long providerId;
    private Long userId;
    private double quantity;
    private double pricePerLiter;
    private String milkType; // "COW" / "BUFFALO" / "GOAT"
    private LocalDate date;
    // Getters & Setters
}
