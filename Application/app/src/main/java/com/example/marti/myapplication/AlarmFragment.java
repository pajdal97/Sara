package com.example.marti.myapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.ArrayList;


public class AlarmFragment extends Fragment{

    public ListView alarmList;
    public ArrayList<Texts> alarms;
    public ArrayList<CountDownTimer> timers;
    public BaseAdapter adapter;
    private int year, month, day , hours, minutes;
    private int yearFinal ,monthFinal , dayFinal , hoursFinal , minutesFinal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_alarm, container, false);
        alarmList = (ListView) v.findViewById(R.id.alarms);
        alarms = new ArrayList<>();
        timers = new ArrayList<>();
        adapter = new AlarmAdapter(getActivity(),alarms);
        alarmList.setAdapter(adapter);


        v.findViewById(R.id.add_alarm).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                addAlarm();
            }
        });
        return v;
    }

    public void addAlarm(){
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, final int year, final int month, int dayOfMonth) {
                yearFinal = year;
                monthFinal = month+1;
                dayFinal = dayOfMonth;

                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute){
                        hours = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
                        if(hours == 23){
                            hours = 1;
                        }else if(hours ==22){
                            hours = 0;
                        }else{
                            hours+=2;
                        }
                        minutes = Calendar.getInstance().get(Calendar.MINUTE);
                        hoursFinal = hourOfDay;
                        minutesFinal = minute;

                        StringBuilder ym = new StringBuilder();
                        ym.append(""+getMonth(monthFinal)+" ");
                        ym.append(""+yearFinal);

                        StringBuilder hm = new StringBuilder();
                        addZero(hm,hoursFinal);
                        hm.append(""+hoursFinal+":");
                        addZero(hm,minutesFinal);

                        hm.append(""+minutesFinal+" "+getDayStage(hoursFinal));
                        if(getSeconds()>0 && day<=dayFinal && month<=monthFinal && year<= yearFinal){
                            alarms.add(new Texts(ym.toString(),hm.toString(),getSeconds()));
                            adapter.notifyDataSetChanged();
                            new MyTimer(alarms.size()-1);
                        }else{
                            Toast.makeText(getActivity(),"Not a validate datum",Toast.LENGTH_SHORT).show();
                        }
                    }
                },hours, minutes, true);
                timePickerDialog.show();
            }
        },year,month,day);
        datePickerDialog.show();
    }

    private class MyTimer{
        public MyTimer(final int index){
            timers.add(new CountDownTimer(getSeconds()*1000,1000){
                @Override
                public void onTick(long millisUntilFinished) {
                    alarms.get(index).setSecondsRemain(millisUntilFinished/1000);
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onFinish() {
                    Intent serv = new Intent(getContext(),POSTtoMainServer.class);
                    SharedPreferences pref = getContext().getSharedPreferences("User", 0);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("switchType", "ON");
                    editor.putString("lightType", "0");
                    editor.apply();
                    getContext().startService(serv);
                    getContext().stopService(serv);
                }
            }.start());
        }
    }

    public int getSeconds(){
        int a = minutes*60 + hours*3600;//to seconds
        int b = minutesFinal*60 + hoursFinal*3600;//final to seconds

        if(a<b){
            return (b-a);
        }else{
            return (3600*24*(dayFinal-day))-(a-b);
        }
    }

    public void addZero(StringBuilder builder,int value){
        if(value < 10){
            builder.append("0");
        }
    }

    public String getDayStage(int hours){
        return hours>0 && hours<12? "AM":"PM";
    }

    public String getMonth(int month){
        switch(month){
            case 1: return "January";
            case 2: return "February";
            case 3: return "March";
            case 4: return "April";
            case 5: return "May";
            case 6: return "June";
            case 7: return "July";
            case 8: return "August";
            case 9: return "September";
            case 10:return "October";
            case 11:return "November";
            case 12:return "December";
            default:return "none";
        }
    }

    public class AlarmAdapter extends BaseAdapter{

        private ArrayList<Texts> alarms;
        private Context context;

        public AlarmAdapter(Context context, ArrayList<Texts> alarms){
            this.context  = context;
            this.alarms = alarms;
        }

        @Override
        public int getCount() {
            return alarms.size();
        }

        @Override
        public Object getItem(int position) {
            return alarms.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(context).inflate(R.layout.alarm_layout, null);
            try {
                TextView ym_time = (TextView) view.findViewById(R.id.year_month);
                TextView hm_time = (TextView) view.findViewById(R.id.hour_minute);
                TextView time_remain = (TextView) view.findViewById(R.id.seconds_remaining);
                ym_time.setText(alarms.get(position).getYm());
                hm_time.setText(alarms.get(position).getHm());
                time_remain.setText("(remaining " + (int) alarms.get(position).getSecondsRemain() + " s)");

                ImageButton deleteButton = (ImageButton) view.findViewById(R.id.delete);

                deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            alarms.remove(position);
                            timers.get(position).cancel();
                            timers.remove(position);
                            adapter.notifyDataSetChanged();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }catch(Exception e){
                e.printStackTrace();
            }
            return view;
        }
    }
}
