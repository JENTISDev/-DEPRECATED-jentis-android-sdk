package com.jentis.tracking.model;

import com.jentis.tracking.model.enums.Environment;

import org.json.JSONException;
import org.json.JSONObject;

public class TrackConfig {

    /// The trackDomain from your Jentis Account
    public  String trackDomain = null;

    /// The trackID from your Jentis Account
    public String trackID = null;

    /// The environment to track in (stage | live)
    public Environment environment = null;

    public TrackConfig(String trackDomain, String trackID, Environment environment) {
        this.trackDomain = trackDomain;
        this.trackID = trackID;
        this.environment = environment;
    }
}
