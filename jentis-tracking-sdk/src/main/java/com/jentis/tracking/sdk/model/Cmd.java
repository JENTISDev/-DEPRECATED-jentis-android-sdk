package com.jentis.tracking.sdk.model;

import com.google.gson.JsonObject;

public class Cmd {
    String key;
    CmdDatum[] data;

    public String getKey() {
        return key;
    }

    public CmdDatum[] getData() {
        return data;
    }

    public Cmd() {
    }

    public Cmd(String key, CmdDatum[] data) {
        this.key = key;
        this.data = data;
    }

    public JsonObject toJSON() {
        JsonObject jsonObject = new JsonObject();
        if (getKey() != null)
            jsonObject.addProperty("key", getKey());
        if (data != null) {
            jsonObject.addProperty("data", data.toString());
        }

        return jsonObject;
    }
}
