package com.jentis.tracking;

import android.content.Context;

import com.jentis.tracking.model.Client;
import com.jentis.tracking.model.DebugInformation;
import com.jentis.tracking.model.Device;
import com.jentis.tracking.model.JentisData;
import com.jentis.tracking.model.JentisException;
import com.jentis.tracking.model.JentisLogger;
import com.jentis.tracking.model.Parent;
import com.jentis.tracking.model.TrackConfig;
import com.jentis.tracking.model.Tracking;
import com.jentis.tracking.model.interfaces.ResultHandler;

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


//    private Data[] storedTrackings = []

    private void init() {
        // Load userId from UserDefaults
        // Generate userId if it does not exist
        String userId = UserSettings.getInstance(context).getUserId();
        if(userId != null) {
            this.userId = userId;
        } else {
            userId = Utils.generateRandomUUID();
            UserSettings.getInstance(context).setUserId(userId);
        }
        this.sessionId = Utils.generateRandomUUID();
        this.sessionCreatedAt = new Date();
    }

    /**
     * Initializes the Jentis Tracking in the SDK
     * Parameter config: Contains the Configuration to initialize the SDK
     */
    public void initTracking(TrackConfig config) {
        String consent = UserSettings.getInstance(context).getUserId();

        if (UserSettings.getInstance(context).getConsents() != null) {
            this.consents = UserSettings.getInstance(context).getConsents();
        }

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
    public void setConsents(HashMap<String, Boolean> consents, ResultHandler<Device> handler) {
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

//        sendConsentSettings(String consentId, Map<String, Boolean> consents, Map<String, Boolean> vendorsChanged)
//        sendConsentSettings(consentId: consentId, vendors: consents, vendorsChanged: vendorsChanged, completion: completion)
    }


    /**
     * Set debugging of tracking
     * Parameter shouldDebug: Bool to activate/deactivate debugging
     * Parameter debugId: (optional) Set the ID when enabling debugging
     * Parameter version: (otpional) Set the version when enabling debugging
     */
    public void setConsents(Boolean shouldDebug, String debugId, String version) {
        if(config == null){
            log.error("[JENTIS] Call initTracking first");
            return;
        }
        if(shouldDebug ){
            if(debugId != null && version != null) {
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
    public void push(Map<String, Object> data) {
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


        if (trackString == Tracking.TRACK.SUBMIT.toString()) {
//            submitPush()
        }
    }

    /**
     * Sends all consent settings to Jentis server
     *
     * @param consentId: the consentID that needs to be sent
     * @param vendors: contains the vendors list with their corresponding consent
     * @param vendorsChanged: contains the map of changed vendors with their corresponding consent
     */
    private void sendConsentSettings(String consentId, Map<String, Boolean> vendors, Map<String, Boolean> vendorsChanged, ResultHandler<Object> resultHandler) {

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

        Api.getInstance().setConsentSettings(trackingData, resultHandler);
//        API.shared.setConsentSettings(trackingData) { [weak self] result in
//
//            switch result {
//                case .success:
//                self.consents = vendors
//                    UserSettings.shared.setConsents(vendors)
//                    UserSettings.shared.setConsentId(consentId)
//                case .failure:
//                return
//            }
//
//            if self.isTrackingDisabled() {
//                // User disabled all Tracking options - discard tracking
//                self.storedTrackings = []
//                print("[JENTIS]: clearing stored trackings - all vendors are set to false")
//                return
//            }
//
//            // User set consents - send stored trackings
//            let currentlyStoredTrackings = self.storedTrackings
//
//            for storedTracking in currentlyStoredTrackings {
//                API.shared.submitTracking(storedTracking) { _ in }
//            }
//
//            self.storedTrackings = []
//        }
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

    public Boolean isTrackingDisabled() {
        if(consents == null) {
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

        return !allConsentsDisabled;
    }

    public Client getClient() {
        Client client = new Client();
        client.setClientTimestamp(System.currentTimeMillis());
        client.setDomain(Utils.deletingPrefix(config.trackDomain, Tracking.trackingDomainPrefix));
        return client;
    }
}
