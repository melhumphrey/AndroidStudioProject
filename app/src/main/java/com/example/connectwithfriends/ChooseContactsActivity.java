package com.example.connectwithfriends;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ChooseContactsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_contacts);
    }

    public void CompareCalendar(View view) {
        Intent intent = new Intent(this, CompareCalendarActivity.class);
        startActivity(intent);
    }
}
