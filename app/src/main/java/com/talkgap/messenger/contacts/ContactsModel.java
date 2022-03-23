package com.talkgap.messenger.contacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.talkgap.messenger.addFragment.Contact;
import com.talkgap.messenger.persistence.DataBaseBackend;

import java.util.ArrayList;
import java.util.List;

public class ContactsModel {

    private static final String LOGTAG = "TalkGapChatConnection";
    private static ContactsModel sContactMoel;
    private Context mContext;
    private SQLiteDatabase mDataBase;


    private ContactsModel(Context context)
    {
       mContext = context;
       mDataBase = DataBaseBackend.getinstance(mContext).getWritableDatabase();



    }

    public static ContactsModel get(Context context)
    {
        if(sContactMoel == null)
        {
           sContactMoel = new ContactsModel(context);
        }
        return  sContactMoel;
    }

    public  List<Contacts>getContact()
    {
        List<Contacts> contact = new ArrayList<>();
        ContactsCursorWrapper cursor = queryContacts(null, null);

        try
        {
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                contact.add(cursor.getContact());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return contact;
    }

    public Contacts getContactByJidString(String jidString)
    {
        List<Contacts> contacts = getContact();
        List<String> stringJids = new ArrayList<>();

        Contacts mContact = null;

        Log.d(LOGTAG,"Looping around contacts============");

        for(Contacts contact :contacts)
        {
            Log.d(LOGTAG,"Contact Jid :"+contact.getJid());
            Log.d(LOGTAG,"Subscription type :"+ contact.getTypeStringValue(contact.getSubscriptionType()));
            if(contact.getJid().equals(jidString))
            {
                mContact = contact;
            }
        }
        return mContact;
    }

    public List<String> getContactsJidStrings()
    {
        List<Contacts> contacts = getContact();
        List<String> stringJids = new ArrayList<>();
        for(Contacts contact :contacts)
        {
            Log.d(LOGTAG,"Contact Jid :"+contact.getJid());
            stringJids.add(contact.getJid());
        }
        return stringJids;
    }

    public boolean isContactStranger(String contactJid)
    {
        List<String> contacts = getContactsJidStrings();
        return !contacts.contains(contactJid);
    }


    private ContactsCursorWrapper  queryContacts (String whereClause , String [] whereArgs)
    {
        Cursor cursor = mDataBase.query(
                Contacts.TABLE_NAME,
                null, // Colums - null selected all colums
                whereClause,
                whereArgs,
                null, //groupBy
                null, //having
                null //orderBy
        );

        return  new ContactsCursorWrapper(cursor);
    }


    public  boolean addContact(Contacts c)
    {
        // TODO: Check if contact already in the db before adding.
        ContentValues values = c.getContentValues();
        if ((mDataBase.insert(Contacts.TABLE_NAME, null, values) == -1))
        {
            return false;
        } else
        {
            return true;
        }
    }

    public boolean updateContactSubscription(Contacts contact)
    {
        Contacts mContact = contact;
        String jidString = contact.getJid();

        //Get new contentvalues to add to db
        ContentValues values = contact.getContentValues();
        //db.update returns the number of affected rows in the db, if this return value is not zero, we succeeded

        int rows = mDataBase.update(Contacts.TABLE_NAME, values, "jid = ? ", new String[] { jidString } );
        Log.d(LOGTAG,rows + " rows affected in db");

        if( rows != 0)
        {
            Log.d(LOGTAG,"DB record update successful ");
            return true;
        }
        return false;
    }

    public void updateContactSubscriptionOnSendSubscribed(String contact)
    {
        //When we send a subscribed, the pending_from changes to from
        Contacts mContact = getContactByJidString(contact);
        mContact.setPendingFrom(false);
        updateContactSubscription(mContact);
    }

    public boolean deleteContact(Contacts c)
    {
        int uniqueId = c.getPersistID();
        return deleteContact(uniqueId);
    }


    public boolean deleteContact(int uniqueId)
    {
        int value = mDataBase.delete(Contacts.TABLE_NAME, Contacts.Cols.CONTACT_UNIQUE_ID +"= ?", new String[] {String.valueOf(uniqueId)});

        if (value == 1)
        {
            Log.d(LOGTAG, "Successfully Deleted a record");
            return  true;
        } else
        {
            Log.d(LOGTAG, "Could not delete record");
            return false;
        }


    }

}
