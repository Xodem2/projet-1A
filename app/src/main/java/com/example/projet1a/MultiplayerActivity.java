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
    private ImageButton rankingMultiplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multijoueur);
        this.joinMultiplayer = (ImageButton) findViewById(R.id.button_join_session);
        this.joinMultiplayer.setOnClickListener(this);
        this.createMultiplayer = (ImageButton)  findViewById(R.id.button_create_session);
        this.createMultiplayer.setOnClickListener(this);
        this.rankingMultiplayer = (ImageButton)  findViewById(R.id.button_ranking);
        this.rankingMultiplayer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == this.createMultiplayer.getId()) this.showCreatePage();
        else if(v.getId() == this.joinMultiplayer.getId()) this.showJoinPage();
        else if(v.getId() == this.rankingMultiplayer.getId()) this.showRankPage();
    }

    private void showCreatePage(){
        Intent createActivityIntent = new Intent(this, MainMultiplayerActivity.class);
        startActivity(createActivityIntent);
    }

    private void showJoinPage(){
        Intent joinActivityIntent = new Intent(this, JoinActivity.class);
        startActivity(joinActivityIntent);
    }

    private void showRankPage(){
        Intent rankActivityIntent = new Intent(this, PlayerRankingActivity.class);
        startActivity(rankActivityIntent );
    }
}