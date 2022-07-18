package com.jentis.tracking.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JentisData {
    Client client;
    Cmd cmd = new Cmd();
    ArrayList<TrackingDataDatum> data = new ArrayList<>();

    public JentisData() { }
    public JentisData(Client client, Cmd cmd, ArrayList<TrackingDataDatum> data) {
        this.client = client;
        if(data != null) {
            this.cmd = cmd;
        } else {
            this.cmd = new Cmd();
        }

        if(data != null) {
            this.data = data;
        } else {
            data = new ArrayList<TrackingDataDatum>();
        }
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setCmd(Cmd cmd) {
        this.cmd = cmd;
    }

    public void setData(ArrayList<TrackingDataDatum> data) {
        this.data = data;
    }

    public Client getClient() {
        return client;
    }

    public Cmd getCmd() {
        return cmd;
    }

    public ArrayList<TrackingDataDatum> getData() {
        return data;
    }

    public String toJSON() {
        JSONObject jsonObject= new JSONObject();
        try {
            jsonObject.put("client", getClient());
            jsonObject.put("cmd", getCmd());
            jsonObject.put("data", getData());

            return jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }
}
