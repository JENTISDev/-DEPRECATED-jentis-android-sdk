package com.jentis.tracking.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Property {
    String appDeviceBrand, appDeviceModel, appDeviceOS, appDeviceRegion, appDeviceOSVersion;
    String appDeviceLanguage;
    Integer appDeviceWidth, appDeviceHeight;
    String appApplicationName, appApplicationVersion;
    String appApplicationBuildNumber;
    String jtsDebug, jtsVersion;
    String[] jtspushedcommands;
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

    public Property(String appDeviceBrand, String appDeviceModel, String appDeviceOS, String appDeviceRegion, String appDeviceOSVersion, String appDeviceLanguage, Integer appDeviceWidth, Integer appDeviceHeight, String appApplicationName, String appApplicationVersion, String appApplicationBuildNumber, String jtsDebug, String jtsVersion, String[] jtspushedcommands, String documentRef, String documentTitle, String windowLocationHref, Integer dateNow, String userDocID, String eventDocID, String documentLocationHref, String track, String consentid, Long lastupdate, Map<String, Boolean> vendors, Boolean send, Boolean userconsent, Map<String, Boolean> vendorsChanged) {
        this.appDeviceBrand = appDeviceBrand;
        this.appDeviceModel = appDeviceModel;
        this.appDeviceOS = appDeviceOS;
        this.appDeviceRegion = appDeviceRegion;
        this.appDeviceOSVersion = appDeviceOSVersion;
        this.appDeviceLanguage = appDeviceLanguage;
        this.appDeviceWidth = appDeviceWidth;
        this.appDeviceHeight = appDeviceHeight;
        this.appApplicationName = appApplicationName;
        this.appApplicationVersion = appApplicationVersion;
        this.appApplicationBuildNumber = appApplicationBuildNumber;
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
        return appApplicationName;
    }

    public String getAppApplicationVersion() {
        return appApplicationVersion;
    }

    public String getAppApplicationBuildNumber() {
        return appApplicationBuildNumber;
    }

    public String getJtsDebug() {
        return jtsDebug;
    }

    public String getJtsVersion() {
        return jtsVersion;
    }

    public String[] getJtspushedcommands() {
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

    public void setAppApplicationName(String appApplicationName) {
        this.appApplicationName = appApplicationName;
    }

    public void setAppApplicationVersion(String appApplicationVersion) {
        this.appApplicationVersion = appApplicationVersion;
    }

    public void setAppApplicationBuildNumber(String appApplicationBuildNumber) {
        this.appApplicationBuildNumber = appApplicationBuildNumber;
    }

    public void setJtsDebug(String jtsDebug) {
        this.jtsDebug = jtsDebug;
    }

    public void setJtsVersion(String jtsVersion) {
        this.jtsVersion = jtsVersion;
    }

    public void setJtspushedcommands(String[] jtspushedcommands) {
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

    public String toJSON() {
        JSONObject jsonObject= new JSONObject();
        try {
            jsonObject.put("app_device_model", getAppDeviceModel());
            jsonObject.put("app_device_brand", getAppDeviceBrand());
            jsonObject.put("app_device_os", getAppDeviceOS());
            jsonObject.put("app_device_os_version", getAppDeviceOSVersion());
            jsonObject.put("app_device_language", getAppDeviceLanguage());
            jsonObject.put("app_device_region", getAppDeviceRegion());
            jsonObject.put("app_device_width", getAppDeviceWidth());
            jsonObject.put("app_device_height", getAppDeviceHeight());
            jsonObject.put("app_application_name", getAppApplicationName());
            jsonObject.put("app_application_version", getAppApplicationVersion());
            jsonObject.put("app_application_build_number", getAppApplicationBuildNumber());
            jsonObject.put("jts_debug", getJtsDebug());
            jsonObject.put("jts_version", getJtsVersion());
            jsonObject.put("jtspushedcommands", getJtspushedcommands());
            jsonObject.put("document_ref", getDocumentRef());
            jsonObject.put("document_title", getDocumentTitle());
            jsonObject.put("window_location_href", getWindowLocationHref());
            jsonObject.put("date_now", getDateNow());
            jsonObject.put("user_doc_id", getUserDocID());
            jsonObject.put("event_doc_id", getEventDocID());
            jsonObject.put("document_location_href", getDocumentLocationHref());
            jsonObject.put("track", getTrack());
            jsonObject.put("consentid", getConsentid());
            jsonObject.put("lastupdate", getLastupdate());
            jsonObject.put("vendors", getVendors());
            jsonObject.put("send", getSend());
            jsonObject.put("userconsent", getUserconsent());
            jsonObject.put("vendorsChanged", getVendorsChanged());

            return jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

}



