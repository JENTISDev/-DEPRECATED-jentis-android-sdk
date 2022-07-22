package com.jentis.tracking.model;

import androidx.annotation.RestrictTo;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jentis.tracking.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestrictTo(RestrictTo.Scope.LIBRARY)
public class Property {
    String appDeviceBrand, appDeviceModel, appDeviceOS, appDeviceRegion, appDeviceOSVersion;
    String appDeviceLanguage;
    Integer appDeviceWidth, appDeviceHeight;
    String applicationName, applicationVersion;
    Integer applicationBuildNumber;
    String jtsDebug, jtsVersion;
    ArrayList<String> jtspushedcommands;
    String documentRef;
    String documentTitle;
    String windowLocationHref;
    Integer dateNow;
    String userDocID, eventDocID;
    String documentLocationHref;
    String track, consentid;
    Long lastupdate;
    Map<String, Boolean> vendors;
    Boolean send, userconsent;
    Map<String, Boolean> vendorsChanged;

    public Property() {
    }

    public Property(String appDeviceBrand, String appDeviceModel, String appDeviceOS, String appDeviceRegion, String appDeviceOSVersion, String appDeviceLanguage, Integer appDeviceWidth, Integer appDeviceHeight, String applicationName, String applicationVersion, Integer applicationBuildNumber, String jtsDebug, String jtsVersion, ArrayList<String> jtspushedcommands, String documentRef, String documentTitle, String windowLocationHref, Integer dateNow, String userDocID, String eventDocID, String documentLocationHref, String track, String consentid, Long lastupdate, Map<String, Boolean> vendors, Boolean send, Boolean userconsent, Map<String, Boolean> vendorsChanged) {
        this.appDeviceBrand = appDeviceBrand;
        this.appDeviceModel = appDeviceModel;
        this.appDeviceOS = appDeviceOS;
        this.appDeviceRegion = appDeviceRegion;
        this.appDeviceOSVersion = appDeviceOSVersion;
        this.appDeviceLanguage = appDeviceLanguage;
        this.appDeviceWidth = appDeviceWidth;
        this.appDeviceHeight = appDeviceHeight;
        this.applicationName = applicationName;
        this.applicationVersion = applicationVersion;
        this.applicationBuildNumber = applicationBuildNumber;
        this.jtsDebug = jtsDebug;
        this.jtsVersion = jtsVersion;
        this.jtspushedcommands = jtspushedcommands;
        this.documentRef = documentRef;
        this.documentTitle = documentTitle;
        this.windowLocationHref = windowLocationHref;
        this.dateNow = dateNow;
        this.userDocID = userDocID;
        this.eventDocID = eventDocID;
        this.documentLocationHref = documentLocationHref;
        this.track = track;
        this.consentid = consentid;
        this.lastupdate = lastupdate;
        this.vendors = vendors;
        this.send = send;
        this.userconsent = userconsent;
        this.vendorsChanged = vendorsChanged;
    }

    public String getAppDeviceBrand() {
        return appDeviceBrand;
    }

    public String getAppDeviceModel() {
        return appDeviceModel;
    }

    public String getAppDeviceOS() {
        return appDeviceOS;
    }

    public String getAppDeviceRegion() {
        return appDeviceRegion;
    }

    public String getAppDeviceOSVersion() {
        return appDeviceOSVersion;
    }

    public String getAppDeviceLanguage() {
        return appDeviceLanguage;
    }

    public Integer getAppDeviceWidth() {
        return appDeviceWidth;
    }

    public Integer getAppDeviceHeight() {
        return appDeviceHeight;
    }

    public String getAppApplicationName() {
        return applicationName;
    }

    public String getAppApplicationVersion() {
        return applicationVersion;
    }

    public Integer getAppApplicationBuildNumber() {
        return applicationBuildNumber;
    }

    public String getJtsDebug() {
        return jtsDebug;
    }

    public String getJtsVersion() {
        return jtsVersion;
    }

    public ArrayList<String> getJtspushedcommands() {
        return jtspushedcommands;
    }

    public String getDocumentRef() {
        return documentRef;
    }

    public String getDocumentTitle() {
        return documentTitle;
    }

    public String getWindowLocationHref() {
        return windowLocationHref;
    }

    public Integer getDateNow() {
        return dateNow;
    }

    public String getUserDocID() {
        return userDocID;
    }

    public String getEventDocID() {
        return eventDocID;
    }

    public String getDocumentLocationHref() {
        return documentLocationHref;
    }

    public String getTrack() {
        return track;
    }

    public String getConsentid() {
        return consentid;
    }

    public Long getLastupdate() {
        return lastupdate;
    }

    public Map<String, Boolean> getVendors() {
        return vendors;
    }

    public Boolean getSend() {
        return send;
    }

    public Boolean getUserconsent() {
        return userconsent;
    }

    public Map<String, Boolean> getVendorsChanged() {
        return vendorsChanged;
    }

    public void setAppDeviceBrand(String appDeviceBrand) {
        this.appDeviceBrand = appDeviceBrand;
    }

