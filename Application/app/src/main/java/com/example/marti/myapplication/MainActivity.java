package com.example.marti.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    Intent getService;
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        button=(Button)findViewById(R.id.button);
        setContentView(R.layout.activity_main);
        getService = new Intent(this, GETweather.class);
        startService(getService);
        stopService(getService);
        final HashMap<String, String> data = GETweather.getWeatherData();
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                textView.setText(data.get("data"));
            }
        });
    }
}