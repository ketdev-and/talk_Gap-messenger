package com.talkgap.messenger;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;


import com.talkgap.messenger.messTab.messTabEng;
import com.google.android.material.tabs.TabLayout;

public class Message extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    RelativeLayout relativeLayout;
    private static final String LOGTAG = "TalkGapChatConnection";
    Button s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        tabLayout = findViewById(R.id.mess_tab_lay);
        viewPager = findViewById(R.id.contact_view);
        relativeLayout = findViewById(R.id.newGroup);
        s = findViewById(R.id.s);

        //Calling the TabEng from Tab package
        messTabEng messtabeng = new messTabEng(getSupportFragmentManager());
        messtabeng.CreateTab(tabLayout, viewPager);



        moveToAddGroup();



    }

    public void moveToAddGroup(){
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(Message.this, AddGroup.class);
                startActivity(intent);
            }
        });


    }


}