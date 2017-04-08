package com.example.marti.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class FragmentClass extends Fragment {
    private TextView temperatureTV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fragment_class, container, false);


        final TextView temp= (TextView) rootView.findViewById(R.id.tempeature);
        final TextView clouds= (TextView) rootView.findViewById(R.id.clouds);
        final ImageView imageView= (ImageView) rootView.findViewById(R.id.imageView);

        final Intent getWeather = new Intent(getContext(), GETweather.class);
        final SharedPreferences sh = getContext().getSharedPreferences("User", 0);

        CountDownTimer timer = new CountDownTimer(4000, 1000) {
            public void onTick(long millisUntilFinished) {
            }
            public void onFinish() {
                String pocasi = sh.getString("pocasi", "");
                String teplota = sh.getString("teplota", "");
                temp.setText(String.valueOf(Double.parseDouble(teplota)-273));
                clouds.setText(pocasi);
                if(Objects.equals(pocasi, "Rain")){
                  //  imageView.setImageIcon(R.mipmap.weather);
                }

            }
        };
        timer.start();


        return rootView;
    }

}
