package com.jentis.tracking;

import android.content.Context;

import com.jentis.tracking.model.Parent;
import com.jentis.tracking.model.Property;
import com.jentis.tracking.model.System;
import com.jentis.tracking.model.TrackConfig;
import com.jentis.tracking.model.Tracking;
import com.jentis.tracking.model.TrackingDataDatum;
import com.jentis.tracking.model.enums.Action;
import com.jentis.tracking.model.enums.DocumentType;
import com.jentis.tracking.model.enums.TrackEnum;

import java.util.Date;
import java.util.Map;

public class TrackServiceUtils {

    public static TrackingDataDatum getUserData(Parent parent, TrackConfig config) {
        TrackingDataDatum userData = new TrackingDataDatum();
        userData.setId(parent.getUser());
        userData.setAction(Action.UDP.toString());
        userData.setDocumentType(DocumentType.USER.toString());

        System userDataSystem = new System();
        userDataSystem.setEnvironment(Tracking.systemEnvironment);
        userData.setSystem(userDataSystem);

        userData.setAccount(getAccount(config));

        return userData;
    }

    public static TrackingDataDatum getConsentData(Parent parent, String consentId, Map<String, Boolean> vendors, Map<String, Boolean> vendorsChanged, TrackConfig config, Context context) {
        Long timestamp = java.lang.System.currentTimeMillis();
        UserSettings.getInstance(context).setLastConsentUpdate(timestamp);

        TrackingDataDatum consentData = new TrackingDataDatum();
        consentData.setId(Utils.generateRandomUUID());
        consentData.setDocumentType(DocumentType.CONSENT.toString());
        consentData.setAction(Action.NEW.toString());

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

    private static String getAccount(TrackConfig config) {
        if(config == null){
            return "";
        }

        return String.format("%1$s.%1$s", config.trackID, config.environment.toString());
    }
}
