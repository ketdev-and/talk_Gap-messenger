package com.talkgap.messenger.Online;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.talkgap.messenger.MainActivity;
import com.talkgap.messenger.R;

import java.util.ArrayList;

public class OnlineEng {


    RecyclerView recyclerView;
    Context context;

    public OnlineEng(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;

    }

    public ArrayList<OnlineModel> AddToList(){
        ArrayList<OnlineModel> arrayList = new ArrayList<>();

        OnlineModel cm = new OnlineModel();
        cm.setAdim(R.drawable.tick);
        cm.setAdmessage("@lauren");
        cm.setAdtitle("Chinedu anyways");
        cm.setUser(R.drawable.user_friend);
        arrayList.add(cm);

        cm = new OnlineModel();
        cm.setAdim(R.drawable.tick);
        cm.setAdmessage("@ket_24");
        cm.setAdtitle("ketem needed");
        cm.setUser(R.drawable.contact_icon);
        arrayList.add(cm);

        cm = new OnlineModel();
        cm.setAdim(R.drawable.tick);
        cm.setAdmessage("@ket_24");
        cm.setAdtitle("ketem stuff");
        cm.setUser(R.drawable.user_friend);
        arrayList.add(cm);

        cm = new OnlineModel();
        cm.setAdim(R.drawable.tick);
        cm.setAdmessage("@ket_24");
        cm.setAdtitle("ketem NEduuuuu");
        cm.setUser(R.drawable.contact_icon);
        arrayList.add(cm);

        cm = new OnlineModel();
        cm.setAdim(R.drawable.tick);
        cm.setAdmessage("@ket_24");
        cm.setAdtitle("ketem Nedu");
        cm.setUser(R.drawable.user_friend);
        arrayList.add(cm);

        cm = new OnlineModel();
        cm.setAdim(R.drawable.tick);
        cm.setAdmessage("@ket_24");
        cm.setAdtitle("ketem pointlist");
        cm.setUser(R.drawable.user_friend);
        arrayList.add(cm);

        cm = new OnlineModel();
        cm.setAdim(R.drawable.tick);
        cm.setAdmessage("@ket_24");
        cm.setAdtitle("ketem lololo");
        cm.setUser(R.drawable.user_friend);
        arrayList.add(cm);

        cm = new OnlineModel();
        cm.setAdim(R.drawable.tick);
        cm.setAdmessage("@ket_24");
        cm.setAdtitle("ketem histon");
        cm.setUser(R.drawable.contact_icon);
        arrayList.add(cm);


        cm = new OnlineModel();
        cm.setAdim(R.drawable.tick);
        cm.setAdmessage("@ket_24");
        cm.setAdtitle("ketem needed");
        cm.setUser(R.drawable.contact_icon);
        arrayList.add(cm);

        cm = new OnlineModel();
        cm.setAdim(R.drawable.tick);
        cm.setAdmessage("@ket_24");
        cm.setAdtitle("ketem stuff");
        cm.setUser(R.drawable.contact_icon);
        arrayList.add(cm);

        cm = new OnlineModel();
        cm.setAdim(R.drawable.tick);
        cm.setAdmessage("@ket_24");
        cm.setAdtitle("ketem Nedu");
        cm.setUser(R.drawable.user_friend);
        arrayList.add(cm);

        cm = new OnlineModel();
        cm.setAdim(R.drawable.tick);
        cm.setAdmessage("@ket_24");
        cm.setAdtitle("ketem pointlist");
        cm.setUser(R.drawable.user_friend);
        arrayList.add(cm);

        cm = new OnlineModel();
        cm.setAdim(R.drawable.tick);
        cm.setAdmessage("@ket_24");
        cm.setAdtitle("ketem lololo");
        cm.setUser(R.drawable.user_friend);
        arrayList.add(cm);

        cm = new OnlineModel();
        cm.setAdim(R.drawable.tick);
        cm.setAdmessage("@ket_24");
        cm.setAdtitle("ketem histon");
        cm.setUser(R.drawable.contact_icon);
        arrayList.add(cm);


        cm = new OnlineModel();
        cm.setAdim(R.drawable.tick);
        cm.setAdmessage("@ket_24");
        cm.setAdtitle("ketem needed");
        cm.setUser(R.drawable.contact_icon);
        arrayList.add(cm);

        cm = new OnlineModel();
        cm.setAdim(R.drawable.tick);
        cm.setAdmessage("@ket_24");
        cm.setAdtitle("ketem stuff");
        cm.setUser(R.drawable.contact_icon);
        arrayList.add(cm);
        return arrayList;
    }


    public void setRecyclerView(){
        LinearLayoutManager llm = new LinearLayoutManager(context, RecyclerView.VERTICAL, false );
        this.recyclerView.setLayoutManager(llm);
        this.recyclerView.setNestedScrollingEnabled(false);
        this.recyclerView.setHasFixedSize(false);
        this.recyclerView.setAdapter(new OnlineAdapter(new MainActivity(),AddToList() ));
    }

}
