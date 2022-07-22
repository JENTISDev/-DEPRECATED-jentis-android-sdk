package com.jentis.tracking;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.RestrictTo;

import com.jentis.tracking.model.JentisLogger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestrictTo(RestrictTo.Scope.LIBRARY)
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