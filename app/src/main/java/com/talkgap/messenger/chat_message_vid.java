package com.talkgap.messenger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.shape.CornerFamily;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.BlurTransformation;

public class chat_message_vid extends AppCompatActivity {
    ShapeableImageView send_vid, rec_vid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_message_vid);

        send_vid = findViewById(R.id.send_vid);
        rec_vid = findViewById(R.id.rec_vid);

        send_vid.setShapeAppearanceModel(send_vid.getShapeAppearanceModel()
                .toBuilder()
                .setTopRightCorner(CornerFamily.ROUNDED,40)
                .setBottomLeftCorner(CornerFamily.ROUNDED,40)
                .setBottomRightCorner(CornerFamily.ROUNDED, 40)
                .build());

        rec_vid.setShapeAppearanceModel(rec_vid.getShapeAppearanceModel()
                .toBuilder()
                .setTopLeftCorner(CornerFamily.ROUNDED,40)
                .setBottomLeftCorner(CornerFamily.ROUNDED,40)
                .setTopRightCorner(CornerFamily.ROUNDED, 40)
                .build());

        Picasso.get()
                .load(R.drawable.sim)
                .transform(new BlurTransformation(this, 50))
                .into(send_vid);
    }
}