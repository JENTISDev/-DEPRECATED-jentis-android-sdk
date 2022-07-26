package com.jentis.analytics;

import android.content.Context;
import android.content.SharedPreferences;

import com.jentis.analytics.model.JentisLogger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class JentisSharedPrefs {
    private static JentisSharedPrefs jentisSharedPrefs;
    private SharedPreferences mPrefs;

    public static JentisSharedPrefs getInstance(Context context) {
        if (jentisSharedPrefs == null) {
            jentisSharedPrefs = new JentisSharedPrefs(context);
        }
        return jentisSharedPrefs;
    }

    private static String userId = JentisConfig.keyPrefix + ".userId";
    private static String consents = JentisConfig.keyPrefix + ".consents";
    private static String consentId = JentisConfig.keyPrefix + ".consentId";
    private static String lastConsentUpdate = JentisConfig.keyPrefix + ".lastConsentUpdate";

    private JentisSharedPrefs(Context context) {
        String packageName =  context.getPackageName();
        mPrefs = context.getSharedPreferences(packageName,Context.MODE_PRIVATE);
    }

    /**
     * Retrieves the userId from SharedPreferences
     */
    public String getUserId() {
        return mPrefs.getString(userId, null);
    }

    /**
     * Retrieves the list of consents that are stored in shared preferences
     * Transforms that list into a map of key - vendor value - accepted/rejected
     * @return the map of values generated
     */
    public Map<String,Boolean> getConsents() {
        Map<String,Boolean> outputMap = new HashMap<>();

        try {
            if (mPrefs != null) {
                String jsonString = mPrefs.getString(consents, (new JSONObject()).toString());
                if (jsonString != null) {
                    JSONObject jsonObject = new JSONObject(jsonString);
                    Iterator<String> keysItr = jsonObject.keys();
                    while (keysItr.hasNext()) {
                        String key = keysItr.next();
                        Boolean value = jsonObject.getBoolean(key);
                        outputMap.put(key, value);
                    }
                }
            }
        } catch (JSONException e){
            JentisLogger.getLogger("User Settings").error(e);
        }
        return outputMap;
    }

    /**
     * Retrieves the consentId from SharedPreferences
     */
    public String getConsentId() {
        return mPrefs.getString(consentId, null);
    }

    /**
     * Retrieves the lastConsentUpdate from SharedPreferences
     */
    public String getLastConsentUpdate() {
        return mPrefs.getString(lastConsentUpdate, null);
    }

    /**
     * Retrieves the userId from SharedPreferences
     */
    public void setUserId(String userId) {
        saveValue(this.userId, userId);
    }

    /**
     * saves the map of consents into shared preferences
     */
    public void setConsents(Map<String, Boolean> consents) {
        JSONObject jsonObject = new JSONObject(consents);
        String consentsString = jsonObject.toString();

        saveValue(this.consents, consentsString);
    }

    /**
     * saves the consentid into shared preferences
     */
    public void setConsentId(String consentId) {
        saveValue(this.consentId, consentId);
    }

    /**
     * saves the last consent update into shared preferences
     */
    public void setLastConsentUpdate(Long lastConsentUpdate) {
        saveValue(this.lastConsentUpdate, String.valueOf(lastConsentUpdate));
    }

    /**
     * Saves a specific value in SharedPreferences
     */
    private void saveValue(String key, String value) {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(key, value);
        editor.apply();
    }
}