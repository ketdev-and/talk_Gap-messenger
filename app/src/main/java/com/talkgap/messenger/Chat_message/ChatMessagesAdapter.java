package com.talkgap.messenger.Chat_message;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.talkgap.messenger.R;
import com.talkgap.messenger.Roaster.RoasterConnection;
import com.talkgap.messenger.Roaster.RoasterConnectionService;
import com.talkgap.messenger.Utility;

import java.util.List;


public class ChatMessagesAdapter extends RecyclerView.Adapter<ChatMessageViewHolder>{



    private static final int SENT = 1;
    private static  final int RECEIVED = 2;
    private static final String LOGTAG = "ChatMessageAdapter";
    private List<ChatMessageModel> mChatMessageList;
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private String contactJid;
    private OnInformRecyclerViewToScrollDownListener mOnInformRecyclerViewToScrollDownListener;
    private OnItemLongClickListener onItemLongClickListener;
    public ChatMessagesAdapter(Context context, String contactJid)
    {
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.contactJid = contactJid;
        mChatMessageList = ChatMessagesModel.get(context).getMessages(contactJid);

    }

    public void setmOnInformRecyclerViewToScrollDownListener(OnInformRecyclerViewToScrollDownListener mOnInformRecyclerViewToScrollDownListener) {
        this.mOnInformRecyclerViewToScrollDownListener = mOnInformRecyclerViewToScrollDownListener;
    }

    public OnItemLongClickListener getOnItemLongClickListener() {
        return onItemLongClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    public void  informRecyclerViewToScrollDown()
    {
        mOnInformRecyclerViewToScrollDownListener.onInformRecyclerViewToScrollDown(mChatMessageList.size());
    }

    @NonNull
    @Override
    public ChatMessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        switch (viewType)
        {
            case  SENT:
                    itemView = mLayoutInflater.inflate(R.layout.chat_message_sent, parent, false);
                    return  new ChatMessageViewHolder(itemView, this);
            case RECEIVED:
                   itemView = mLayoutInflater.inflate(R.layout.chat_message_recieved, parent, false);
                  return  new ChatMessageViewHolder(itemView, this);
            default:
                   itemView = mLayoutInflater.inflate(R.layout.chat_message_sent, parent, false);
                   return  new ChatMessageViewHolder(itemView, this);


        }



    }

    @Override
    public void onBindViewHolder(@NonNull ChatMessageViewHolder holder, int position) {
       ChatMessageModel chatMessage = mChatMessageList.get(position);
       holder.bindChat(chatMessage);

    }

    @Override
    public int getItemCount() {
        return mChatMessageList.size();
    }

    @Override
    public int getItemViewType(int position) {

        ChatMessageModel.Type messageType = mChatMessageList.get(position).getType();
        if (messageType == ChatMessageModel.Type.SENT)
        {
            return SENT;
        } else {
            return RECEIVED;
        }

    }

    public void onMessageAdd() {
        mChatMessageList = ChatMessagesModel.get(mContext).getMessages(contactJid);
        notifyDataSetChanged();
        informRecyclerViewToScrollDown();

    }

    public interface  OnInformRecyclerViewToScrollDownListener {
        public void onInformRecyclerViewToScrollDown(int size);
    }

    public interface OnItemLongClickListener{
        public void onItemLongClick(int uniqueId, View anchor);
    }
}


class ChatMessageViewHolder extends RecyclerView.ViewHolder{

    private static final String LOGTAG = "ChatMessageViewHolder" ;
    TextView message, time;
    private ChatMessageModel mChatMessage;
    private ChatMessagesAdapter mAdapter;

    public ChatMessageViewHolder(final View itemView, final ChatMessagesAdapter mAdapter) {
        super(itemView);

        message = itemView.findViewById(R.id.chat_message_body);
        time = itemView.findViewById(R.id.chat_message_time);
        this.mAdapter = mAdapter;


        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                ChatMessagesAdapter.OnItemLongClickListener listener = mAdapter.getOnItemLongClickListener();
                if ( listener!=null)
                {
                    listener.onItemLongClick(mChatMessage.getPersistID(),itemView);
                }

                return false;
            }
        });

    }
    public void bindChat (ChatMessageModel chatMessage)
    {
        mChatMessage = chatMessage;
        message.setText(chatMessage.getMessage());
        time.setText(Utility.getFormattedTime(chatMessage.getTime()));

//        ChatMessageModel.Type type = mChatMessage.getType();


//        if( type == ChatMessageModel.Type.RECEIVED)
//        {
//            RoasterConnection TG = RoasterConnectionService.getmConnection();
//            if(TG != null)
//            {
//                String imageAbsPath = TG.getProfileImageAbsolutePath(mChatMessage.getContactJid());
//                if(imageAbsPath != null)
//                {
//                    Drawable d = Drawable.createFromPath(imageAbsPath);
//                    profileImage.setImageDrawable(d);
//                }
//            }
//        }
//

//        if( type == ChatMessageModel.Type.SENT)
//        {
//            TalkGapChatConnection TG = TalkGapChatConnectionService.getConnection();
//            if(TG != null)
//            {
//                String selfJid = PreferenceManager.getDefaultSharedPreferences(mAdapter.getContext()).getString("xmpp_jid", null);
//                if(selfJid != null)
//                {
//                    Log.d(LOGTAG, "God a vaild self jid :"+ selfJid);
//                    String imageAbsPath = TG.getProfileImageAbsolutePath(selfJid);
//                    if (imageAbsPath != null)
//                    {
//                        Drawable d = Drawable.createFromPath(imageAbsPath);
//                        profileImage.setImageDrawable(d);
//                    }
//                } else
//                {
//                    Log.d(LOGTAG, "Could not get a Valid self jid");
//                }
//            }
//
//        }

    }




}