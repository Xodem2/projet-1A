package com.example.projet1a;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.projet1a.profile.PlayerProfile;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String PLAYER_PROFILE_EXTRA = "playerProfileExtra";

    private PlayerProfile player;
    private Button profileButton;
    private Button teenButton;
    private Button childButton;
    private MainActivity quit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.quit=this;
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.game_layout);
//        final ProgressBar pb = findViewById(R.id.progressBarToday);
//
//        // for eg: if countdown is to go for 30 seconds
//        pb.setMax(500);
//
//        // the progress in our progressbar decreases with the decrement
//        // in the remaining time for countdown to be over
//        pb.setProgress(500);
//
//        final int[] currentProgress = {500};
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                currentProgress[0] -= 1;
//                pb.setProgress(currentProgress[0]);
//                if(currentProgress[0] != 0){
//                    new Handler().postDelayed(this, 10);
//                }
//            }
//        }, 1000);

        // TODO: load player profile from XML if existing
        setContentView(R.layout.activity_main);
        this.player = new PlayerProfile();

        this.profileButton = (Button) findViewById(R.id.button_adult);
        this.profileButton.setOnClickListener(this);

        this.teenButton = (Button) findViewById(R.id.button_teen);
        this.teenButton.setOnClickListener(this);

        this.childButton = (Button) findViewById(R.id.button_child);
        this.childButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == this.profileButton.getId()) this.showProfilePage();
        if(v.getId() == this.teenButton.getId()) this.showTeenPage();
        if(v.getId() == this.childButton.getId()) this.showChildPage();
        //if(v.getId() == this.childButton.getId()){
        //    AlertDialog.Builder myPopup = new AlertDialog.Builder((activity))
        };


    private void showProfilePage() {
        Intent profileActivityIntent = new Intent(this, ProfileActivity.class);
        profileActivityIntent.putExtra(PLAYER_PROFILE_EXTRA, this.player);
        startActivity(profileActivityIntent);
    }

    private void showTeenPage() {
        //Intent teenActivityIntent = new Intent(this, TeenActivity.class);
        //startActivity(teenActivityIntent);
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
        switch (item.getItemId()){
            case R.id.activity_main:
                setContentView(R.layout.activity_main);
                break;
            case R.id.activity_profile:
                this.showProfilePage();
                break;
            case R.id.profile_layout:
                setContentView((R.layout.profile_layout));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}