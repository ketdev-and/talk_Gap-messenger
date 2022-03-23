package com.talkgap.messenger.messTab;

import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.talkgap.messenger.addFragment.Contact;
import com.talkgap.messenger.addFragment.Friend;

import com.google.android.material.tabs.TabLayout;

public class messTabEng {
    private FragmentManager fragmentManager;
    public messTabEng(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void CreateTab(TabLayout tabLayout, ViewPager viewPager){
        tabLayout.setupWithViewPager(viewPager);
        ViewPagerDemo(viewPager);
    }

    private void ViewPagerDemo(ViewPager viewPager) {
        messTabAdapter adapter = new messTabAdapter(this.fragmentManager);

        adapter.AddFragments(new Contact(), "Contacts");
        adapter.AddFragments(new Friend(), "Friend");
//        adapter.AddFragments(new Group(), "Group");


        viewPager.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


}
