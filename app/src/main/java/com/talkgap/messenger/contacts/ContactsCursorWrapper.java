package com.talkgap.messenger.contacts;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.talkgap.messenger.addFragment.Contact;


/**
 * Created by Everest on March / 16 / 2021
 */

public class ContactsCursorWrapper extends CursorWrapper {
/**
 * Create a cursor wrapper.
 *
 *
 * @param cursor The underLying cursor to wrap.
 */

public ContactsCursorWrapper(Cursor cursor){

    super(cursor);
}

  public Contacts getContact(){

    String subscriptionTypeString = getString(getColumnIndex(Contacts.Cols.SUBSCRIPTION_TYPE));
    String jid = getString(getColumnIndex(Contacts.Cols.CONTACT_JID));
    int ContactsUniqueId = getInt(getColumnIndex(Contacts.Cols.CONTACT_UNIQUE_ID));
    String profileImagePath = getString(getColumnIndex(Contacts.Cols.PROFILE_IMAGE_PATH));
      int pendingFromInt = getInt(getColumnIndex(Contacts.Cols.PENDING_STATUS_FROM));
      int pendingToInt = getInt(getColumnIndex(Contacts.Cols.PENDING_STATUS_TO));
      int onlineStatusInt = getInt(getColumnIndex(Contacts.Cols.ONLINE_STATUS));

   Contacts.SubscriptionType subscriptionType = null;

      if (subscriptionTypeString.equals("NONE")) {
          subscriptionType = Contacts.SubscriptionType.NONE;
      } else if (subscriptionTypeString.equals("FROM")) {
          subscriptionType = Contacts.SubscriptionType.FROM;
      } else if (subscriptionTypeString.equals("TO")) {
          subscriptionType = Contacts.SubscriptionType.TO;
      } else if (subscriptionTypeString.equals("BOTH")) {
          subscriptionType = Contacts.SubscriptionType.BOTH;
      }


      Contacts Contacts = new Contacts(jid, subscriptionType);
   Contacts.setPersistID(ContactsUniqueId);
   Contacts.setProfileImagePath(profileImagePath);

      Contacts.setPendingFrom((pendingFromInt == 0) ? false:true);
      Contacts.setPendingTo((pendingToInt == 0)? false:true);
      Contacts.setOnlineStatus((onlineStatusInt == 0) ? false:true);
   Contacts.setInviteSwitch(false);



   return Contacts;

  }

}
