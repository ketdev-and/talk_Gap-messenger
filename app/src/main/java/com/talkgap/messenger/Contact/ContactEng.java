//package com.talkgap.messenger.Contact;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.util.Log;
//
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.talkgap.messenger.MainActivity;
//import com.talkgap.messenger.Message;
//import com.talkgap.messenger.R;
//import com.talkgap.messenger.addFragment.Contact;
//import com.talkgap.messenger.persistence.ContactCursorWrapper;
//import com.talkgap.messenger.persistence.DataBaseBackend;
//
//import java.lang.reflect.Array;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ContactEng {
//
//    private static final String LOGCAT = "TalkGapChatConnection" ;
//    Context mcontext;
//    RecyclerView recyclerView;
//    SQLiteDatabase mDatabase;
//    ContactModel contactModels;
//
//
//
//    public ContactEng(Context context) {
//
//        this.mcontext = context;
//        mDatabase = DataBaseBackend.getinstance(mcontext).getWritableDatabase();
//
//
//    }
//
//    public ArrayList<ContactModel> getContacts(){
//        ArrayList<ContactModel> arrayList = new ArrayList<>();
//
//        ContactCursorWrapper cursor = new ContactEng(new Message()).queryContacts(null, null);
//
//        try{
//            cursor.moveToFirst();
//            while(!cursor.isAfterLast()){
//                arrayList.add(cursor.getContact());
//                cursor.moveToNext();
//            }
//        }finally {
//            cursor.close();
//        }
//
//        return arrayList;
//    }
//
//    public ContactCursorWrapper queryContacts(String whereClause, String[] whereArgs){
//        Cursor cursor =  mDatabase.query(
//                ContactModel.TABLE_NAME,
//                null,
//                whereClause,
//                whereArgs,
//                null,
//                null,
//                null
//
//        );
//
//        return new ContactCursorWrapper(cursor);
//    }
//
//
//
//    public boolean addContact(ContactModel c){
//        ContentValues values = c.getContentValues();
//        if((mDatabase.insert(ContactModel.TABLE_NAME, null, values)== -1)){
//            return false;
//        }else{
//            return true;
//        }
//    }
//
//
//    public ContactAdapter adp(){
//        ContactAdapter adapter = new ContactAdapter(new MainActivity(), getContacts());
//        adapter.notifyDataSetChanged();
//        Log.d(LOGCAT, "adp: adp called");
//        return adapter;
//    }
//
//
//}
