package com.example.marti.myapplication;

import android.content.Context;
import android.util.Log;

import java.text.SimpleDateFormat;

import android.content.Context;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LedListItem
{
    public String id;
    public boolean state;

    public LedListItem(Context context, String id, boolean state)
    {
        this.id = id;
        this.state = state;
    }
}

