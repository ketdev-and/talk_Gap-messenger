package com.talkgap.messenger;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.talkgap.messenger.Roaster.Constants;
import com.talkgap.messenger.Roaster.RoasterConnection;
import com.talkgap.messenger.Roaster.RoasterConnectionService;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatUserProfile extends AppCompatActivity implements View.OnClickListener {
    private static final String LOGTAG = "ChatUserProfile";
    private static final int SELECT_PHOTO = 100 ;
    private TextView connectionStatusTextView;
    private BroadcastReceiver mBroadcastReceiver;
    private CircleImageView circleImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_user_profile);

        String status;
        connectionStatusTextView = (TextView) findViewById(R.id.connection_status);
        RoasterConnection connection = RoasterConnectionService.getmConnection();

        if (connection != null){
           status = connection.getConnectionStateString();
           connectionStatusTextView.setText(status);
        }

        circleImageView = findViewById(R.id.user_profile_picture);
        circleImageView.setOnClickListener(this);


        String selfJid = PreferenceManager.getDefaultSharedPreferences(getApplicationContext())
                .getString("xmpp_jid",null);

        RoasterConnection Tc = RoasterConnectionService.getmConnection();


            String imageAbsPath = Tc.getProfileImageAbsolutePath(selfJid);
            if ( imageAbsPath != null)
            {
                Drawable d = Drawable.createFromPath(imageAbsPath);
               circleImageView.setImageDrawable(d);
            }

    }

    @Override
    protected void onPause() {
        super.onPause();
        this.unregisterReceiver(mBroadcastReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();

                switch (action)
                {
                    case Constants.BroadCastMessage.UI_CONNECTION_STATUS_CHANGE_FLAG:
                        String status = intent.getStringExtra(Constants.UI_CONNECTION_STATUS_CHANGE);
                        connectionStatusTextView.setText(status);
                        break;

                }
            }
        };
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constants.BroadCastMessage.UI_CONNECTION_STATUS_CHANGE_FLAG);
        this.registerReceiver(mBroadcastReceiver, filter);

    }

    @Override
    public void onClick(View v) {
        Log.d(LOGTAG,"Clicked on the profile picture");
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode) {
            case SELECT_PHOTO:
                if(resultCode == RESULT_OK){
                    Log.d(LOGTAG,"Result is OK");
                    Uri selectedImage = data.getData();

                    Bitmap bm = null;

                    try {
                        bm = decodeUri(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    if( bm != null)
                    {
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bm.compress(Bitmap.CompressFormat.PNG, 10, stream);
                        byte[] byteArray = stream.toByteArray();
                        Log.d(LOGTAG,"Bitmap not NULL, proceeding with setting image. The array size is :" +byteArray.length);

                        RoasterConnection rc = RoasterConnectionService.getmConnection();

                            rc.setSelfAvatar(byteArray);

                            Log.d(LOGTAG, "Avatar set correctly");
                                //Set the avatar to be shown in the profile Image View
                                Drawable image = new BitmapDrawable(getResources(),
                                        BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length));
                                circleImageView.setImageDrawable(image);

                                rc.saveUserAvatarsLocaly();




                    }
                }else
                {
                    Log.d(LOGTAG,"Canceled out the Image selection act");
                }
        }
    }

    private Bitmap decodeUri(Uri selectedImage) throws FileNotFoundException {

        // Decode image size
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o);

        // The new size we want to scale to
        final int REQUIRED_SIZE = 140;

        // Find the correct scale value. It should be the power of 2.
        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;
        while (true) {
            if (width_tmp / 2 < REQUIRED_SIZE
                    || height_tmp / 2 < REQUIRED_SIZE) {
                break;
            }
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        // Decode with inSampleSize
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        return BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o2);

    }
}