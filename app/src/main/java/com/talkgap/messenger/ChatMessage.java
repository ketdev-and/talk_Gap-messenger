package com.talkgap.messenger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.talkgap.messenger.ChatList.chatModel;
import com.talkgap.messenger.Chat_message.ChatMessageAdapter;
import com.talkgap.messenger.Chat_message.ChatMessageModel;
import com.talkgap.messenger.Chat_message.ChatMessagesAdapter;
import com.talkgap.messenger.Chat_message.ChatMessagesModel;
import com.talkgap.messenger.Roaster.Constants;
import com.talkgap.messenger.Roaster.RoasterConnection;
import com.talkgap.messenger.Roaster.RoasterConnectionService;
import com.talkgap.messenger.contacts.Contacts;
import com.talkgap.messenger.contacts.ContactsModel;
import com.talkgap.messenger.keyboardUtil.keyboardUtil;

import org.jivesoftware.smack.chat2.Chat;

import java.util.ArrayList;

public class ChatMessage extends AppCompatActivity implements keyboardUtil.KeyboardVisibilityListener, ChatMessagesAdapter.OnInformRecyclerViewToScrollDownListener, ChatMessagesAdapter.OnItemLongClickListener {
    private static final String LOGTAG = "talkgapconnection" ;
    Toolbar toolbar;
    boolean showing = true;
    ConstraintLayout cl;
    RecyclerView recyclerView;
    EditText sendedit;
    public ArrayList<ChatMessageModel> arrayList = new ArrayList<>();
    ImageView sendmessage, chat_message_menu;
    ChatMessagesAdapter adapter;
    TextView title, pre ;
    String counterpartJid;
    BroadcastReceiver broadcastReceiver;

    private View snackbar;
    private View snackbarStranger;

    private TextView snackBarActionAccept;
    private TextView snackBarActionDeny;

    private TextView snackBarStrangerAddContact;
    private TextView snackBarStrangerBlock;
    private ImageView friend_img, user_img;

