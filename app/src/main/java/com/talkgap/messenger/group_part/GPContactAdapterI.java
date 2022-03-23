package com.talkgap.messenger.group_part;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.talkgap.messenger.R;

import java.util.ArrayList;

public class GPContactAdapterI extends RecyclerView.Adapter<GPContactHolder> {
    Context context;
    ArrayList<GPContactModel> contactModels = new ArrayList<>();

    public GPContactAdapterI(Context context, ArrayList<GPContactModel> contactModels) {
        this.context = context;
        this.contactModels = contactModels;
    }
    @NonNull
    @Override
    public GPContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  =  LayoutInflater.from(parent.getContext()).inflate(R.layout.group_part, parent, false);
        return  new GPContactHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GPContactHolder holder, int position) {

        holder.cimg.setImageResource(contactModels.get(position).getCimg());
        holder.username.setText(contactModels.get(position).getUsername());
        holder.phone.setText(contactModels.get(position).getPhone());


    }

    @Override
    public int getItemCount() {
        return contactModels.size();
    }
}
