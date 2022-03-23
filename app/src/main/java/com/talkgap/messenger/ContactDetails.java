package com.talkgap.messenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.talkgap.messenger.Roaster.RoasterConnection;
import com.talkgap.messenger.Roaster.RoasterConnectionService;
import com.talkgap.messenger.addFragment.Contact;
import com.talkgap.messenger.contacts.Contacts;
import com.talkgap.messenger.contacts.ContactsModel;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContactDetails extends AppCompatActivity {
    private static final String LOGCAT = "talkgapconnection";
    private String contactJid;
    private CircleImageView profileImage;
    private CheckBox fromCheckBox;
    private CheckBox toCheckBox;
    private Context mApplicationContext;


    private TextView pendingFrom, username;
    private TextView pendingTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        mApplicationContext = getApplicationContext();

        username = findViewById(R.id.contact_details_user_name);


        Intent intent = getIntent();
        contactJid = intent.getStringExtra("ContactJid");
        setTitle(contactJid);



        username.setText(contactJid);


        pendingFrom = findViewById(R.id.pending_from);
        pendingTo = findViewById(R.id.pending_to);

        fromCheckBox = (CheckBox) findViewById(R.id.them_to_me);
        fromCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (fromCheckBox.isChecked()) {
                    //There is nothing to do here
                    Log.d(LOGCAT, "The FROM checkbox is checked");
                } else {
                    //Send unsubscribed to cancel subscription
                    Log.d(LOGCAT, "The FROM checkbox is UNchecked");
                    if (RoasterConnectionService.getmConnection().unsubscribed(contactJid)) {
                        Toast.makeText(mApplicationContext, "Successfully Stopped sending presence updates to " + contactJid, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        toCheckBox = (CheckBox) findViewById(R.id.me_to_tem);
        toCheckBox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (toCheckBox.isChecked()) {
                    //Send subscription request
                    Log.d(LOGCAT, "The TO checkbox is checked");
                    if (RoasterConnectionService.getmConnection().subscribe(contactJid)) {
                        Toast.makeText(mApplicationContext, "Subscription request sent to  " + contactJid, Toast.LENGTH_LONG).show();
                    }
                } else {
                    //Send them an unsubscribe
                    Log.d(LOGCAT, "The TO checkbox is UNchecked");
                    if (RoasterConnectionService.getmConnection().unsubscribe(contactJid)) {
                        Toast.makeText(mApplicationContext, "You successfuly stopped getting presence updates from  " + contactJid, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });


        if (!ContactsModel.get(getApplication()).isContactStranger(contactJid)) {

            Contacts contacts = ContactsModel.get(mApplicationContext).getContactByJidString(contactJid);
            Contacts.SubscriptionType subType = contacts.getSubscriptionType();

            if (subType == Contacts.SubscriptionType.NONE) {
                fromCheckBox.setEnabled(false);
                fromCheckBox.setChecked(false);
                toCheckBox.setChecked(false);
            } else if (subType == Contacts.SubscriptionType.FROM) {
                fromCheckBox.setEnabled(true);
                fromCheckBox.setChecked(true);
                toCheckBox.setChecked(false);

            } else if (subType == Contacts.SubscriptionType.TO) {
                fromCheckBox.setEnabled(false);
                fromCheckBox.setChecked(false);
                toCheckBox.setChecked(true);

            } else if (subType == Contacts.SubscriptionType.BOTH) {
                fromCheckBox.setEnabled(true);
                fromCheckBox.setChecked(true);
                toCheckBox.setChecked(true);

            }


        if(contacts.isPendingFrom())
        {
            pendingFrom.setVisibility(View.VISIBLE);
        }else
        {
            pendingFrom.setVisibility(View.GONE);
        }

        if(contacts.isPendingTo())
        {
            pendingTo.setVisibility(View.VISIBLE);
        }else
        {
            pendingTo.setVisibility(View.GONE);
        }

        if(subType == Contacts.SubscriptionType.NONE)
        {
            fromCheckBox.setEnabled(false);
            fromCheckBox.setChecked(false);
            toCheckBox.setChecked(false);
        }else if (subType == Contacts.SubscriptionType.FROM)
        {
            fromCheckBox.setEnabled(true);
            fromCheckBox.setChecked(true);
            toCheckBox.setChecked(false);

        }else if (subType == Contacts.SubscriptionType.TO)
        {
            fromCheckBox.setEnabled(false);
            fromCheckBox.setChecked(false);
            toCheckBox.setChecked(true);

        }else if (subType == Contacts.SubscriptionType.BOTH)
        {
            fromCheckBox.setEnabled(true);
            fromCheckBox.setChecked(true);
            toCheckBox.setChecked(true);

        }

    }else
    {
        fromCheckBox.setEnabled(false);
        fromCheckBox.setChecked(false);
        toCheckBox.setChecked(false);
        toCheckBox.setEnabled(true);
    }

    }

}