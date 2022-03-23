package com.talkgap.messenger.group_media;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.talkgap.messenger.R;

import java.util.ArrayList;

public class GMStatusAdapter extends RecyclerView.Adapter<GMViewHolder> {
    ArrayList<Integer> Images = new ArrayList<>();
    Context context;

    public GMStatusAdapter(Context context, ArrayList<Integer> images) {
        Images = images;
        context = context;
    }

    @NonNull
    @Override
    public GMViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_media, parent, false);
        return new GMViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GMViewHolder holder, int position) {
        holder.CustomImage.setImageResource(Images.get(position));

    }

    @Override
    public int getItemCount() {
        return Images.size();
    }
}
