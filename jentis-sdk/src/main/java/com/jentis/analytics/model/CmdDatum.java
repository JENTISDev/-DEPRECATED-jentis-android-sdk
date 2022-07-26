package com.jentis.analytics.model;

import com.google.gson.JsonObject;
import com.jentis.analytics.JentisUtils;

import java.util.ArrayList;


public class CmdDatum {
    String name, value;
    Integer exdays;
    ArrayList<String> pluginids;

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public Integer getExdays() {
        return exdays;
    }

    public ArrayList<String> getPluginids() {
        return pluginids;
    }

    public CmdDatum() {}

    public CmdDatum(String name, String value, Integer exdays, ArrayList<String> pluginids) {
        this.name = name;
        this.value = value;
        this.exdays = exdays;
        this.pluginids = pluginids;
    }

    public String toJSON() {
        JsonObject jsonObject= new JsonObject();
        jsonObject.addProperty("name", getName());
        jsonObject.addProperty("value", getValue());
        jsonObject.addProperty("exdays", getExdays());
        if(pluginids != null) {
            jsonObject.add("pluginids", JentisUtils.arrayToJsonArray(pluginids));
        }

        return jsonObject.toString();
    }
}
