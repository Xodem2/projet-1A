package com.example.projet1a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TeenActivity extends AppCompatActivity  implements View.OnClickListener {

    private Button FractionButton;
    private Button Equation1Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teen);
        this.FractionButton = (Button) findViewById(R.id.button_fraction);
        this.FractionButton.setOnClickListener(this);
        this.Equation1Button = (Button)  findViewById(R.id.button_Equation1);
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