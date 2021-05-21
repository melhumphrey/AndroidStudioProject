package com.example.connectwithfriends;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DeleteEventActivity extends AppCompatActivity {

    public static final String DELETE_EVENT = "DELETE_EVENT";
    public boolean event1used = false;
    public Integer friday1;
    public Integer friday2;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_event);

        Set<Integer> keys = CalendarActivity.getEvents().keySet();
        List<Integer> key_list = new ArrayList<>(keys);
        if (CalendarActivity.getEvents().size() > 4) {
            friday1 = key_list.get(4);
        }
        if (CalendarActivity.getEvents().size() > 5) {
            friday2 = key_list.get(5);
        }

//        TextView view = findViewById(R.id.sunday_checkbox_delete);
//        view.setText("Sunday Event 1");
//        view.setBackground(getResources().getDrawable(R.drawable.rounded_edit_text));
    }

    public void ConfirmDeleteEvent(View view) {
        Intent intent = new Intent(this, CalendarActivity.class);
        CheckBox monday = (CheckBox) findViewById(R.id.monday_checkbox_delete);
        CheckBox wednesday = (CheckBox) findViewById(R.id.wednesday_checkbox_delete);
        CheckBox thursday = (CheckBox) findViewById(R.id.thursday_checkbox_delete);
        CheckBox friday = (CheckBox) findViewById(R.id.friday_checkbox_delete);
        CheckBox sunday1 = (CheckBox) findViewById(R.id.sunday_checkbox_delete);
        CheckBox sunday2 = (CheckBox) findViewById(R.id.sunday2_checkbox_delete);

        if (monday.isChecked()) {
            intent.putExtra(DELETE_EVENT,R.id.monday_all_day_event_space);
        } else if (wednesday.isChecked() && !event1used) {
            intent.putExtra(DELETE_EVENT,R.id.wednesday_all_day_event_space);
        } else if (thursday.isChecked() && !event1used) {
            intent.putExtra(DELETE_EVENT,R.id.thursday_all_day_event_space);
        } else if (friday.isChecked() && !event1used) {
            intent.putExtra(DELETE_EVENT,R.id.friday_all_day_event_space);
        } else if (sunday1.isChecked() && !event1used) {
            intent.putExtra(DELETE_EVENT,friday1);
        } else if (sunday2.isChecked() && !event1used) {
            intent.putExtra(DELETE_EVENT,friday2);
        }

        startActivity(intent);
    }
}
