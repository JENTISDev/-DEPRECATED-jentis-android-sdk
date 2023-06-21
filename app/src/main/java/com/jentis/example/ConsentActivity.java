package com.jentis.example;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.jentis.tracking.sdk.JentisTrackService;
import com.jentis.tracking.sdk.model.JentisException;
import com.jentis.tracking.sdk.model.JentisLogger;
import com.jentis.tracking.sdk.model.interfaces.ResultHandler;

import java.util.HashMap;
import java.util.Map;

public class ConsentActivity extends AppCompatActivity{

    private final String TAG = "ConsentActivity";

    Switch xandr, googleAnalytics, googleAnalyticsV4Server, facebook, easyMarketing, criteo;
    Button submit ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consent);

        Map<String, Boolean> consents = JentisTrackService.getInstance().getCurrentConsents();
        xandr = findViewById(R.id.xandrSwitch);
        googleAnalytics = findViewById(R.id.googleSwitch);
        googleAnalyticsV4Server = findViewById(R.id.googleV4ServerSwitch);
        facebook = findViewById(R.id.facebookSwitch);
        easyMarketing = findViewById(R.id.easymarketingSwitch);
        criteo = findViewById(R.id.criterioSwitch);

        submit = findViewById(R.id.submitButton);

        if(consents != null && consents.keySet().size() > 0){
            xandr.setChecked(consents.get("xandr"));
            googleAnalytics.setChecked(consents.get("googleanalytics"));
            facebook.setChecked(consents.get("facebook"));
            easyMarketing.setChecked(consents.get("easymarketing"));
            criteo.setChecked(consents.get("criteo"));
            googleAnalyticsV4Server.setChecked(consents.getOrDefault("google_analytics_4_server-side", false));
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, Boolean> consents = new HashMap<String, Boolean>();
                consents.put("xandr", xandr.isChecked());
                consents.put("googleanalytics", googleAnalytics.isChecked());
                consents.put("google_analytics_4_server-side", googleAnalyticsV4Server.isChecked());
                consents.put("facebook", facebook.isChecked());
                consents.put("easymarketing", easyMarketing.isChecked());
                consents.put("criteo", criteo.isChecked());

                JentisTrackService.getInstance().setConsents(consents, new ResultHandler<Boolean>() {
                    @Override
                    public void onSuccess(Boolean data) {
                        JentisLogger.getLogger(TAG).info( "consent set successfully");
                    }

                    @Override
                    public void onFailure(JentisException e) {
                        JentisLogger.getLogger(TAG).info( "consent set failed: $e");
                    }
                });
            }
        });
    }
}
