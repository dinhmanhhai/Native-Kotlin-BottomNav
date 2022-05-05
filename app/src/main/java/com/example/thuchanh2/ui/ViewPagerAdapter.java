package com.example.thuchanh2.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> fragmentList;
    ArrayList<String> title;

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        this.fragmentList = new ArrayList<>();
        this.title = new ArrayList<>();
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void addFragment(Fragment fragment, String title) {
        this.fragmentList.add(fragment);
        this.title.add(title);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }

}