package com.talkgap.messenger.Chat_message;

import android.database.Cursor;
import android.database.CursorWrapper;
import android.util.Log;

public class ChatCursorWrapper extends CursorWrapper {
    private static final String LOGTAG = "talkgapconnection";

    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public ChatCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public ChatMessageModel getChatMessage (){

        String jid = getString(getColumnIndex(ChatMessageModel.Col.CONTACT_JID));
        String messageType = getString(getColumnIndex(ChatMessageModel.Col.MESSAGE_TYPE));
        String Message = getString(getColumnIndex(ChatMessageModel.Col.MESSAGE));
        long lastMessageTimeStamp = getLong(getColumnIndex(ChatMessageModel.Col.TIME));
        int uniqueId = getInt(getColumnIndex(ChatMessageModel.Col.MESSAGE_UNIQUE_ID));

        String sentStatusString = getString(getColumnIndex(ChatMessageModel.Col.SENT_STATUS));
        String attachmentPath = getString(getColumnIndex(ChatMessageModel.Col.ATTACHMENT_PATH));

        ChatMessageModel.Type chatMessageType = null;
        ChatMessageModel.SentStatus sentStatus = null;


        ChatMessageModel.Type ChatType = null;

        if( messageType.equals("SENT"))
        {
            ChatType = ChatMessageModel.Type.SENT;
        }else if(messageType.equals("RECEIVED"))
        {
            ChatType = ChatMessageModel.Type.RECEIVED;
        }

        ChatMessageModel chatMessage = new ChatMessageModel(Message,lastMessageTimeStamp,ChatType,jid);
        chatMessage.setPersistID(uniqueId);
        Log.d(LOGTAG, "added false messages"+chatMessage.getMessage());
        return  chatMessage;
    }
}
