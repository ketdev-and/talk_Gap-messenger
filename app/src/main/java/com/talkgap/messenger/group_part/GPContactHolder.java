package com.talkgap.messenger.group_part;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.talkgap.messenger.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class GPContactHolder extends RecyclerView.ViewHolder {
    CircleImageView cimg;
    Button invite;
    TextView username, phone;

    public GPContactHolder(@NonNull View itemView) {
        super(itemView);
        cimg = itemView.findViewById(R.id.ContactImage);
        username = itemView.findViewById(R.id.Contactusername);
        phone = itemView.findViewById(R.id.ContactPhone);

    }
}
