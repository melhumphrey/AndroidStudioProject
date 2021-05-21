package com.example.connectwithfriends;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class AddEventActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    ArrayList<String> days = new ArrayList<>(Arrays.asList("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18",
            "19","20","21","22","23","24","25","26","27","28","29","30","31"));
    //ArrayList<String> months = new ArrayList<>(Arrays.asList("Jan","Feb","March","April","May","June","July","Aug","Sept","Oct","Nov","Dec"));
    ArrayList<String> months = new ArrayList<>(Arrays.asList("Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"));
    ArrayList<String> years = new ArrayList<>(Arrays.asList("2021","2022","2023"));
    ArrayList<String> hours = new ArrayList<>(Arrays.asList("1","2","3","4","5","6","7","8","9","10","11","12"));
    ArrayList<String> minutes = new ArrayList<>(Arrays.asList("00","15","30","45","60"));
    ArrayList<String> am_pm = new ArrayList<>(Arrays.asList("am","pm"));
    public Event event = new Event();

    public static final String EVENT_MESSAGE = "EVENT_MESSAGE";
    public static final String EVENT = "EVENT_DATE";
    public static final String START_HOUR = "START_HOUR";
    public static final String END_HOUR = "END_HOUR";
    public static final String PM_BOOL = "PM_BOOL";
    public static final String END_PM_BOOL = "END_PM_BOOL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        Spinner day_spinner = findViewById(R.id.day_spinner);
        day_spinner.setOnItemSelectedListener(this);
        ArrayAdapter day_ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item , days);
        day_ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        day_spinner.setAdapter(day_ad);

        Spinner month_spinner = findViewById(R.id.month_spinner);
        month_spinner.setOnItemSelectedListener(this);
        ArrayAdapter month_ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item , months);
        month_ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        month_spinner.setAdapter(month_ad);

        Spinner year_spinner = findViewById(R.id.year_spinner);
        year_spinner.setOnItemSelectedListener(this);
        ArrayAdapter year_ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item , years);
        year_ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year_spinner.setAdapter(year_ad);

        Spinner hour_spinner = findViewById(R.id.hour_spinner);
        hour_spinner.setOnItemSelectedListener(this);
        ArrayAdapter hour_ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item , hours);
        hour_ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hour_spinner.setAdapter(hour_ad);

        Spinner min_spinner = findViewById(R.id.min_spinner);
        min_spinner.setOnItemSelectedListener(this);
        ArrayAdapter min_ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item , minutes);
        min_ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        min_spinner.setAdapter(min_ad);

        Spinner ampm_spinner = findViewById(R.id.am_pm_spinner);
        ampm_spinner.setOnItemSelectedListener(this);
        ArrayAdapter ampm_ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item , am_pm);
        ampm_ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ampm_spinner.setAdapter(ampm_ad);

        Spinner hour_spinner2 = findViewById(R.id.hour_spinner2);
        hour_spinner2.setOnItemSelectedListener(this);
        hour_spinner2.setAdapter(hour_ad);

        Spinner min_spinner2 = findViewById(R.id.min_spinner2);
        min_spinner2.setOnItemSelectedListener(this);
        min_spinner2.setAdapter(min_ad);

        Spinner ampm_spinner2 = findViewById(R.id.am_pm_spinner2);
        ampm_spinner2.setOnItemSelectedListener(this);
        ampm_spinner2.setAdapter(ampm_ad);


        //TODO: add event details to intent
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.day_spinner) {
            event.setDay(days.get(position));
        } else if(parent.getId() == R.id.month_spinner) {
            event.setMonth(months.get(position));
        } else if(parent.getId() == R.id.year_spinner) {
            event.setYear(years.get(position));
        } else if(parent.getId() == R.id.hour_spinner) {
            event.getStart_time().setHour(hours.get(position));
        } else if(parent.getId() == R.id.min_spinner) {
            event.getStart_time().setMinute(minutes.get(position));
        } else if(parent.getId() == R.id.am_pm_spinner) {
            event.getStart_time().setPm(am_pm.get(position).equals("pm"));
        } else if(parent.getId() == R.id.hour_spinner2) {
            event.getEnd_time().setHour(hours.get(position));
        } else if(parent.getId() == R.id.min_spinner2) {
            event.getEnd_time().setMinute(minutes.get(position));
        } else if(parent.getId() == R.id.am_pm_spinner2) {
            event.getEnd_time().setPm(am_pm.get(position).equals("pm"));
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void ConfirmEvent(View view) {
        Intent intent = new Intent(this, CalendarActivity.class);
        EditText editText = (EditText) findViewById(R.id.event_name);
        String message = editText.getText().toString();
        intent.putExtra(EVENT_MESSAGE, message);
//        Spinner day = (Spinner) findViewById(R.id.day_spinner);
//        Spinner month = (Spinner) findViewById(R.id.month_spinner);
//        Spinner year = (Spinner) findViewById(R.id.year_spinner);
//        intent.putExtra(EVENT,AddEventActivity.event);
//        TextView textView = findViewById(R.id.sunday_event_space);
//        textView.setText(message);

        intent.putExtra(START_HOUR, event.getStart_time().getHour());
        intent.putExtra(PM_BOOL, event.getStart_time().isPm());
        intent.putExtra(END_HOUR, event.getEnd_time().getHour());
        intent.putExtra(END_PM_BOOL,event.getStart_time().isPm());
        startActivity(intent);
    }
}
