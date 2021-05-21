package com.example.connectwithfriends;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class CompareCalendarActivity extends AppCompatActivity {

    public static final String TIME = "CHOSEN_TIME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_calendar);
    }

    public void ConfirmChosenTime(View view) {
        Intent intent = new Intent(this, CalendarActivity.class);
        CheckBox first_time = (CheckBox) findViewById(R.id.first_suggested_time);
        if (first_time.isChecked()) {
            intent.putExtra(TIME,"Friday 8am");
        } else {
            intent.putExtra(TIME,"Sunday 12pm");
        }
        startActivity(intent);
    }

}
