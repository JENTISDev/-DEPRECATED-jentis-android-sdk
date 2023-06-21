package com.jentis.tracking.sdk;

import android.os.AsyncTask;

import com.jentis.tracking.sdk.model.JentisException;
import com.jentis.tracking.sdk.model.JentisLogger;
import com.jentis.tracking.sdk.model.interfaces.ResultHandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

class JentisNetworking extends
        AsyncTask<Void, Void, Boolean> {

    String urlString;
    String paramsData;
    ResultHandler resultHandler;

    private final String TAG = "JENTIS-NETWORKING";

    public JentisNetworking(String urlString, String paramsData, ResultHandler resultHandler) {
        this.paramsData = paramsData;
        this.urlString = urlString;
        this.resultHandler = resultHandler;
    }

    @Override
    protected void onPreExecute() {
        JentisLogger.getLogger(TAG).info( "onPreExecute request");
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        boolean status = false;

        String response = "";

        try {
            response = performPostCall(urlString, paramsData, resultHandler);
        } catch (Exception e) {
            JentisLogger.getLogger(TAG).error(e.getLocalizedMessage(), e);
        }

        if (!response.equalsIgnoreCase("") && response.contains("Corrupt")) {
            status = true;
        } else {
            status = false;
        }

        return status;
    }

    @Override
    protected void onPostExecute(Boolean result) {}

    public String performPostCall(String requestURL,
                                  String postDataParams, ResultHandler resultHandler) {

        URL url;
        String response = "";
        try {
            url = new URL(requestURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(30000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            conn.setRequestProperty("Content-Type", "application/json");

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(postDataParams);

            writer.flush();
            writer.close();
            os.close();

            int responseCode = conn.getResponseCode();

            JentisLogger.getLogger(TAG).info("[Jentis] responseCode: " + responseCode);

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
            } else {
                response = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultHandler.onFailure(new JentisException(e.getLocalizedMessage(), e));
        }

        if(resultHandler != null) {
            resultHandler.onSuccess(true);
        }
        return response;
    }
}
