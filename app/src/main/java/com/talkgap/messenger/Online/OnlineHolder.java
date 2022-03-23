package com.talkgap.messenger.Online;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.talkgap.messenger.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class OnlineHolder extends RecyclerView.ViewHolder {
    CircleImageView Adim;
    ImageView user;
    TextView Adname, AdTitle;

    public OnlineHolder(@NonNull View itemView) {
        super(itemView);
        Adim = itemView.findViewById(R.id.adstatusImage);
        AdTitle = itemView.findViewById(R.id.adusername);
        Adname = itemView.findViewById(R.id.admessage);
        user = itemView.findViewById(R.id.user);

    }
}
