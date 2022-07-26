package com.jentis.tracking.sdk;

public class JentisTrackConfig {

    /// The trackDomain from your Jentis Account
    public  String trackDomain = null;

    /// The trackID from your Jentis Account
    public String trackID = null;

    /// The environment to track in (stage | live)
    public String environment = null;

    public JentisTrackConfig(String trackDomain, String trackID, String environment) {
        this.trackDomain = trackDomain;
        this.trackID = trackID;
        this.environment = environment;
    }
}
