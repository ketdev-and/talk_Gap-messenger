package com.talkgap.messenger.ChatList;

import android.database.Cursor;
import android.database.CursorWrapper;
import android.util.Log;

public class ChatCursorWrapper extends CursorWrapper {
    private static final String LOGTAG = "TalkGapChatConnection" ;
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public ChatCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public chatModel getChat(){
        String jid = getString(getColumnIndex(chatModel.Col.CONTACT_JID));
        String contactType = getString(getColumnIndex(chatModel.Col.CONTACT_TYPE));
        String lastMessage = getString(getColumnIndex(chatModel.Col.LAST_MESSAGE));
        long unreadCount = getLong(getColumnIndex(chatModel.Col.UNREAD_COUNT));
        long lastMessageTimeStamp = getLong(getColumnIndex(chatModel.Col.LAST_MESSAGE_TIME_STAMP));
        int uniqueId = getInt(getColumnIndex(chatModel.Col.CHAT_UNIQUE_ID));
        int cim = getInt(getColumnIndex((chatModel.Col.PROFILE_IMG_PATH)));

        Log.d(LOGTAG, "Got a Chat from database the Unique ID is :" + uniqueId);

        chatModel.ContactType chatType = null;

        if (contactType.equals("GROUP")) {
            chatType = chatModel.ContactType.GROUP;
        } else if (contactType.equals("ONE_ON_ONE")) {
            chatType = chatModel.ContactType.ONE_ON_ONE;
        } else if (contactType.equals("STRANGER")){
            chatType = chatModel.ContactType.STRANGER;
        }
        chatModel chat = new chatModel(jid,lastMessage,chatType, lastMessageTimeStamp,unreadCount, cim);
        chat.setPersistID(uniqueId);
        return chat;

    }

}
