package com.example.marti.myapplication;

import android.app.LauncherActivity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.concurrent.LinkedTransferQueue;

public class LedListItemAdapter extends ArrayAdapter<LedListItem>
{
    private int layoutResource;

    public LedListItemAdapter(Context context, int layoutResource)
    {
        super(context, layoutResource);
        this.layoutResource = layoutResource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view = convertView;

        if (view == null)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(layoutResource, null);
        }

        LedListItem listItem = getItem(position);

        if (listItem != null)
        {
            TextView ledTV = (TextView)view.findViewById(R.id.ledTV);
            ledTV.setText("Light "+listItem.id);
        }

        return view;
    }

}
