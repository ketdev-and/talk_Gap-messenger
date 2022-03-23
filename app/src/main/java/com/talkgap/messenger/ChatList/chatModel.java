package com.talkgap.messenger.ChatList;

import android.content.ContentValues;
import android.text.format.DateFormat;

import java.util.concurrent.TimeUnit;


public class chatModel {
   private int cim, status;
   private String title;
   private String message;
   private long time;
   public static final String TABLE_NAME = "talkgap_chat";
   private int persistID;
   private ContactType contacttype;
   private long unreadCount, lastMessageTimeStamp;

   public int getPersistID() {
      return persistID;
   }

   public chatModel(String title, String message, ContactType contacttype, long unreadCount, long lastMessageTimeStamp, int cim) {
      this.title = title;
      this.message = message;
      this.contacttype = contacttype;
      this.unreadCount = unreadCount;
      this.lastMessageTimeStamp = lastMessageTimeStamp;
   }

   public int getCim() {
      return cim;
   }

   public void setCim(int cim) {
      this.cim = cim;
   }

   public int getStatus() {
      return status;
   }

   public void setStatus(int status) {
      this.status = status;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getMessage() {
      return message;
   }

   public void setMessage(String message) {
      this.message = message;
   }

   public long getTime() {
      return time;
   }

   public void setTime(long time) {
      this.time = time;
   }

   public void setPersistID(int persistID) {
      this.persistID = persistID;
   }

   public ContactType getContacttype() {
      return contacttype;
   }

   public void setContacttype(ContactType contacttype) {
      this.contacttype = contacttype;
   }

   public long getUnreadCount() {
      return unreadCount;
   }

   public void setUnreadCount(long unreadCount) {
      this.unreadCount = unreadCount;
   }

   public long getLastMessageTimeStamp() {
      return lastMessageTimeStamp;
   }

   public void setLastMessageTimeStamp(long lastMessageTimeStamp) {
      this.lastMessageTimeStamp = lastMessageTimeStamp;
   }

   public ContentValues getContentValues() {
      ContentValues values = new ContentValues();
      values.put(chatModel.Col.CONTACT_JID, title);
      values.put(chatModel.Col.CONTACT_TYPE, getTypeStringValue(contacttype));
      values.put(chatModel.Col.PROFILE_IMG_PATH, cim);
      values.put(chatModel.Col.LAST_MESSAGE, message);
      values.put(chatModel.Col.LAST_MESSAGE_TIME_STAMP, lastMessageTimeStamp);
      values.put(chatModel.Col.UNREAD_COUNT, unreadCount);


      return values;
   }

   public String getTypeStringValue(ContactType type)
   {
      if(type== ContactType.ONE_ON_ONE)
         return "ONE_ON_ONE";
      else if(type == ContactType.GROUP)
         return "GROUP";
      else if(type == ContactType.STRANGER)
         return "STRANGER";
      else
         return null;
   }




   public enum ContactType {
      ONE_ON_ONE, GROUP, STRANGER;
   }

   public static final class Col {
      public static final String CHAT_UNIQUE_ID = "talKgap_chat_UID";
      public static final String CONTACT_JID = "talKgap_chat_JID";
      public static final String CONTACT_TYPE = "talKgap_contacts_type";
      public static final String PROFILE_IMG_PATH = "talKgap_contacts_PIP";
      public static final String LAST_MESSAGE = "talKgap_chat_LM";
      public static final String UNREAD_COUNT = "talKgap_chat_UC";
      public static final String LAST_MESSAGE_TIME_STAMP = "talKgap_chat_LTS";

   }



}