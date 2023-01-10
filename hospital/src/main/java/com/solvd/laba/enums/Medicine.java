package com.solvd.laba.enums;

public enum Medicine {
    IBUPROFEN("600mg", 8),
    LIDOCAINE("300mg", 10),
    DICLOFENAC("50mg", 20);

    private String dose;
    private int quantity;

    Medicine(String dose, int quantity) {
        this.dose = dose;
        this.quantity = quantity;
    }

    public String getDose() {
        return dose;
    }

    public int getQuantity() {
        return quantity;
    }
}
