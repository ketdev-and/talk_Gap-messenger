package com.talkgap.messenger.group_admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.talkgap.messenger.R;

import java.util.ArrayList;

public class GPAContactAdapterI extends RecyclerView.Adapter<GPAContactHolder> {
    Context context;
    ArrayList<GPAContactModel> contactModels = new ArrayList<>();

    public GPAContactAdapterI(Context context, ArrayList<GPAContactModel> contactModels) {
        this.context = context;
        this.contactModels = contactModels;
    }
    @NonNull
    @Override
    public GPAContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  =  LayoutInflater.from(parent.getContext()).inflate(R.layout.group_admin, parent, false);
        return  new GPAContactHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GPAContactHolder holder, int position) {

        holder.cimg.setImageResource(contactModels.get(position).getCimg());
        holder.username.setText(contactModels.get(position).getUsername());

    }

    @Override
    public int getItemCount() {
        return contactModels.size();
    }
}
