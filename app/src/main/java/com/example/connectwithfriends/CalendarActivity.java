package com.example.connectwithfriends;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import com.google.android.material.snackbar.Snackbar;


public class CalendarActivity extends AppCompatActivity {

    private static Map<Integer,String> events = new HashMap<Integer, String>();
    private static int here = 0;

    public static Map<Integer,String> getEvents(){
        return events;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        here ++;

        //setup
        if (here == 1) {
            events.put(R.id.monday_all_day_event_space,"");
            events.put(R.id.tuesday_all_day_event_space,"");
            events.put(R.id.thursday_all_day_event_space,"");
            events.put(R.id.friday_all_day_event_space,"");
        }


        Intent intent = getIntent();
        // figure out where to put object
        if (intent.hasExtra(AddEventActivity.EVENT_MESSAGE)) {
            String message = intent.getStringExtra(AddEventActivity.EVENT_MESSAGE);
            String start_hour = intent.getStringExtra(AddEventActivity.START_HOUR);
            boolean pm = intent.getBooleanExtra(AddEventActivity.PM_BOOL,true);
            String end_hour = intent.getStringExtra(AddEventActivity.END_HOUR);
            boolean end_pm = intent.getBooleanExtra(AddEventActivity.END_PM_BOOL,true);
            int eventId = getTimeId(start_hour,pm);
            TextView textView = findViewById(eventId);
            textView.setText(message);
            textView.setBackground(getResources().getDrawable(R.drawable.rounded_edit_text));
            events.put(eventId,message);
        }

        if (intent.hasExtra(CompareCalendarActivity.TIME)) {
            String time = intent.getStringExtra(CompareCalendarActivity.TIME);
            TextView textView;
            int eventId;
            if (time.equals("Friday 8am")) {
                eventId = R.id.friday_friend_event_space;
            } else {
                eventId = R.id.sunday_friend_event_space;
                TextView textView2 = findViewById(R.id.sunday_friend_event_space);
                textView2.setBackground(getResources().getDrawable(R.drawable.rounded_edit_text));
                events.put(eventId,"");
            }
            textView = findViewById(eventId);
            textView.setBackground(getResources().getDrawable(R.drawable.rounded_edit_text));
            events.put(eventId,"pending busy time");

            View view = findViewById(R.id.notification_sent_view);
            Snackbar eventSnackbar = Snackbar.make(view,R.string.notification_sent,8000);
            eventSnackbar.show();
        }

        if (intent.hasExtra(DeleteEventActivity.DELETE_EVENT)) {
            int event = intent.getIntExtra(DeleteEventActivity.DELETE_EVENT,0);
            synchronized (events) {
                if (events.containsKey(event)) {
                    TextView view = findViewById(event);
                    view.setBackground(getResources().getDrawable(R.drawable.blank_box));
                    events.remove(event);

                }
            }


        }


        for (Integer key : events.keySet()) {
            TextView textView = findViewById(key);
            textView.setText(events.get(key));
            textView.setBackground(getResources().getDrawable(R.drawable.rounded_edit_text));
        }
    }

    public void AddEvent(View view) {
        Intent intent = new Intent(this, AddEventActivity.class);
        startActivity(intent);
    }

    public void DeleteEvent(View view) {
        Intent intent = new Intent(this, DeleteEventActivity.class);
        startActivity(intent);
    }

    public void ChooseContacts(View view) {
        Intent intent = new Intent(this, ChooseContactsActivity.class);
        startActivity(intent);
    }

    public void CompareCalendar(View view) {
        Intent intent = new Intent(this, CompareCalendarActivity.class);
        startActivity(intent);
    }


    protected int getTimeId(String start_hour, boolean start_pm) {
        int event_id;
        int am_event_id = 0;
        int pm_event_id = 0;
        switch (start_hour) {
            case "12":
            case "1":
                am_event_id = R.id.sunday12am_event_space;
                pm_event_id = R.id.sunday12pm_event_space;
                break;
            case "2":
            case "3":
                am_event_id = R.id.sunday2am_event_space;
                pm_event_id = R.id.sunday2pm_event_space;
                break;
            case "4":
            case "5":
                am_event_id = R.id.sunday4am_event_space;
                pm_event_id = R.id.sunday4pm_event_space;
                break;
            case "6":
            case "7":
                am_event_id = R.id.sunday6am_event_space;
                pm_event_id = R.id.sunday6pm_event_space;
                break;
            case "8":
            case "9":
                am_event_id = R.id.sunday8am_event_space;
                pm_event_id = R.id.sunday8pm_event_space;
                break;
            case "10":
            case "11":
                am_event_id = R.id.sunday10am_event_space;
                pm_event_id = R.id.sunday10pm_event_space;
                break;
        }
        if (start_pm) {
            event_id = pm_event_id;
        } else {
            event_id = am_event_id;
        }

        return event_id;
    }

}