package com.jentis.example;

import android.app.Application;

import com.jentis.tracking.TrackService;
import com.jentis.tracking.model.TrackConfig;

public class JentisApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        TrackService.getInstance().initTracking(this, new TrackConfig(getString(R.string.jentis_track_domain), getString(R.string.jentis_track_id), getString(R.string.jentis_environment)));
    }
}
