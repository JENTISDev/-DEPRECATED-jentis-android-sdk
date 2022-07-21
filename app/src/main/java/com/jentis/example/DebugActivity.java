package com.jentis.example;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.jentis.tracking.TrackService;
import com.jentis.tracking.model.TrackConfig;

public class DebugActivity extends AppCompatActivity{

    Button submit ;
    EditText debugId, debugVersion ;
    Switch enabledSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);

        debugId = findViewById(R.id.debugIdET);
        debugVersion = findViewById(R.id.debugVersionET);
        enabledSwitch = findViewById(R.id.enabledSwitch);

        submit = findViewById(R.id.submitButton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TrackService.getInstance().debugTracking(enabledSwitch.isChecked(), debugId.getText().toString(), debugVersion.getText().toString());
            }
        });
    }
}