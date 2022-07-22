package com.jentis.tracking.model;

import androidx.annotation.RestrictTo;

@RestrictTo(RestrictTo.Scope.LIBRARY)
public enum Device {
    OS("os", 0),
    BRAND("brand", 1);

    private String stringValue;
    private int intValue;
    private Device(String toString, int value) {
        stringValue = toString;
        intValue = value;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}