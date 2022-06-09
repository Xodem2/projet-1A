package com.example.projet1a;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.projet1a.point.Point;

import java.util.Random;

public class FractionActivity extends AppCompatActivity implements View.OnClickListener {
    private ProgressBar progressBar;
    private TextView num;
    private TextView den;
    Rect rNum;
    Rect rDen;

    private TextView num1;
    private TextView num2;
    private TextView num3;
    private TextView den1;
    private TextView den2;
    private TextView den3;
    private ProgressBar progressBar1;
    private ProgressBar progressBar2;
    private ProgressBar progressBar3;
    Rect r1Num;
    Rect r1Den;
    Rect r2Num;
    Rect r2Den;
    Rect r3Num;
    Rect r3Den;

    int a;
    int b;
    int[][] choix;

    Random random;

    int[] prime = {2, 3, 5, 7};

    Button choix1;
    Button choix2;
    Button choix3;

    Point score;
    Point score_max;

    ProgressBar pb;
    int[] currentProgress;

    int[] bon_choix;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        bon_choix = new int[2];
        score = new Point();
        this.score_max = new Point(this.score);
        choix = new int[3][2];
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fraction);

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

        random = new Random();

        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.num = (TextView) findViewById(R.id.num);
        this.den = (TextView) findViewById(R.id.den);
        this.rNum = new Rect();
        this.rDen = new Rect();

        this.progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
        this.num1 = (TextView) findViewById(R.id.num1);
        this.den1 = (TextView) findViewById(R.id.den1);
        this.r1Num = new Rect();
        this.r1Den = new Rect();

        this.progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
        this.num2 = (TextView) findViewById(R.id.num2);
        this.den2 = (TextView) findViewById(R.id.den2);
        this.r2Num = new Rect();
        this.r2Den = new Rect();

        this.progressBar3 = (ProgressBar) findViewById(R.id.progressBar3);
        this.num3 = (TextView) findViewById(R.id.num3);
        this.den3 = (TextView) findViewById(R.id.den3);
        this.r3Num = new Rect();
        this.r3Den = new Rect();

        generate();
        this.choix1 = (Button) findViewById(R.id.choice1ID);
        this.choix1.setOnClickListener(this);
        this.choix2 = (Button) findViewById(R.id.choice2ID);
        this.choix2.setOnClickListener(this);
        this.choix3 = (Button) findViewById(R.id.choice3ID);
        this.choix3.setOnClickListener(this);
        ((TextView) findViewById(R.id.delta)).setText("");
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onClick(View v) {
        if (this.currentProgress[0]>0) {
            if (v.getId() == this.choix1.getId()) {
                if (this.num1.getText() == String.valueOf(this.bon_choix[0]) && this.den1.getText() == String.valueOf(this.bon_choix[1])) {
                    score.incr();
                    ((TextView) findViewById(R.id.delta)).setTextColor(Color.parseColor("#00ff00"));
                    ((TextView) findViewById(R.id.delta)).setText("+" + String.valueOf(this.score.getSensibility()));
                } else {
                    score.decr();
                    ((TextView) findViewById(R.id.delta)).setTextColor(Color.parseColor("#ff0000"));
                    ((TextView) findViewById(R.id.delta)).setText("-" + String.valueOf(this.score.getSensibility()));
                }
            }
            if (v.getId() == this.choix2.getId()) {
                if (this.num2.getText() == String.valueOf(this.bon_choix[0]) && this.den2.getText() == String.valueOf(this.bon_choix[1])) {
                    score.incr();
                    ((TextView) findViewById(R.id.delta)).setTextColor(Color.parseColor("#00ff00"));
                    ((TextView) findViewById(R.id.delta)).setText("+" + String.valueOf(this.score.getSensibility()));
                } else {
                    score.decr();
                    ((TextView) findViewById(R.id.delta)).setTextColor(Color.parseColor("#ff0000"));
                    ((TextView) findViewById(R.id.delta)).setText("-" + String.valueOf(this.score.getSensibility()));
                }
            }
            if (v.getId() == this.choix3.getId()) {
                if (this.num3.getText() == String.valueOf(this.bon_choix[0]) && this.den3.getText() == String.valueOf(this.bon_choix[1])) {
                    score.incr();
                    ((TextView) findViewById(R.id.delta)).setTextColor(Color.parseColor("#00ff00"));
                    ((TextView) findViewById(R.id.delta)).setText("+" + String.valueOf(this.score.getSensibility()));
                } else {
                    score.decr();
                    ((TextView) findViewById(R.id.delta)).setTextColor(Color.parseColor("#ff0000"));
                    ((TextView) findViewById(R.id.delta)).setText("-" + String.valueOf(this.score.getSensibility()));
                }
            }
        }
        else{
            if (v.getId() == this.choix1.getId()) {
                if (this.num1.getText() == String.valueOf(this.bon_choix[0]) && this.den1.getText() == String.valueOf(this.bon_choix[1])) {
                    ((TextView) findViewById(R.id.delta)).setTextColor(Color.parseColor("#00ff00"));
                    ((TextView) findViewById(R.id.delta)).setText("+0");
                } else {
                    score.decr();
                    ((TextView) findViewById(R.id.delta)).setTextColor(Color.parseColor("#ff0000"));
                    ((TextView) findViewById(R.id.delta)).setText("-" + String.valueOf(this.score.getSensibility()));
                }
            }
            if (v.getId() == this.choix2.getId()) {
                if (this.num2.getText() == String.valueOf(this.bon_choix[0]) && this.den2.getText() == String.valueOf(this.bon_choix[1])) {
                    ((TextView) findViewById(R.id.delta)).setTextColor(Color.parseColor("#00ff00"));
                    ((TextView) findViewById(R.id.delta)).setText("+0");
                } else {
                    score.decr();
                    ((TextView) findViewById(R.id.delta)).setTextColor(Color.parseColor("#ff0000"));
                    ((TextView) findViewById(R.id.delta)).setText("-" + String.valueOf(this.score.getSensibility()));
                }
            }
            if (v.getId() == this.choix3.getId()) {
                if (this.num3.getText() == String.valueOf(this.bon_choix[0]) && this.den3.getText() == String.valueOf(this.bon_choix[1])) {
                    ((TextView) findViewById(R.id.delta)).setTextColor(Color.parseColor("#00ff00"));
                    ((TextView) findViewById(R.id.delta)).setText("+0");
                } else {
                    score.decr();
                    ((TextView) findViewById(R.id.delta)).setTextColor(Color.parseColor("#ff0000"));
                    ((TextView) findViewById(R.id.delta)).setText("-" + String.valueOf(this.score.getSensibility()));
                }
            }
        }
        generate();
    }

    public void shuffle(){
        int[][] aux = new int[3][2];
        for (int i=0; i<3; i++){
            for (int j=0; j<2; j++){
                aux[i][j] = this.choix[i][j];
            }
        }
        int[] place = new int[3];
        place[0] = random.nextInt(3);
        place[1] = random.nextInt(3);
        while (place[1]==place[0]) {
            place[1] = random.nextInt(3);
        }
        while (place[2]==place[1] || place[2]==place[0]) {
            place[2] = random.nextInt(3);
        }
        for (int i=0; i<3; i++) {
            this.choix[place[i]] = aux[i];
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void generate(){
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
        int length = random.nextInt(5)+2;
        this.b = this.prime[random.nextInt(this.prime.length)];
        this.a = b;

        for (int i=0; i<length-1; i++){
            this.b *= this.prime[random.nextInt(this.prime.length)];
            this.a *= this.prime[random.nextInt(this.prime.length)];
        }

        this.choix = new int[3][2];
        this.choix[0][0] = a;
        this.choix[0][1] = b;
        for (int i=0; i<this.prime.length; i++){
            while (this.choix[0][0]%this.prime[i]==0 && this.choix[0][1]%this.prime[i]==0){
                this.choix[0][0] /= this.prime[i];
                this.choix[0][1] /= this.prime[i];
            }
        }
        int sauv00 = this.choix[0][0];
        int sauv01 = this.choix[0][1];
        this.choix[1][0] = this.choix[0][0]+random.nextInt(21)-10;
        this.choix[1][1] = this.choix[0][1]+random.nextInt(21)-10;
        this.choix[2][0] = this.choix[1][0]+random.nextInt(21)-10;
        this.choix[2][1] = this.choix[1][1]+random.nextInt(21)-10;
        while (this.choix[1][1]==0 || (this.choix[1][0]==this.choix[0][0] && this.choix[1][1]==this.choix[0][1])){
            this.choix[1][0] = sauv00 +random.nextInt(21)-10;
            this.choix[1][1] = sauv01 +random.nextInt(21)-10;
        }
        while (this.choix[2][1]==0 || (this.choix[2][0]==this.choix[0][0] && this.choix[2][1]==this.choix[0][1]) || (this.choix[2][0]==this.choix[1][0] && this.choix[2][1]==this.choix[1][1])){
            this.choix[2][0] = sauv00 +random.nextInt(21)-10;
            this.choix[2][1] = sauv01 +random.nextInt(21)-10;
        }
        this.bon_choix[0] = this.choix[0][0];
        this.bon_choix[1] = this.choix[0][1];
        this.shuffle();
//        question
        this.num.setText(String.valueOf(a));
        this.den.setText(String.valueOf(b));
        this.progressBar.setMinHeight(Integer.parseInt("10"));
        this.progressBar.setMaxHeight(Integer.parseInt("10"));
        this.num.getPaint().getTextBounds(this.num.getText(), 0, this.num.getText().length(), rNum);
        this.den.getPaint().getTextBounds(this.den.getText(), 0, this.den.getText().length(), rDen);
        this.progressBar.setMinWidth((int)(Math.max(rNum.width(), rDen.width())*1.1));
        this.progressBar.setMaxWidth((int)(Math.max(rNum.width(), rDen.width())*1.1));

//        choix 1
        this.num1.setText(String.valueOf(this.choix[0][0]));
        this.den1.setText(String.valueOf(this.choix[0][1]));
        this.progressBar1.setMinHeight(Integer.parseInt("10"));
        this.progressBar1.setMaxHeight(Integer.parseInt("10"));
        this.num1.getPaint().getTextBounds(this.num1.getText(), 0, this.num1.getText().length(), r1Num);
        this.den1.getPaint().getTextBounds(this.den1.getText(), 0, this.den1.getText().length(), r1Den);
        this.progressBar1.setMinWidth((int)(Math.max(r1Num.width(), r1Den.width())*1.1));
        this.progressBar1.setMaxWidth((int)(Math.max(r1Num.width(), r1Den.width())*1.1));

//        choix 2
        this.num2.setText(String.valueOf(this.choix[1][0]));
        this.den2.setText(String.valueOf(this.choix[1][1]));
        this.progressBar2.setMinHeight(Integer.parseInt("10"));
        this.progressBar2.setMaxHeight(Integer.parseInt("10"));
        this.num2.getPaint().getTextBounds(this.num2.getText(), 0, this.num2.getText().length(), r2Num);
        this.den2.getPaint().getTextBounds(this.den2.getText(), 0, this.den2.getText().length(), r2Den);
        this.progressBar2.setMinWidth((int)(Math.max(r2Num.width(), r2Den.width())*1.1));
        this.progressBar2.setMaxWidth((int)(Math.max(r2Num.width(), r2Den.width())*1.1));

//        choix 3
        this.num3.setText(String.valueOf(this.choix[2][0]));
        this.den3.setText(String.valueOf(this.choix[2][1]));
        this.progressBar3.setMinHeight(Integer.parseInt("10"));
        this.progressBar3.setMaxHeight(Integer.parseInt("10"));
        this.num3.getPaint().getTextBounds(this.num3.getText(), 0, this.num3.getText().length(), r3Num);
        this.den3.getPaint().getTextBounds(this.den3.getText(), 0, this.den3.getText().length(), r3Den);
        this.progressBar3.setMinWidth((int)(Math.max(r3Num.width(), r3Den.width())*1.1));
        this.progressBar3.setMaxWidth((int)(Math.max(r3Num.width(), r3Den.width())*1.1));
        this.score_max.incr();
    }
}