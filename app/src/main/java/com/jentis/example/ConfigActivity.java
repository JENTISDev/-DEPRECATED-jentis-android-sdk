package com.jentis.example;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.jentis.analytics.JentisTrackService;
import com.jentis.analytics.model.JentisTrackConfig;

public class ConfigActivity extends AppCompatActivity{

    EditText trackDomain, trackId, environment ;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        trackDomain = findViewById(R.id.trackDomainET);
        trackId = findViewById(R.id.trackIdET);
        environment = findViewById(R.id.environmentET);
        submit = findViewById(R.id.submitButton);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JentisTrackConfig config = new JentisTrackConfig(trackDomain.getText().toString(), trackId.getText().toString(), environment.getText().toString());
                JentisTrackService.getInstance().initTracking(getApplicationContext(), config);
            }
        });
    }
}