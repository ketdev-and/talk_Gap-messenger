package com.talkgap.messenger.group_media;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.talkgap.messenger.R;

public class GMViewHolder extends RecyclerView.ViewHolder {
   ImageView CustomImage;
    public GMViewHolder(@NonNull View itemView) {
        super(itemView);
        CustomImage = itemView.findViewById(R.id.GMstatusImage);
    }
}
