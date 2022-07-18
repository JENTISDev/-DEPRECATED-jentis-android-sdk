package com.jentis.tracking;

import android.os.Build;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class Utils {
    public static String generateRandomUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * Checks if a string contains a specified prefix
     * Returns the string if it doesn't contain or the string without prefix if it contains
     */
    public static String deletingPrefix(String word, String prefix) {
        if(!word.startsWith(prefix)) {
            return  word;
        }

        return  word.substring(prefix.length());
    }

    /**
     * Retrieves the Device manufacturer and model
     */
    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.toLowerCase().startsWith(manufacturer.toLowerCase())) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
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
    public static Date stringToDate(String dateString){
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
    public static String dateToString(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        String dateString = dateFormat.format(date);
        return dateString;
    }
}
