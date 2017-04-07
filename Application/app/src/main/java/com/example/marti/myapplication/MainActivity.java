package com.example.marti.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    Intent getStates, getWeather;
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWeather = new Intent(this, GETweather.class);
        getStates = new Intent(this, GETfromMainServer.class);
        button = (Button) findViewById(R.id.button);
        startService(getStates);
        stopService(getStates);
        startService(getWeather);
        stopService(getWeather);
        final HashMap<String, ArrayList<String>> data = GETweather.getWeatherData();
        button.setOnClickListener(new View.OnClickListener() {

            String responce = GETfromMainServer.getAllLights().toString();
            public void onClick(View v) {
                CountDownTimer timer = new CountDownTimer(10000, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        textView.setText( responce);
                    }
                };
                timer.start();
            }
        });
    }
}