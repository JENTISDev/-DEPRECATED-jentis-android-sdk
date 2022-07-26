package com.jentis.tracking.sdk.model;

import com.jentis.tracking.sdk.model.enums.TrackEnum;

public class Tracking {
    public static Integer idLength = 23;
    public static String pluginId = "jts_push_submit";
    public static Integer sessionDuration = 30;
    public static String trackKey = "track";
    public static String dataKey = "data";
    public static String documentTypeKey = "documentType";
    public static String propertiesKey = "property";
    public static String systemEnvironment = "sdk-android";
    public static String trackingDomainPrefix = "https://";

    public static TrackEnum TRACK;
}