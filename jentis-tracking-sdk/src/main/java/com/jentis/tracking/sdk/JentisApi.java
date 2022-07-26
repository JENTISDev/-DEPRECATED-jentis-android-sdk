package com.jentis.tracking.sdk;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jentis.tracking.sdk.model.JentisData;
import com.jentis.tracking.sdk.model.JentisLogger;
import com.jentis.tracking.sdk.model.interfaces.ResultHandler;

class JentisApi {
    private static JentisApi INSTANCE = null;
    private String baseUrl;
    
    private JentisApi() {};

    private final String TAG = "JENTIS-API";

    /**
     * create the singleton instance of the Api class
     * @return initialize Api instance
     */
    public static JentisApi getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new JentisApi();
        }
        return(INSTANCE);
    }

    /**
     * set the base url for further api calls
     */
    public void setup(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    /**
     * Creates the body for setConsent api call and uses submitTracking for the api call itself
     * @param trackingData: the object that will be transformed into Json and after that into string
     * @param resultHandler: response handler
     */
    public void setConsentSettings(JentisData trackingData, ResultHandler<Object> resultHandler) {
        try {
            JsonObject jsonData = trackingData.toJSON();
            submitTracking(jsonData, resultHandler);
        } catch (Exception e) {
            JentisLogger.getLogger(TAG).error(e);
        }
    }

    /**
     * Creates the body for submitTracking api call
     * @param data: the JsonObject that will be transformed into string
     * @param resultHandler: response handler
     */
    public void submitTracking(JsonObject data, ResultHandler<Object> resultHandler) {
        if(baseUrl == null){
            return;
        }

        String jsonString = new Gson().toJson(data);
        JentisNetworking sendPostReqAsyncTask = new JentisNetworking(baseUrl, jsonString, resultHandler);
        sendPostReqAsyncTask.execute();
    }
}
