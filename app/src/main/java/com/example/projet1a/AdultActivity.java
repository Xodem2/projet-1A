package com.example.projet1a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.projet1a.profile.PlayerProfile;

public class AdultActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton vectorButton;

    private PlayerProfile player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adult);

        this.player = getIntent().getParcelableExtra(MainActivity.PLAYER_PROFILE_EXTRA);

        this.vectorButton = (ImageButton) findViewById(R.id.adultPageVectorActivityID);
        this.vectorButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == this.vectorButton.getId()) this.showVectorActivity();
    }

    private void showVectorActivity() {
        Intent vectorActivityIntent = new Intent(this, VectorActivity.class);
        vectorActivityIntent.putExtra(MainActivity.PLAYER_PROFILE_EXTRA, this.player);
        startActivity(vectorActivityIntent);
    }
}