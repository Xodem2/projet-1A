package com.example.projet1a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TeenActivity extends AppCompatActivity  implements View.OnClickListener {

    private Button FractionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teen);
        this.FractionButton = (Button) findViewById(R.id.button_fraction);
        this.FractionButton.setOnClickListener(this);
    }

@Override
    public void onClick(View v) {
        if (v.getId() == this.FractionButton.getId()) this.showFractionPage();
    }

    private void showFractionPage(){
        Intent fractionActivityIntent = new Intent(this, FractionActivity.class);
        startActivity(fractionActivityIntent);
    }

}