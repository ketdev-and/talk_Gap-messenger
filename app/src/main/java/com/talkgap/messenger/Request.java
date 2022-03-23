package com.talkgap.messenger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.talkgap.messenger.AddFriend.error;

public class Request extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        recyclerView = findViewById(R.id.request_rec);
        recyclerView.setNestedScrollingEnabled(false);

        error req = new error(recyclerView);
        req.setRecyclerView();
    }
}