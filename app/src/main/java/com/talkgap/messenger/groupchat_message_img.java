package com.talkgap.messenger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.imageview.ShapeableImageView;

public class groupchat_message_img extends AppCompatActivity {
    ShapeableImageView send_img, rec_img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.groupchat_message_audio);


//        send_img = findViewById(R.id.send_img);
//        rec_img = findViewById(R.id.rec_img);
//
//
//        send_img.setShapeAppearanceModel(send_img.getShapeAppearanceModel()
//                .toBuilder()
//                .setTopLeftCorner(CornerFamily.ROUNDED,13)
//                .setTopRightCorner(CornerFamily.ROUNDED,13)
//                .setBottomLeftCorner(CornerFamily.ROUNDED,13)
//                .setBottomRightCorner(CornerFamily.ROUNDED, 13)
//                .build());
//        rec_img.setShapeAppearanceModel(rec_img.getShapeAppearanceModel()
//                .toBuilder()
//                .setTopLeftCorner(CornerFamily.ROUNDED,13)
//                .setTopRightCorner(CornerFamily.ROUNDED,13)
//                .setBottomLeftCorner(CornerFamily.ROUNDED,13)
//                .setBottomRightCorner(CornerFamily.ROUNDED, 13)
//                .build());
//
//
//        Picasso.get()
//                .load(R.drawable.sim)
//                .transform(new BlurTransformation(this, 60))
//                .into(send_img);
    }
}