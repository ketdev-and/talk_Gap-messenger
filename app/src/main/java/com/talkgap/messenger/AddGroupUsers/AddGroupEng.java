package com.talkgap.messenger.AddGroupUsers;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.talkgap.messenger.MainActivity;
import com.talkgap.messenger.R;

import java.util.ArrayList;

public class AddGroupEng {


    RecyclerView recyclerView;
    Context context;

    public AddGroupEng(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;

    }

    public ArrayList<AddGroupModel> AddToList(){
        ArrayList<AddGroupModel> arrayList = new ArrayList<>();

        AddGroupModel cm = new AddGroupModel();
        cm.setAdim(R.drawable.tick);
        cm.setAdmessage("@lauren");
        cm.setAdtitle("Chinedu anyways");
        cm.setUser(R.drawable.user_friend);
        arrayList.add(cm);

        cm = new AddGroupModel();
        cm.setAdim(R.drawable.tick);
        cm.setAdmessage("@ket_24");
        cm.setAdtitle("ketem needed");
        cm.setUser(R.drawable.contact_icon);
        arrayList.add(cm);

        cm = new AddGroupModel();
        cm.setAdim(R.drawable.tick);
        cm.setAdmessage("@ket_24");
        cm.setAdtitle("ketem needed");
        cm.setUser(R.drawable.contact_icon);
        arrayList.add(cm);

        cm = new AddGroupModel();
        cm.setAdim(R.drawable.tick);
        cm.setAdmessage("@ket_24");
        cm.setAdtitle("ketem needed");
        cm.setUser(R.drawable.contact_icon);
        arrayList.add(cm);

        cm = new AddGroupModel();
        cm.setAdim(R.drawable.tick);
        cm.setAdmessage("@ket_24");
        cm.setAdtitle("ketem needed");
        cm.setUser(R.drawable.contact_icon);
        arrayList.add(cm);

        cm = new AddGroupModel();
        cm.setAdim(R.drawable.tick);
        cm.setAdmessage("@lauren");
        cm.setAdtitle("Chinedu anyways");
        cm.setUser(R.drawable.user_friend);
        arrayList.add(cm);

        cm = new AddGroupModel();
        cm.setAdim(R.drawable.tick);
        cm.setAdmessage("@ket_24");
        cm.setAdtitle("ketem needed");
        cm.setUser(R.drawable.contact_icon);
        arrayList.add(cm);


        cm = new AddGroupModel();
        cm.setAdim(R.drawable.tick);
        cm.setAdmessage("@lauren");
        cm.setAdtitle("Chinedu anyways");
        cm.setUser(R.drawable.user_friend);
        arrayList.add(cm);

        cm = new AddGroupModel();
        cm.setAdim(R.drawable.tick);
        cm.setAdmessage("@ket_24");
        cm.setAdtitle("ketem needed");
        cm.setUser(R.drawable.contact_icon);
        arrayList.add(cm);

        return arrayList;
    }


    public void setRecyclerView(){
        LinearLayoutManager llm = new LinearLayoutManager(context, RecyclerView.VERTICAL, false );
        this.recyclerView.setLayoutManager(llm);
        this.recyclerView.setNestedScrollingEnabled(false);
        this.recyclerView.setHasFixedSize(false);
        this.recyclerView.setAdapter(new AddGroupAdapter(new MainActivity(),AddToList() ));
    }

}
