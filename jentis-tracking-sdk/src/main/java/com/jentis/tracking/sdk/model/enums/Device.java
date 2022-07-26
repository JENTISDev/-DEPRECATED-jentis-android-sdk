package com.jentis.tracking.sdk.model.enums;

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