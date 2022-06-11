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

public class FractionActivity extends GameMaster {
    private TextView num;
    private TextView den;
    private ProgressBar progressBar;
    Rect rNum;
    Rect rDen;

    public final static String id="FractionActivity";


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

    int[] bon_choix;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_fraction);
        super.onCreate(savedInstanceState);
        super.setId(this.id);
        this.bon_choix = new int[2];
        this.choix = new int[3][2];
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);

        this.random = new Random();

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
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onClick(View v) {
        if (v.getId() == this.choix1Button.getId()){
            super.update(this.num1.getText() == String.valueOf(this.bon_choix[0]) && this.den1.getText() == String.valueOf(this.bon_choix[1]));
        }
        else if(v.getId() == this.choix2Button.getId()){
            super.update(this.num2.getText() == String.valueOf(this.bon_choix[0]) && this.den2.getText() == String.valueOf(this.bon_choix[1]));
        }
        else{
            super.update(this.num3.getText() == String.valueOf(this.bon_choix[0]) && this.den3.getText() == String.valueOf(this.bon_choix[1]));
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
    @Override
    public void generate(){
        super.generate();
        int length = random.nextInt(3)+2;
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
    }
}