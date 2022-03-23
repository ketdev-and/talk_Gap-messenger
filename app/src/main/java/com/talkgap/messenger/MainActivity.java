package com.talkgap.messenger;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import com.talkgap.messenger.ChatList.ChatListAdapter;
import com.talkgap.messenger.Roaster.RoasterConnectionService;
import com.talkgap.messenger.Status.StatusEng;
import com.talkgap.messenger.Tab.TabEng;
import com.talkgap.messenger.Tab.noScroll;
import com.talkgap.messenger.ui.login.LoginActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private static final String LOGTAG = "TalkGapChatConnection" ;
    TabLayout tabLayout;
    ViewPager viewPager;
    RecyclerView recyclerView;
    FloatingActionButton fla, user;
    protected static final int REQUEST_EXCEMP_OP = 188;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean logged_in_state = PreferenceManager.getDefaultSharedPreferences(getApplicationContext())
                .getBoolean("xmpp_logged_in",false);
        if (!logged_in_state)
        {
            Log.d(LOGTAG,"Logged_in_state:"+ logged_in_state );
            Intent i = new Intent (MainActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        } else
        {
            if (!Utility.isServiceRunning(RoasterConnectionService.class, getApplicationContext()))
            {
                Log.d(LOGTAG, "service not running, start it....");
                //start the service
                Intent i1 = new Intent(this, RoasterConnectionService.class);
                startService(i1);

            } else
            {
                Log.d(LOGTAG, "The service is already running.");
            }
        }

        ChatListAdapter adapter = new ChatListAdapter(getApplicationContext());
        adapter.onChatCountChange();

        tabLayout = findViewById(R.id.mainTab);
        viewPager = (noScroll) findViewById(R.id.chatViewPager);
        recyclerView = findViewById(R.id.statusRec);
        fla = findViewById(R.id.action);
        user = findViewById(R.id.p);



        //Calling the TabEng from Tab package
        TabEng tabEng = new TabEng(getSupportFragmentManager());
        tabEng.CreateTab(tabLayout, viewPager);

        //Calling StatusEng from Status package
        StatusEng statuseng = new StatusEng(recyclerView);
        statuseng.Recycle();

        //Navigate FAB
        newActivity();

        boolean deniedBatterOptimizationRequest = PreferenceManager.getDefaultSharedPreferences(getApplicationContext())
                .getBoolean("Denied_battery_optimization_request", false);

        boolean userHasGoneThroughBatterOptimizations = PreferenceManager.getDefaultSharedPreferences(getApplicationContext())
                .getBoolean("User_has_got_through_battery_optimization", false);

        if (!deniedBatterOptimizationRequest && !userHasGoneThroughBatterOptimizations)
        {
           requestBatteryOptimization();
        } else
        {
            Log.d(LOGTAG, "user has chosen to opt out of battery Optimation. Don't BOTHER");

        }

    }

    public void requestBatteryOptimization() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(MainActivity.this);
            builder.setTitle("Battery Optimization Request");
            builder.setMessage("Battery Optimizations are need to make the app work right");


        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Log.d(LOGTAG, "User click on OK");

                Intent intent = new Intent();


                intent.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
                intent.setData(Uri.parse("package:" + getApplicationContext().getPackageName()));
                startActivityForResult(intent, REQUEST_EXCEMP_OP);



            }
        });

        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d(LOGTAG, "User clicked on cancel");
                //save the users choice and Naver bother the again.
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                prefs.edit().putBoolean("Denied_battery_optimization_request", true).commit();
                dialog.cancel();

            }
        });
        builder.show();
    }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)
        {
            if(requestCode == REQUEST_EXCEMP_OP)
            {
                Log.d(LOGTAG, "User Want to except app from OPTIMIZATIONS");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    Intent intent = new Intent();
                    String packageName = getPackageName();
                    PowerManager pm = (PowerManager) getSystemService(getApplicationContext().POWER_SERVICE);
                    if(pm.isIgnoringBatteryOptimizations(packageName))
                    {
                        intent.setAction(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS);
                        startActivity(intent);

                    }
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    prefs.edit().putBoolean("User_has_got_through_battery_optimization", true).commit();
                }

            }
        } else {
            if (requestCode == REQUEST_EXCEMP_OP) {
                Log.d(LOGTAG, "Result code is cancel");
            }

        }

    }

    public void newActivity(){
        fla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Message.class);
                startActivity(intent);
            }
        });

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ChatUserProfile.class );
                startActivity(i);
            }
        });
    }

    //Optional request Navigation
    public  void optional(View v){
        Intent intent = new Intent(this, Request.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        ChatListAdapter adapter = new ChatListAdapter(getApplicationContext());
        adapter.onChatCountChange();
        adapter.notifyDataSetChanged();
        super.onResume();
    }

    @Override
    protected void onResumeFragments() {
        ChatListAdapter adapter = new ChatListAdapter(getApplicationContext());
        adapter.onChatCountChange();
        adapter.notifyDataSetChanged();
        super.onResumeFragments();
    }
}