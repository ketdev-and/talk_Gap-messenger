package com.talkgap.messenger.addFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.talkgap.messenger.AddFriend.AddFriendEngI;
import com.talkgap.messenger.Online.OnlineEng;
import com.talkgap.messenger.R;

public class Friend extends Fragment {
    RecyclerView recyclerView, recyclerView2;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_friends2, container, false);
        recyclerView = view.findViewById(R.id.New_friend_rec);
        recyclerView2 = view.findViewById(R.id.friend_suggestions);

        recyclerView.setNestedScrollingEnabled(false);
        recyclerView2.setNestedScrollingEnabled(false);

        OnlineEng onlineeng = new OnlineEng(recyclerView);
        onlineeng.setRecyclerView();

        AddFriendEngI cng =  new AddFriendEngI(recyclerView2);
        cng.setRecyclerView();
        return view;
    }
}