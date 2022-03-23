package com.talkgap.messenger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.shape.CornerFamily;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.BlurTransformation;


public class chat_message_img extends AppCompatActivity {
   ShapeableImageView send_img, rec_img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_message_img);

        send_img = findViewById(R.id.send_img);
        rec_img = findViewById(R.id.rec_img);


        send_img.setShapeAppearanceModel(send_img.getShapeAppearanceModel()
                .toBuilder()
                .setTopRightCorner(CornerFamily.ROUNDED,40)
                .setBottomLeftCorner(CornerFamily.ROUNDED,40)
                .setBottomRightCorner(CornerFamily.ROUNDED, 40)
                .build());
       rec_img.setShapeAppearanceModel(rec_img.getShapeAppearanceModel()
                .toBuilder()
                .setTopLeftCorner(CornerFamily.ROUNDED,40)
                .setBottomLeftCorner(CornerFamily.ROUNDED,40)
                .setTopRightCorner(CornerFamily.ROUNDED, 40)
                .build());


        Picasso.get()
                .load(R.drawable.sim)
                .transform(new BlurTransformation(this, 60))
                .into(send_img);


    }
}