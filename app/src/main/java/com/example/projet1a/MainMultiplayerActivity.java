package com.example.projet1a;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.VideoView;

import com.example.projet1a.database.DataBase;
import com.example.projet1a.database.DataBaseV2;
import com.example.projet1a.database.MyLocalDatabaseHelper;
import com.example.projet1a.profile.PlayerProfile;

public class MainMultiplayerActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton FractionButton;
    private ImageButton Equation1Button;
    private ImageButton Equation2Button;
    private ImageButton pythagoreButton;
    private ImageButton thalesButton;

    private ImageButton sommeButton;
    private ImageButton diffButton;
    private ImageButton multButton;
    private ImageButton divButton;

    private ImageButton vectorButton;
    private ImageButton matriceButton;
    MediaPlayer mMediaPlayer;
    int mCurrentVideoPosition;
    private VideoView adultback;

    private int id_session;
    private PlayerProfile player;

    private String idGame ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_multiplayer);

        this.FractionButton = (ImageButton) findViewById(R.id.main_multiplayer_fraction);
        this.FractionButton.setOnClickListener(this);

        this.Equation1Button = (ImageButton)  findViewById(R.id.main_multiplayer_equation1);
        this.Equation1Button.setOnClickListener(this);

        this.Equation2Button = (ImageButton)  findViewById(R.id.main_multiplayer_equation2);
        this.Equation2Button.setOnClickListener(this);

        this.pythagoreButton = (ImageButton)  findViewById(R.id.main_multiplayer_pythagore);
        this.pythagoreButton.setOnClickListener(this);

        this.thalesButton = (ImageButton)  findViewById(R.id.main_multiplayer_thales);
        this.thalesButton.setOnClickListener(this);

        this.sommeButton = (ImageButton) findViewById(R.id.main_multiplayer_somme);
        this.sommeButton.setOnClickListener(this);

        this.diffButton = (ImageButton) findViewById(R.id.main_multiplayer_difference);
        this.diffButton.setOnClickListener(this);

        this.multButton = (ImageButton) findViewById(R.id.main_multiplayer_mult);
        this.multButton.setOnClickListener(this);

        this.divButton = (ImageButton) findViewById(R.id.main_multiplayer_division);
        this.divButton.setOnClickListener(this);

        this.player = DataProvider.getInstance().getPlayer();

        this.vectorButton = (ImageButton) findViewById(R.id.main_multiplayer_vector);
        this.vectorButton.setOnClickListener(this);

        this.matriceButton = (ImageButton) findViewById(R.id.main_multiplayer_matrice);
        this.matriceButton.setOnClickListener(this);
        /*adultback = (VideoView) findViewById(R.id.adultback);
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
        });*/

    }/*
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
*/
    @Override
    public void onClick(View v) {
        if (v.getId() == this.FractionButton.getId()) this.showFractionPage();
        else if(v.getId() == this.Equation1Button.getId()) this.showEquation1Page();
        else if(v.getId() == this.Equation2Button.getId()) this.showEquation2Page();
        else if(v.getId() == this.pythagoreButton.getId()) this.showPythagorePage();
        else if(v.getId() == this.thalesButton.getId()) this.showThalesPage();
        else if(v.getId() == this.sommeButton.getId()) this.showSommePage();
        else if(v.getId() == this.diffButton.getId()) this.showDiffPage();
        else if(v.getId() == this.multButton.getId()) this.showMultPage();
        else if(v.getId() == this.divButton.getId()) this.showDivPage();
        else if(v.getId() == this.vectorButton.getId()) this.showVectorActivity();
        else if(v.getId() == this.matriceButton.getId()) this.showMatriceActivity();
    }

    private void showVectorActivity() {
        this.idGame = VectorActivity.id;
        DataBaseV2 db = DataProvider.getInstance().getDataBaseV2();
        int id = db.create_private_game(this.idGame);
        System.out.println(id);
//        DataBase dataBase = new DataBase();
//        this.id_session = dataBase.create_private_game(this.idGame);
//        launchActivity();
    }

    private void showMatriceActivity() {
        this.idGame = MatricesActivity.id;
        launchActivity();
    }

    private void showFractionPage(){
        this.idGame = FractionActivity.id;
        launchActivity();
    }

    private void showEquation1Page(){
        this.idGame = Equation1Activity.id;
        launchActivity();
    }

    private void showEquation2Page(){
        this.idGame = Equation2Activity.id;
        launchActivity();
    }

    private void showPythagorePage(){
        this.idGame = PythagoreActivity.id;
        launchActivity();
    }

    private void showThalesPage(){
        this.idGame = ThalesActivity.id;
        launchActivity();
    }

    private void showSommePage() {
        this.idGame = SommeActivity.id;
        launchActivity();
    }

    private void showDiffPage() {
        this.idGame = MoinsActivity.id;
        launchActivity();
    }

    private void showMultPage() {
        this.idGame = MultActivity.id;
        launchActivity();
    }

    private void showDivPage() {
        this.idGame = DivisionActivity.id;
        launchActivity();
    }

    public void launchActivity(){
        Intent roomActivityIntent = new Intent(this, RoomActivity.class);
        startActivity(roomActivityIntent);
    }

    public String getIdGame(){
        return this.idGame;
    }
    public int getIdSession(){ return this.id_session; }
}