package com.jentis.example;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.jentis.tracking.Api;
import com.jentis.tracking.ToasterMessage;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ToasterMessage.s(this, "fdsafsda");
    }
}