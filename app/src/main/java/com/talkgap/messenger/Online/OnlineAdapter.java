package com.talkgap.messenger.Online;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.talkgap.messenger.ChatMessage;
import com.talkgap.messenger.R;

import java.util.ArrayList;

public class OnlineAdapter extends RecyclerView.Adapter<OnlineHolder> {
    Context context;
    ArrayList<OnlineModel> onlineModels = new ArrayList<>();

    public OnlineAdapter(Context context, ArrayList<OnlineModel> addFriendModels) {
        this.context = context;
        this.onlineModels = addFriendModels;
    }
    @NonNull
    @Override
    public OnlineHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  =  LayoutInflater.from(parent.getContext()).inflate(R.layout.online, parent, false);
        return  new OnlineHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OnlineHolder holder, int position) {

        holder.Adim.setImageResource(onlineModels.get(position).getAdim());
        holder.Adname.setText(onlineModels.get(position).getAdmessage());
        holder.AdTitle.setText(onlineModels.get(position).getAdtitle());
        holder.user.setImageResource(onlineModels.get(position).getUser());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ChatMessage.class);
                view.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return onlineModels.size();
    }
}
