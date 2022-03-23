package com.talkgap.messenger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.talkgap.messenger.group_admin.GPAContactEngI;
import com.talkgap.messenger.group_media.GMStatusEng;
import com.talkgap.messenger.group_part.GPContactEngI;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class group_profile extends AppCompatActivity {

    CollapsingToolbarLayout collapsingToolbar;
    AppBarLayout appBarLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_profile);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        RecyclerView recyclerView = findViewById(R.id.group_info_rec);
        recyclerView.setNestedScrollingEnabled(false);
        GMStatusEng adfriendeng = new GMStatusEng(recyclerView);
        adfriendeng.Recycle();

        RecyclerView recyclerViewMem = findViewById(R.id.group_mem_rec);
        recyclerViewMem.setNestedScrollingEnabled(false);
        GPContactEngI gpContactEngI = new GPContactEngI(recyclerViewMem);
        gpContactEngI.setRecyclerView();

        RecyclerView recyclerViewAd = findViewById(R.id.group_admin_rec);
        recyclerViewAd.setNestedScrollingEnabled(false);
        GPAContactEngI gpaContactEngI = new GPAContactEngI(recyclerViewAd);
        gpaContactEngI.setRecyclerView();







    }



}