package com.talkgap.messenger.Chat_message;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.talkgap.messenger.R;

import java.util.ArrayList;

public class ChatMessageAdapter extends RecyclerView.Adapter<ChatMessageHolder> {
    private static final int SENT = 1;
    private static  final int RECEIVED = 2;

    Context context;
    ArrayList<ChatMessageModel> chatMessageModels = new ArrayList<>();

    public ChatMessageAdapter(Context context, ArrayList<ChatMessageModel> chatmessagemodels) {
        this.context = context;
        this.chatMessageModels = chatmessagemodels;

    }
    @NonNull
    @Override
    public ChatMessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView;
        switch (viewType)
        {
            case  SENT:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_message_sent, parent, false);
                return  new ChatMessageHolder(itemView);
            case RECEIVED:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_message_recieved, parent, false);
                return  new ChatMessageHolder(itemView);
            default:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_message_sent, parent, false);
                return  new ChatMessageHolder(itemView);


        }
    }

    @Override
    public void onBindViewHolder(@NonNull ChatMessageHolder holder, int position) {

        holder.message.setText(chatMessageModels.get(position).getMessage());
        holder.time.setText(chatMessageModels.get(position).getFormattedTime());


    }

    @Override
    public int getItemCount() {
        return chatMessageModels.size();
    }

    @Override
    public int getItemViewType(int position) {

        ChatMessageModel.Type messageType = chatMessageModels.get(position).getType();
        if (messageType == ChatMessageModel.Type.SENT)
        {
            return SENT;
        } else {
            return RECEIVED;
        }

    }
}
