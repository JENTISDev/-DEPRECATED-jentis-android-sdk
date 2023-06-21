package com.jentis.example;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.jentis.tracking.sdk.JentisTrackService;

import java.util.HashMap;

public class TrackingActivity extends AppCompatActivity{

    EditText event, pageView, virtualPagePath ;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);

        event = findViewById(R.id.eventET);
        pageView = findViewById(R.id.pageViewET);
        virtualPagePath = findViewById(R.id.virtualPagePathET);
        submit = findViewById(R.id.submitButton);

        JentisTrackService.getInstance().debugTracking(true, getString(R.string.jentis_debug_id), getString(R.string.jentis_debug_version));

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap<String, Object> data = new HashMap<>();
                data.put("track", event.getText().toString());
                data.put("pagetitle", pageView.getText().toString());
                data.put("virtualPagePath", virtualPagePath.getText().toString());
                data.put("track", "submit");

                JentisTrackService.getInstance().push(data);
            }
        });
    }
}
