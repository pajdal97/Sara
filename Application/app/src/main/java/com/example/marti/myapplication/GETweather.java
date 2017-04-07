package com.example.marti.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;

public class GETweather extends Service {
    private static HashMap<String, String> data;

    public GETweather() {
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://api.openweathermap.org/data/2.5/weather?q=Brno&appid=507113590c3c266513b9a946bfd30156";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        data.put("data",response);
                    }
                }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),
                        error.getMessage(),Toast.LENGTH_LONG).show();
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
    static HashMap<String, String> getWeatherData(){
        return data;
    }
}
