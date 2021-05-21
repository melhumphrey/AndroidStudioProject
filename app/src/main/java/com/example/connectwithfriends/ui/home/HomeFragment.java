package com.example.connectwithfriends.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.connectwithfriends.MessagingActivity;
import com.example.connectwithfriends.ProfileActivity;
import com.example.connectwithfriends.R;
import com.example.connectwithfriends.ui.contacts.ContactModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<NotificationItemModel> arrayList = new ArrayList<NotificationItemModel>();
    com.example.connectwithfriends.ui.home.HomeAdapter adapter;
    private com.example.connectwithfriends.ui.home.HomeAdapter.RecyclerViewClickListener listener;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = root.findViewById(R.id.recycler_view);

        NotificationItemModel model1 = new NotificationItemModel();
        model1.setName("Emma");
        model1.setText("Emma's birthday was yesterday");
        model1.setTimestamp("Today");
        arrayList.add(model1);

        NotificationItemModel model2 = new NotificationItemModel();
        model2.setName("Ben");
        model2.setText("You haven't talked to Ben in 2 weeks");
        model2.setTimestamp("Yesterday");
        arrayList.add(model2);

        NotificationItemModel model3 = new NotificationItemModel();
        model3.setName("Peter");
        model3.setText("You haven't talked to Peter in 4 weeks");
        model3.setTimestamp("Last Thursday");
        arrayList.add(model3);

        NotificationItemModel model4 = new NotificationItemModel();
        model4.setName("Jessica");
        model4.setText("You haven't talked to Jessica in 2 months");
        model4.setTimestamp("Last Monday");
        arrayList.add(model4);

        NotificationItemModel model5 = new NotificationItemModel();
        model5.setName("Sam");
        model5.setText("You haven't talked to Sam in 14 years");
        model5.setTimestamp("Last month");
        arrayList.add(model5);

        setOnClickListner();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        adapter = new com.example.connectwithfriends.ui.home.HomeAdapter(getActivity(),arrayList, listener);
        recyclerView.setAdapter(adapter);
        return root;
    }

    private void setOnClickListner() {
        listener = new com.example.connectwithfriends.ui.home.HomeAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getActivity().getApplicationContext(), MessagingActivity.class);
//                intent.putExtra("name", arrayList.get(position).getName());
//                intent.putExtra("text", arrayList.get(position).getText());
//                intent.putExtra("timestamp", arrayList.get(position).getTimestamp());
                startActivity(intent);
            }
        };
    }
}