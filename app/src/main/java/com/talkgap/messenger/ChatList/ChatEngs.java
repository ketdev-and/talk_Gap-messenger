package com.talkgap.messenger.ChatList;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.talkgap.messenger.Chat_message.ChatMessageModel;
import com.talkgap.messenger.persistence.DataBaseBackend;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Everest  March /8/ 2021
 */

public class ChatEngs {

    private static final String LOGTAG = "ChatModel" ;
    private static ChatEngs sChatsModel;
    private Context mContext;
    private SQLiteDatabase mDatabase;


    private ChatEngs(Context context)
    {
        mContext = context;
        mDatabase = DataBaseBackend.getinstance(mContext).getWritableDatabase();
    }

    public static ChatEngs get(Context context)
    {
        if(sChatsModel == null)
        {
            sChatsModel = new ChatEngs(context);
        }

        return sChatsModel;
    }

    public List<chatModel> getChats()
    {
        List<chatModel> chats = new ArrayList<>();

        ChatCursorWrapper cursor = queryChats(null,null);

        try
        {
            cursor.moveToFirst();
            while( !cursor.isAfterLast())
            {
                Log.d(LOGTAG , "Got a chat from db : Timestamp :"+cursor.getChat().getLastMessageTimeStamp());
                chats.add(cursor.getChat());
                cursor.moveToNext();
            }

        }finally {
            cursor.close();
        }
        return chats;

    }


    public List<chatModel> getChatsByJid(String jid)
    {
        List<chatModel> chats = new ArrayList<>();

        ChatCursorWrapper cursor = queryChats(chatModel.Col.CONTACT_JID + "= ?",new String [] {jid});

        try
        {
            cursor.moveToFirst();
            while( !cursor.isAfterLast())
            {
                chats.add(cursor.getChat());
                cursor.moveToNext();
            }

        }finally {
            cursor.close();
        }
        return chats;
    }



    public boolean addChat( chatModel c )
    {
        //TODO: Check if Chat already in db before adding.
        ContentValues values = c.getContentValues();
        if ((mDatabase.insert(chatModel.TABLE_NAME, null, values)== -1))
        {
            return false;
        }else
        {
            return true;
        }
    }

    public boolean updateLastMessageDetails(ChatMessageModel chatMessage)
    {
        List<chatModel>  chats = getChatsByJid(chatMessage.getContactJid());
        if( !chats.isEmpty())
        {
            chatModel chat = chats.get(0);
            //Apply new settings to the chat object
            chat.setLastMessageTimeStamp(chatMessage.getTime());
            chat.setMessage(chatMessage.getMessage());

            ContentValues values = chat.getContentValues();

            //Update the information
            int ret = mDatabase.update(chatModel.TABLE_NAME, values,
                    chatModel.Col.CHAT_UNIQUE_ID + "=?",
                    new String[]{ String.valueOf(chat.getPersistID())});
            if(ret == 1)
            {
                Log.d(LOGTAG,"Chat Last Message updated successfully");
                return true;
            }else
            {
                Log.d(LOGTAG,"Could not update Chat Last Message info");
                return false;
            }
        }
        return false;
    }


    public boolean deleteChat(chatModel c)
    {
        return deleteChat(c.getPersistID());
    }

    public boolean deleteChat(int uniqueId)
    {
        int value =mDatabase.delete(chatModel.TABLE_NAME,chatModel.Col.CHAT_UNIQUE_ID+"=?",new String[] {String.valueOf(uniqueId)});

        if(value == 1)
        {
            Log.d(LOGTAG, "Successfully deleted a record");
            return true;
        }else
        {
            Log.d(LOGTAG, "Could not delete record");
            return false;
        }
    }

    private ChatCursorWrapper queryChats(String whereClause , String [] whereArgs)
    {
        Cursor cursor = mDatabase.query(
                chatModel.TABLE_NAME,
                null ,//Columns - null selects all columns
                whereClause,
                whereArgs,
                null ,//groupBy
                null, //having
                null//orderBy
        );
        return new ChatCursorWrapper(cursor);
    }



}

