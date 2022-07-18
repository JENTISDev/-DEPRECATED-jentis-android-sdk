package com.jentis.tracking;

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

public class Api {
    private static Api INSTANCE = null;
    private String baseUrl;
    
    private Api() {};

    public static Api getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Api();
        }
        return(INSTANCE);
    }


    public void setup(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void performPostCall(String requestURL, String postDataParams, ResultHandler<Object> resultHandler) {

        URL url;
        String response = "";
        try {
            url = new URL(requestURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setDoOutput(true);


            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(postDataParams);

            writer.flush();
            writer.close();
            os.close();
            int responseCode=conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line=br.readLine()) != null) {
                    response+=line;
                }
            }
            else {
                resultHandler.onSuccess("");
            }
        } catch (Exception e) {
            resultHandler.onFailure(new JentisException(e.getLocalizedMessage(), e));
            e.printStackTrace();
        }

        resultHandler.onSuccess(response);
    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }

    public void setConsentSettings(JentisData trackingData, ResultHandler<Object> resultHandler) {
        try {
            String jsonData = trackingData.toJSON();
            submitTracking(jsonData, resultHandler);
        } catch (Exception e) {
            JentisLogger.getLogger("Api").error(e);
        }
    }

    public void submitTracking(String data, ResultHandler<Object> resultHandler) {
        if(baseUrl == null){
            return;
        }

        performPostCall(baseUrl, data, resultHandler);
    }
}
