package com.jentis.tracking;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jentis.tracking.model.Client;
import com.jentis.tracking.model.DebugInformation;
import com.jentis.tracking.model.Device;
import com.jentis.tracking.model.JentisData;
import com.jentis.tracking.model.JentisException;
import com.jentis.tracking.model.JentisLogger;
import com.jentis.tracking.model.Parent;
import com.jentis.tracking.model.TrackConfig;
import com.jentis.tracking.model.Tracking;
import com.jentis.tracking.model.TrackingDataDatum;
import com.jentis.tracking.model.enums.DocumentType;
import com.jentis.tracking.model.interfaces.ResultHandler;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class TrackService {
    /// The singleton instance of TrackService
    private TrackService() {};
    private static TrackService INSTANCE = null;

    public static TrackService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TrackService();
        }
        return(INSTANCE);
    }

    private DebugInformation debugInformation;
    private String userId;
    private Context context;
    private String sessionId;
    private Date sessionCreatedAt;
    private ArrayList<String> currentTracks = new ArrayList<String>();
    private Map<String, Boolean> consents;
    private HashMap<String, Object> currentProperties = new HashMap<>();
    private TrackConfig config;
    private static final JentisLogger log = JentisLogger.getLogger("TrackService");


    private ArrayList<JsonObject> storedTrackings = new ArrayList<JsonObject>();

    private void init() {
        String userId = UserSettings.getInstance(context).getUserId();
        if(userId != null) {
            this.userId = userId;
        } else {
            this.userId = Utils.generateRandomUUID();
            UserSettings.getInstance(context).setUserId(this.userId);
        }
        JentisLogger.disableLogging(true);
        this.sessionId = Utils.generateRandomUUID();
        this.sessionCreatedAt = new Date();
    }

    /**
     * Initializes the Jentis Tracking in the SDK
     * @param context: application context
     * @param config: Contains the Configuration to initialize the SDK
     */
    public void initTracking(Context context, TrackConfig config) {
        this.context = context;

        if (UserSettings.getInstance(context).getConsents() != null) {
            this.consents = UserSettings.getInstance(context).getConsents();
        }
        init();
        this.config = config;
        Api.getInstance().setup(config.trackDomain);
    }


    /**
     * Get the current consent settings
     * Returns: A list of the current consents with true/false
     */
    public Map<String, Boolean> getCurrentConsents() {
        return consents;
    }

    /**
     * Get the current consent ID
     * Returns: The current consent ID if it was created already
     */
    public String getCurrentConsentId() {
        return UserSettings.getInstance(context).getConsentId();
    }

    /**
     * Get the date of the last consent update
     * Returns: The last consent update Date
     */
    public Date getLastConsentUpdate() {
        String lastConsent = UserSettings.getInstance(context).getLastConsentUpdate();
        if(lastConsent != null) {
            return Utils.stringToDate(lastConsent);
        }

        return  null;
    }

    /**
     * Set new consent values
     * Trackings which were stored previously (while consent was nil) are sent automatically (if at least one tracking provider is enabled)
     * Parameter consents: A list of the new Consents with true/false
     * Parameter completion: Contains the information whether the request was successful or not
     */
    public void setConsents(HashMap<String, Boolean> consents, ResultHandler<Object> handler) {
        if(config == null){
            handler.onFailure(new JentisException("[JENTIS] Call initTracking first"));
            log.error("[JENTIS] Call initTracking first");
            return;
        }

        Map<String, Boolean> previousConsents = UserSettings.getInstance(context).getConsents();
        String consentId = UserSettings.getInstance(context).getConsentId();
        if(consentId == null){
            consentId = Utils.generateRandomUUID();
        }

        // Get changed Vendors List
        HashMap<String, Boolean> vendorsChanged = new HashMap<>();

        if(previousConsents != null){
            for (String previousConsentKey : previousConsents.keySet())
            {
                if(previousConsents.get(previousConsentKey) != consents.get(previousConsentKey)) {
                    vendorsChanged.put(previousConsentKey, consents.get(previousConsentKey));
                }
            }
        }

        sendConsentSettings(consentId, consents, vendorsChanged);
    }


    /**
     * Set debugging of tracking
     * Parameter shouldDebug: Bool to activate/deactivate debugging
     * Parameter debugId: (optional) Set the ID when enabling debugging
     * Parameter version: (otpional) Set the version when enabling debugging
     */
    public void debugTracking(Boolean shouldDebug, String debugId, String version) {
        if(config == null){
            log.error("[JENTIS] Call initTracking first");
            return;
        }
        JentisLogger.disableLogging(!shouldDebug);

        if(shouldDebug ){
            if(debugId == null || version == null) {
                log.error("[JENTIS] Set debugId & version to debug");
                return;
            }

            debugInformation = new DebugInformation(shouldDebug, debugId, version);
        } else {
            debugInformation = new DebugInformation(shouldDebug);
        }
    }


    /**
     * Tracking method
     * - if no consent was set until now, the tracking is stored until a consent is set / the app is closed
     * - if the consent was set but all trackingproviders are disabled the tracking is discarded
     * - if the consent was set and at least one trackingprovider is enabled -> tracking is sent to the server
     *
     * @param data: Contains the key:value pairs
     */
    public void push(Map<String, String> data) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                if(config == null){
                    log.error("[JENTIS] Call initTracking first");
                    return;
                }

                if(isTrackingDisabled()) {
                    // User disabled all Tracking options - discard tracking
                    currentTracks = new ArrayList<String>();
                    currentProperties = new HashMap<>();
                    log.error("[JENTIS]: not tracking - all vendors are set to false");
                    return;
                }

                String trackString = (String) data.get(Tracking.trackKey);
                if(trackString == null){
                    log.error("[JENTIS] track not included");
                }
                currentTracks.add(trackString);

                for (String entryKey: data.keySet())
                {
                    if(entryKey != Tracking.trackKey) {
                        currentProperties.put(entryKey, data.get(entryKey));
                    }
                }


                if (trackString.equalsIgnoreCase(Tracking.TRACK.SUBMIT.toString())) {
                    submitPush();
                }
            }
        });
    }

    /**
     * Sends all consent settings to Jentis server
     *
     * @param consentId: the consentID that needs to be sent
     * @param vendors: contains the vendors list with their corresponding consent
     * @param vendorsChanged: contains the map of changed vendors with their corresponding consent
     */
    private void sendConsentSettings(String consentId, Map<String, Boolean> vendors, Map<String, Boolean> vendorsChanged) {

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                if(!isSessionValid()){
                    sessionId = Utils.generateRandomUUID();
                }

                JentisData trackingData = new JentisData();
                Parent parent = new Parent();
                parent.setUser(userId);
                parent.setSession(sessionId);

                trackingData.setClient(getClient());
                trackingData.getData().add(TrackServiceUtils.getUserData(parent, config));
                trackingData.getData().add(TrackServiceUtils.getConsentData(parent, consentId, vendors, vendorsChanged, config, context));

                Api.getInstance().setConsentSettings(trackingData, new ResultHandler<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        consents = vendors;
                        UserSettings.getInstance(context).setConsents(vendors);
                        UserSettings.getInstance(context).setConsentId(consentId);

                        if(isTrackingDisabled()){
                            // User disabled all Tracking options - discard tracking
                            storedTrackings = new ArrayList<JsonObject>();
                            log.info("[JENTIS]: clearing stored trackings - all vendors are set to false");
                            return;
                        }

                        // User set consents - send stored trackings
                        ArrayList<JsonObject> currentlyStoredTrackings = storedTrackings;

                        for (JsonObject storedTracking: currentlyStoredTrackings) {
                            Api.getInstance().submitTracking(storedTracking, null);
                        }

                        storedTrackings = new ArrayList<JsonObject>();
                    }

                    @Override
                    public void onFailure(JentisException e) {
                        log.error(e.getLocalizedMessage(), e);
                    }
                });
            }
        });
    }

    private void submitPush(){
        if(!isSessionValid()) {
            sessionId = Utils.generateRandomUUID();
        }

        JentisData eventData = TrackServiceUtils.getTrackingData(getClient(), userId, sessionId, config, debugInformation, consents, currentTracks, context);

        currentTracks = new ArrayList<String>();

        if(eventData == null) {
            log.error("[JENTIS] Error - Failed to get tracking data");
            return;
        }

        JsonObject data = eventData.toJSON();
        JsonArray properties = data.get("data").getAsJsonArray();

        for (JsonElement element: properties) {
            if(element.getAsJsonObject().get("documentType").getAsString().equalsIgnoreCase(DocumentType.EVENT.toString())) {
                JsonObject prop = element.getAsJsonObject().get("property").getAsJsonObject();

                if(currentProperties != null){
                    for (String entryKey: currentProperties.keySet())
                    {
                        prop.addProperty(entryKey, (String) currentProperties.get(entryKey));
                    }
                }
            }
        }

        if(isTrackingDisabled() || consents.keySet().size() == 0) {
            storedTrackings.add(data);
        } else {

            Api.getInstance().submitTracking(data, new ResultHandler<Object>() {
                @Override
                public void onSuccess(Object data) {
                    log.info(data);
                }

                @Override
                public void onFailure(JentisException e) {
                    log.info(data);
                }
            });
        }
    }

    /**
     * Checks if a session is valid comparing sessionCreatedAt with current time
     * if the difference if greater than the sessionDuration invalidate the session
     */
    private Boolean isSessionValid() {
        // Check if the session is not longer valid then the specified session duration
        Long diff = new Date().getTime() - sessionCreatedAt.getTime();
        Long minutes = diff / (1000 * 60);
        if(minutes < Tracking.sessionDuration) {
            return true;
        } else {
            return false;
        }
    }

    private Boolean isTrackingDisabled() {
        if(consents == null || consents.keySet().size() == 0) {
            return false;
        }

        boolean allConsentsDisabled = true;

        for (String consentKey: consents.keySet())
        {
            if(consents.get(consentKey) != false) {
                allConsentsDisabled = false;
                break;
            }
        }

        return allConsentsDisabled;
    }

    private Client getClient() {
        Client client = new Client();
        client.setClientTimestamp(System.currentTimeMillis());
        client.setDomain("." + Utils.deletingPrefix(config.trackDomain, Tracking.trackingDomainPrefix));
        return client;
    }
}
