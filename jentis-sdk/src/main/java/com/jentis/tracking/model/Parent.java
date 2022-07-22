package com.jentis.tracking.model;

import androidx.annotation.RestrictTo;

import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

@RestrictTo(RestrictTo.Scope.LIBRARY)
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

    public JsonObject toJSON() {
        JsonObject jsonObject= new JsonObject();
        jsonObject.addProperty("user", getUser());
        jsonObject.addProperty("session", getSession());

        return jsonObject;
    }
}
