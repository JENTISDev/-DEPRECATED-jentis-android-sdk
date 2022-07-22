package com.jentis.tracking.model;

import androidx.annotation.RestrictTo;

import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

@RestrictTo(RestrictTo.Scope.LIBRARY)
public class Aggr {
    public Aggr() {

    }

    public JsonObject toJSON() {
        JsonObject jsonObject= new JsonObject();
        return jsonObject;
    }
}
