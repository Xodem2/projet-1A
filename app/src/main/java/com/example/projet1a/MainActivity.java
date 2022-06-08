package com.example.projet1a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;

import com.example.projet1a.profile.PlayerProfile;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String PLAYER_NICKNAME = "playerNickname";
    public static final String PLAYER_AGE = "playerAge";
    public static final String PLAYER_ID = "playerID";

    private PlayerProfile player;
    private Button profileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: load player profile from XML if existing
        this.player = new PlayerProfile();

        this.profileButton = (Button) findViewById(R.id.button_adult);
        this.profileButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == this.profileButton.getId()) this.showProfilePage();
    }

    private void showProfilePage() {
        Intent profileActivityIntent = new Intent(this, ProfileActivity.class);
        profileActivityIntent.putExtra(PLAYER_NICKNAME, this.player.getNickname());
        profileActivityIntent.putExtra(PLAYER_AGE, this.player.getAge());
        profileActivityIntent.putExtra(PLAYER_ID, this.player.getID());
        startActivity(profileActivityIntent);
    }
}