    public void setAppDeviceModel(String appDeviceModel) {
        this.appDeviceModel = appDeviceModel;
    }

    public void setAppDeviceOS(String appDeviceOS) {
        this.appDeviceOS = appDeviceOS;
    }

    public void setAppDeviceRegion(String appDeviceRegion) {
        this.appDeviceRegion = appDeviceRegion;
    }

    public void setAppDeviceOSVersion(String appDeviceOSVersion) {
        this.appDeviceOSVersion = appDeviceOSVersion;
    }

    public void setAppDeviceLanguage(String appDeviceLanguage) {
        this.appDeviceLanguage = appDeviceLanguage;
    }

    public void setAppDeviceWidth(Integer appDeviceWidth) {
        this.appDeviceWidth = appDeviceWidth;
    }

    public void setAppDeviceHeight(Integer appDeviceHeight) {
        this.appDeviceHeight = appDeviceHeight;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public void setApplicationVersion(String applicationVersion) {
        this.applicationVersion = applicationVersion;
    }

    public void setApplicationBuildNumber(Integer applicationBuildNumber) {
        this.applicationBuildNumber = applicationBuildNumber;
    }

    public void setJtsDebug(String jtsDebug) {
        this.jtsDebug = jtsDebug;
    }

    public void setJtsVersion(String jtsVersion) {
        this.jtsVersion = jtsVersion;
    }

    public void setJtspushedcommands(ArrayList<String> jtspushedcommands) {
        this.jtspushedcommands = jtspushedcommands;
    }

    public void setDocumentRef(String documentRef) {
        this.documentRef = documentRef;
    }

    public void setDocumentTitle(String documentTitle) {
        this.documentTitle = documentTitle;
    }

    public void setWindowLocationHref(String windowLocationHref) {
        this.windowLocationHref = windowLocationHref;
    }

    public void setDateNow(Integer dateNow) {
        this.dateNow = dateNow;
    }

    public void setUserDocID(String userDocID) {
        this.userDocID = userDocID;
    }

    public void setEventDocID(String eventDocID) {
        this.eventDocID = eventDocID;
    }

    public void setDocumentLocationHref(String documentLocationHref) {
        this.documentLocationHref = documentLocationHref;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public void setConsentid(String consentid) {
        this.consentid = consentid;
    }

    public void setLastupdate(Long lastupdate) {
        this.lastupdate = lastupdate;
    }

    public void setVendors(Map<String, Boolean> vendors) {
        this.vendors = vendors;
    }

    public void setSend(Boolean send) {
        this.send = send;
    }

    public void setUserconsent(Boolean userconsent) {
        this.userconsent = userconsent;
    }

    public void setVendorsChanged(Map<String, Boolean> vendorsChanged) {
        this.vendorsChanged = vendorsChanged;
    }

    public JsonObject toJSON() {
        JsonObject jsonObject= new JsonObject();
        jsonObject.addProperty("app_device_model", getAppDeviceModel());
        jsonObject.addProperty("app_device_brand", getAppDeviceBrand());
        jsonObject.addProperty("app_device_os", getAppDeviceOS());
        jsonObject.addProperty("app_device_os_version", getAppDeviceOSVersion());
        jsonObject.addProperty("app_device_language", getAppDeviceLanguage());
        jsonObject.addProperty("app_device_region", getAppDeviceRegion());
        jsonObject.addProperty("app_device_width", getAppDeviceWidth());
        jsonObject.addProperty("app_device_height", getAppDeviceHeight());
        jsonObject.addProperty("app_application_name", getAppApplicationName());
        jsonObject.addProperty("app_application_version", getAppApplicationVersion());
        jsonObject.addProperty("app_application_build_number", getAppApplicationBuildNumber());
        jsonObject.addProperty("jts_debug", getJtsDebug());
        jsonObject.addProperty("jts_version", getJtsVersion());
        if(jtspushedcommands != null) {
            jsonObject.add("jtspushedcommands", Utils.arrayToJsonArray(jtspushedcommands));
        }
        jsonObject.addProperty("document_ref", getDocumentRef());
        jsonObject.addProperty("document_title", getDocumentTitle());
        jsonObject.addProperty("window_location_href", getWindowLocationHref());
        jsonObject.addProperty("date_now", getDateNow());
        jsonObject.addProperty("user_doc_id", getUserDocID());
        jsonObject.addProperty("event_doc_id", getEventDocID());
        jsonObject.addProperty("document_location_href", getDocumentLocationHref());
        jsonObject.addProperty("track", getTrack());
        jsonObject.addProperty("consentid", getConsentid());
        jsonObject.addProperty("lastupdate", getLastupdate());
        if(getVendors() != null) {
            jsonObject.add("vendors", Utils.hashMapToJsonObjectBoolean(getVendors()));
        }
        jsonObject.addProperty("send", getSend());
        jsonObject.addProperty("userconsent", getUserconsent());
        if(getVendorsChanged() != null) {
            jsonObject.add("vendorsChanged", Utils.hashMapToJsonObjectBoolean(getVendorsChanged()));
        }

        return jsonObject;
    }

}



