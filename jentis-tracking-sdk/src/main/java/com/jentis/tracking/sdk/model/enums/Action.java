package com.jentis.tracking.sdk.model.enums;

public enum Action {
    UPD("upd", 0),
    NEW("new", 1);

    private String stringValue;
    private int intValue;
    private Action(String toString, int value) {
        stringValue = toString;
        intValue = value;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}
