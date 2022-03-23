package com.talkgap.messenger.GroupUsers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.talkgap.messenger.R;

import java.util.ArrayList;

public class GroupUsersAdapter extends RecyclerView.Adapter<GroupUsersHolder> {
    ArrayList<GroupUserModel> Images = new ArrayList<>();
    Context context;

    public GroupUsersAdapter(Context context, ArrayList<GroupUserModel> images) {
        this.Images = images;
        this.context = context;
    }

    @NonNull
    @Override
    public GroupUsersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_names, parent, false);
        return new GroupUsersHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupUsersHolder holder, int position) {
        holder.CustomImage.setImageResource(Images.get(position).getCimg());
        holder.text.setText(Images.get(position).getUsername());
    }

    @Override
    public int getItemCount() {
        return Images.size();
    }
}
