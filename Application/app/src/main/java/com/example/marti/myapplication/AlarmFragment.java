package com.example.marti.myapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.ArrayList;


public class AlarmFragment extends Fragment{

    public ListView alarmList;
    public ArrayList<Texts> alarms;
    private int year, month, day , hours, minutes;
    private int yearFinal ,monthFinal , dayFinal , hoursFinal , minutesFinal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_alarm, container, false);
        alarmList = (ListView) v.findViewById(R.id.alarms);
        alarms = new ArrayList<>();
        final BaseAdapter adapter = new AlarmAdapter(getActivity(),alarms);
        alarmList.setAdapter(adapter);

        v.findViewById(R.id.add_alarm).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                addAlarm(adapter);
            }
        });

        return v;
    }

    public void addAlarm(final BaseAdapter adapter){
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, final int month, int dayOfMonth) {
                yearFinal = year;
                monthFinal = month+1;
                dayFinal = dayOfMonth;

                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute){
                        hours = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
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
                        alarms.add(new Texts(ym.toString(),hm.toString()));
                        adapter.notifyDataSetChanged();

                    }
                },hours, minutes, true);
                timePickerDialog.show();
            }
        },year,month,day);
        datePickerDialog.show();
    }

    public int getSeconds(){

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
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(context).inflate(R.layout.alarm_layout,null);
            TextView ym_time = (TextView) view.findViewById(R.id.year_month);
            TextView hm_time = (TextView) view.findViewById(R.id.hour_minute);
            ym_time.setText(alarms.get(position).getYm());
            hm_time.setText(alarms.get(position).getHm());
            return view;
        }
    }
}
