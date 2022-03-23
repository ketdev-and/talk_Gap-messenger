package com.talkgap.messenger.Roaster;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.annotation.Nullable;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.ping.android.ServerPingWithAlarmManager;

import java.io.IOException;

public class RoasterConnectionService extends Service {

    private static  final  String LOGTAG =  "TChatConnectionServices";

    private boolean mActivive;//stores whether or not the thread is active
    private Thread mThread;
    private Handler mTHandler;// we use this handler to post message to the background thread.
    private static RoasterConnection mConnection;

    public static RoasterConnection getmConnection() {
        return mConnection;
    }

    public RoasterConnectionService() {

    }

    public void initConnection()
    {
        Log.d(LOGTAG,"initConnection()");
        if(mConnection == null)

        {
            mConnection = new RoasterConnection(this);
        }

        try {
            mConnection.connect();
        } catch (IOException |SmackException |XMPPException e) {
            stop();
            Log.d(LOGTAG, "something whent wrong while connecting check credentials");
            Intent i = new Intent(Constants.BroadCastMessage.UI_CONNECTION_ERROR);
            i.setPackage(getApplicationContext().getPackageName());
            getApplicationContext().sendBroadcast(i);


            boolean logged_in_state = PreferenceManager.getDefaultSharedPreferences(getApplicationContext())
                    .getBoolean("xmpp_logged_in", false);

            if (!logged_in_state)
            {
                Log.d(LOGTAG, "Logged in State :" + logged_in_state + "called stopself()");
                stopSelf();
            }  else
            {
                Log.d(LOGTAG, "Logged in state : " + logged_in_state);

            }
            e.printStackTrace();

        }

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ServerPingWithAlarmManager.onCreate(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        start();
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ServerPingWithAlarmManager.onDestroy();
        stop();
    }

    public  void start()
    {
        Log.d(LOGTAG,"Service Start() function called. mActive : " + mActivive);
        if(!mActivive)
        {
            mActivive = true;
            if (mThread == null || !mThread.isAlive())
            {
                mThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        mTHandler = new Handler();
                        initConnection();

                        //THE CODE HERE RUNS IN A BACKGROUND THREAD.
                        Looper.loop();
                    }
                });
                mThread.start();
            }
        }
    }

    public void stop()
    {
        Log.d(LOGTAG,"stop()");
        mActivive = false;
        mTHandler.post(new Runnable() {
            @Override
            public void run() {
                if (mConnection != null)
                {
                    mConnection.disconnect();
                }

            }
        });

    }
}
