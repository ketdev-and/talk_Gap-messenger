//package com.talkgap.messenger.Contact;
//
//import android.content.Context;
//
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.talkgap.messenger.Message;
//import com.talkgap.messenger.R;
//
//import java.util.ArrayList;
//
//public class ContactEngI {
//
//
//    RecyclerView recyclerView;
//    Context context;
//
//    public ContactEngI(RecyclerView recyclerView) {
//        this.recyclerView = recyclerView;
//    }
//
//    public ArrayList<ContactModel> AddToList(){
//        ArrayList<ContactModel> arrayList = new ArrayList<>();
//
//        ContactModel cm = new  ContactModel(null);
//        cm.setCimg(R.drawable.tick);
//        cm.setPhone("0705700119");
//        cm.setUsername("Chinedu anyways");
//        cm.onOFF = false;
//        arrayList.add(cm);
//
//        cm = new ContactModel(null);
//        cm.setCimg(R.drawable.tick);
//        cm.setPhone("0705700119");
//        cm.setUsername("Chinedu anyways");
//        cm.onOFF = false;
//        arrayList.add(cm);
//
//        cm = new ContactModel(null);
//        cm.setCimg(R.drawable.tick);
//        cm.setPhone("0705700119");
//        cm.setUsername("Chinedu anyways");
//        cm.onOFF = false;
//        arrayList.add(cm);
//
//        cm = new ContactModel(null);
//        cm.setCimg(R.drawable.tick);
//        cm.setPhone("0705700119");
//        cm.setUsername("Chinedu anyways");
//        cm.onOFF = false;
//        arrayList.add(cm);
//
//        cm = new ContactModel(null);
//        cm.setCimg(R.drawable.tick);
//        cm.setPhone("0705700119");
//        cm.setUsername("Chinedu anyways");
//        cm.onOFF = false;
//        arrayList.add(cm);
//
//        cm = new ContactModel(null);
//        cm.setCimg(R.drawable.tick);
//        cm.setPhone("0705700119");
//        cm.setUsername("Chinedu anyways");
//        cm.onOFF = false;
//        arrayList.add(cm);
//
//        return arrayList;
//    }
//
//
//    public void setRecyclerView(){
//        LinearLayoutManager llm = new LinearLayoutManager(context, RecyclerView.VERTICAL, false );
//        this.recyclerView.setLayoutManager(llm);
//        this.recyclerView.setNestedScrollingEnabled(false);
//        this.recyclerView.setHasFixedSize(false);
//        this.recyclerView.setAdapter(new ContactAdapterI(new Message(), AddToList() ));
//    }
//
//}
