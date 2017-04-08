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

public class GETfromMainServer extends Service {

    private static ArrayList<String> statesOnOff;
    public GETfromMainServer() {
    }

    static HashMap<String, ArrayList<String>> allStates = new HashMap<>();

    public int onStartCommand(Intent intent, int flags, int startId){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.238:5000/lights/API";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>(){
                    public void onResponse(String response){
                        try {
                            ArrayList<String> listState = new ArrayList<>();
                            statesOnOff=listState;
                            ArrayList<String> listId = new ArrayList<>();
                            JSONArray allLights = new JSONArray(response);

                            SharedPreferences pref = getApplicationContext().getSharedPreferences("User", MODE_PRIVATE);

                            SharedPreferences.Editor editor = pref.edit();
                            for (int i = 0; i < allLights.length(); i++) {
                                JSONObject light = allLights.getJSONObject(i);
                                listId.add(i, light.getString("id"));
                                listState.add(i, light.getString("state"));
                            }
                            allStates.put("state", listState);
                            allStates.put("id", listId);
                            editor.putInt("cislo", allLights.length());
                            editor.apply();

                        } catch (JSONException ignored) {
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(stringRequest);
        this.stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    static HashMap<String, ArrayList<String>> getAllLights() {
        return allStates;
    }

    static ArrayList<String>getOnOffStates(){
        return statesOnOff;
    }
}
