package com.jentis.tracking.model;

public class DebugInformation {
    Boolean debugEnabled;
    String debugId;
    String version;

    public DebugInformation(Boolean debugEnabled) {
        this.debugEnabled = debugEnabled;
    }

    public DebugInformation(Boolean debugEnabled, String debugId, String version) {
        this.debugEnabled = debugEnabled;
        this.debugId = debugId;
        this.version = version;
    }
}
