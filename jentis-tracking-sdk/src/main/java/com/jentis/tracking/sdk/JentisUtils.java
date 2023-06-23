package com.jentis.tracking.sdk;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jentis.tracking.sdk.model.Tracking;

import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class JentisUtils {
    public static String generateRandomUUID() {
        final String AB = "0123456789";
        SecureRandom rnd = new SecureRandom();

        StringBuilder sb = new StringBuilder(Tracking.idLength);
        for (int i = 0; i < Tracking.idLength; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

    /**
     * Checks if a string contains a specified prefix
     * Returns the string if it doesn't contain or the string without prefix if it contains
     */
    public static String deletingPrefix(String word, String prefix) {
        if (!word.startsWith(prefix)) {
            return word;
        }

        return word.substring(prefix.length());
    }

    /**
     * Transforms a lowercaps String into a capitalized one
     * Returns: a capitalized string
     */
    private static String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }

    /**
     * Transforms a String into a Date
     * Returns: a Date from the specified String
     */
    public static Date stringToDate(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        try {
            Date date = format.parse(dateString);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Transforms a Date into a String
     * Returns: a string from the specified date
     */
    public static String dateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        String dateString = dateFormat.format(date);
        return dateString;
    }

    /**
     * Transforms a Date into a String
     * Returns: a string from the specified date
     */
    public static JsonArray arrayToJsonArray(ArrayList<String> data) {
        JsonArray array = new JsonArray();
        for (String value : data) {
            array.add(value);
        }

        return array;
    }

    public static JsonArray objectArrayToJsonArray(ArrayList<Object> data) {
        JsonArray array = new JsonArray();
        for (Object value : data) {
            if (value instanceof String) {
                array.add(value.toString());
            } else if (value instanceof Integer) {
                array.add(Integer.parseInt(value.toString()));
            } else if (value instanceof Double) {
                array.add(Double.parseDouble(value.toString()));
            } else {
                Log.e("JentisUtils", "arrayToJsonArray: Unknown type" + value.getClass());
            }
        }

        return array;
    }

    /**
     * Transforms a Hashmap into a JsonObject
     *
     * @param data : the hashmap that needs to be transformed into JsonObject
     * @return : the newly created JsonObject
     */
    public static JsonObject hashMapToJsonObjectBoolean(Map<String, Boolean> data) {
        JsonObject obj = new JsonObject();

        for (String entryKey : data.keySet()) {
            obj.addProperty(entryKey, data.get(entryKey));
        }

        return obj;
    }

    /**
     * Transforms a Hashmap into a JsonObject
     *
     * @param data : the hashmap that needs to be transformed into JsonObject
     * @return : the newly created JsonObject
     */
    public static JsonObject hashMapToJsonObjectString(Map<String, String> data) {
        JsonObject obj = new JsonObject();

        for (String entryKey : data.keySet()) {
            obj.addProperty(entryKey, data.get(entryKey));
        }

        return obj;
    }
}
