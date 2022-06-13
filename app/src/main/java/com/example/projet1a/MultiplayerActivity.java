package com.example.projet1a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MultiplayerActivity extends AppCompatActivity  implements View.OnClickListener {

    private ImageButton joinMultiplayer;
    private ImageButton createMultiplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multijoueur);
        this.joinMultiplayer = (ImageButton) findViewById(R.id.button_join_session);
        this.joinMultiplayer.setOnClickListener(this);
        this.createMultiplayer = (ImageButton)  findViewById(R.id.button_create_session);
        this.createMultiplayer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == this.createMultiplayer.getId()) this.showCreatePage();
        else if(v.getId() == this.joinMultiplayer.getId()) this.showJoinPage();
    }

    private void showCreatePage(){
        Intent createActivityIntent = new Intent(this, CreateActivity.class);
        startActivity(createActivityIntent);
    }

    private void showJoinPage(){
        Intent joinActivityIntent = new Intent(this, JoinActivity.class);
        startActivity(joinActivityIntent);
    }
}