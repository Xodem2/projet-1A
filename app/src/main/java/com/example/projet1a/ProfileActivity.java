package com.example.projet1a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projet1a.profile.PlayerLevel;
import com.example.projet1a.profile.PlayerProfile;



public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private PlayerProfile player;
    private EditText nickname;
    private EditText age;
    private TextView playerIdFill;
    private Button updateButton;
    private ProgressBar xpBar;
    private TextView levelText;
    private TextView spScore;
    private TextView mpScore;
    private TextView totalScore;
    private ImageButton successButton;
    private ImageButton gameStatsButton;

    private ImageView profilePicture;
    private ImageView frame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        this.player = DataProvider.getInstance().getPlayer();

        this.nickname = (EditText) findViewById(R.id.profilePageEditNicknameId);
        this.age = (EditText) findViewById(R.id.profilePageEditAgeId);
        this.playerIdFill = (TextView) findViewById(R.id.profilePagePlayerIdFillId);
        this.updateButton = (Button) findViewById(R.id.profilePageUpdateButtonId);
        this.xpBar = (ProgressBar) findViewById(R.id.profilePageProgressBarId);
        this.levelText = (TextView) findViewById(R.id.profilePageLevelFillId);
        this.spScore = (TextView) findViewById(R.id.profilePageSpScoreFillId);
        this.mpScore = (TextView) findViewById(R.id.profilePageMpScoreFillId);
        this.totalScore = (TextView) findViewById(R.id.profilePageTotalScoreFillId);
        this.successButton = (ImageButton) findViewById(R.id.profilePageSuccesButtonId);
        this.gameStatsButton = (ImageButton) findViewById(R.id.profilePageGameStatsButtonId);
        this.profilePicture = (ImageView) findViewById(R.id.avatar1);
        this.frame = (ImageView) findViewById(R.id.frame1);

        this.updateButton.setOnClickListener(this);
        this.successButton.setOnClickListener(this);
        this.gameStatsButton.setOnClickListener(this);
        this.profilePicture.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == this.updateButton.getId()) this.doUpdate();
        else if(v.getId() == this.successButton.getId()) this.showSuccessPage();
        else if(v.getId() == this.gameStatsButton.getId()) this.showGameStatsPage();
        else if(v.getId() == this.profilePicture.getId()) this.showProfilePictureDialog();
    }

    private void showProfilePictureDialog() {
        ProfilePictureDialog dialog = new ProfilePictureDialog(this, profilePicture, this.frame);
        dialog.show();
    }

    private void showGameStatsPage() {
        startActivity(new Intent(this, GameStatsActivity.class));
    }

    @Override
    public void onResume(){
        super.onResume();
        this.showPlayerInfo();
    }

    private void doUpdate() {
        this.player.setNickname(this.nickname.getText().toString());
        this.player.setAge(Integer.parseInt(this.age.getText().toString()));
        DataProvider.getInstance().getMyLocalDatabase().savePlayer(this.player);
//        (new DataBase()).update_player(this.nickname.getText().toString());
        DataProvider.getInstance().getMyFirebaseHelper().savePlayer(this.player);

        Toast toast = Toast.makeText(this, "Profil mis à jour", Toast.LENGTH_SHORT);
        toast.show();
    }

    private void showPlayerInfo(){
        this.nickname.setText(this.player.getNickname());
        this.age.setText(String.valueOf(this.player.getAge()));
        this.playerIdFill.setText(this.player.getID());
        this.spScore.setText(String.valueOf(this.player.getStats().getSingleplayerScore()));
        this.mpScore.setText(String.valueOf(this.player.getStats().getMultiplayerScore()));
        this.totalScore.setText(String.valueOf(this.player.getStats().getTotalScore()));

        PlayerLevel level = this.player.getLevel();
        int progress = (int) ((float) level.getCurrentXp() * 100 / level.getNeededXp());
        this.xpBar.setProgress(progress);
        this.levelText.setText(String.valueOf(level.getLevel()));
    }

    private void showSuccessPage(){
        startActivity(new Intent(this, SuccessActivity.class));
    }
}
