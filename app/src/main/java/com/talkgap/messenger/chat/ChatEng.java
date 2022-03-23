//package com.talkgap.messenger.chat;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.util.Log;
//
//import com.talkgap.messenger.ChatList.ChatCursorWrapper;
//import com.talkgap.messenger.ChatList.chatModel;
//import com.talkgap.messenger.persistence.DataBaseBackend;
//
//import java.util.ArrayList;
//
//public class ChatEng  {
//
//
//
//    Context mcontext;
//    private SQLiteDatabase mDatabase;
//    private static final String LOGTAG = "TalkGapChatConnection" ;
//
//    public ChatEng(Context context) {
//        this.mcontext = context;
//        this.mDatabase = DataBaseBackend.getinstance(mcontext).getWritableDatabase();
//
//    }
//
//
//    public ArrayList<chatModel> AddToList(){
//        ArrayList<chatModel> arrayList = new ArrayList<>();
//
//        ChatCursorWrapper cursor = queryChats(null,null);
//
//        try
//        {
//            cursor.moveToFirst();
//            while( !cursor.isAfterLast())
//            {
//                Log.d(LOGTAG , "Got a chat from db : Timestamp :"+cursor.getChat().getLastMessageTimeStamp());
//                arrayList.add(cursor.getChat());
//                cursor.moveToNext();
//            }
//
//        }finally {
//            cursor.close();
//        }
//
//        return arrayList;
//    }
//
//
//    public boolean addChat( chatModel c )
//    {
//        //TODO: Check if Chat already in db before adding.
//        ContentValues values = c.getContentValues();
//        if ((mDatabase.insert(chatModel.TABLE_NAME, null, values)== -1))
//        {
//            return false;
//        }else
//        {
//            return true;
//        }
//    }
//
//    public boolean deleteChat(chatModel c)
//    {
//        return deleteChat(c.getPersistID());
//    }
//
//    public boolean deleteChat(int uniqueId)
//    {
//        int value =mDatabase.delete(chatModel.TABLE_NAME,chatModel.Col.CHAT_UNIQUE_ID+"=?",new String[] {String.valueOf(uniqueId)});
//
//        if(value == 1)
//        {
//            Log.d(LOGTAG, "Successfully deleted a record");
//            return true;
//        }else
//        {
//            Log.d(LOGTAG, "Could not delete record");
//            return false;
//        }
//    }
//
//
//    private ChatCursorWrapper queryChats(String whereClause , String [] whereArgs)
//    {
//        Cursor cursor = mDatabase.query(
//                chatModel.TABLE_NAME,
//                null ,//Columns - null selects all columns
//                whereClause,
//                whereArgs,
//                null ,//groupBy
//                null, //having
//                null//orderBy
//        );
//        return new ChatCursorWrapper(cursor);
//    }
//
//
//
//
//
//}
