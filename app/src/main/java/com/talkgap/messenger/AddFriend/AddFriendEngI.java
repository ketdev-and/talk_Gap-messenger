package com.talkgap.messenger.AddFriend;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.talkgap.messenger.MainActivity;
import com.talkgap.messenger.R;

import java.util.ArrayList;

public class AddFriendEngI {


    RecyclerView recyclerView;
    Context context;

    public AddFriendEngI(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;

    }

    public ArrayList<AddFriendModel> AddToList(){
        ArrayList<AddFriendModel> arrayList = new ArrayList<>();

        AddFriendModel cm = new AddFriendModel();
        cm.setAdim(R.drawable.tick);
        cm.setAdmessage("@lauren");
        cm.setAdtitle("Chinedu anyways");
        arrayList.add(cm);

        cm = new AddFriendModel();
        cm.setAdim(R.drawable.tick);
        cm.setAdmessage("@ket_24");
        cm.setAdtitle("ketem needed");
        arrayList.add(cm);

        cm = new AddFriendModel();
        cm.setAdim(R.drawable.tick);
        cm.setAdmessage("@ket_24");
        cm.setAdtitle("ketem stuff");
        arrayList.add(cm);

        cm = new AddFriendModel();
        cm.setAdim(R.drawable.tick);
        cm.setAdmessage("@ket_24");
        cm.setAdtitle("ketem NEduuuuu");
        arrayList.add(cm);

        cm = new AddFriendModel();
        cm.setAdim(R.drawable.tick);
        cm.setAdmessage("@ket_24");
        cm.setAdtitle("ketem Nedu");
        arrayList.add(cm);

        cm = new AddFriendModel();
        cm.setAdim(R.drawable.tick);
        cm.setAdmessage("@ket_24");
        cm.setAdtitle("ketem pointlist");
        arrayList.add(cm);

        cm = new AddFriendModel();
        cm.setAdim(R.drawable.tick);
        cm.setAdmessage("@ket_24");
        cm.setAdtitle("ketem lololo");
        arrayList.add(cm);

        cm = new AddFriendModel();
        cm.setAdim(R.drawable.tick);
        cm.setAdmessage("@ket_24");
        cm.setAdtitle("ketem histon");
        arrayList.add(cm);

        return arrayList;
    }


    public void setRecyclerView(){
        LinearLayoutManager llm = new LinearLayoutManager(context, RecyclerView.VERTICAL, false );
        this.recyclerView.setLayoutManager(llm);
        this.recyclerView.setNestedScrollingEnabled(false);
        this.recyclerView.setHasFixedSize(false);
        this.recyclerView.setAdapter(new AddFreindAdapter(new MainActivity(),AddToList() ));
    }

}
