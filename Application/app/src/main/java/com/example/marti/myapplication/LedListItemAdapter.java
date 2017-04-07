package com.example.marti.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

public class LedListItemAdapter extends ArrayAdapter<LedListItem>
{
    private int layoutResource;

    public LedListItemAdapter(Context context, int layoutResource)
    {
        super(context, layoutResource);
        this.layoutResource = layoutResource;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
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
            TextView ledTV = (TextView) view.findViewById(R.id.ledTV);
            ledTV.setText("Light " + listItem.id);
            Switch sw = (Switch) view.findViewById(R.id.ledSwitch);
            ArrayList<String> arrayList = GETfromMainServer.getOnOffStates();

            if (Objects.equals(arrayList.get(position), "ON "))
            {
                sw.setChecked(true);
            } else
            {
                sw.setChecked(false);
            }

            /*
            Toast.makeText(getContext(),arrayList.toString(),Toast.LENGTH_LONG).show();
            for(int i=0;i<arrayList.size();i++){
                if (Objects.equals(arrayList.get(i), "ON ")){
                    sw.setChecked(true);
                }
                else {
                    sw.setChecked(false);
                }
            }
            sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    SharedPreferences pref = getContext().getSharedPreferences("User", 0);
                    SharedPreferences.Editor editor = pref.edit();

                    editor.putString("lightType", String.valueOf(position));
                    if (isChecked) {
                        editor.putString("switchType","ON");
                    } else {
                        editor.putString("switchType","OFF");
                    }
                    editor.apply();
                    Intent postState = new Intent(getContext(), POSTtoMainServer.class);
                    getContext().startService(postState);
                    getContext().stopService(postState);
                }
            });
            */
        }

        return view;
    }
}
