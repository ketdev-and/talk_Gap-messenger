package com.talkgap.messenger.Chat_message;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.talkgap.messenger.R;

public class ChatMessageHolder extends RecyclerView.ViewHolder {

    TextView message, time;

    public ChatMessageHolder(@NonNull View itemView) {
        super(itemView);

        message = itemView.findViewById(R.id.chat_message_body);
        time = itemView.findViewById(R.id.chat_message_time);

    }
}
