package com.example.marti.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class LedListItemAdapter extends ArrayAdapter<LedListItem> {
    private int layoutResource;

    public LedListItemAdapter(Context context, int layoutResource) {
        super(context, layoutResource);
        this.layoutResource = layoutResource;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(layoutResource, null);
        }

        LedListItem listItem = getItem(position);

        if (listItem != null) {
            TextView ledTV = (TextView) view.findViewById(R.id.ledTV);
            ledTV.setText("Light " + listItem.id);
            Switch sw = (Switch) view.findViewById(R.id.ledSwitch);
            sw.setChecked(true);
            sw.setChecked(listItem.state);

            sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    SharedPreferences pref = getContext().getSharedPreferences("User", 0);
                    SharedPreferences.Editor editor = pref.edit();

                    editor.putString("lightType", String.valueOf(position));
                    if (isChecked) {
                        editor.putString("switchType", "ON");
                    } else {
                        editor.putString("switchType", "OFF");
                    }
                    editor.apply();
                    Intent postState = new Intent(getContext(), POSTtoMainServer.class);
                    getContext().startService(postState);
                    getContext().stopService(postState);
                }
            });
        }

        return view;
    }
}
