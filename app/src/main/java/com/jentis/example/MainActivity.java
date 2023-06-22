package com.jentis.example;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button config, debug, consent, tracking, product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        config = findViewById(R.id.configButton);
        debug = findViewById(R.id.debugButton);
        consent = findViewById(R.id.consentButton);
        tracking = findViewById(R.id.trackingButton);
        product = findViewById(R.id.productSampleButton);

        config.setOnClickListener(this);
        debug.setOnClickListener(this);
        consent.setOnClickListener(this);
        tracking.setOnClickListener(this);
        product.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.configButton:
                startActivity(new Intent(MainActivity.this, ConfigActivity.class));
                break;
            case R.id.debugButton:
                startActivity(new Intent(MainActivity.this, DebugActivity.class));
                break;
            case R.id.consentButton:
                startActivity(new Intent(MainActivity.this, ConsentActivity.class));
                break;
            case R.id.trackingButton:
                startActivity(new Intent(MainActivity.this, TrackingActivity.class));
                break;
            case R.id.productSampleButton:
                startActivity(new Intent(MainActivity.this, ProductSampleActivity.class));
                break;
            default:
                break;
        }
    }
}
