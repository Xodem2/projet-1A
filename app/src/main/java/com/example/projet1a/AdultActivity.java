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
    private ImageButton matriceButton;

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
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == this.vectorButton.getId()) this.showVectorActivity();
        if(v.getId() == this.matriceButton.getId()) this.showMatriceActivity();
    }

    private void showVectorActivity() {
        Intent vectorActivityIntent = new Intent(this, VectorActivity.class);
        startActivity(vectorActivityIntent);
    }

    private void showMatriceActivity() {
        Intent matriceActivityIntent = new Intent(this, MatricesActivity.class);
        startActivity(matriceActivityIntent);
    }
}