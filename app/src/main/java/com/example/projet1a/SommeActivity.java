package com.example.projet1a;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.projet1a.enfant.Operation;

public class SommeActivity extends AppCompatActivity implements View.OnClickListener{

    Operation op;

    Button choix1Button;
    Button choix2Button;
    Button choix3Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_somme);
        final ProgressBar pb = findViewById(R.id.progressBarToday);

        // for eg: if countdown is to go for 30 seconds
        pb.setMax(500);

        // the progress in our progressbar decreases with the decrement
        // in the remaining time for countdown to be over
        pb.setProgress(500);

        final int[] currentProgress = {500};
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                currentProgress[0] -= 1;
                pb.setProgress(currentProgress[0]);
                if(currentProgress[0] != 0){
                    new Handler().postDelayed(this, 10);
                }
            }
        }, 1000);

        this.op = new Operation();
        generate();

        this.choix1Button = (Button) findViewById(R.id.choice1ID);
        this.choix1Button.setOnClickListener(this);
        this.choix2Button = (Button) findViewById(R.id.choice2ID);
        this.choix2Button.setOnClickListener(this);
        this.choix3Button = (Button) findViewById(R.id.choice3ID);
        this.choix3Button.setOnClickListener(this);
    }

    public void generate(){
        this.op.generate();
        ((TextView) findViewById(R.id.op1)).setText(String.valueOf(this.op.getA()));
        ((TextView) findViewById(R.id.op2)).setText(String.valueOf(this.op.getB()));
    }

    @Override
    public void onClick(View v){
        this.generate();
    }
}