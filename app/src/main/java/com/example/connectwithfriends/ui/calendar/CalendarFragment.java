package com.example.connectwithfriends.ui.calendar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.example.connectwithfriends.CalendarActivity;
import com.example.connectwithfriends.R;


public class CalendarFragment extends Fragment {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Intent intent = new Intent(CalendarFragment.this.getActivity(), CalendarActivity.class);
        startActivity(intent);

        View view = inflater.inflate(R.layout.activity_calendar, container, false);

        return view;
    }

}