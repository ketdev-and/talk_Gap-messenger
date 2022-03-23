package com.talkgap.messenger.contacts;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.talkgap.messenger.R;
import com.talkgap.messenger.addFragment.Contact;


import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * created by everest on March / 11 /2021
 */

public class ContactListAdaptertwo extends RecyclerView.Adapter<ContactHoldertwo> {

    private static final String LOGTAG = "ContactListAdapter";
    private List<Contacts> mContacts;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListerner mOnItemLongClickListerner;
    public ContactListAdaptertwo(Context context, List<Contacts> contacts)
    {
        mContacts = contacts;
        mContext = context;
        Log.d(LOGTAG,"Contructor of ChatListAdapter, the size of the backing list is :" + mContacts);

    }

    public OnItemClickListener getmOnItemClickListener() {
        return mOnItemClickListener;
    }

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;

    }

    public OnItemLongClickListerner getmOnItemLongClickListerner() {
        return mOnItemLongClickListerner;
    }

    public void setmOnItemLongClickListerner(OnItemLongClickListerner mOnItemLongClickListerner) {
        this.mOnItemLongClickListerner = mOnItemLongClickListerner;
    }

    @NonNull
    @Override
    public ContactHoldertwo onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater
                .inflate(R.layout.contact_cus, parent,
                        false);

        return new ContactHoldertwo(view, this);

    }

    @Override
    public void onBindViewHolder(@NonNull ContactHoldertwo holder, int position) {
        Contacts contact = mContacts.get(position);
        holder.bindContact(contact);

    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    public void onContactCountChange(){
        mContacts = ContactsModel.get(mContext).getContact();
        notifyDataSetChanged();
        Log.d(LOGTAG,"ContactListAdapter Knows of the change in messages");
    }

    public void getContacttwo(){
        Contacts contacts = new Contacts("ketem", Contacts.SubscriptionType.NONE);
        contacts.setPersistID(1);
        contacts.setProfileImagePath("no img");
        contacts.setInviteSwitch(true);

        contacts = new Contacts("ketem", Contacts.SubscriptionType.NONE);
        contacts.setPersistID(1);
        contacts.setProfileImagePath("no img");
        contacts.setInviteSwitch(true);

        contacts = new Contacts("ketem", Contacts.SubscriptionType.NONE);
        contacts.setPersistID(1);
        contacts.setProfileImagePath("no img");
        contacts.setInviteSwitch(true);

        mContacts.add(contacts);
    }

    public interface OnItemClickListener{
        public void onItemClick(String contactJid);
    }


    public interface OnItemLongClickListerner {

        public void onItemLongClick(int uniqueId, String contactJid, View anchor);
    }

}

class ContactHoldertwo extends RecyclerView.ViewHolder{
    private  static final  String LOGTAG = "ContactHolder";
    private TextView jidTextView;
    private  TextView subscriptionTypeTextView;
    private Contacts mContact;
    private ImageView profile_image;
    private Button invite;
    private ContactListAdaptertwo mAdapter;

    public ContactHoldertwo(@NonNull final View itemView, ContactListAdaptertwo adapter) {
        super(itemView);
        mAdapter = adapter;
        jidTextView = (TextView) itemView.findViewById(R.id.Contactusername);
        profile_image =  (CircleImageView) itemView.findViewById(R.id.ContactImage);
        invite = (Button) itemView.findViewById(R.id.invitebtn);




        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(LOGTAG, "calling the Contact Item");
                ContactListAdaptertwo.OnItemClickListener listener = mAdapter.getmOnItemClickListener();

                if ( listener != null )
                {
                    listener.onItemClick(jidTextView.getText().toString());

                    Log.d(LOGTAG, "calling the listerner method");
                }
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                ContactListAdaptertwo.OnItemLongClickListerner listener = mAdapter.getmOnItemLongClickListerner();

                if ( listener != null )
                {

                    mAdapter.getmOnItemLongClickListerner().onItemLongClick(mContact.getPersistID(), mContact.getJid(), itemView);
                    return true;
                }

                return false;
            }
        });





    }

    void bindContact (Contacts c)
    {
        mContact = c;
        if(mContact == null)
        {
            return;
        }
        jidTextView.setText(mContact.getJid());
        profile_image.setImageResource(R.drawable.ic_profile_image);


        if(mContact.isInviteSwitch()){
            invite.setVisibility(View.VISIBLE);
        }else{
            invite.setVisibility(View.GONE);
        }
    }









}

