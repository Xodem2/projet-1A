package com.example.projet1a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChildActivity extends AppCompatActivity implements View.OnClickListener {

    private Button sommeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);
        this.sommeButton = (Button) findViewById(R.id.button_somme);
        this.sommeButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == this.sommeButton.getId()) this.showSommePage();
    }
    private void showSommePage() {
        Intent sommeActivityIntent = new Intent(this, SommeActivity.class);
        startActivity(sommeActivityIntent);
    }


}