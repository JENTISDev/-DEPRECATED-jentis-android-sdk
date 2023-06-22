package com.jentis.tracking.sdk.model;

import com.google.gson.JsonObject;

public class Parent {
    String user;
    String session;

    public Parent() {
    }

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
        JsonObject jsonObject = new JsonObject();
        if (getUser() != null)
            jsonObject.addProperty("user", getUser());
        if (getSession() != null)
            jsonObject.addProperty("session", getSession());

        return jsonObject;
    }
}
