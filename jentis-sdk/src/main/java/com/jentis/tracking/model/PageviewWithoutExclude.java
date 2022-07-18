package com.jentis.tracking.model;

import org.json.JSONException;
import org.json.JSONObject;

public class PageviewWithoutExclude {
    String fbEventID;

    public String getFbEventID() {
        return fbEventID;
    }

    public void setFbEventID(String fbEventID) {
        this.fbEventID = fbEventID;
    }

    public PageviewWithoutExclude() {}

    public PageviewWithoutExclude(String fbEventID) {
        setFbEventID(fbEventID);
    }

    public String toJSON() {
        JSONObject jsonObject= new JSONObject();
        try {
            jsonObject.put("fb_event_id", getFbEventID());

            return jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }
}
