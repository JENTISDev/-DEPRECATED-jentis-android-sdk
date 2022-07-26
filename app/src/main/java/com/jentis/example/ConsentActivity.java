package com.jentis.example;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.jentis.analytics.JentisTrackService;
import com.jentis.analytics.model.JentisException;
import com.jentis.analytics.model.interfaces.ResultHandler;

import java.util.HashMap;
import java.util.Map;

public class ConsentActivity extends AppCompatActivity{

    Switch xandr, googleAnalytics, facebook, easyMarketing, criteo;
    Button submit ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consent);

        Map<String, Boolean> consents = JentisTrackService.getInstance().getCurrentConsents();
        xandr = findViewById(R.id.xandrSwitch);
        googleAnalytics = findViewById(R.id.googleSwitch);
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
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, Boolean> consents = new HashMap<String, Boolean>();
                consents.put("xandr", xandr.isChecked());
                consents.put("googleanalytics", googleAnalytics.isChecked());
                consents.put("facebook", facebook.isChecked());
                consents.put("easymarketing", easyMarketing.isChecked());
                consents.put("criteo", criteo.isChecked());

                JentisTrackService.getInstance().setConsents(consents, new ResultHandler<Object>() {
                    @Override
                    public void onSuccess(Object data) {

                    }

                    @Override
                    public void onFailure(JentisException e) {

                    }
                });
            }
        });
    }
}