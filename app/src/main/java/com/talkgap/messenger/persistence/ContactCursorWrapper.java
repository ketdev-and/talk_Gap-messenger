//package com.talkgap.messenger.persistence;
//
//import android.database.Cursor;
//import android.database.CursorWrapper;
//
//import com.talkgap.messenger.Contact.ContactModel;
//
//public class ContactCursorWrapper extends CursorWrapper {
//    /**
//     * Creates a cursor wrapper.
//     *
//     * @param cursor The underlying cursor to wrap.
//     */
//    public ContactCursorWrapper(Cursor cursor) {
//        super(cursor);
//    }
//
//    public ContactModel getContact(){
//        String subscriptionTypeString = getString(getColumnIndex(ContactModel.Col.SUBSCRIPTION_TYPE));
//        String contactjid = getString(getColumnIndex(ContactModel.Col.CONTACT_JID));
//        int contactuniqueid = getInt(getColumnIndex(ContactModel.Col.CONTACT_UNIQUE_ID));
//        String profileimagepath = getString(getColumnIndex(ContactModel.Col.PROFILE_IMG_PATH));
//
//        ContactModel.SubscriptionType subscriptionType = null;
//
//        switch (subscriptionTypeString){
//            case "NONE_NONE":
//                subscriptionType = ContactModel.SubscriptionType.NONE_NONE;
//                break;
//            case "NONE_PENDING":
//                subscriptionType = ContactModel.SubscriptionType.NONE_PENDING;
//                break;
//            case "NONE_TO":
//                subscriptionType = ContactModel.SubscriptionType.NONE_TO;
//                break;
//            case "PENDING_NONE":
//                subscriptionType = ContactModel.SubscriptionType.PENDING_NONE;
//                break;
//            case "PENDING_PENDING":
//                subscriptionType = ContactModel.SubscriptionType.PENDING_PENDING;
//                break;
//            case "PENDING_TO":
//                subscriptionType = ContactModel.SubscriptionType.PENDING_TO;
//                break;
//            case "FROM_NONE":
//                subscriptionType = ContactModel.SubscriptionType.FROM_NONE;
//                break;
//            case "FROM_PENDING":
//                subscriptionType = ContactModel.SubscriptionType.FROM_PENDING;
//                break;
//            case "FROM_TO":
//                subscriptionType = ContactModel.SubscriptionType.FROM_TO;
//                break;
//
//            default:
//                subscriptionType = null;
//                break;
//        }
//
//        ContactModel cm = new ContactModel(null);
//        cm.setPersistID(contactuniqueid);
////        cm.setCimg(profileimagepath);
//        cm.setUsername(contactjid);
//
//        return cm;
//    }
//}
