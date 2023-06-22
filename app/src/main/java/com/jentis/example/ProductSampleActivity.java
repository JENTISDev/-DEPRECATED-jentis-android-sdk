package com.jentis.example;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.jentis.tracking.sdk.JentisTrackService;

import java.util.HashMap;

public class ProductSampleActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_sample);

        JentisTrackService.getInstance().debugTracking(true, getString(R.string.jentis_debug_id), getString(R.string.jentis_debug_version));

        sendProductSample();
    }

    private void sendProductSample() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("track", "pageview");
        data.put("pagetitle", "Product Screen");
        data.put("virtualPagePath", "ProductScreen/TrackingScreen");
        JentisTrackService.getInstance().push(data);

        data.clear();
        data.put("track", "product");
        data.put("type", "order");
        data.put("id", 12_345_567);
        data.put("name", "Baby Oil");
        JentisTrackService.getInstance().push(data);

        data.clear();
        data.put("track", "product");
        data.put("type", "order");
        data.put("id", 11_111_111);
        data.put("name", "Nivea");
        data.put("missing", 110.0);
        JentisTrackService.getInstance().push(data);

        data.clear();
        data.put("track", "productview");
        data.put("type", "order");
        data.put("id", 9_999_999);
        data.put("name", "APP DEVELOPMENT");
        data.put("color", "blue");
        JentisTrackService.getInstance().push(data);

        data.clear();
        data.put("track", "order");
        JentisTrackService.getInstance().push(data);

        data.clear();
        data.put("track", "submit");
        JentisTrackService.getInstance().push(data);
    }
}
