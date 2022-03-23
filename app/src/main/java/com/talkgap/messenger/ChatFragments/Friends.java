package com.talkgap.messenger.ChatFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.talkgap.messenger.Online.OnlineEng;
import com.talkgap.messenger.R;


public class Friends extends Fragment {
    RecyclerView chrecyclerView;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      view = inflater.inflate(R.layout.fragment_friends, container, false);

        chrecyclerView = view.findViewById(R.id.online_rec);
        chrecyclerView.setNestedScrollingEnabled(false);
        OnlineEng onlineeng = new OnlineEng(chrecyclerView);
        onlineeng.setRecyclerView();

        return view;
    }
}