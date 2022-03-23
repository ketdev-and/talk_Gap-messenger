package com.talkgap.messenger.group_admin;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.talkgap.messenger.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class GPAContactHolder extends RecyclerView.ViewHolder {
    CircleImageView cimg;
    Button invite;
    TextView username, phone;

    public GPAContactHolder(@NonNull View itemView) {
        super(itemView);
        cimg = itemView.findViewById(R.id.group_admin_img);
        username = itemView.findViewById(R.id.group_admin_name);
        phone = itemView.findViewById(R.id.invitebtn);

    }
}
