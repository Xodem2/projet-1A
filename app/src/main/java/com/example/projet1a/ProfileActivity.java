package com.example.projet1a;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.projet1a.profile.PlayerProfile;
import com.example.projet1a.profile.PlayerStatistics;

import org.w3c.dom.Text;


public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private PlayerProfile player;

    private EditText editNickname;
    private EditText editAge;
    private TextView playerId;
    private Button updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        this.player = DataProvider.getInstance().getPlayer();

        this.editNickname = (EditText) findViewById(R.id.profilePageEditNicknameId);
        this.editAge = (EditText) findViewById(R.id.profilePageEditAgeId);
        this.playerId = (TextView) findViewById(R.id.profilePagePlayerIdFillId);
        this.updateButton = (Button) findViewById(R.id.profilePageUpdateButtonId);
        this.updateButton.setOnClickListener(this);

        this.showPlayerInfo();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.showPlayerInfo();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == this.updateButton.getId()) this.updatePlayer();
    }

    private void updatePlayer() {
        this.player.setNickname(this.editNickname.getText().toString());
        this.player.setAge(Integer.parseInt(this.editAge.getText().toString()));

        // update database
        DataProvider.getInstance().getMyLocalDatabase().savePlayer(this.player);
    }

    private void showPlayerInfo(){
        this.playerId.setText(this.player.getID());
        this.editNickname.setText(this.player.getNickname());
        this.editAge.setText(String.valueOf(this.player.getAge()));
    }

}