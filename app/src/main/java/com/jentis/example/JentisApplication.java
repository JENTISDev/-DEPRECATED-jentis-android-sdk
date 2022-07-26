package com.jentis.example;

import android.app.Application;

import com.jentis.analytics.JentisTrackService;
import com.jentis.analytics.model.JentisTrackConfig;

public class JentisApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        JentisTrackService.getInstance().initTracking(this, new JentisTrackConfig(getString(R.string.jentis_track_domain), getString(R.string.jentis_track_id), getString(R.string.jentis_environment)));
    }
}
