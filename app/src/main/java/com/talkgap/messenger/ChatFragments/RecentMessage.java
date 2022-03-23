package com.talkgap.messenger.ChatFragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.talkgap.messenger.AddFriend.AddFriendEng;
import com.talkgap.messenger.ChatList.ChatEngs;
import com.talkgap.messenger.ChatList.ChatListAdapter;
import com.talkgap.messenger.ChatMessage;
import com.talkgap.messenger.R;

import com.talkgap.messenger.ChatList.chatModel;
import com.talkgap.messenger.Roaster.Constants;

public class RecentMessage extends Fragment implements ChatListAdapter.OnItemClickListener, ChatListAdapter.OnItemLongClickListener {
  RecyclerView chrecyclerView;
  RecyclerView adrecyclerView;
  private static final String LOGTAG = "TalkGapChatConnection" ;
  ChatListAdapter adapter;

  View view;
  private BroadcastReceiver mbroadcastReceiver;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        view = inflater.inflate(R.layout.fragment_recent_message, container, false);
        chrecyclerView = view.findViewById(R.id.chatRec);

        LinearLayoutManager llm = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false );
        chrecyclerView.setLayoutManager(llm);
        chrecyclerView.setNestedScrollingEnabled(false);
        chrecyclerView.setHasFixedSize(false);


        adrecyclerView = view.findViewById(R.id.addfriends);
        adrecyclerView.setNestedScrollingEnabled(false);
        AddFriendEng adfriendeng = new AddFriendEng(adrecyclerView);
        adfriendeng.setRecyclerView();

        adapter = new ChatListAdapter(view.getContext());
        chrecyclerView.setAdapter(adapter);
        chrecyclerView.invalidate();

        adapter.setmOnItemClickListener(this);


        return view;
    }


    @Override
    public void onItemClick(String contactJid, chatModel.ContactType chatType) {
        Intent i =  new Intent(getActivity(), ChatMessage.class);
        i.putExtra("ContactJid", contactJid);
        i.putExtra("ChatType", chatType);
        startActivity(i);
    }

    @Override
    public void onItemLongClick(String contactJid, int chatUniqueId, View anchor) {

    }

    @Override
    public void onPause() {
        super.onPause();

        super.onPause();
        getActivity().unregisterReceiver(mbroadcastReceiver);

    }

    @Override
    public void onResume() {
        super.onResume();
        mbroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                switch (action)
                {
                    case Constants.BroadCastMessage.UI_NEW_CHAT_ITEM:
                        adapter.onChatCountChange();
                        return;
                }

            }
        };

        IntentFilter filter = new IntentFilter(Constants.BroadCastMessage.UI_NEW_CHAT_ITEM);
        getActivity().registerReceiver(mbroadcastReceiver,filter);


        Log.d(LOGTAG, "Act cre resume");
        adapter.onChatCountChange();
        adapter.notifyDataSetChanged();
        chrecyclerView.invalidate();

    }
}