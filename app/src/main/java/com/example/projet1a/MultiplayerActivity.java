package com.example.projet1a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MultiplayerActivity extends AppCompatActivity  implements View.OnClickListener {

    private Button joinMultiplayer;
    private Button createMultiplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multijoueur);
        this.joinMultiplayer = (Button) findViewById(R.id.button_join_session);
        this.joinMultiplayer.setOnClickListener(this);
        this.createMultiplayer = (Button)  findViewById(R.id.button_create_session);
        this.createMultiplayer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        /*if (v.getId() == this.createMultiplayer.getId()) this.showCreatePage();*/
        if(v.getId() == this.joinMultiplayer.getId()) this.showJoinPage();
    }

    /*private void showCreatePage(){
        Intent createActivityIntent = new Intent(this, .class);
        startActivity(fractionActivityIntent);
    }*/

    private void showJoinPage(){
        Intent joinActivityIntent = new Intent(this, JoinActivity.class);
        startActivity(joinActivityIntent);
    }
}