package com.jentis.tracking.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Cmd {
    String key = "";
    CmdDatum[] data = null;

    public String getKey() {
        return key;
    }

    public CmdDatum[] getData() {
        return data;
    }

    public Cmd() {}

    public Cmd(String key, CmdDatum[] data) {
        this.key = key;
        this.data = data;
    }

    public String toJSON() {
        JSONObject jsonObject= new JSONObject();
        try {
            jsonObject.put("key", getKey());
            jsonObject.put("data", getData());

            return jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }
}