package com.jentis.tracking;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import androidx.annotation.RestrictTo;

import com.jentis.tracking.model.Client;
import com.jentis.tracking.model.DebugInformation;
import com.jentis.tracking.model.Device;
import com.jentis.tracking.model.JentisData;
import com.jentis.tracking.model.Parent;
import com.jentis.tracking.model.Property;
import com.jentis.tracking.model.System;
import com.jentis.tracking.model.TrackConfig;
import com.jentis.tracking.model.Tracking;
import com.jentis.tracking.model.TrackingDataDatum;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

@RestrictTo(RestrictTo.Scope.LIBRARY)
public class TrackServiceUtils {

    /**
     * Generates the userData that will be sent to the server side
     * @param parent: contains the user id that will be used to generate user data
     * @param config: used to retrieve the account info
     *
     * @return userData as TrackingDataDatum object
     */
    public static TrackingDataDatum getUserData(Parent parent, TrackConfig config) {
        TrackingDataDatum userData = new TrackingDataDatum();
        userData.setId(parent.getUser());
        userData.setAction(Config.ACTION.UDP.toString());
        userData.setDocumentType(Config.DOC_TYPE.USER.toString());

        System userDataSystem = new System();
        userDataSystem.setEnvironment(Tracking.systemEnvironment);
        userData.setSystem(userDataSystem);

        userData.setAccount(getAccount(config));

        return userData;
    }

    /**
     * Generates the consentData that will be sent to the server side
     * @return consentData as TrackingDataDatum object
     */
    public static TrackingDataDatum getConsentData(Parent parent, String consentId, Map<String, Boolean> vendors, Map<String, Boolean> vendorsChanged, TrackConfig config, Context context) {
        Long timestamp = java.lang.System.currentTimeMillis();
        UserSettings.getInstance(context).setLastConsentUpdate(timestamp);

        TrackingDataDatum consentData = new TrackingDataDatum();
        consentData.setId(Utils.generateRandomUUID());
        consentData.setDocumentType(Config.DOC_TYPE.CONSENT.toString());
        consentData.setAction(Config.ACTION.NEW.toString());

        Parent consentDataParent = new Parent();
        consentDataParent.setUser(parent.getUser());
        consentData.setParent(consentDataParent);

        consentData.setAccount(getAccount(config));

        Property consentDataProperty = new Property();
        consentDataProperty.setTrack(Tracking.TRACK.CONSENT.toString());
        consentDataProperty.setConsentid(consentId);
        consentDataProperty.setLastupdate(timestamp);
        consentDataProperty.setVendors(vendors);
        consentDataProperty.setSend(true);
        consentDataProperty.setUserconsent(true);
        consentDataProperty.setVendorsChanged(vendorsChanged);

        consentData.setProperty(consentDataProperty);

        return consentData;

    }

    /**
     * Generates the JentisData that will be sent to the server side
     * @return consentData as TrackingDataDatum object
     */
    public static JentisData getTrackingData(Client client, String userId, String sessionId, TrackConfig config, DebugInformation debugInfo, Map<String, Boolean> consents, ArrayList<String> currentTracks, Context context) {
        JentisData trackingData = new JentisData();
        Parent parent = new Parent(userId, sessionId);

        trackingData.setClient(client);

        ArrayList<TrackingDataDatum> data = new ArrayList<>();
        data.add(getUserData(parent, config));
        data.add(getSessionData(parent, debugInfo, config));
        data.add(getEventData(parent, config, consents, currentTracks, userId, context));

        trackingData.setData(data);

        return trackingData;
    }

    /**
     * Generates the TrackingDataDatum that will be sent to the server side
     * @return sessionData as TrackingDataDatum object
     */
    private static TrackingDataDatum getSessionData(Parent parent, DebugInformation debugInfo, TrackConfig config) {
        TrackingDataDatum sessionData = new TrackingDataDatum();

        sessionData.setId(parent.getSession());
        sessionData.setAction(Config.ACTION.UDP.toString());
        sessionData.setAccount(getAccount(config));
        sessionData.setDocumentType(Config.DOC_TYPE.SESSION.toString());

        Property sessionDataProperty = new Property();

        if(debugInfo != null && debugInfo.getDebugEnabled()) {
            sessionDataProperty.setJtsDebug(debugInfo.getDebugId());
            sessionDataProperty.setJtsVersion(debugInfo.getVersion());
        }

        sessionData.setSystem(new System());
        sessionData.setProperty(sessionDataProperty);
        sessionData.setParent(parent);

        return sessionData;

    }

    /**
     * Generates the TrackingDataDatum that will be sent to the server side
     * @return event data as TrackingDataDatum object
     */
    public static TrackingDataDatum getEventData(Parent parent, TrackConfig config, Map<String, Boolean> consents, ArrayList<String> currentTracks, String userId, Context context) {
        TrackingDataDatum eventData = new TrackingDataDatum();
        String eventId = Utils.generateRandomUUID();
        eventData.setId(eventId);
        eventData.setDocumentType(Config.DOC_TYPE.EVENT.toString());
        eventData.setAction(Config.ACTION.NEW.toString());

        eventData.setParent(parent);
        eventData.setPluginid(Tracking.pluginId);
        eventData.setAccount(getAccount(config));

        System eventDataSystem = new System();

        eventDataSystem.setConsent(consents);
        eventDataSystem.setHref("");

        eventData.setSystem(eventDataSystem);

        DisplayMetrics displayMetrics = new DisplayMetrics();

        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getMetrics(displayMetrics);

        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        String appName = context.getApplicationInfo().loadLabel(context.getPackageManager()).toString();
        String version = "";
        int code = 0;

        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            version = pInfo.versionName;
            code = pInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        Property eventDataProperty = new Property();

        eventDataProperty.setJtspushedcommands(currentTracks);
        eventDataProperty.setUserDocID(userId);
        eventDataProperty.setEventDocID(eventId);
        eventDataProperty.setAppDeviceBrand(Build.MANUFACTURER);
        eventDataProperty.setAppDeviceModel(Build.MODEL);
        eventDataProperty.setAppDeviceOS("android");
        eventDataProperty.setAppDeviceOSVersion(Build.VERSION.RELEASE + " " + Build.VERSION.SDK_INT);
        eventDataProperty.setAppDeviceLanguage(Locale.getDefault().getDisplayLanguage());
        eventDataProperty.setAppDeviceRegion(context.getResources().getConfiguration().locale.getCountry());
        eventDataProperty.setAppDeviceWidth(width);
        eventDataProperty.setAppDeviceHeight(height);
        eventDataProperty.setApplicationName(appName);
        eventDataProperty.setApplicationVersion(version);
        eventDataProperty.setApplicationBuildNumber(code);

        eventData.setProperty(eventDataProperty);

        return eventData;
    }

    /**
     * Generates the TrackingDataDatum that will be sent to the server side
     * @return empty string if no config file or a String containing trackId and environment
     */
    private static String getAccount(TrackConfig config) {
        if(config == null){
            return "";
        }

        return String.format("%1$s.%2$s", config.trackID, config.environment);
    }
}
