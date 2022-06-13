package com.example.projet1a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.projet1a.database.DataBase;
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
  //  private VideoView profileback;
    private Button gameStatsButton;
    MediaPlayer mMediaPlayer;
    int mCurrentVideoPosition;

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
        this.gameStatsButton = (Button) findViewById(R.id.profilePageGameStatsButtonId);

        this.updateButton.setOnClickListener(this);
        this.successButton.setOnClickListener(this);
        this.gameStatsButton.setOnClickListener(this);

     /*   profileback = (VideoView) findViewById(R.id.profileback);
        String uriPath = "android.resource://"+getPackageName()+"/"+R.raw.profileback;
        Uri uri = Uri.parse(uriPath);
        profileback.setVideoURI(uri);
        profileback.start();
        profileback.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mMediaPlayer = mediaPlayer;
                // We want our video to play over and over so we set looping to true.
                mMediaPlayer.setLooping(true);
                // We then seek to the current posistion if it has been set and play the video.
                if (mCurrentVideoPosition != 0) {
                    mMediaPlayer.seekTo(mCurrentVideoPosition);
                    mMediaPlayer.start();
                }
            }
        });*/
    }
    /*
    @Override
    protected void onPostResume() {
        profileback.resume();
        super.onPostResume();
    }

    @Override
    protected void onRestart() {
        profileback.start();
        super.onRestart();
    }

    @Override
    protected void onPause() {
        profileback.suspend();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        profileback.stopPlayback();
        super.onDestroy();
    }

     */

    @Override
    public void onClick(View v) {
        if(v.getId() == this.updateButton.getId()) this.doUpdate();
        if(v.getId() == this.successButton.getId()) this.showSuccessPage();
        if(v.getId() == this.gameStatsButton.getId()) this.showGameStatsPage();
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
        (new DataBase()).update_player(this.nickname.getText().toString());
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
