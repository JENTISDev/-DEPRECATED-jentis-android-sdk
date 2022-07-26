package com.jentis.analytics.model.enums;


public enum JentisError {
    SET_CONSENT_ERROR("setConsentError", 0),
    REQUEST_ERROR("requestError", 1);

    private String stringValue;
    private int intValue;
    private JentisError(String toString, int value) {
        stringValue = toString;
        intValue = value;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}