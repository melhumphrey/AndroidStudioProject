package com.example.connectwithfriends.ui.contacts;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.connectwithfriends.AddContactActivity;
import com.example.connectwithfriends.ProfileActivity;
import com.example.connectwithfriends.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ContactsFragment extends Fragment {

    RecyclerView recyclerView;
    TextView textView;
    private ContactsViewModel contactsViewModel;

    ArrayList<ContactModel> arrayList = new ArrayList<ContactModel>();
    com.example.connectwithfriends.ui.contacts.MainAdapter adapter;
    private com.example.connectwithfriends.ui.contacts.MainAdapter.RecyclerViewClickListener listener;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_contacts, container, false);

        recyclerView = root.findViewById(R.id.recycler_view);

        textView = root.findViewById(R.id.text_contacts);

        int count = checkPermission();

        if (count == 0) {
            contactsViewModel =
                    new ViewModelProvider(this).get(ContactsViewModel.class);
            root = inflater.inflate(R.layout.fragment_no_contacts, container, false);
            final TextView textView = root.findViewById(R.id.text_contacts);
            contactsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
                @Override
                public void onChanged(@Nullable String s) {
                    textView.setText(s);
                }
            });
        }

        FloatingActionButton fab = root.findViewById(R.id.add_contact);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), AddContactActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }

    private int checkPermission() {
        int count;
        if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext()
                , Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(getActivity()
                    ,new String[]{Manifest.permission.READ_CONTACTS},100);
            count = -1;
        } else {
            count = getContactList();
        }
        return count;
    }

    private int getContactList() {
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        String sort = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" ASC";
        Cursor cursor = getActivity().getApplicationContext().getContentResolver().query(
                uri,null,null,null,sort
        );
        int count = cursor.getCount();
        if (cursor.getCount() > 0) {
            textView.setVisibility(View.INVISIBLE);
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex(
                        ContactsContract.Contacts._ID
                ));
                String name  = cursor.getString(cursor.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME
                ));
                Uri uriPhone = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                String selection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                        +" =?";
                Cursor phoneCursor = getActivity().getApplicationContext().getContentResolver().query(
                        uriPhone,null,selection,new String[]{id},null
                );
                if (phoneCursor.moveToNext()) {
                    String number = phoneCursor.getString(phoneCursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER
                    ));
                    String photo = phoneCursor.getString(phoneCursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.PHOTO_URI
                    ));
                    ContactModel model = new ContactModel();
                    model.setName(name);
                    model.setNumber(number);
                    model.setPhoto(photo);
                    arrayList.add(model);
                    phoneCursor.close();
                }
            }
            cursor.close();
        }
        setOnClickListner();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        adapter = new com.example.connectwithfriends.ui.contacts.MainAdapter(getActivity(),arrayList, listener);
        recyclerView.setAdapter(adapter);
        return count;
    }

    private void setOnClickListner() {
        listener = new com.example.connectwithfriends.ui.contacts.MainAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getActivity().getApplicationContext(), ProfileActivity.class);
                intent.putExtra("name", arrayList.get(position).getName());
                intent.putExtra("number", arrayList.get(position).getNumber());
                intent.putExtra("photo", arrayList.get(position).getPhoto());
                startActivity(intent);
            }
        };
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100 && grantResults.length > 0 && grantResults[0]
                == PackageManager.PERMISSION_GRANTED) {
            getContactList();
        } else {
            Toast.makeText(getActivity().getApplicationContext(),"Permission Denied."
                    ,Toast.LENGTH_SHORT).show();
            checkPermission();
        }
    }
}