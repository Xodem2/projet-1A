package com.example.projet1a;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.projet1a.profile.PlayerProfile;
import com.example.projet1a.profile.PlayerStatistics;


public class ProfileActivity extends AppCompatActivity {

    private PlayerProfile player;

    private TextView nicknameTW;
    private TextView ageTW;
    private TextView idTW;
    private TextView totalScoreTW;
    private TextView spScoreTW;
    private TextView mpScoreTW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        this.player = getIntent().getParcelableExtra(MainActivity.PLAYER_PROFILE_EXTRA);

        this.nicknameTW = (TextView) findViewById(R.id.profilePagePseudoFillID);
        this.ageTW = (TextView) findViewById(R.id.profilePageAgeFillID);
        this.idTW = (TextView) findViewById(R.id.profilePageIdFillID);
        this.totalScoreTW = (TextView) findViewById(R.id.profilePageStatsScoreTotFillID);
        this.spScoreTW = (TextView) findViewById(R.id.profilePageStatsScoreSoloFillID);
        this.mpScoreTW = (TextView) findViewById(R.id.profilePageStatsScoreMultFillID);

        this.showPlayerInfo();
        this.showPlayerStats();
    }

    @Override
    protected void onResume() {

        super.onResume();
        this.showPlayerInfo();
        this.showPlayerStats();
    }

    private void showPlayerInfo(){
        this.nicknameTW.setText(this.player.getNickname());
        this.ageTW.setText(String.valueOf(this.player.getAge()) + " ans");
        this.idTW.setText(this.player.getID());
    }

    private void showPlayerStats(){
        PlayerStatistics stats = this.player.getStats();
        this.totalScoreTW.setText(String.valueOf(stats.getTotalScore()) + "points");
        this.spScoreTW.setText(String.valueOf(stats.getSingleplayerScore()) + "points");
        this.mpScoreTW.setText(String.valueOf(stats.getMultiplayerScore()) + "points");
    }


}