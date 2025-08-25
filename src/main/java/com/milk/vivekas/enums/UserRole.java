package com.milk.vivekas.enums;

public enum UserRole {
    ADMIN("System Administrator"),
    CUSTOMER("Milk Customer"),
    PROVIDER("Milk Provider");

    private final String description;

    UserRole(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
