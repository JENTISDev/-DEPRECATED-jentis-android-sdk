package com.jentis.tracking.model.enums;

public enum Environment {
    LIVE("live", 0),
    STAGING("staging", 1);

    private String stringValue;
    private int intValue;
    private Environment(String toString, int value) {
        stringValue = toString;
        intValue = value;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}