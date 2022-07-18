package com.jentis.tracking.model;

import org.json.JSONException;
import org.json.JSONObject;

public class CmdDatum {
    String name, value = null;
    Integer exdays = 0;
    String[] pluginids = null;

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public Integer getExdays() {
        return exdays;
    }

    public String[] getPluginids() {
        return pluginids;
    }

    public CmdDatum() {}

    public CmdDatum(String name, String value, Integer exdays, String[] pluginids) {
        this.name = name;
        this.value = value;
        this.exdays = exdays;
        this.pluginids = pluginids;
    }

    public String toJSON() {
        JSONObject jsonObject= new JSONObject();
        try {
            jsonObject.put("name", getName());
            jsonObject.put("value", getValue());
            jsonObject.put("exdays", getExdays());
            jsonObject.put("pluginids", getPluginids());

            return jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }
}
