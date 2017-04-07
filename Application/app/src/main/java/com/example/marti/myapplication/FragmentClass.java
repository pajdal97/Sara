package com.example.marti.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class FragmentClass extends Fragment
{
    private HashMap<String, ArrayList<String>> weather;
    private TextView temperatureTV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fragment_class, container, false);

        //temperatureTV = (TextView) rootView.findViewById(R.id.temperatureTV);

        Intent getWeather = new Intent(getContext(), GETweather.class);

        getContext().startService(getWeather);
        getContext().stopService(getWeather);

        weather = GETweather.getWeatherData();

        CountDownTimer timer = new CountDownTimer(8000, 1000) {
            public void onTick(long millisUntilFinished) {
            }

            public void onFinish()
            {
                //Toast.makeText(getContext(),weather.toString(),Toast.LENGTH_SHORT).show();
            }
        };
        timer.start();


        return rootView;
    }

}
