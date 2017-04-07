package com.example.marti.myapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.text.DateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;


public class AlarmFragment extends Fragment {

    public ListView alarmList;
    public ArrayList<TextView> alarms;
    private int year, month, day , hours, minutes;
    private int yearFinal ,monthFinal , dayFinal , hoursFinal , minutesFinal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_alarm, container, false);
        alarmList = (ListView) v.findViewById(R.id.alarms);
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

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                yearFinal = year;
                monthFinal = month;
                dayFinal = dayOfMonth;

                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hoursFinal = hourOfDay;
                        minutesFinal = minute;
                    }
                },hours, minutes, true);
                timePickerDialog.show();
            }
        },year,month,day);
        datePickerDialog.show();
    }
}
