package com.jentis.tracking.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Parent {
    String user;
    String session;

    public Parent(){}

    public Parent(String user, String session) {
        this.user = user;
        this.session = session;
    }

    public String getUser() {
        return user;
    }

    public String getSession() {
        return session;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String toJSON() {
        JSONObject jsonObject= new JSONObject();
        try {
            jsonObject.put("user", getUser());
            jsonObject.put("session", getSession());

            return jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }
}
