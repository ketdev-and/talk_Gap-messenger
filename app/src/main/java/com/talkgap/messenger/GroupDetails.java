package com.talkgap.messenger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.talkgap.messenger.GroupUsers.GroupUsersEng;

public class GroupDetails extends AppCompatActivity {
    RecyclerView recyclerView;
    RadioGroup radioGroup;
    Dialog dialog;
    TextView textView;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_details);

        button = findViewById(R.id.btn_category);
        recyclerView = findViewById(R.id.group_names_rec);
        textView = findViewById(R.id.cat_textView);
        dialog = new Dialog(this);


        recyclerView.setNestedScrollingEnabled(false);


        GroupUsersEng groupUsersEng = new GroupUsersEng(recyclerView);
        groupUsersEng.Recycle();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               dialog.setContentView(R.layout.dialog);
               radioGroup = dialog.findViewById(R.id.dialog);
               dialog.show();

               radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                   @Override
                   public void onCheckedChanged(RadioGroup group, int checkedId) {
                       if (checkedId == R.id.sport) {
                           textView.setText("Sports");
                           dialog.dismiss();
                       } else if (checkedId == R.id.religion) {
                           textView.setText("Religion");
                           dialog.dismiss();
                       } else if (checkedId == R.id.LifeStyle) {
                           textView.setText("Life");
                           dialog.dismiss();
                       } else if (checkedId == R.id.Health) {
                           textView.setText("Health");
                           dialog.dismiss();
                       } else if (checkedId == R.id.Art) {
                           textView.setText("Art and Culture");
                           dialog.dismiss();
                       } else if (checkedId == R.id.Business) {
                           textView.setText("Business");
                           dialog.dismiss();
                       } else {
                           textView.setText("");
                           dialog.dismiss();
                       }
                   }
               });
            }
        });
    }
}