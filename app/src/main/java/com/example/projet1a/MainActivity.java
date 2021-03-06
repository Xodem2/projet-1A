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

import com.example.projet1a.database.MyFirebaseHelper;
import com.example.projet1a.database.LocalBDDmulti;
import com.example.projet1a.database.MyLocalDatabaseHelper;
import com.example.projet1a.profile.PlayerProfile;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private PlayerProfile player;
    private ImageButton multiplayerButton;
    private ImageButton adultButton;
    private ImageButton teenButton;
    private ImageButton childButton;
    private ImageButton profil;
    private VideoView videocar;
    private Animation animation_adultButton;
    private Animation animation_teenButton;
    private Animation animation_childButton;
    MediaPlayer mMediaPlayer;
    int mCurrentVideoPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MyLocalDatabaseHelper myDatabase = new MyLocalDatabaseHelper(this);
        myDatabase.getWritableDatabase();
        LocalBDDmulti dataBase = new LocalBDDmulti(this);
        dataBase.getWritableDatabase();
        DataProvider.getInstance().setMyLocalDatabase(myDatabase);

        // load player from SQLite (local)
        this.player = myDatabase.loadPlayer();
        if(this.player == null){
            this.player = new PlayerProfile();
            myDatabase.addPlayer(this.player);
        }
        DataProvider.getInstance().setPlayer(this.player);

        this.multiplayerButton = (ImageButton) findViewById(R.id.button_multiplayer);
        this.multiplayerButton.setOnClickListener(this);

        this.adultButton = (ImageButton) findViewById(R.id.button_adult);
        animation_adultButton = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_in);

        this.adultButton.setOnClickListener(this);

        this.teenButton = (ImageButton) findViewById(R.id.button_teen);
        animation_teenButton = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_in);
        this.teenButton.setOnClickListener(this);

        this.childButton = (ImageButton) findViewById(R.id.button_child);
        this.childButton.setOnClickListener(this);

        this.profil = (ImageButton) findViewById(R.id.profil);
        this.profil.setOnClickListener(this);

        // video adding main page

        videocar = (VideoView) findViewById(R.id.videocar);
        String uriPath = "android.resource://"+getPackageName()+"/"+R.raw.driveway;
        Uri uri = Uri.parse(uriPath);
        videocar.setVideoURI(uri);
        videocar.start();
        videocar.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
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
//        DataBase.run();
        //DataProvider.getInstance().setDataBaseV2(new DataBaseV2());
        MyFirebaseHelper myFirebaseHelper = new MyFirebaseHelper();
        myFirebaseHelper.savePlayer(this.player);
        DataProvider.getInstance().setMyFirebaseHelper(myFirebaseHelper);
        DataProvider.getInstance().getPlayer().setIsFinished(-1);
    }


    @Override
    protected void onPostResume() {
        videocar.resume();
        super.onPostResume();
    }

    @Override
    protected void onRestart() {
        videocar.start();
        super.onRestart();
    }

    @Override
    protected void onPause() {
        videocar.suspend();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        videocar.stopPlayback();
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == this.adultButton.getId()){ this.showAdultPage();
            adultButton.startAnimation(animation_adultButton);}
        else if(v.getId() == this.teenButton.getId()) { this.showTeenPage();
            teenButton.startAnimation(animation_teenButton);}
        else if(v.getId() == this.childButton.getId()) this.showChildPage();
        else if(v.getId() == this.profil.getId()) this.showProfilePage();
        else if(v.getId() == this.multiplayerButton.getId()) this.showMultiplayerPage();

        //if(v.getId() == this.childButton.getId()){
        //    AlertDialog.Builder myPopup = new AlertDialog.Builder((activity))
    }

    @Override
    public void onResume(){
        super.onResume();
        this.updatePlayerProfile();
    }

    private void updatePlayerProfile(){
        PlayerProfile current = DataProvider.getInstance().getPlayer();
        if(current != null) this.player = current;
    }

    private void showAdultPage() {
        Intent adultActivityIntent = new Intent(this, AdultActivity.class);
        startActivity(adultActivityIntent);
    }

    private void quitApp() {
        this.finishAffinity();
    }

    private void showProfilePage() {
        Intent profileActivityIntent = new Intent(this, ProfileActivity.class);
        startActivity(profileActivityIntent);
    }

    private void showMultiplayerPage() {
        Intent multiplayerActivityIntent = new Intent(this, MultiplayerActivity.class);
        startActivity(multiplayerActivityIntent);
    }

    private void showTeenPage() {
        Intent vectorActivityIntent = new Intent(this, TeenActivity.class);
        startActivity(vectorActivityIntent);
    }

    private void showChildPage() {
        Intent childActivityIntent = new Intent(this, ChildActivity.class);
        startActivity(childActivityIntent);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.mymenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(item.getItemId() == R.id.activity_main) startActivity((new Intent(this, MainActivity.class)));
        else if(item.getItemId() == R.id.button_multiplayer) startActivity((new Intent(this, JoinActivity.class)));
        else if(item.getItemId() == R.id.activity_profile) startActivity((new Intent(this, ProfileActivity.class)));
        else if(item.getItemId() == R.id.profile_layout) startActivity((new Intent(this, ProfileActivity.class)));
        return super.onOptionsItemSelected(item);
    }

}