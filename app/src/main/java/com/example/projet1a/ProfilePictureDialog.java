package com.example.projet1a;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import androidx.annotation.NonNull;

public class ProfilePictureDialog extends Dialog implements View.OnClickListener {

    private ImageView avatar1;
    private ImageView avatar2;
    private ImageView avatar3;
    private ImageView avatar4;
    private ImageView frame1;
    private ImageView frame2;
    private ImageView frame3;
    private ImageView frame4;

    private ImageView profilePicture;
    private ImageView frame;

    public ProfilePictureDialog(@NonNull Context context, ImageView profilePicture, ImageView frame) {
        super(context);
        this.profilePicture = profilePicture;
        this.frame = frame;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        setContentView(R.layout.profile_picture_dialog);

        this.avatar1 = (ImageView) findViewById(R.id.profilePageAvatar1Id);
        this.avatar2 = (ImageView) findViewById(R.id.profilePageAvatar2Id);
        this.avatar3 = (ImageView) findViewById(R.id.profilePageAvatar3Id);
        this.avatar4 = (ImageView) findViewById(R.id.profilePageAvatar4Id);
        this.frame1 = (ImageView) findViewById(R.id.profilePageFrame1Id);
        this.frame2 = (ImageView) findViewById(R.id.profilePageFrame2Id);
        this.frame3 = (ImageView) findViewById(R.id.profilePageFrame3Id);
        this.frame4 = (ImageView) findViewById(R.id.profilePageFrame4Id);

        this.avatar1.setOnClickListener(this);
        this.avatar2.setOnClickListener(this);
        this.avatar3.setOnClickListener(this);
        this.avatar4.setOnClickListener(this);
        this.frame1.setOnClickListener(this);
        this.frame2.setOnClickListener(this);
        this.frame3.setOnClickListener(this);
        this.frame4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == avatar1.getId() || v.getId() == avatar2.getId()
        || v.getId() == avatar3.getId() || v.getId() == avatar4.getId()) this.profilePicture.setImageDrawable(((ImageView)v).getDrawable());
        else this.frame.setImageDrawable(((ImageView)v).getDrawable());
        this.dismiss();
    }
}
