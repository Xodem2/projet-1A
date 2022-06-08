package com.example.projet1a;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.projet1a.profile.PlayerProfile;

public class ProfileActivity extends AppCompatActivity {

    private PlayerProfile player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        this.player = getIntent().getParcelableExtra(MainActivity.PLAYER_PROFILE_EXTRA);

        this.showPlayerInfo();
    }

    private void showPlayerInfo(){
        ((TextView) findViewById(R.id.profilePagePlayerNickname)).setText(this.player.getNickname());
        ((TextView) findViewById(R.id.profilePagePlayerAge)).setText(String.valueOf(this.player.getAge()));
        ((TextView) findViewById(R.id.profilePagePlayerId)).setText(this.player.getID());
    }


}