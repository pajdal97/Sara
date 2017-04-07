package com.example.marti.myapplication;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    Intent getStates, getWeather;
    TextView textView;
    Button button;

    //Nothing

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermissions();

        getWeather = new Intent(this, GETweather.class);
        getStates = new Intent(this, GETfromMainServer.class);
        button = (Button) findViewById(R.id.button);
        startService(getStates);
        stopService(getStates);
        startService(getWeather);
        stopService(getWeather);

        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                HashMap<String, ArrayList<String>> listLights = GETfromMainServer.getAllLights();
                ArrayList<String> ids = listLights.get("id");
                ArrayList<String> states = listLights.get("state");

                for (int i = 0; i < ids.size(); i++) {
                    Toast.makeText(getApplicationContext(), i + states.get(i), Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void checkPermissions() {
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.RECORD_AUDIO}, 0);
    }

    public void recognize(View view) {
        try {
            SpeechRecognition speechRecognition = new SpeechRecognition();
            speechRecognition.context = getApplicationContext();
            speechRecognition.startListen();
        } catch (Exception ex) {
            Log.e("SpeechError", ex.toString());
        }
    }

}