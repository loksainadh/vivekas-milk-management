package com.milk.vivekas.enums;

public enum MilkType {
    COW("Cow Milk"),
    BUFFALO("Buffalo Milk"),
    GOAT("Goat Milk");

    private final String description;

    MilkType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
