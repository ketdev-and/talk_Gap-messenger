package com.talkgap.messenger.chatTheme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.talkgap.messenger.R;

import java.util.ArrayList;

public class themeAdapter extends BaseAdapter {
    private Context mcontext;
    ArrayList<themModel> list;
    public themeAdapter(Context context, ArrayList<themModel> list ){
        this.mcontext = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View grid;
        LayoutInflater layoutInflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null){
            grid = layoutInflater.inflate(R.layout.chat_theme, null);
            ImageView themebg = grid.findViewById(R.id.theme_bg);
            ImageView selected = grid.findViewById(R.id.selected_theme);

            if(list.get(position).selected == true){
                selected.setVisibility(View.VISIBLE);

            }
            themebg.setImageResource(list.get(position).getThemeImage());
        }else{
            grid = (View) convertView;
        }

        return grid;
    }
}
