package com.jentis.analytics.model;

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

    public Boolean getDebugEnabled() {
        return debugEnabled;
    }

    public void setDebugEnabled(Boolean debugEnabled) {
        this.debugEnabled = debugEnabled;
    }

    public String getDebugId() {
        return debugId;
    }

    public void setDebugId(String debugId) {
        this.debugId = debugId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
