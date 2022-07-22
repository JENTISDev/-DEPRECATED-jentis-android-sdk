package com.jentis.tracking;

import androidx.annotation.RestrictTo;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jentis.tracking.model.JentisData;
import com.jentis.tracking.model.JentisException;
import com.jentis.tracking.model.JentisLogger;
import com.jentis.tracking.model.interfaces.ResultHandler;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

@RestrictTo(RestrictTo.Scope.LIBRARY)
public class Api {
    private static Api INSTANCE = null;
    private String baseUrl;
    
    private Api() {};

    private final String TAG = "JENTIS-API";

    /**
     * create the singleton instance of the Api class
     * @return initialize Api instance
     */
    public static Api getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Api();
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
        Networking sendPostReqAsyncTask = new Networking(baseUrl, jsonString, resultHandler);
        sendPostReqAsyncTask.execute();
    }
}
