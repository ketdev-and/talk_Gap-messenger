package com.talkgap.messenger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.talkgap.messenger.chatTheme.themModel;
import com.talkgap.messenger.chatTheme.themeAdapter;

import java.util.ArrayList;

public class chat_theme extends AppCompatActivity {
GridView grid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_theme);

        grid = findViewById(R.id.theme_grid);


        themeAdapter adapter = new themeAdapter(this, list() );
        grid.setAdapter(adapter);
    }

    public ArrayList<themModel> list() {
        ArrayList<themModel> atm =  new ArrayList<themModel>();
        themModel tm = new themModel();
        tm.setThemeImage(R.drawable.sim);
        tm.selected = true;
        atm.add(tm);

        tm = new themModel();
        tm.setThemeImage(R.drawable.chat_bg);
        tm.selected = false;
        atm.add(tm);

        tm = new themModel();
        tm.setThemeImage(R.drawable.sim);
        tm.selected = false;
        atm.add(tm);

        tm = new themModel();
        tm.setThemeImage(R.drawable.chat_bg);
        tm.selected = false;
        atm.add(tm);

        tm = new themModel();
        tm.setThemeImage(R.drawable.sim);
        tm.selected = false;
        atm.add(tm);

        tm = new themModel();
        tm.setThemeImage(R.drawable.chat_bg);
        tm.selected = false;
        atm.add(tm);

        tm = new themModel();
        tm.setThemeImage(R.drawable.sim);
        tm.selected = false;
        atm.add(tm);

        tm = new themModel();
        tm.setThemeImage(R.drawable.chat_bg);
        tm.selected = false;
        atm.add(tm);

        tm = new themModel();
        tm.setThemeImage(R.drawable.sim);
        tm.selected = false;
        atm.add(tm);

        tm = new themModel();
        tm.setThemeImage(R.drawable.chat_bg);
        tm.selected = false;
        atm.add(tm);

        tm = new themModel();
        tm.setThemeImage(R.drawable.sim);
        tm.selected = false;
        atm.add(tm);

        tm = new themModel();
        tm.setThemeImage(R.drawable.chat_bg);
        tm.selected = false;
        atm.add(tm);

        tm = new themModel();
        tm.setThemeImage(R.drawable.sim);
        tm.selected = false;
        atm.add(tm);

        tm = new themModel();
        tm.setThemeImage(R.drawable.chat_bg);
        tm.selected = false;
        atm.add(tm);

        tm = new themModel();
        tm.setThemeImage(R.drawable.sim);
        tm.selected = false;
        atm.add(tm);

        tm = new themModel();
        tm.setThemeImage(R.drawable.chat_bg);
        tm.selected = false;
        atm.add(tm);

        tm = new themModel();
        tm.setThemeImage(R.drawable.sim);
        tm.selected = false;
        atm.add(tm);

        tm = new themModel();
        tm.setThemeImage(R.drawable.chat_bg);
        tm.selected = false;
        atm.add(tm);

        tm = new themModel();
        tm.setThemeImage(R.drawable.sim);
        tm.selected = false;
        atm.add(tm);

        tm = new themModel();
        tm.setThemeImage(R.drawable.chat_bg);
        tm.selected = false;
        atm.add(tm);

        tm = new themModel();
        tm.setThemeImage(R.drawable.sim);
        tm.selected = false;
        atm.add(tm);

        tm = new themModel();
        tm.setThemeImage(R.drawable.chat_bg);
        tm.selected = false;
        atm.add(tm);

        tm = new themModel();
        tm.setThemeImage(R.drawable.sim);
        tm.selected = false;
        atm.add(tm);

        tm = new themModel();
        tm.setThemeImage(R.drawable.chat_bg);
        tm.selected = false;
        atm.add(tm);

        tm = new themModel();
        tm.setThemeImage(R.drawable.sim);
        tm.selected = false;
        atm.add(tm);

        tm = new themModel();
        tm.setThemeImage(R.drawable.chat_bg);
        tm.selected = false;
        atm.add(tm);

        return  atm;
    }
}