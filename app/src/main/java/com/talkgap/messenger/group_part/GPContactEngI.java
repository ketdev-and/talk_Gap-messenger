package com.talkgap.messenger.group_part;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.talkgap.messenger.Message;
import com.talkgap.messenger.R;

import java.util.ArrayList;

public class GPContactEngI {


    RecyclerView recyclerView;
    Context context;

    public GPContactEngI(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;

    }

    public ArrayList<GPContactModel> AddToList(){
        ArrayList<GPContactModel> arrayList = new ArrayList<>();

        GPContactModel cm = new GPContactModel();
        cm.setCimg(R.drawable.sim);
        cm.setPhone("@chino");
        cm.setUsername("Chinedu anyways");
        arrayList.add(cm);

        cm = new GPContactModel();
        cm.setCimg(R.drawable.sim);
        cm.setPhone("@chino");
        cm.setUsername("Chinedu anyways");
        arrayList.add(cm);

        cm = new GPContactModel();
        cm.setCimg(R.drawable.sim);
        cm.setPhone("@chino");
        cm.setUsername("Chinedu anyways");
        arrayList.add(cm);

        cm = new GPContactModel();
        cm.setCimg(R.drawable.sim);
        cm.setPhone("@chino");
        cm.setUsername("Chinedu anyways");
        arrayList.add(cm);

        cm = new GPContactModel();
        cm.setCimg(R.drawable.sim);
        cm.setPhone("@chino");
        cm.setUsername("Chinedu anyways");
        arrayList.add(cm);


        return arrayList;
    }


    public void setRecyclerView(){
        LinearLayoutManager llm = new LinearLayoutManager(context, RecyclerView.VERTICAL, false );
        this.recyclerView.setLayoutManager(llm);
        this.recyclerView.setNestedScrollingEnabled(false);
        this.recyclerView.setHasFixedSize(false);
        this.recyclerView.setAdapter(new GPContactAdapterI(new Message(), AddToList() ));
    }

}
