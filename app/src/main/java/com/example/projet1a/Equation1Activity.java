package com.example.projet1a;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;
import com.example.projet1a.ado_games.EquationPremier;


import java.util.Random;

public class Equation1Activity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonChoice1;
    private Button buttonChoice2;
    private Button buttonChoice3;

    private TextView equation;
    private TextView choice1;
    private TextView choice2;
    private TextView choice3;

    private int score = 0;
    private int goodAnswerPosition;
    private int random1;
    private int random2;

    private EquationPremier eq1;


    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equation1);

        this.buttonChoice1 = findViewById(R.id.choice1ID);
        this.buttonChoice2 = findViewById(R.id.choice2ID);
        this.buttonChoice3 = findViewById(R.id.choice3ID);

        this.buttonChoice1.setOnClickListener(this);
        this.buttonChoice2.setOnClickListener(this);
        this.buttonChoice3.setOnClickListener(this);

        initEquation();

    }

    public void onClick(View v) {
        // Code here executes on main thread after user presses button
        initEquation();

        if(v.getId() == this.choice1.getId() && this.goodAnswerPosition == 0){score += 1;((TextView) findViewById(R.id.delta)).setText("+1");}
        else if(v.getId() == this.choice2.getId() && this.goodAnswerPosition == 1){score += 1;((TextView) findViewById(R.id.delta)).setText("+1");}
        else if(v.getId() == this.choice3.getId() && this.goodAnswerPosition == 2){score += 1;((TextView) findViewById(R.id.delta)).setText("+1");}
        else{score -= 1;;((TextView) findViewById(R.id.delta)).setText("-1");}

        setScoreText();

    }

    public void initEquation(){
        this.equation = (TextView) findViewById(R.id.EQ1);
        this.eq1 = new EquationPremier();

        this.choice1 = (TextView) findViewById(R.id.choice1ID);
        this.choice2 = (TextView) findViewById(R.id.choice2ID);
        this.choice3 = (TextView) findViewById(R.id.choice3ID);

        writeiteration();
        writeInDisorder();
    }

    public void writeiteration(){
        this.equation.setText(this.eq1.getA() + "x" + "+" + this.eq1.getB() + "=0");
    }

    public void writeInDisorder(){
        goodAnswerPosition = randomNumber(2);
        giveRandomValues();
        if(this.goodAnswerPosition == 0){
            this.choice1.setText("x= " + this.eq1.getX());
            this.choice2.setText("x= " + this.random1);
            this.choice3.setText("x= " + this.random2);
        }
        else if (this.goodAnswerPosition == 1){
            this.choice2.setText("x= " + this.eq1.getX());
            this.choice1.setText("x= " + this.random1);
            this.choice3.setText("x= " + this.random2);
        }
        else {
            this.choice3.setText("x= " + this.eq1.getX());
            this.choice2.setText("x= " + this.random1);
            this.choice1.setText("x= " + this.random2);
        }
    }

    public int randomNumber(int x){     //Function to create random numbers between 0 and x
        Random random = new Random();
        return random.nextInt(x);
    }

    public void giveRandomValues(){     //Create 2 fake random answers to confuse the user
        do {
            random1 = randomNumber(10);
            random2 = randomNumber(10);
        } while(random1 == random2);
    }

    public void setScoreText(){
        ((TextView) findViewById(R.id.Score)).setText(String.valueOf(this.score));
    }
}
