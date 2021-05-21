package com.example.connectwithfriends.ui.contacts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ContactsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ContactsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Cannot find any contacts on the phone.");
    }

    public LiveData<String> getText() {
        return mText;
    }
}