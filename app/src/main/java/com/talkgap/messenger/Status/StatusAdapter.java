package com.talkgap.messenger.Status;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.talkgap.messenger.R;

import java.util.ArrayList;

public class StatusAdapter extends RecyclerView.Adapter<ViewHolder> {
    ArrayList<Integer> Images = new ArrayList<>();
    Context context;

    public StatusAdapter(Context context, ArrayList<Integer> images) {
        Images = images;
        context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.status, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.CustomImage.setImageResource(Images.get(position));
    }

    @Override
    public int getItemCount() {
        return Images.size();
    }
}
