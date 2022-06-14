package com.example.projet1a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.VideoView;

import com.example.projet1a.ado_games.Thales;

public class TeenActivity extends AppCompatActivity  implements View.OnClickListener {

    private ImageButton FractionButton;
    private ImageButton Equation1Button;
    private ImageButton Equation2Button;
    private ImageButton pythagoreButton;
    private ImageButton thalesButton;
    MediaPlayer mMediaPlayer;
    int mCurrentVideoPosition;
    private VideoView backteen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teen);
        this.FractionButton = (ImageButton) findViewById(R.id.button_fraction);
        this.FractionButton.setOnClickListener(this);
        this.Equation1Button = (ImageButton)  findViewById(R.id.button_Equation1);
        this.Equation1Button.setOnClickListener(this);
        this.Equation2Button = (ImageButton)  findViewById(R.id.button_Equation2);
        this.Equation2Button.setOnClickListener(this);
        this.pythagoreButton = (ImageButton)  findViewById(R.id.pythagore);
        this.pythagoreButton.setOnClickListener(this);
        this.thalesButton = (ImageButton)  findViewById(R.id.button_thales);
        this.thalesButton.setOnClickListener(this);

        backteen = (VideoView) findViewById(R.id.backteen);
        String uriPath = "android.resource://"+getPackageName()+"/"+R.raw.backteen;
        Uri uri = Uri.parse(uriPath);
        backteen.setVideoURI(uri);
        backteen.start();
        backteen.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
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
        backteen.resume();
        super.onPostResume();
    }

    @Override
    protected void onRestart() {
        backteen.start();
        super.onRestart();
    }

    @Override
    protected void onPause() {
        backteen.suspend();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        backteen.stopPlayback();
        super.onDestroy();
    }

@Override
    public void onClick(View v) {
        if (v.getId() == this.FractionButton.getId()) this.showFractionPage();
        else if(v.getId() == this.Equation1Button.getId()) this.showEquation1Page();
        else if(v.getId() == this.Equation2Button.getId()) this.showEquation2Page();
        else if(v.getId() == this.pythagoreButton.getId()) this.showPythagorePage();
        else if(v.getId() == this.thalesButton.getId()) this.showThalesPage();
}

    private void showFractionPage(){
        Intent fractionActivityIntent = new Intent(this, FractionActivity.class);
        startActivity(fractionActivityIntent);
    }

    private void showEquation1Page(){
        Intent equation1ActivityIntent = new Intent(this, Equation1Activity.class);
        startActivity(equation1ActivityIntent);
    }

    private void showEquation2Page(){
        Intent equation2ActivityIntent = new Intent(this, Equation2Activity.class);
        startActivity(equation2ActivityIntent);
    }

    private void showPythagorePage(){
        Intent pythagoreActivityIntent = new Intent(this, PythagoreActivity.class);
        startActivity(pythagoreActivityIntent);
    }

    private void showThalesPage(){
        Intent thalesActivityIntent = new Intent(this, ThalesActivity.class);
        startActivity(thalesActivityIntent);
    }
}