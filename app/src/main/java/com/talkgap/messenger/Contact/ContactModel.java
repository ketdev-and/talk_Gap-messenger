//package com.talkgap.messenger.Contact;
//
//import android.content.ContentValues;
//import android.content.Context;
//
//public class ContactModel {
//   public boolean onOFF = true;
//   private int Cimg;
//   private int Button;
//
//
//
//   private String Username, Phone;
//   private SubscriptionType subscriptionType;
//   Context mcontext;
//   private String ProfileImgPath;
//   private int persistID;
//
//
//   public static final String TABLE_NAME = "talKgap_contacts";
//
//   public static final class Col{
//      public static final String CONTACT_UNIQUE_ID = "talKgap_contacts_UID";
//      public static final String CONTACT_JID = "talKgap_contacts_JID";
//      public static final String SUBSCRIPTION_TYPE = "talKgap_contacts_SY";
//      public static final String PROFILE_IMG_PATH = "talKgap_contacts_PIP";
//   }
//
//   public ContactModel(Context context) {
//      this.mcontext = context;
//   }
//
//   public ContentValues getContentValues(){
//      ContentValues values = new ContentValues();
//      values.put(Col.CONTACT_JID, Username);
//      values.put(Col.SUBSCRIPTION_TYPE, getTypeStringValue(subscriptionType));
//      values.put(Col.PROFILE_IMG_PATH, ProfileImgPath);
//
//      return values;
//   }
//
//   public String getTypeStringValue(SubscriptionType subscriptionType) {
//         String subType;
//      switch (subscriptionType){
//         case NONE_NONE:
//            subType = "NONE_NONE";
//            break;
//         case NONE_PENDING:
//            subType = "NONE_PENDING";
//         break;
//         case NONE_TO:
//            subType = "NONE_TO";
//         break;
//         case PENDING_NONE:
//            subType = "PENDING_NONE";
//         break;
//         case PENDING_PENDING:
//            subType = "PENDING_PENDING";
//         break;
//         case PENDING_TO:
//            subType = "PENDING_TO";
//         break;
//         case FROM_NONE:
//            subType = "FROM_NONE";
//         break;
//         case FROM_PENDING:
//            subType = "FROM_PENDING";
//         break;
//         case FROM_TO:
//            subType = "FROM_TO";
//         break;
//
//         default:
//            subType = "INTERMEDIATE";
//         break;
//      }
//
//      return subType;
//   }
//
//   public int getCimg() {
//      return Cimg;
//   }
//
//   public void setCimg(int cimg) {
//      Cimg = cimg;
//   }
//
//   public int getButton() {
//      return Button;
//   }
//
//   public void setButton(int button) {
//      Button = button;
//   }
//
//   public String getUsername() {
//      return Username;
//   }
//
//   public void setUsername(String username) {
//      Username = username;
//   }
//
//   public String getPhone() {
//      return Phone;
//   }
//
//   public void setPhone(String phone) {
//      Phone = phone;
//   }
//
//   public enum SubscriptionType{
//      NONE_NONE,
//      NONE_PENDING,
//      NONE_TO,
//
//      PENDING_NONE,
//      PENDING_PENDING,
//      PENDING_TO,
//
//      FROM_NONE,
//      FROM_PENDING,
//      FROM_TO
//
//
//   }
//
//   public SubscriptionType getSubscriptionType() {
//      return subscriptionType;
//   }
//
//   public void setSubscriptionType(SubscriptionType subscriptionType) {
//      this.subscriptionType = subscriptionType;
//   }
//
//   public String getProfileImgPath() {
//      return ProfileImgPath;
//   }
//
//   public void setProfileImgPath(String profileImgPath) {
//      ProfileImgPath = profileImgPath;
//   }
//
//   public int getPersistID() {
//      return persistID;
//   }
//
//   public void setPersistID(int persistID) {
//      this.persistID = persistID;
//   }
//}
