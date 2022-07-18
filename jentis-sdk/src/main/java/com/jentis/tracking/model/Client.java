package com.jentis.tracking.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Client {
    Long clientTimestamp;
    String domain = "";

    public Client() {}

    public Client(Long clientTimestamp, String domain) {
        this.clientTimestamp = clientTimestamp;
        this.domain = domain;
    }

    public Long getClientTimestamp() {
        return clientTimestamp;
    }

    public String getDomain() {
        return domain;
    }

    public void setClientTimestamp(Long clientTimestamp) {
        this.clientTimestamp = clientTimestamp;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String toJSON() {
        JSONObject jsonObject= new JSONObject();
        try {
            jsonObject.put("clientTimestamp", getClientTimestamp());
            jsonObject.put("domain", getDomain());

            return jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }
}
