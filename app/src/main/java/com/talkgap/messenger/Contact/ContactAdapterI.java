//package com.talkgap.messenger.Contact;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.talkgap.messenger.R;
//
//import java.util.ArrayList;
//
//public class ContactAdapterI extends RecyclerView.Adapter<ContactHolder> {
//    Context context;
//    ArrayList<ContactModel> contactModels = new ArrayList<>();
//
//    public ContactAdapterI(Context context, ArrayList<ContactModel> contactModels) {
//        this.context = context;
//        this.contactModels = contactModels;
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
//        holder.cimg.setImageResource(contactModels.get(position).getCimg());
//        holder.username.setText(contactModels.get(position).getUsername());
//        holder.phone.setText(contactModels.get(position).getPhone());
//
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return contactModels.size();
//    }
//}
