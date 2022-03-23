package com.talkgap.messenger.group_admin;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.talkgap.messenger.Message;
import com.talkgap.messenger.R;

import java.util.ArrayList;

public class GPAContactEngI {


    RecyclerView recyclerView;
    Context context;

    public GPAContactEngI(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;

    }

    public ArrayList<GPAContactModel> AddToList(){
        ArrayList<GPAContactModel> arrayList = new ArrayList<>();

        GPAContactModel cm = new GPAContactModel();
        cm.setCimg(R.drawable.sim);
        cm.setUsername("Chinedu anyways");
        arrayList.add(cm);

        cm = new GPAContactModel();
        cm.setCimg(R.drawable.sim);
        cm.setUsername("Chinedu anyways");
        arrayList.add(cm);

        cm = new GPAContactModel();
        cm.setCimg(R.drawable.sim);
        cm.setUsername("Chinedu anyways");
        arrayList.add(cm);

        cm = new GPAContactModel();
        cm.setCimg(R.drawable.sim);
        cm.setUsername("Chinedu anyways");
        arrayList.add(cm);

        cm = new GPAContactModel();
        cm.setCimg(R.drawable.sim);
        cm.setUsername("Chinedu anyways");
        arrayList.add(cm);


        return arrayList;
    }


    public void setRecyclerView(){
        LinearLayoutManager llm = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false );
        this.recyclerView.setLayoutManager(llm);
        this.recyclerView.setNestedScrollingEnabled(false);
        this.recyclerView.setHasFixedSize(false);
        this.recyclerView.setAdapter(new GPAContactAdapterI(new Message(), AddToList() ));
    }

}
