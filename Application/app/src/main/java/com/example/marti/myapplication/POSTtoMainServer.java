package com.example.marti.myapplication;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class POSTtoMainServer extends Service {
    public POSTtoMainServer() {
    }

    static HashMap<String, ArrayList<String>> allStates = new HashMap<>();

    public int onStartCommand(Intent intent, int flags, int startId) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.238:5000/lights/API";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    }
                }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                SharedPreferences pref = getApplicationContext().getSharedPreferences("User", MODE_PRIVATE);
                params.put("id", pref.getString("lightType", "0"));
                params.put("state", pref.getString("switchType", "ON"));
                return params;
            }
        };
        queue.add(stringRequest);
        return super.onStartCommand(intent, flags, startId);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}

