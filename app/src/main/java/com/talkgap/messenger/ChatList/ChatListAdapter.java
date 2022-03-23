package com.talkgap.messenger.ChatList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.talkgap.messenger.Chat_message.ChatMessageModel;
import com.talkgap.messenger.R;
import com.talkgap.messenger.Roaster.RoasterConnection;
import com.talkgap.messenger.Roaster.RoasterConnectionService;
import com.talkgap.messenger.Utility;
import com.talkgap.messenger.contacts.ContactsModel;


import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatListAdapter extends RecyclerView.Adapter<ChatHolder> {

    private static final String LOGTAG = "ChatListAdapter" ;
   List<chatModel> chatList;
   private OnItemClickListener mOnItemClickListener;
   private OnItemLongClickListener onItemLongClickListener;
   private Context mContext;
    public ChatListAdapter( Context context) {

        this.chatList = ChatEngs.get(context).getChats();
        this.mContext = context;

    }

    public OnItemClickListener getmOnItemClickListener() {
        return mOnItemClickListener;
    }

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public OnItemLongClickListener getOnItemLongClickListener() {
        return onItemLongClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    @NonNull
    @Override
    public ChatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater
                .inflate(R.layout.chatsample, parent,
                        false);

        return new ChatHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatHolder holder, int position) {

        chatModel chat = chatList.get(position);
        holder.bindChat(chat);

    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    public void onChatCountChange(){
        chatList = ChatEngs.get(mContext).getChats();
        notifyDataSetChanged();
        Log.d(LOGTAG, "ChatListAdapter Knows of the change in messages");
    }

    public interface OnItemClickListener{
       public  void onItemClick(String contactJid, chatModel.ContactType chatType);

   }

   public interface OnItemLongClickListener {

       public void onItemLongClick(String contactJid, int chatUniqueId, View anchor);

   }


}

class ChatHolder extends  RecyclerView.ViewHolder{

    private static  final  String LOGTAG = "ChatHolder";
    CircleImageView cim, Adim;
    TextView title, message, time, Adname, AdTitle;
    ImageView status, remove;
    private chatModel mChat;
    private ChatListAdapter mChatListAdapter;
    private boolean onoff;



    public ChatHolder(@NonNull final View itemView, ChatListAdapter adapter ) {
        super(itemView);

        cim = (CircleImageView) itemView.findViewById(R.id.statusImage);
        title = (TextView) itemView.findViewById(R.id.username);
        message = (TextView) itemView.findViewById(R.id.message);
        time = (TextView) itemView.findViewById(R.id.time);
        remove = (ImageView) itemView.findViewById(R.id.removeconmes);
        mChatListAdapter = adapter;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 ChatListAdapter.OnItemClickListener listener = mChatListAdapter.getmOnItemClickListener();

                 if ( listener != null )
                 {
                     listener.onItemClick(title.getText().toString(),mChat.getContacttype());
                 }

                Log.d(LOGTAG, "Click on the item in the recyclerView");
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                if(onoff){
                    remove.setVisibility(View.GONE);
                }else{
                    remove.setVisibility(View.VISIBLE);

                }

                onoff = !onoff;
                Log.d(LOGTAG, "onoff : " + onoff);

                remove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ChatEngs.get(itemView.getContext()).deleteChat(mChat.getPersistID())) {
                            mChatListAdapter.onChatCountChange();
                            Toast.makeText(
                                    itemView.getContext(),
                                    "Contact Deleted Successfully",
                                    Toast.LENGTH_SHORT
                            ).show();

                            remove.setVisibility(View.GONE);

                            onoff = false;
                            Log.d(LOGTAG, "onoffafter : " + onoff);

                        }

                    }
                });

                return false;

            }
        });
    }



    public void  bindChat(chatModel chat)
    {

        mChat = chat;
        title.setText(chat.getTitle());

        if(chat.getMessage().length() > 25) {
            message.setText(chat.getMessage().substring(0, Math.min(chat.getMessage().length()
                    , 25)) + " . . . .");
        }else{
            message.setText(chat.getMessage());
        }
        cim.setImageResource(R.drawable.ic_profile_image);
        time.setText(Utility.getFormattedTime(mChat.getLastMessageTimeStamp()));


        RoasterConnection TG = RoasterConnectionService.getmConnection();
        if(TG != null)
        {
            String imageAbsPath = TG.getProfileImageAbsolutePath(mChat.getTitle());
            if ( imageAbsPath != null)
            {
                Drawable d = Drawable.createFromPath(imageAbsPath);
                cim.setImageDrawable(d);
            }
        }


    }
}


