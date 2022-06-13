package com.example.projet1a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.projet1a.profile.PlayerProfile;
import com.example.projet1a.profile.PlayerStatistics;

import org.w3c.dom.Text;


public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private PlayerProfile player;

    private EditText editNickname;
    private EditText editAge;
    private TextView playerId;
    private Button updateButton;
    private TextView spScore;
    private TextView mpScore;
    private TextView totalScore;
    private Button gameStatsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        this.player = DataProvider.getInstance().getPlayer();

        this.editNickname = (EditText) findViewById(R.id.profilePageEditNicknameId);
        this.editAge = (EditText) findViewById(R.id.profilePageEditAgeId);
        this.playerId = (TextView) findViewById(R.id.profilePagePlayerIdFillId);
        this.updateButton = (Button) findViewById(R.id.profilePageUpdateButtonId);
        this.spScore = (TextView) findViewById(R.id.profilePageSpScoreFillId);
        this.mpScore = (TextView) findViewById(R.id.profilePageMpScoreFillId);
        this.totalScore = (TextView) findViewById(R.id.profilePageTotalScoreFillId);
        this.gameStatsButton = (Button) findViewById(R.id.profilePageGameStatsButtonId);

        this.updateButton.setOnClickListener(this);
        this.gameStatsButton.setOnClickListener(this);

        this.showPlayerInfo();
        this.showPlayerStats();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.showPlayerInfo();
        this.showPlayerStats();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == this.updateButton.getId()) this.updatePlayer();
        if(v.getId() == this.gameStatsButton.getId()) this.showGameStats();
    }

    private void showGameStats() {

    }

    private void updatePlayer() {
        this.player.setNickname(this.editNickname.getText().toString());
        this.player.setAge(Integer.parseInt(this.editAge.getText().toString()));

        // update database
        DataProvider.getInstance().getMyLocalDatabase().savePlayer(this.player);
    }

    private void showPlayerInfo(){
        this.playerId.setText(this.player.getID());
        this.editNickname.setText(this.player.getNickname());
        this.editAge.setText(String.valueOf(this.player.getAge()));
    }

    private void showPlayerStats(){
        this.spScore.setText(String.valueOf(this.player.getStats().getSingleplayerScore()));
        this.mpScore.setText(String.valueOf(this.player.getStats().getMultiplayerScore()));
        this.totalScore.setText(String.valueOf(this.player.getStats().getTotalScore()));
    }

}