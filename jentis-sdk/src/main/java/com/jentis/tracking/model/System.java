package com.jentis.tracking.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class System {
    String environment, navigatorUserAgent, href;
    HashMap<String, Boolean> consent;

    public System() {}

    public System(String environment, String navigatorUserAgent, String href, HashMap<String, Boolean> consent) {
        setEnvironment(environment);
        setNavigatorUserAgent(navigatorUserAgent);
        setHref(href);
        setConsent(consent);
    }

    public String toJSON() {
        JSONObject jsonObject= new JSONObject();
        try {
            jsonObject.put("environment", getEnvironment());
            jsonObject.put("navigator-userAgent", getNavigatorUserAgent());
            jsonObject.put("href", getHref());
            jsonObject.put("consent", getConsent());

            return jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
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

    public HashMap<String, Boolean> getConsent() {
        return consent;
    }

    public void setConsent(HashMap<String, Boolean> consent) {
        this.consent = consent;
    }
}
