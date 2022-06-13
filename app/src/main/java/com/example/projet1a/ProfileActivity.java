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


public class ProfileActivity extends AppCompatActivity  {

    private PlayerProfile player;

    private EditText editNickname;
    private EditText editAge;
    private TextView playerId;
    private Button updateButton;
    private TextView spScore;
    private TextView mpScore;
    private TextView totalScore;
    private TextView level;
    private TextView currentXp;
    private TextView neededXp;
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

        this.gameStatsButton = (Button) findViewById(R.id.profilePageGameStatsButtonId);

    }
}