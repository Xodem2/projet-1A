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

public class ChildActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton sommeButton;
    private ImageButton diffButton;
    private ImageButton multButton;
    private ImageButton divButton;
    MediaPlayer mMediaPlayer;
    int mCurrentVideoPosition;
    private VideoView childback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);
        this.sommeButton = (ImageButton) findViewById(R.id.button_somme);
        this.sommeButton.setOnClickListener(this);

        this.diffButton = (ImageButton) findViewById(R.id.button_fraction);
        this.diffButton.setOnClickListener(this);

        this.multButton = (ImageButton) findViewById(R.id.button_Mult);
        this.multButton.setOnClickListener(this);

        this.divButton = (ImageButton) findViewById(R.id.button_division);
        this.divButton.setOnClickListener(this);
        childback = (VideoView) findViewById(R.id.childback);
        String uriPath = "android.resource://"+getPackageName()+"/"+R.raw.childback;
        Uri uri = Uri.parse(uriPath);
        childback.setVideoURI(uri);
        childback.start();
        childback.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
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
        childback.resume();
        super.onPostResume();
    }

    @Override
    protected void onRestart() {
        childback.start();
        super.onRestart();
    }

    @Override
    protected void onPause() {
        childback.suspend();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        childback.stopPlayback();
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == this.sommeButton.getId()) this.showSommePage();
        if(v.getId() == this.diffButton.getId()) this.showDiffPage();
        if(v.getId() == this.multButton.getId()) this.showMultPage();
        if(v.getId() == this.divButton.getId()) this.showDivPage();
    }

    private void showSommePage() {
        Intent sommeActivityIntent = new Intent(this, SommeActivity.class);
        startActivity(sommeActivityIntent);
    }

    private void showDiffPage() {
        Intent moinsActivityIntent = new Intent(this, MoinsActivity.class);
        startActivity(moinsActivityIntent);
    }

    private void showMultPage() {
        Intent multActivityIntent = new Intent(this, MultActivity.class);
        startActivity(multActivityIntent);
    }

    private void showDivPage() {
        Intent divActivityIntent = new Intent(this, DivisionActivity.class);
        startActivity(divActivityIntent);
    }
}