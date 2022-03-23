package com.talkgap.messenger.AddFriend;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.talkgap.messenger.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddFriendHolder extends RecyclerView.ViewHolder {
    CircleImageView Adim;
    TextView Adname, AdTitle;

    public AddFriendHolder(@NonNull View itemView) {
        super(itemView);
        Adim = itemView.findViewById(R.id.adstatusImage);
        AdTitle = itemView.findViewById(R.id.adusername);
        Adname = itemView.findViewById(R.id.admessage);

    }
}
