package com.example.marti.myapplication;

import android.Manifest;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.*;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {
    private Intent getStates, getWeather;
    private TextView textView;
    private Button button;
    private ArrayList<String> ids;
    private ArrayList<String> states;
    private SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        checkPermissions();

        getWeather = new Intent(MainActivity.this, GETweather.class);
        getStates = new Intent(MainActivity.this, GETfromMainServer.class);
        button = (Button) findViewById(R.id.button);
        startService(getStates);
        stopService(getStates);
        startService(getWeather);
        stopService(getWeather);

        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                final HashMap<String, ArrayList<String>> lights = GETfromMainServer.getAllLights();


                Toast.makeText(getApplicationContext(),"HEY",Toast.LENGTH_LONG).show();

                CountDownTimer timer = new CountDownTimer(10000, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish()
                    {
                        Toast.makeText(getApplicationContext(),lights.toString(),Toast.LENGTH_SHORT).show();
                        saveLightsState(lights);
                    }
                };
                timer.start();

               /* HashMap<String, ArrayList<String>> listLights = GETfromMainServer.getAllLights();
                ArrayList<String> ids = listLights.get("id");
                ArrayList<String> states = listLights.get("state");

                for (int i = 0; i < ids.size(); i++) {
                    Toast.makeText(getApplicationContext(), i + states.get(i), Toast.LENGTH_LONG).show();
                }*/

            }
        });
    }

    private void saveLightsState(HashMap<String,ArrayList<String>> lights)
    {
        System.out.print("a");

        ids = lights.get("id");
        states = lights.get("state");

        for (int i = 0; i < ids.size(); i++)
        {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("light"+ids.get(i),states.get(i));
        }

    }

    private void checkPermissions() {
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.RECORD_AUDIO}, 0);
    }
    public void weatherOn(View v){
        startActivity(new Intent(this, FragmentActivity.class));
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