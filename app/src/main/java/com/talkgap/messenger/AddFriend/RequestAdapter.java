package com.talkgap.messenger.AddFriend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.talkgap.messenger.R;

import java.util.ArrayList;

public class RequestAdapter extends RecyclerView.Adapter<AddFriendHolder> {
    Context context;
    ArrayList<AddFriendModel> addFriendModels = new ArrayList<>();

    public RequestAdapter(Context context, ArrayList<AddFriendModel> addFriendModels) {
        this.context = context;
        this.addFriendModels = addFriendModels;
    }
    @NonNull
    @Override
    public AddFriendHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  =  LayoutInflater.from(parent.getContext()).inflate(R.layout.addfirend, parent, false);
        return  new AddFriendHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddFriendHolder holder, int position) {

        holder.Adim.setImageResource(addFriendModels.get(position).getAdim());
        holder.Adname.setText(addFriendModels.get(position).getAdmessage());
        holder.AdTitle.setText(addFriendModels.get(position).getAdtitle());


    }

    @Override
    public int getItemCount() {
        return addFriendModels.size();
    }
}
