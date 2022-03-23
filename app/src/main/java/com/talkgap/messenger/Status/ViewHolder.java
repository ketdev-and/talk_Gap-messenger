package com.talkgap.messenger.Status;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.talkgap.messenger.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewHolder extends RecyclerView.ViewHolder {
    CircleImageView CustomImage;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        CustomImage = itemView.findViewById(R.id.statusImage);
    }
}
