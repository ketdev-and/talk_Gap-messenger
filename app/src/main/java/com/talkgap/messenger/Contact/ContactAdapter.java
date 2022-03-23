//package com.talkgap.messenger.Contact;
//
//import android.content.Context;
//import android.content.Intent;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.talkgap.messenger.ChatMessage;
//import com.talkgap.messenger.Message;
//import com.talkgap.messenger.R;
//import com.talkgap.messenger.addFragment.Contact;
//
//import java.lang.reflect.Array;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ContactAdapter extends RecyclerView.Adapter<ContactHolder> {
//    Context mcontext;
//    ArrayList<ContactModel> mcontactModels;
//    private static final String LOGCAT = "TalkGapChatConnection";
//
//
//    public ContactAdapter(Context context, ArrayList<ContactModel> contactModels) {
//        this.mcontext = context;
//        this.mcontactModels = contactModels;
//        this.notifyDataSetChanged();
//
//
//    }
//    @NonNull
//    @Override
//    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view  =  LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_message_audio, parent, false);
//        return  new ContactHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ContactHolder holder, int position) {
//
//        holder.cimg.setImageResource(mcontactModels.get(position).getCimg());
//        holder.invite.setVisibility(View.GONE);
//
////        if(contactModels.get(position).onOFF == true){
////            holder.invite.setVisibility(View.VISIBLE);
////        }else{
////            holder.invite.setVisibility((View.GONE));
////        }
//        holder.username.setText(mcontactModels.get(position).getUsername());
//        holder.phone.setText(mcontactModels.get(position).getPhone());
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(view.getContext(), ChatMessage.class);
//                view.getContext().startActivity(intent);
//            }
//        });
//
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return mcontactModels.size();
//
//    }
//
//
//}
