package com.jentis.tracking.model.enums;

import androidx.annotation.RestrictTo;

@RestrictTo(RestrictTo.Scope.LIBRARY)
public enum TrackEnum {
    CONSENT("consent", 0),
    PAGEVIEW("pageview", 1),
    SUBMIT("submit", 2);

    private String stringValue;
    private int intValue;
    private TrackEnum(String toString, int value) {
        stringValue = toString;
        intValue = value;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}