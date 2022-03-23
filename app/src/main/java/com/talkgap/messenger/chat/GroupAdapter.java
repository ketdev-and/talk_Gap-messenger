//package com.talkgap.messenger.chat;
//
//import android.content.Context;
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.talkgap.messenger.ChatList.chatModel;
//import com.talkgap.messenger.Group;
//import com.talkgap.messenger.R;
//
//import java.util.ArrayList;
//
//public class GroupAdapter extends RecyclerView.Adapter<ChatHolder>{
//    Context context;
//    ArrayList<chatModel> chatModels = new ArrayList<>();
//
//    public GroupAdapter(Context context, ArrayList<chatModel> chatModels) {
//        this.context = context;
//        this.chatModels = chatModels;
//    }
//    @NonNull
//    @Override
//    public ChatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view  =  LayoutInflater.from(parent.getContext()).inflate(R.layout.chatsample, parent, false);
//        return  new ChatHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ChatHolder holder, int position) {
//
//        holder.cim.setImageResource(chatModels.get(position).getCim());
//        holder.message.setText(chatModels.get(position).getMessage());
//        holder.title.setText(chatModels.get(position).getTitle());
//        holder.time.setText(chatModels.get(position).getTime());
//        holder.status.setImageResource(chatModels.get(position).getStatus());
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(view.getContext(), Group.class);
//                view.getContext().startActivity(intent);
//            }
//        });
//
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return chatModels.size();
//    }
//}
