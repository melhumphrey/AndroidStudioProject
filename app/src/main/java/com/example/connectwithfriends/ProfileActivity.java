package com.example.connectwithfriends;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);
        TextView nameTxt = findViewById(R.id.nameTextView);
        TextView numberTxt = findViewById(R.id.numberTextView);
        CircleImageView imageView = findViewById(R.id.iv_image);
        Spinner dropdown = findViewById(R.id.spinner1);

        String name = "Name not set";
        String number = "Number not set";
        String photo = "Photo not set";
        String[] items = new String[]{"Low", "Medium", "High"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            name = extras.getString("name");
            number = extras.getString("number");
            photo = extras.getString("photo");
        }

        nameTxt.setText(name);
        numberTxt.setText(number);
        if(photo != null) {
            Picasso.get().load(photo).into(imageView);
        } else {
            imageView.setImageResource(R.mipmap.ic_launcher_round);
        }
    }

}