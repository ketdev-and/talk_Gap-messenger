package com.talkgap.messenger.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.talkgap.messenger.Chat_message.ChatMessageModel;

import com.talkgap.messenger.ChatList.chatModel;
import com.talkgap.messenger.contacts.Contacts;


public class DataBaseBackend extends SQLiteOpenHelper {
    private static final String LOGCAT = "TalkGapChatConnection";
    private static final String DATABASE_NAME = "talkgap_db";
    private static final int DATABASE_VERSION = 2;
    private static DataBaseBackend instance = null;

    private static String CREATE_CHAT_LIST_STATEMENT = "create table "
            + chatModel.TABLE_NAME + "("
            + chatModel.Col.CHAT_UNIQUE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + chatModel.Col.CONTACT_TYPE + " TEXT, " + chatModel.Col.PROFILE_IMG_PATH + " TEXT,"
            + chatModel.Col.CONTACT_JID + " TEXT,"
            + chatModel.Col.LAST_MESSAGE + " TEXT, " + chatModel.Col.UNREAD_COUNT + " NUMBER,"
            + chatModel.Col.LAST_MESSAGE_TIME_STAMP + " NUMBER"
            + ");";

    private static String CREATE_CONTACT_LIST_STATEMENT = "create table "
            + Contacts.TABLE_NAME + "("
            + Contacts.Cols.CONTACT_UNIQUE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + Contacts.Cols.SUBSCRIPTION_TYPE + " TEXT, " + Contacts.Cols.CONTACT_JID + " TEXT,"
            + Contacts.Cols.PROFILE_IMAGE_PATH + " TEXT,"
            + Contacts.Cols.PENDING_STATUS_FROM + " NUMBER DEFAULT 0,"
            + Contacts.Cols.PENDING_STATUS_TO + " NUMBER DEFAULT 0,"
            + Contacts.Cols.ONLINE_STATUS + " NUMBER DEFAULT 0"
            + ");";

    private static String CREATE_CHAT_MESSAGE_STATEMENT = "create table "
            + ChatMessageModel.TABLE_NAME + "("
            + ChatMessageModel.Col.MESSAGE_UNIQUE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ChatMessageModel.Col.MESSAGE + " TEXT, "
            + ChatMessageModel.Col.MESSAGE_TYPE + " TEXT, "
            + ChatMessageModel.Col.TIME + " NUMBER, "
            + ChatMessageModel.Col.CONTACT_JID + " TEXT"
            + ");";

    private DataBaseBackend(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DataBaseBackend getinstance(Context context){
        Log.d(LOGCAT, "Getting db Instance");
        if(instance == null){
            instance = new DataBaseBackend(context);
        }

        return instance;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(LOGCAT, " Creating the tables ");

        db.execSQL(CREATE_CONTACT_LIST_STATEMENT);
        db.execSQL(CREATE_CHAT_LIST_STATEMENT);
        db.execSQL(CREATE_CHAT_MESSAGE_STATEMENT);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2 && newVersion >= 2) {
            Log.d(LOGCAT,"Upgrading db to version 2....");
            db.execSQL("ALTER TABLE " + Contacts.TABLE_NAME + " ADD COLUMN "
                    + Contacts.Cols.PENDING_STATUS_TO + " NUMBER DEFAULT 0");
            db.execSQL("ALTER TABLE " + Contacts.TABLE_NAME + " ADD COLUMN "
                    + Contacts.Cols.PENDING_STATUS_FROM + " NUMBER DEFAULT 0");
            db.execSQL("ALTER TABLE " + Contacts.TABLE_NAME + " ADD COLUMN "
                    + Contacts.Cols.ONLINE_STATUS + " NUMBER DEFAULT 0");
        }
    }
}
