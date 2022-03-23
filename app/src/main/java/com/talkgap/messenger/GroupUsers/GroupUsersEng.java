package com.talkgap.messenger.GroupUsers;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.talkgap.messenger.MainActivity;
import com.talkgap.messenger.R;

import java.util.ArrayList;

public class GroupUsersEng {
    RecyclerView StatusRecy;
    ArrayList<GroupUserModel> Images = new ArrayList<>();

    public GroupUsersEng(RecyclerView statusRecy) {
        StatusRecy = statusRecy;
    }

    public ArrayList<GroupUserModel> AddImage(){
        GroupUserModel gum = new GroupUserModel();
        gum.setCimg(R.drawable.ic_launcher_background);
        gum.setUsername("Ketem");
        Images.add(gum);

        gum = new GroupUserModel();
        gum.setCimg(R.drawable.ic_launcher_background);
        gum.setUsername("Ketem");
        Images.add(gum);

        gum = new GroupUserModel();
        gum.setCimg(R.drawable.ic_launcher_background);
        gum.setUsername("Ketem");
        Images.add(gum);

        gum = new GroupUserModel();
        gum.setCimg(R.drawable.ic_launcher_background);
        gum.setUsername("Ketem");
        Images.add(gum);

        gum = new GroupUserModel();
        gum.setCimg(R.drawable.ic_launcher_background);
        gum.setUsername("Ketem");
        Images.add(gum);

        gum = new GroupUserModel();
        gum.setCimg(R.drawable.ic_launcher_background);
        gum.setUsername("Ketem");
        Images.add(gum);

        gum = new GroupUserModel();
        gum.setCimg(R.drawable.ic_launcher_background);
        gum.setUsername("Ketem");
        Images.add(gum);

        gum = new GroupUserModel();
        gum.setCimg(R.drawable.ic_launcher_background);
        gum.setUsername("Ketem");
        Images.add(gum);


        gum = new GroupUserModel();
        gum.setCimg(R.drawable.ic_launcher_background);
        gum.setUsername("Ketem");
        Images.add(gum);

        return  Images;
    }

    public  void Recycle(){
        LinearLayoutManager LinearManager =
                new LinearLayoutManager(new MainActivity(), LinearLayoutManager.HORIZONTAL, false);
        StatusRecy.setLayoutManager(LinearManager);
        StatusRecy.setAdapter(new GroupUsersAdapter(new MainActivity(), AddImage()));
    }


}
