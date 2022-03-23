package com.talkgap.messenger.GroupUsers;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.talkgap.messenger.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class GroupUsersHolder extends RecyclerView.ViewHolder {
    CircleImageView CustomImage;
    TextView text;
    public GroupUsersHolder(@NonNull View itemView) {
        super(itemView);
        CustomImage = itemView.findViewById(R.id.group_name_Image);
        text = itemView.findViewById(R.id.group_user_name);
    }
}
