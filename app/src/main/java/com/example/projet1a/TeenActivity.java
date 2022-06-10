package com.example.projet1a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class TeenActivity extends AppCompatActivity  implements View.OnClickListener {

    private ImageButton FractionButton;
    private ImageButton Equation1Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teen);
        this.FractionButton = (ImageButton) findViewById(R.id.button_fraction);
        this.FractionButton.setOnClickListener(this);
        this.Equation1Button = (ImageButton)  findViewById(R.id.button_Equation1);
        this.Equation1Button.setOnClickListener(this);
    }

@Override
    public void onClick(View v) {
        if (v.getId() == this.FractionButton.getId()) this.showFractionPage();
        else if(v.getId() == this.Equation1Button.getId()) this.showEquation1Page();
}

    private void showFractionPage(){
        Intent fractionActivityIntent = new Intent(this, FractionActivity.class);
        startActivity(fractionActivityIntent);
    }

    private void showEquation1Page(){
        Intent equationActivityIntent = new Intent(this, Equation1Activity.class);
        startActivity(equationActivityIntent);
    }
}