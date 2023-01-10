package com.solvd.laba.interfaces;

import com.solvd.laba.enums.Medicine;

public interface IGivePrescription {
    static String getPrescription(Medicine m, int everyXHours) {
        return m + m.getDose() + " x" + m.getQuantity() + ", every " + everyXHours + " hour/s.";
    }
}
