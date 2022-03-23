package com.talkgap.messenger.group_media;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.talkgap.messenger.MainActivity;
import com.talkgap.messenger.R;

import java.util.ArrayList;

public class GMStatusEng {
    RecyclerView StatusRecy;
    ArrayList<Integer> Images = new ArrayList<>();

    public GMStatusEng(RecyclerView statusRecy) {
        StatusRecy = statusRecy;
    }

    public ArrayList<Integer> AddImage(){
        Images.add(R.drawable.sim);
        Images.add(R.drawable.sim);
        Images.add(R.drawable.sim);
        Images.add(R.drawable.sim);
        Images.add(R.drawable.sim);
        Images.add(R.drawable.sim);
        Images.add(R.drawable.sim);
        Images.add(R.drawable.sim);
        Images.add(R.drawable.sim);
        Images.add(R.drawable.sim);
        return Images;
    }

    public  void Recycle(){
        LinearLayoutManager LinearManager =
                new LinearLayoutManager(new MainActivity(), LinearLayoutManager.HORIZONTAL, false);
        StatusRecy.setLayoutManager(LinearManager);
        StatusRecy.setAdapter(new GMStatusAdapter(new MainActivity(), AddImage()));
    }


}
