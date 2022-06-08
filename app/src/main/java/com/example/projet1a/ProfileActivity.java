package com.example.projet1a;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.projet1a.profile.PlayerProfile;

public class ProfileActivity extends AppCompatActivity {

    private TextView playerNicknameTW;
    private TextView playerAgeTW;
    private TextView playerIdTW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        this.playerNicknameTW = (TextView) findViewById(R.id.nicknameTextFill);
        this.playerAgeTW = (TextView) findViewById(R.id.ageTextFill);
        this.playerIdTW = (TextView) findViewById(R.id.idTextFill);

        this.fillText();
    }

    private void fillText(){
        // get extras
        Bundle extras = getIntent().getExtras();
        String nickname = extras.getString(MainActivity.PLAYER_NICKNAME);
        int age = extras.getInt(MainActivity.PLAYER_AGE);
        String id = extras.getString(MainActivity.PLAYER_ID);

        // fill text with extras
        this.playerNicknameTW.setText(nickname);
        this.playerAgeTW.setText(String.valueOf(age));
        this.playerIdTW.setText(id);
    }

}