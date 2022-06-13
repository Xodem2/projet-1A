package com.example.projet1a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.projet1a.ado_games.Thales;

public class TeenActivity extends AppCompatActivity  implements View.OnClickListener {

    private ImageButton FractionButton;
    private ImageButton Equation1Button;
    private Button Equation2Button;
    private Button pythagoreButton;
    private Button thalesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teen);
        this.FractionButton = (ImageButton) findViewById(R.id.button_fraction);
        this.FractionButton.setOnClickListener(this);
        this.Equation1Button = (ImageButton)  findViewById(R.id.button_Equation1);
        this.Equation1Button.setOnClickListener(this);
        this.Equation2Button = (Button)  findViewById(R.id.button_Equation2);
        this.Equation2Button.setOnClickListener(this);
        this.pythagoreButton = (Button)  findViewById(R.id.pythagore);
        this.pythagoreButton.setOnClickListener(this);
        this.thalesButton = (Button)  findViewById(R.id.button_thales);
        this.thalesButton.setOnClickListener(this);
    }

@Override
    public void onClick(View v) {
        if (v.getId() == this.FractionButton.getId()) this.showFractionPage();
        else if(v.getId() == this.Equation1Button.getId()) this.showEquation1Page();
        else if(v.getId() == this.Equation2Button.getId()) this.showEquation2Page();
        else if(v.getId() == this.pythagoreButton.getId()) this.showPythagorePage();
        else if(v.getId() == this.thalesButton.getId()) this.showThalesPage();
}

    private void showFractionPage(){
        Intent fractionActivityIntent = new Intent(this, FractionActivity.class);
        startActivity(fractionActivityIntent);
    }

    private void showEquation1Page(){
        Intent equation1ActivityIntent = new Intent(this, Equation1Activity.class);
        startActivity(equation1ActivityIntent);
    }

    private void showEquation2Page(){
        Intent equation2ActivityIntent = new Intent(this, Equation2Activity.class);
        startActivity(equation2ActivityIntent);
    }

    private void showPythagorePage(){
        Intent pythagoreActivityIntent = new Intent(this, PythagoreActivity.class);
        startActivity(pythagoreActivityIntent);
    }

    private void showThalesPage(){
        Intent thalesActivityIntent = new Intent(this, ThalesActivity.class);
        startActivity(thalesActivityIntent);
    }
}