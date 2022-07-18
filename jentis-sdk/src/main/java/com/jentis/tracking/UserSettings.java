package com.jentis.tracking;

import android.content.Context;
import android.content.SharedPreferences;

import com.jentis.tracking.model.JentisLogger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class UserSettings {
    private static UserSettings userSettings;
    private SharedPreferences mPrefs;

    public static UserSettings getInstance(Context context) {
        if (userSettings == null) {
            userSettings = new UserSettings(context);
        }
        return userSettings;
    }

    private static String userId = Config.keyPrefix + ".userId";
    private static String consents = Config.keyPrefix + ".consents";
    private static String consentId = Config.keyPrefix + ".consentId";
    private static String lastConsentUpdate = Config.keyPrefix + ".lastConsentUpdate";

    private UserSettings(Context context) {
        mPrefs = context.getSharedPreferences("Jentis-Android",Context.MODE_PRIVATE);
    }

    public String getUserId() {
        return mPrefs.getString(userId, null);
    }

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

    public String getConsentId() {
        return mPrefs.getString(consentId, null);
    }

    public String getLastConsentUpdate() {
        return mPrefs.getString(lastConsentUpdate, null);
    }

    public void setUserId(String userId) {
        saveValue(this.userId, userId);
    }

    public void setConsents(HashMap<String, Boolean> consents) {
        JSONObject jsonObject = new JSONObject(consents);
        String consentsString = jsonObject.toString();

        saveValue(this.consents, consentsString);
    }

    public void setConsentId(String consentId) {
        saveValue(this.consentId, consentId);
    }

    public void setLastConsentUpdate(Long lastConsentUpdate) {
        saveValue(this.lastConsentUpdate, String.valueOf(lastConsentUpdate));
    }

    private void saveValue(String key, String value) {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(key, value);
        editor.apply();
    }
}