package com.jentis.tracking.model;

import org.json.JSONException;
import org.json.JSONObject;

public class TrackingDataDatum {
    String id;

    String action;
    String account;
    String documentType = null;
    Property property = new Property();
    System system = null;
    Aggr aggr = new Aggr();
    Parent parent  = new Parent();
    String pluginid = null;

    public TrackingDataDatum() {}

    public TrackingDataDatum(String id, String action, String account, String documentType, Property property, System system, Aggr aggr, Parent parent, String pluginid) {
        this.id = id;
        this.action = action;
        this.account = account;
        this.documentType = documentType;
        this.property = property; //?? Property()
        this.system = system;
        this.aggr = aggr; //?? Aggr()
        this.parent = parent; //?? Parent()
        this.pluginid = pluginid;
    }

    public String getId() {
        return id;
    }

    public String getAction() {
        return action;
    }

    public String getAccount() {
        return account;
    }

    public String getDocumentType() {
        return documentType;
    }

    public Property getProperty() {
        return property;
    }

    public System getSystem() {
        return system;
    }

    public Aggr getAggr() {
        return aggr;
    }

    public Parent getParent() {
        return parent;
    }

    public String getPluginid() {
        return pluginid;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public void setSystem(System system) {
        this.system = system;
    }

    public void setAggr(Aggr aggr) {
        this.aggr = aggr;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public void setPluginid(String pluginid) {
        this.pluginid = pluginid;
    }

    public String toJSON() {
        JSONObject jsonObject= new JSONObject();
        try {
            jsonObject.put("_id", getId());
            jsonObject.put("action", getAction());
            jsonObject.put("account", getAccount());
            jsonObject.put("documentType", getDocumentType());
            jsonObject.put("property", getProperty());
            jsonObject.put("system", getSystem());
            jsonObject.put("aggr", getAggr());
            jsonObject.put("parent", getParent());
            jsonObject.put("pluginid", getPluginid());

            return jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }
}
