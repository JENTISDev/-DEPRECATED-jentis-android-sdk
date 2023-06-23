package com.jentis.tracking.sdk.model;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

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

    public JsonObject toJSON() {
        JsonObject jsonObject= new JsonObject();
        JsonArray dataArray = new JsonArray();

        if(data != null){
            for (TrackingDataDatum trackData: data) {
                dataArray.add(trackData.toJSON());
            }
        }
        if(client != null) {
            jsonObject.add("client", client.toJSON());
        }

        if(cmd != null) {
            jsonObject.add("cmd", cmd.toJSON());
        }
        jsonObject.add("data", dataArray);

        return jsonObject;
    }
}
