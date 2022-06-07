package com.example.projet1a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button profileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.profileButton = (Button) findViewById(R.id.button4);
        this.profileButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == this.profileButton.getId()) this.showProfilePage();
    }

    private void showProfilePage() {
        Intent profileActivityIntent = new Intent(this, ProfileActivity.class);
        startActivity(profileActivityIntent);
    }
}