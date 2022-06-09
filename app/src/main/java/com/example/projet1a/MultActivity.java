package com.example.projet1a;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.projet1a.enfant.Operation;
import com.example.projet1a.point.Point;

public class MultActivity extends AppCompatActivity implements View.OnClickListener {
    Operation op;

    Button choix1Button;
    Button choix2Button;
    Button choix3Button;
    int[] prop;
    Point score;
    int delta_point;
    ProgressBar pb;
    int[] currentProgress;

    Point score_max;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mult);

        this.pb = findViewById(R.id.progressBarToday);

        // for eg: if countdown is to go for 30 seconds
        this.pb.setMax(500);

        // the progress in our progressbar decreases with the decrement
        // in the remaining time for countdown to be over
        this.pb.setProgress(500);

        this.currentProgress = new int[1];
        this.currentProgress[0] = 500;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                currentProgress[0] -= 1;
                pb.setProgress(currentProgress[0]);
                if(currentProgress[0] != 0){
                    new Handler().postDelayed(this, 10);
                }
                else{
                    currentProgress[0] = pb.getProgress();
                    new Handler().postDelayed(this, 10);
                }
            }
        }, 1000);

        this.choix1Button = (Button) findViewById(R.id.choice1ID);
        this.choix1Button.setOnClickListener(this);
        this.choix2Button = (Button) findViewById(R.id.choice2ID);
        this.choix2Button.setOnClickListener(this);
        this.choix3Button = (Button) findViewById(R.id.choice3ID);
        this.choix3Button.setOnClickListener(this);
        ((TextView) findViewById(R.id.prop1_mult)).setText(String.valueOf(""));
        ((TextView) findViewById(R.id.prop2_mult)).setText(String.valueOf(""));
        ((TextView) findViewById(R.id.prop3_mult)).setText(String.valueOf(""));

        this.score = new Point();
        this.delta_point = 3;
        this.score.setSensibility(this.delta_point);
        this.score_max = new Point(this.score);

        this.op = new Operation();
        generate();
        ((TextView) findViewById(R.id.delta)).setText("");
    }

    public void generate(){
        int maxi = 250;
        if (this.score_max.getScore()==0){
            maxi+=250;
        }
        else{
            maxi+=500*(1-((double)this.score.getScore())/((double)this.score_max.getScore()))-1;
        }
        this.currentProgress[0] = maxi;
        this.pb.setMax(maxi);
        ((TextView) findViewById(R.id.Score)).setText(String.valueOf(this.score.getScore()));
        this.op.generate();
        ((TextView) findViewById(R.id.op1_mult)).setText(String.valueOf(this.op.getA()));
        ((TextView) findViewById(R.id.op2_mult)).setText(String.valueOf(this.op.getB()));
        this.prop = this.op.propMult().conv();
        ((TextView) findViewById(R.id.choice1ID)).setText(String.valueOf(this.prop[0]));
        ((TextView) findViewById(R.id.choice2ID)).setText(String.valueOf(this.prop[1]));
        ((TextView) findViewById(R.id.choice3ID)).setText(String.valueOf(this.prop[2]));
        this.score_max.incr();
    }

    @Override
    public void onClick(View v){
        if (this.currentProgress[0]>0) {
            if (v.getId() == this.choix1Button.getId()) {
                if (op.mult(this.prop[0])) {
                    this.score.incr();
                    ((TextView) findViewById(R.id.delta)).setTextColor(Color.parseColor("#00ff00"));
                    ((TextView) findViewById(R.id.delta)).setText("+" + String.valueOf(this.score.getSensibility()));
                } else {
                    this.score.decr();
                    ((TextView) findViewById(R.id.delta)).setTextColor(Color.parseColor("#ff0000"));
                    ((TextView) findViewById(R.id.delta)).setText("-" + String.valueOf(this.score.getSensibility()));
                }
            }
            if (v.getId() == this.choix2Button.getId()) {
                if (op.mult(this.prop[1])) {
                    this.score.incr();
                    ((TextView) findViewById(R.id.delta)).setTextColor(Color.parseColor("#00ff00"));
                    ((TextView) findViewById(R.id.delta)).setText("+" + String.valueOf(this.score.getSensibility()));
                } else {
                    this.score.decr();
                    ((TextView) findViewById(R.id.delta)).setTextColor(Color.parseColor("#ff0000"));
                    ((TextView) findViewById(R.id.delta)).setText("-" + String.valueOf(this.score.getSensibility()));
                }
            }
            if (v.getId() == this.choix3Button.getId()) {
                if (op.mult(this.prop[2])) {
                    this.score.incr();
                    ((TextView) findViewById(R.id.delta)).setTextColor(Color.parseColor("#00ff00"));
                    ((TextView) findViewById(R.id.delta)).setText("+" + String.valueOf(this.score.getSensibility()));
                } else {
                    this.score.decr();
                    ((TextView) findViewById(R.id.delta)).setTextColor(Color.parseColor("#ff0000"));
                    ((TextView) findViewById(R.id.delta)).setText("-" + String.valueOf(this.score.getSensibility()));
                }
            }
        }
        else{
            if (v.getId() == this.choix1Button.getId()) {
                if (op.mult(this.prop[0])) {
                    ((TextView) findViewById(R.id.delta)).setTextColor(Color.parseColor("#00ff00"));
                    ((TextView) findViewById(R.id.delta)).setText("+0");
                } else {
                    this.score.decr();
                    ((TextView) findViewById(R.id.delta)).setTextColor(Color.parseColor("#ff0000"));
                    ((TextView) findViewById(R.id.delta)).setText("-" + String.valueOf(this.score.getSensibility()));
                }
            }
            if (v.getId() == this.choix2Button.getId()) {
                if (op.mult(this.prop[1])) {
                    ((TextView) findViewById(R.id.delta)).setTextColor(Color.parseColor("#00ff00"));
                    ((TextView) findViewById(R.id.delta)).setText("+0");
                } else {
                    this.score.decr();
                    ((TextView) findViewById(R.id.delta)).setTextColor(Color.parseColor("#ff0000"));
                    ((TextView) findViewById(R.id.delta)).setText("-" + String.valueOf(this.score.getSensibility()));
                }
            }
            if (v.getId() == this.choix3Button.getId()) {
                if (op.mult(this.prop[2])) {
                    ((TextView) findViewById(R.id.delta)).setTextColor(Color.parseColor("#00ff00"));
                    ((TextView) findViewById(R.id.delta)).setText("+0");
                } else {
                    this.score.decr();
                    ((TextView) findViewById(R.id.delta)).setTextColor(Color.parseColor("#ff0000"));
                    ((TextView) findViewById(R.id.delta)).setText("-" + String.valueOf(this.score.getSensibility()));
                }
            }
        }
        this.generate();
    }
}