package com.example.marti.myapplication;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class GETweather extends Service {
    public GETweather() {
    }

    public static HashMap<String, ArrayList<String>> data;


    public int onStartCommand(Intent intent, int flags, int startId) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://api.openweathermap.org/data/2.5/weather?q=Brno&appid=507113590c3c266513b9a946bfd30156";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    public void onResponse(String response) {
                        try {
                            JSONObject weatherInfo = new JSONObject(response);
                            for (int i = 0; i < weatherInfo.length(); i++) {
                                JSONArray jsonArray = weatherInfo.getJSONArray("weather");
                                JSONObject temature = weatherInfo.getJSONObject("main");

                                JSONObject js = jsonArray.getJSONObject(0);
                                Toast.makeText(getApplicationContext(), js.getString("main"), Toast.LENGTH_LONG).show();
                                Toast.makeText(getApplicationContext(), temature.getString("temp"), Toast.LENGTH_LONG).show();
                            }
                            SharedPreferences pref = getApplicationContext().getSharedPreferences("User", MODE_PRIVATE);
                            SharedPreferences.Editor editor = pref.edit();

                            Toast.makeText(getApplicationContext(), weatherInfo.toString(), Toast.LENGTH_LONG).show();
                            editor.apply();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        queue.add(stringRequest);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    static HashMap<String, ArrayList<String>> getWeatherData() {
        return data;
    }
}
