package com.jentis.tracking.sdk.model;

import com.google.gson.JsonObject;
import com.jentis.tracking.sdk.JentisUtils;

import java.util.Map;

public class System {
    String environment, navigatorUserAgent, href;
    Map<String, Boolean> consent;

    public System() {
    }

    public System(String environment, String navigatorUserAgent, String href, Map<String, Boolean> consent) {
        setEnvironment(environment);
        setNavigatorUserAgent(navigatorUserAgent);
        setHref(href);
        setConsent(consent);
    }

    public JsonObject toJSON() {
        JsonObject jsonObject = new JsonObject();
        if (getEnvironment() != null)
            jsonObject.addProperty("environment", getEnvironment());
        if (getNavigatorUserAgent() != null)
            jsonObject.addProperty("navigator-userAgent", getNavigatorUserAgent());
        if (getHref() != null)
            jsonObject.addProperty("href", getHref());
        if (getConsent() != null) {
            jsonObject.add("consent", JentisUtils.hashMapToJsonObjectBoolean(getConsent()));
        }

        return jsonObject;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getNavigatorUserAgent() {
        return navigatorUserAgent;
    }

    public void setNavigatorUserAgent(String navigatorUserAgent) {
        this.navigatorUserAgent = navigatorUserAgent;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Map<String, Boolean> getConsent() {
        return consent;
    }

    public void setConsent(Map<String, Boolean> consent) {
        this.consent = consent;
    }
}
