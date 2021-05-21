package com.example.connectwithfriends;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

class AuthenticationPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragmentList = new ArrayList<>();

    public AuthenticationPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    void addFragment(Fragment fragment) {
        fragmentList.add(fragment);
    }
}
