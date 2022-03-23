package com.talkgap.messenger.messTab;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class messTabAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> Fragments = new ArrayList<>();
    ArrayList<String> Title = new ArrayList<>();
    public messTabAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return Fragments.get(position);
    }

    @Override
    public int getCount() {
        return Fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return Title.get(position);
    }

    public void AddFragments(Fragment fragment, String name){
        Fragments.add(fragment);
        Title.add(name);
        notifyDataSetChanged();
    }
}
