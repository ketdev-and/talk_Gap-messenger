package com.talkgap.messenger.Status;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.talkgap.messenger.MainActivity;
import com.talkgap.messenger.R;

import java.util.ArrayList;

public class StatusEng {
    RecyclerView StatusRecy;
    ArrayList<Integer> Images = new ArrayList<>();

    public StatusEng(RecyclerView statusRecy) {
        StatusRecy = statusRecy;
    }

    public ArrayList<Integer> AddImage(){
        Images.add(R.drawable.tick);
        Images.add(R.drawable.tick);
        Images.add(R.drawable.tick);
        Images.add(R.drawable.tick);
        Images.add(R.drawable.tick);
        Images.add(R.drawable.tick);
        Images.add(R.drawable.tick);
        Images.add(R.drawable.tick);
        Images.add(R.drawable.tick);
        Images.add(R.drawable.tick);
        return Images;
    }

    public  void Recycle(){
        LinearLayoutManager LinearManager =
                new LinearLayoutManager(new MainActivity(), LinearLayoutManager.HORIZONTAL, false);
        StatusRecy.setLayoutManager(LinearManager);
        StatusRecy.setAdapter(new StatusAdapter(new MainActivity(), AddImage()));
    }


}
