package com.talkgap.messenger.AddGroupUsers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.talkgap.messenger.R;

import java.util.ArrayList;

public class AddGroupAdapter extends RecyclerView.Adapter<AddGroupHolder> {
    Context context;
    ArrayList<AddGroupModel> addGroupModel = new ArrayList<>();

    public AddGroupAdapter(Context context, ArrayList<AddGroupModel> addGroupModels) {
        this.context = context;
        this.addGroupModel = addGroupModels;
    }
    @NonNull
    @Override
    public AddGroupHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  =  LayoutInflater.from(parent.getContext()).inflate(R.layout.add_group_user, parent, false);
        return  new AddGroupHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddGroupHolder holder, int position) {

        holder.Adim.setImageResource(addGroupModel.get(position).getAdim());
        holder.Adname.setText(addGroupModel.get(position).getAdmessage());
        holder.AdTitle.setText(addGroupModel.get(position).getAdtitle());
        holder.user.setImageResource(addGroupModel.get(position).getUser());



    }

    @Override
    public int getItemCount() {
        return addGroupModel.size();
    }
}
