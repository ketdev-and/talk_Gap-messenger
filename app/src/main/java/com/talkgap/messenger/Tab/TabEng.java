package com.talkgap.messenger.Tab;

import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.talkgap.messenger.ChatFragments.Friends;
import com.talkgap.messenger.ChatFragments.RecentMessage;
import com.google.android.material.tabs.TabLayout;

public class TabEng {
    private FragmentManager fragmentManager;
    public TabEng(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void CreateTab(TabLayout tabLayout, ViewPager viewPager){
        tabLayout.setupWithViewPager(viewPager);
        ViewPagerDemo(viewPager);
    }

    private void ViewPagerDemo(ViewPager viewPager) {
        TabAdapter adapter = new TabAdapter(this.fragmentManager);
        adapter.AddFragments(new RecentMessage(), "Chats");
        adapter.AddFragments(new Friends(), "Online");

        viewPager.setAdapter(adapter);
    }


}
