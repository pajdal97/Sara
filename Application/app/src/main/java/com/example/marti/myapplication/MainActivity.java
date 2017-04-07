package com.example.marti.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.speech.SpeechRecognizer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

            public void onClick(View v) {
                final String responce = GETfromMainServer.getAllLights().toString();
                CountDownTimer timer = new CountDownTimer(5000, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        Toast.makeText(getApplicationContext(),responce,Toast.LENGTH_LONG).show();

                    }
                };
                timer.start();
            }
        });
    }

    public void recognize(View view)
    {
        try
        {
            SpeechRecognition speechRecognition = new SpeechRecognition();
            speechRecognition.context = getApplicationContext();
            speechRecognition.startListen();
        }
        catch (Exception ex)
        {
            Log.e("SpeechError",ex.toString());
        }
    }

}