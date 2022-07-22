package com.jentis.tracking.model.enums;

import androidx.annotation.RestrictTo;

@RestrictTo(RestrictTo.Scope.LIBRARY)
public enum DocumentType {
    USER("user", 0),
    SESSION("session", 1),
    EVENT("event", 2),
    CONSENT("consent", 3);

    private String stringValue;
    private int intValue;
    private DocumentType(String toString, int value) {
        stringValue = toString;
        intValue = value;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}