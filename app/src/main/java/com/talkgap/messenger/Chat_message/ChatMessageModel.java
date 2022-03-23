package com.talkgap.messenger.Chat_message;

import android.content.ContentValues;
import android.text.format.DateFormat;

import java.util.concurrent.TimeUnit;

public class ChatMessageModel {

      private String Message;
      long Time;
      private Type type;
      private String contactJid;
    public static final String TABLE_NAME = "talKgap_message";
      private int persistID;
    private SentStatus sentStatus;
    private String attachmentPath;

    public ChatMessageModel(String message, long time, Type type, String contactJid) {
        this.Message = message;
        this.Time = time;
        this.type = type;
        this.contactJid = contactJid;
    }

    public int getPersistID() {
        return persistID;
    }

    public String getContactJid() {
        return contactJid;
    }

    public void setContactJid(String contactJid) {
        this.contactJid = contactJid;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getMessage() {
         return Message;
      }

      public void setMessage(String message) {
         Message = message;
      }

      public long getTime() {
         return Time;
      }

      public void setTime(long time) {
         Time = time;
      }

    public void setPersistID(int persistID) {
        this.persistID = persistID;
    }

    public SentStatus getSentStatus() {
        return sentStatus;
    }

    public void setSentStatus(SentStatus sentStatus) {
        this.sentStatus = sentStatus;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    public String getTypeStringValue(Type type)
    {
        if(type==Type.SENT)
            return "SENT";
        else if( type == Type.RECEIVED)
            return "RECEIVED";
        else if( type == Type.IMAGE_SENT)
            return "IMAGE_SENT";
        else if( type == Type.IMAGE_RECEIVED)
            return "IMAGE_RECEIVED";
        else if( type == Type.AUDIO_SENT)
            return "AUDIO_SENT";
        else if( type == Type.AUDIO_RECEIVED)
            return "AUDIO_RECEIVED";
        else if( type == Type.VIDEO_SENT)
            return "VIDEO_SENT";
        else if( type == Type.VIDEO_RECEIVED)
            return "VIDEO_RECEIVED";
        else if( type == Type.PDF_SENT)
            return "PDF_SENT";
        else if( type == Type.PDF_RECEIVED)
            return "PDF_RECEIVED";
        else if( type == Type.OFFICE_SENT)
            return "OFFICE_SENT";
        else if( type == Type.OFFICE_RECEIVED)
            return "OFFICE_RECEIVED";
        else if( type == Type.OTHER_SENT)
            return "OTHER_SENT";
        else
            return "OTHER_RECEIVED";
    }

    public String getFormattedTime(){
        long oneDayInMillis = TimeUnit.DAYS.toMillis(1); // 24 * 60 * 60 * 1000;
        long timeDifference = System.currentTimeMillis() - Time;

        return timeDifference < oneDayInMillis
                ? DateFormat.format("hh:mm a", Time).toString()
                : DateFormat.format("dd MMM - hh:mm a", Time).toString();


    }

    public String getSentStatusStringValue(SentStatus sentStatus)
    {
        switch (sentStatus)
        {
            case NONE:
                return "NONE";
            case SENDING:
                return "SENDING";
            case SENT:
                return "SENT";
            case FAILED:
                return "FAILED";
            case RECEIVED:
                return "RECEIVED";
        }
        return "INDETERMINATE";
    }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(Col.MESSAGE, Message);
        values.put(Col.TIME, Time);
        values.put(Col.MESSAGE_TYPE, getTypeStringValue(type));
        values.put(Col.CONTACT_JID, contactJid);
        values.put(Col.SENT_STATUS, getSentStatusStringValue(sentStatus));
        values.put(Col.ATTACHMENT_PATH, attachmentPath);


        return values;
    }

    public enum SentStatus
    {
        NONE,SENDING,SENT,RECEIVED,FAILED
    }

    public static final class Col{
        public static final String MESSAGE_UNIQUE_ID = "talKgap_message_UID";
        public static final String MESSAGE = "talKgap_message";
        public static final String TIME = "talKgap_message_time";
        public static final String CONTACT_JID = "talKgap_contacts_JID";
        public static final String MESSAGE_TYPE = "talKgap_message_type";
        public static final String SENT_STATUS = "sentStatus";
        public static final String ATTACHMENT_PATH= "attachmentPath";
    }

    public enum Type{
        SENT, RECEIVED,
        IMAGE_SENT,IMAGE_RECEIVED,
        AUDIO_SENT,AUDIO_RECEIVED,
        VIDEO_SENT,VIDEO_RECEIVED,
        PDF_SENT,PDF_RECEIVED,
        OFFICE_SENT,OFFICE_RECEIVED,
        OTHER_SENT,OTHER_RECEIVED

    }
}
