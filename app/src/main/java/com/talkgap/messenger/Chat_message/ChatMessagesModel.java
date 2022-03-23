package com.talkgap.messenger.Chat_message;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.talkgap.messenger.ChatList.ChatEngs;
import com.talkgap.messenger.persistence.DataBaseBackend;


import java.util.ArrayList;
import java.util.List;

public class ChatMessagesModel {


    private static final String LOGTAG = "ChatMessagesModel";
    private static ChatMessagesModel sChatMessagesModel;
public List<ChatMessageModel> messages = new ArrayList<>();
    private Context context;
        private SQLiteDatabase mDatabase;;




    private ChatMessagesModel(Context context)
    {
       this.context = context;
       this.mDatabase = DataBaseBackend.getinstance(context).getWritableDatabase();



    }

    public static ChatMessagesModel get(Context context)
    {
        if (sChatMessagesModel == null)
        {
            sChatMessagesModel = new ChatMessagesModel(context);
        }

        return   sChatMessagesModel;
    }

    public List<ChatMessageModel> getMessages(String counterpartJid)

    {
        List<ChatMessageModel> messages = new ArrayList<>();
        ChatCursorWrapper cursor = queryMessages("talKgap_contacts_JID= ?", new String [] {counterpartJid});
        try
        {
            cursor.moveToFirst();
            while( !cursor.isAfterLast())
            {
                messages.add(cursor.getChatMessage());
                cursor.moveToNext();
            }

        }finally {
            cursor.close();
        }



        Log.d(LOGTAG, "added "+ counterpartJid);

        return messages;
    }


    public boolean addMessage(ChatMessageModel message)
    {
        ContentValues values = message.getContentValues();
        if ((mDatabase.insert(ChatMessageModel.TABLE_NAME, null, values)== -1))
        {
            Log.d(LOGTAG, "added false messages"+values.toString());
            return false;
        }else
        {
            Log.d(LOGTAG, "added true messages"+values.toString());
            ChatEngs.get(context).updateLastMessageDetails(message);
            return true;
        }


    }


    public boolean deleteMessage(ChatMessageModel message)
    {

        return  deleteMessage(message.getPersistID());
    }

    public boolean  deleteMessage( int uniqueId)
    {
        int value =mDatabase.delete(ChatMessageModel.TABLE_NAME,ChatMessageModel.Col.MESSAGE_UNIQUE_ID+"=?",new String[] {String.valueOf(uniqueId)});

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




    private ChatCursorWrapper queryMessages(String whereClause , String [] whereArgs)
    {
        Cursor cursor = mDatabase.query(
                ChatMessageModel.TABLE_NAME,
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
