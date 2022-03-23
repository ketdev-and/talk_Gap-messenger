package com.talkgap.messenger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.talkgap.messenger.AddGroupUsers.AddGroupEng;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddGroup extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton actionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);

        recyclerView = findViewById(R.id.group_rec);
        recyclerView.setNestedScrollingEnabled(false);
        actionButton =  findViewById(R.id.action_group);

        AddGroupEng addGroupEng = new AddGroupEng(recyclerView);
        addGroupEng.setRecyclerView();

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(AddGroup.this, GroupDetails.class);
                startActivity(intent);
            }
        });
    }
}