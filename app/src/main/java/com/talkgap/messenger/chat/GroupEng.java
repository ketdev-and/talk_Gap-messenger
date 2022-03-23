//package com.talkgap.messenger.chat;
//
//import android.content.Context;
//
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.talkgap.messenger.ChatList.chatModel;
//import com.talkgap.messenger.MainActivity;
//import com.talkgap.messenger.R;
//
//import java.util.ArrayList;
//
//public class GroupEng {
//
//
//    RecyclerView recyclerView;
//    Context context;
//
//    public GroupEng(RecyclerView recyclerView) {
//        this.recyclerView = recyclerView;
//
//    }
//
//    public ArrayList<chatModel> AddToList(){
//        ArrayList<chatModel> arrayList = new ArrayList<>();
//        chatModel cm =  new chatModel("ketem", "this is my last message", chatModel.ContactType.ONE_ON_ONE, 11, System.currentTimeMillis(), R.drawable.user_friend);
//        arrayList.add(cm);
//
//               return arrayList;
//    }
//
//
//    public void setRecyclerView(){
//        LinearLayoutManager llm = new LinearLayoutManager(context, RecyclerView.VERTICAL, false );
//        this.recyclerView.setLayoutManager(llm);
//        this.recyclerView.setNestedScrollingEnabled(false);
//        this.recyclerView.setHasFixedSize(false);
//        this.recyclerView.setAdapter(new GroupAdapter(new MainActivity(), AddToList()));
//    }
//
//
//
//}