    private chatModel.ContactType chatType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_message);

        pre = findViewById(R.id.prescence);



        title = findViewById(R.id.title_name);
        Intent intent = getIntent();
        counterpartJid = intent.getStringExtra("ContactJid");
        Toast.makeText(this, counterpartJid+" is the one", Toast.LENGTH_SHORT).show();
        chatType = (chatModel.ContactType)intent.getSerializableExtra("ChatType");
        title.setText(counterpartJid.substring(0, Math.min(counterpartJid.length()
                , 12))+" ....");


        toolbar = findViewById(R.id.toolbar);
        cl = findViewById(R.id.att_con);
        recyclerView = (RecyclerView) findViewById(R.id.chat_message_mes);
        sendedit = findViewById(R.id.send_messageEdit);
        sendmessage = findViewById(R.id.send_message);
        chat_message_menu = findViewById(R.id.chat_message_menu);

        LinearLayoutManager llm = new LinearLayoutManager(this, RecyclerView.VERTICAL, false );
        recyclerView.setLayoutManager(llm);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(false);

        adapter = new ChatMessagesAdapter(getApplicationContext(), counterpartJid);
        adapter.setmOnInformRecyclerViewToScrollDownListener(this);
        adapter.setOnItemLongClickListener(this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        user_img = findViewById(R.id.adstatusImage);
        RoasterConnection TG = RoasterConnectionService.getmConnection();
        if(TG != null)
        {
            String imageAbsPath = TG.getProfileImageAbsolutePath(counterpartJid);
            if(imageAbsPath != null)
            {
                Drawable d = Drawable.createFromPath(imageAbsPath);
                user_img.setImageDrawable(d);
            }
        }


        friend_img = findViewById(R.id.cir_img);
        if(TG != null)
        {
            String selfJid = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("xmpp_jid", null);
            if(selfJid != null)
            {
                Log.d(LOGTAG, "God a vaild self jid :"+ selfJid);
                String imageAbsPath = TG.getProfileImageAbsolutePath(selfJid);
                if (imageAbsPath != null)
                {
                    Drawable d = Drawable.createFromPath(imageAbsPath);
                   friend_img.setImageDrawable(d);
                }
            } else
            {
                Log.d(LOGTAG, "Could not get a Valid self jid");
            }
        }



        sendmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RoasterConnectionService.getmConnection().sendmessage(sendedit.getText().toString(), counterpartJid);
//                adapter.notifyDataSetChanged();
                adapter.onMessageAdd();
                sendedit.setText("");

            }
        });

        Contacts mContact = ContactsModel.get(getApplicationContext()).getContactByJidString(counterpartJid);

        if(!ContactsModel.get(getApplicationContext()).isContactStranger(counterpartJid)) {
            if (mContact.isOnlineStatus()) {
                Log.d(LOGTAG, counterpartJid + "is ONLINE");
                pre.setText("Online");

            } else {
                Log.d(LOGTAG, counterpartJid + "is OFFLINE");
                pre.setText("Offline");

            }
        }

        snackbar = findViewById(R.id.snackbar);
        snackbarStranger = findViewById(R.id.snackbar_stranger);

        if( !ContactsModel.get(getApplicationContext()).isContactStranger(counterpartJid))
        {
            snackbarStranger.setVisibility(View.GONE);
            Log.d(LOGTAG,counterpartJid + " is not a stranger");
            Contacts contact = ContactsModel.get(this).getContactByJidString(counterpartJid);
            Log.d(LOGTAG,"We got a contact with JID :" + contact.getJid());

            if( contact.isPendingFrom())
            {
                Log.d(LOGTAG," Your subscription to "+ contact.getJid() + " is in the FROM direction is in pending state. Should show the snackbar");
                int paddingBottom = getResources().getDimensionPixelOffset(R.dimen.chatview_recycler_view_padding_huge);
               recyclerView.setPadding(0,0,0,paddingBottom);
                snackbar.setVisibility(View.VISIBLE);
            }else
            {
                int paddingBottom = getResources().getDimensionPixelOffset(R.dimen.chatview_recycler_view_padding_normal);
                recyclerView.setPadding(0,0,0,paddingBottom);
                snackbar.setVisibility(View.GONE);
            }

        }else
        {
            if(chatType == chatModel.ContactType.STRANGER)
            {
                Log.d(LOGTAG,"Chat type is STRANGER");
                //We fall here if this was a subscription request from a stranger
                int paddingBottom = getResources().getDimensionPixelOffset(R.dimen.chatview_recycler_view_padding_huge);
                recyclerView.setPadding(0,0,0,paddingBottom);
                snackbar.setVisibility(View.VISIBLE);
                snackbarStranger.setVisibility(View.GONE);

            }else
            {
                Log.d(LOGTAG,counterpartJid + " is a stranger. Hiding snackbar");
                int paddingBottom = getResources().getDimensionPixelOffset(R.dimen.chatview_recycler_view_padding_huge);
                recyclerView.setPadding(0,0,0,paddingBottom);
                snackbarStranger.setVisibility(View.VISIBLE);
                snackbar.setVisibility(View.GONE);

            }


        }


        snackBarActionAccept = (TextView) findViewById(R.id.snackbar_action_accept);
        snackBarActionAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //User accepts presence subscription

                //Add Them to your roster if they are strangers
                if (ContactsModel.get(getApplicationContext()).isContactStranger(counterpartJid))
                {
                    if(ContactsModel.get(getApplicationContext()).addContact(new Contacts(counterpartJid, Contacts.SubscriptionType.NONE)))
                    {
                        Log.d(LOGTAG,"Previously stranger contact "+counterpartJid + "now successfully added to local Roster");
                    }
                }
                Log.d(LOGTAG," Accept presence subscription from :" + counterpartJid);
                if(RoasterConnectionService.getmConnection().subscribed(counterpartJid))
                {
                    ContactsModel.get(getApplicationContext()).updateContactSubscriptionOnSendSubscribed(counterpartJid);
                    Toast.makeText(ChatMessage.this,"Subscription from "+counterpartJid + "accepted",
                            Toast.LENGTH_LONG).show();
                }
                snackbar.setVisibility(View.GONE);

            }
        });

        snackBarActionDeny = (TextView) findViewById(R.id.snackbar_action_deny);
        snackBarActionDeny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //User denies presence subscription
                Log.d(LOGTAG," Deny presence subscription from :" + counterpartJid);
                if(RoasterConnectionService.getmConnection().unsubscribed(counterpartJid))
                {
                    ContactsModel.get(getApplicationContext()).updateContactSubscriptionOnSendSubscribed(counterpartJid);

                    //No action required in the Contact Model regarding subscriptions
                    Toast.makeText(getApplicationContext(),"Subscription Rejected",Toast.LENGTH_LONG).show();
                }
                snackbar.setVisibility(View.GONE);

            }
        });

        snackBarStrangerAddContact= findViewById(R.id.snackbar_action_accept_stranger);
        snackBarStrangerAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContactsModel.get(getApplicationContext()).addContact(new Contacts(counterpartJid, Contacts.SubscriptionType.NONE)))
                {
                    if(RoasterConnectionService.getmConnection().addContactToRoster(counterpartJid))
                    {
                        Log.d(LOGTAG,counterpartJid + " successfully added to remote roster");
                        snackbarStranger.setVisibility(View.GONE);
                    }
                }

            }
        });

        snackBarStrangerBlock = findViewById(R.id.snackbar_action_deny_stranger);
        snackBarStrangerBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ChatMessage.this,"Feature not implemented yet",Toast.LENGTH_SHORT).show();


            }
        });


        chat_message_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatMessage.this, ContactDetails.class );
                intent.putExtra("ContactJid", counterpartJid);
                startActivity(intent);
            }
        });



        keyboardUtil.setKeyboardVisibilityListener(this,this);

    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();

        adapter.informRecyclerViewToScrollDown();
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                switch (action)
                {
                    case Constants.BroadCastMessage.UI_NEW_MASSAGE_FLAG:
                        adapter.onMessageAdd();
                        adapter.informRecyclerViewToScrollDown();
                    case Constants.BroadCastMessage.UI_ONLINE_STATUS_CHANGE:
                        String contactJid = intent.getStringExtra(Constants.ONLINE_STATUS_CHANGE_CONTACT);
                        Log.d(LOGTAG," Online status change for "+contactJid + " received in ChatViewActivity");

                        if(!ContactsModel.get(getApplicationContext()).isContactStranger(counterpartJid))
                        {
                            if(counterpartJid.equals(contactJid))
                            {
                                Contacts mContact = ContactsModel.get(getApplicationContext()).getContactByJidString(contactJid);
                                if(mContact.isOnlineStatus())
                                {
                                    Log.d(LOGTAG,"From Chat View, user " +contactJid + " has come ONLINE");
                                    pre.setText("Online");

                                }else
                                {
                                    Log.d(LOGTAG,"From Chat View, user " +contactJid + " has gone OFFLINE");
                                    pre.setText("Offline");

                                }
                            }

                        }


                        return;
                }


            }
        };
        IntentFilter filter = new IntentFilter( Constants.BroadCastMessage.UI_NEW_MASSAGE_FLAG);
        registerReceiver(broadcastReceiver, filter);
    }

    public void setAttachDrawer (View view){
        if (showing) {
            cl.setVisibility(View.VISIBLE);
            showing = false;
        } else {
            cl.setVisibility(View.GONE);
            showing = true;
        }


    }

    @Override
    public void onKeyboardVisibilityChanged(boolean keyboardVisible) {
       adapter.informRecyclerViewToScrollDown();
    }


    @Override
    public void onInformRecyclerViewToScrollDown(int size) {
        recyclerView.scrollToPosition(size-1);
    }

    @Override
    public void onItemLongClick(final int uniqueId, View anchor) {
        PopupMenu popup = new PopupMenu(ChatMessage.this,anchor, Gravity.CENTER);
        //Inflating the Popup using xml file
        popup.getMenuInflater()
                .inflate(R.menu.chatmessage_delete, popup.getMenu());

        //registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (item.getItemId() == R.id.delete_message) {
                    if (ChatMessagesModel.get(getApplicationContext()).deleteMessage(uniqueId)) {
                        adapter.onMessageAdd();
                        Toast.makeText(
                                ChatMessage.this,
                                "Message deleted successfully ",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                }
                return true;
            }
        });
        popup.show();

    }


}
