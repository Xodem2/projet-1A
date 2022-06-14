package com.example.projet1a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.VideoView;

import com.example.projet1a.profile.PlayerProfile;

public class AdultActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton vectorButton;
    private ImageButton matriceButton;
    //private ImageButton trigoButton;
    private Button trigoButton;
    MediaPlayer mMediaPlayer;
    int mCurrentVideoPosition;
    private VideoView adultback;

    private PlayerProfile player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adult);

        this.player = DataProvider.getInstance().getPlayer();

        this.vectorButton = (ImageButton) findViewById(R.id.button_Mult);
        this.vectorButton.setOnClickListener(this);

        this.matriceButton = (ImageButton) findViewById(R.id.icone_Matrice);
        this.matriceButton.setOnClickListener(this);
        this.trigoButton = (Button) findViewById(R.id.button_trigo);
        this.trigoButton.setOnClickListener(this);
        adultback = (VideoView) findViewById(R.id.adultback);
        String uriPath = "android.resource://"+getPackageName()+"/"+R.raw.adultback;
        Uri uri = Uri.parse(uriPath);
        adultback.setVideoURI(uri);
        adultback.start();
        adultback.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
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
        });

    }
    @Override
    protected void onPostResume() {
        adultback.resume();
        super.onPostResume();
    }

    @Override
    protected void onRestart() {
        adultback.start();
        super.onRestart();
    }

    @Override
    protected void onPause() {
        adultback.suspend();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        adultback.stopPlayback();
        super.onDestroy();
    }
    @Override
    public void onClick(View v) {
        if(v.getId() == this.vectorButton.getId()) this.showVectorActivity();
        else if(v.getId() == this.matriceButton.getId()) this.showMatriceActivity();
        else if(v.getId() == this.trigoButton.getId()) this.showTrigoPage();
    }

    private void showVectorActivity() {
        Intent vectorActivityIntent = new Intent(this, VectorActivity.class);
        startActivity(vectorActivityIntent);
    }

    private void showMatriceActivity() {
        Intent matriceActivityIntent = new Intent(this, MatricesActivity.class);
        startActivity(matriceActivityIntent);
    }

    private void showTrigoPage(){
        Intent trigoActivityIntent = new Intent(this, TrigoActivity.class);
        startActivity(trigoActivityIntent);
    }
}