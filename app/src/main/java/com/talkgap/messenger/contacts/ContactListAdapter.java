package com.talkgap.messenger.contacts;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.talkgap.messenger.ChatMessage;
import com.talkgap.messenger.ContactDetails;
import com.talkgap.messenger.R;
import com.talkgap.messenger.Roaster.RoasterConnection;
import com.talkgap.messenger.Roaster.RoasterConnectionService;
import com.talkgap.messenger.addFragment.Contact;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * created by everest on March / 11 /2021
 */

public class ContactListAdapter extends RecyclerView.Adapter<ContactHolder> {
    private static final String LOGTAG = "ContactListAdapter";
    private List<Contacts> mContacts;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListerner mOnItemLongClickListerner;

   public ContactListAdapter(Context context)
   {
       mContacts = ContactsModel.get(context).getContact();
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
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.contact_cus, parent, false);
        return new ContactHolder(view, this);

   }

    @Override
    public void onBindViewHolder(@NonNull ContactHolder holder, int position) {
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

    public interface OnItemClickListener{
        public void onItemClick(String contactJid);
    }

    public interface OnItemLongClickListerner {
        public void onItemLongClick(int uniqueId, String contactJid, View anchor);
    }

}

class ContactHolder extends RecyclerView.ViewHolder{
   private  static final  String LOGTAG = "ContactHolder";
   private TextView jidTextView;
   private  TextView subscriptionTypeTextView;
   private Contacts mContact;
   private ImageView profile_image, remove, details;
   private Button invite;
   private ContactListAdapter mAdapter;
   private boolean onoff;

    public ContactHolder(@NonNull final View itemView, ContactListAdapter adapter) {
        super(itemView);
        mAdapter = adapter;
        jidTextView = (TextView) itemView.findViewById(R.id.Contactusername);
        profile_image =  (CircleImageView) itemView.findViewById(R.id.ContactImage);
        invite = (Button) itemView.findViewById(R.id.invitebtn);
        remove = (ImageView) itemView.findViewById(R.id.removecon);
        details = (ImageView) itemView.findViewById(R.id.detailsbtncon);

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
               if(onoff){
                   remove.setVisibility(View.GONE);
                   details.setVisibility(View.GONE);
               }else{
                   remove.setVisibility(View.VISIBLE);
                   details.setVisibility(View.VISIBLE);
               }

                onoff = !onoff;
                Log.d(LOGTAG, "onoff : " + onoff);

                remove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ContactsModel.get(itemView.getContext()).deleteContact(mContact.getPersistID())) {
                            mAdapter.onContactCountChange();

                            if(RoasterConnectionService.getmConnection().removeRosterEntry(mContact.getJid()))
                            {
                                Log.d(LOGTAG,mContact.getJid() + "Successfully deleted from Roster");
                                Toast.makeText(
                                        itemView.getContext(),
                                        "Contact Deleted Successfully",
                                        Toast.LENGTH_SHORT
                                ).show();
                            }

                            remove.setVisibility(View.GONE);
                            details.setVisibility(View.GONE);

                            onoff = false;
                            Log.d(LOGTAG, "onoffafter : " + onoff);

                        }

                    }
                });

                return false;

            }
        });


        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d(LOGTAG, "calling the Contact Item");
                ContactListAdapter.OnItemClickListener listener = mAdapter.getmOnItemClickListener();

                if ( listener != null )
                {
                    listener.onItemClick(jidTextView.getText().toString());

                    Log.d(LOGTAG, "calling the listerner method");
                }
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

        if(mContact.isInviteSwitch()){
            invite.setVisibility(View.VISIBLE);
        }else{
            invite.setVisibility(View.GONE);
        }

        RoasterConnection rc = RoasterConnectionService.getmConnection();
        if(rc != null)
        {
            String imageAbsPath = rc.getProfileImageAbsolutePath(mContact.getJid());
            if ( imageAbsPath != null)
            {
                Drawable d = Drawable.createFromPath(imageAbsPath);
                profile_image.setImageDrawable(d);
            }
            else {
                profile_image.setImageResource(R.drawable.user_friend);
            }
        }
    }


}
