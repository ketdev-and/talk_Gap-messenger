package com.talkgap.messenger.addFragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.talkgap.messenger.AddFriend.AddFriendEng;


import com.talkgap.messenger.ChatFragments.RecentMessage;
import com.talkgap.messenger.ChatList.ChatEngs;
import com.talkgap.messenger.ChatList.ChatListAdapter;
import com.talkgap.messenger.ChatList.chatModel;
import com.talkgap.messenger.ChatMessage;
import com.talkgap.messenger.Chat_message.ChatMessageModel;
import com.talkgap.messenger.R;
import com.talkgap.messenger.contacts.ContactListAdapter;
import com.talkgap.messenger.contacts.ContactListAdaptertwo;
import com.talkgap.messenger.contacts.Contacts;
import com.talkgap.messenger.contacts.ContactsModel;

import java.util.ArrayList;
import java.util.List;


public class Contact extends Fragment implements ContactListAdapter.OnItemClickListener, ContactListAdapter.OnItemLongClickListerner {
    View view;
    public static final String LOGCAT = "TalkGapChatConnection";
    RecyclerView recyclerView, recyclerView2, recyclerView3;
    Button s;
    ContactListAdapter adapter;
    ContactListAdaptertwo adaptertwo;
    ChatListAdapter chatListAdapter;
    ImageView remove, details;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_contact, container, false);
        recyclerView = view.findViewById(R.id.contact_rec);
        recyclerView2 = view.findViewById(R.id.contact_rec2);
        recyclerView3 = view.findViewById(R.id.contact_suggestions);
        s = view.findViewById(R.id.s);
        remove = view.findViewById(R.id.removecon);
        details = view.findViewById(R.id.detailsbtncon);

        recyclerView.setNestedScrollingEnabled(false);
        recyclerView2.setNestedScrollingEnabled(false);
        recyclerView3.setNestedScrollingEnabled(false);


        LinearLayoutManager llm = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false );
        recyclerView.setLayoutManager(llm);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(false);

         adapter = new ContactListAdapter(getActivity());
         recyclerView.setAdapter(adapter);
         recyclerView.invalidate();

         adapter.setmOnItemClickListener(this);
         adapter.setmOnItemLongClickListerner(this);

        LinearLayoutManager llm2 = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false );
        recyclerView2.setLayoutManager(llm2);
        recyclerView2.setNestedScrollingEnabled(false);
        recyclerView2.setHasFixedSize(false);

        adaptertwo = new ContactListAdaptertwo(getActivity(), getContacttwo());
        recyclerView2.setAdapter(adaptertwo);
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addContacts();
            }
        });
        AddFriendEng cng3 =  new AddFriendEng(recyclerView3);
        cng3.setRecyclerView();



        return view;
    }

    public void addContacts(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add Contact");
        // Set up the intput
        final EditText input = new EditText(getActivity());
        //Specify the type of input expected; this, for example, sets the input as a password, and word.
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // set up the buttons
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d(LOGCAT, "user clicked on Ok");
//                ContactModel model = new ContactModel(getContext());
//                model.setUsername(input.getText().toString());
//                model.setSubscriptionType(ContactModel.SubscriptionType.NONE_NONE);

                if(ContactsModel.get(getActivity()).addContact(new Contacts(input.getText().toString(), Contacts.SubscriptionType.NONE))){
                   adapter.onContactCountChange();
                    Log.d(LOGCAT, input.getText().toString() + "added");

                }else{
                    Log.d(LOGCAT, "no contact");
                }


            }
        });

        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d(LOGCAT, "User Clicked on Cancel");

                dialog.cancel();
            }
        });
        builder.show();

    }


    @Override
    public void onItemClick(String contactJid) {
        Log.d(LOGCAT,"Inside contactListActivity The clicked contact is :" + contactJid);

        //Create a Chat in the Chat List table associated with this contact
        List<chatModel> chats = ChatEngs.get(getContext()).getChatsByJid(contactJid);
        if( chats.size() == 0)
        {
            Log.d(LOGCAT, contactJid + " is a new chat, adding them. With timestamp :"+ System.currentTimeMillis());


            if(ChatEngs.get(getActivity()).addChat(new chatModel(contactJid,"", chatModel.ContactType.ONE_ON_ONE, 10, System.currentTimeMillis(), R.drawable.user_friend ))){
                Intent intent = new Intent(getActivity(), ChatMessage.class);
                intent.putExtra("ContactJid", contactJid);
                startActivity(intent);
                getActivity().finish();
         }
            //Inside here we start the chat activity
        }else
        {

            Log.d(LOGCAT, contactJid + " is ALREADY in chat db.Just opening conversation");
            //Inside here we start the chat activity
            Intent intent = new Intent(getActivity(), ChatMessage.class);
            intent.putExtra("ContactJid",contactJid);
            startActivity(intent);
            getActivity().finish();



        }

    }

    public List<Contacts> getContacttwo(){
        List<Contacts> con =  new ArrayList<>();
        Contacts contacts = new Contacts("ketem", Contacts.SubscriptionType.NONE);
        contacts.setPersistID(1);
        contacts.setProfileImagePath("no img");
        contacts.setInviteSwitch(true);
        con.add(contacts);

        contacts = new Contacts("kele", Contacts.SubscriptionType.NONE);
        contacts.setPersistID(2);
        contacts.setProfileImagePath("no img");
        contacts.setInviteSwitch(true);
        con.add(contacts);

        contacts = new Contacts("Ay", Contacts.SubscriptionType.NONE);
        contacts.setPersistID(3);
        contacts.setProfileImagePath("no img");
        contacts.setInviteSwitch(true);
        con.add(contacts);

        return  con;
    }

    @Override
    public void onItemLongClick( final int uniqueId, String contactJid, View anchor) {

    }


}