package com.example.projet1a;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;

import androidx.annotation.NonNull;

public class ProfilePictureDialog extends Dialog {

    public ProfilePictureDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        setContentView(R.layout.game_stats_item);
    }
}
