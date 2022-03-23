//package com.talkgap.messenger.addFragment;
//
//import android.os.Bundle;
//
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.talkgap.messenger.R;
//import com.talkgap.messenger.chat.GroupEng;
//
//public class Group extends Fragment {
//    RecyclerView recyclerView;
//    View view;
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        view = inflater.inflate(R.layout.fragment_group, container, false);
//        recyclerView = view.findViewById(R.id.group_rec);
//
//        recyclerView.setNestedScrollingEnabled(false);
//        GroupEng cng =  new GroupEng(recyclerView);
//        cng.setRecyclerView();
//
//        return view;
//    }
//}