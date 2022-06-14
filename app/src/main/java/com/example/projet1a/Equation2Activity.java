package com.example.projet1a;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.projet1a.ado_games.EquationSecond;
import com.example.projet1a.profile.PlayerProfile;


import java.util.Random;

public class Equation2Activity extends GameMaster {
    public final static String id="Equation2Activity";
    public final static String idInd="équation2nd";

    private TextView equation;

    private int score = 0;
    private int goodAnswerPosition;
    private int random1;
    private int random2;
    private int random3;
    private int random4;

    private PlayerProfile player;

    private EquationSecond eq2;

    protected void onCreate(Bundle savedInstanceState){
        setContentView(R.layout.activity_equation2);
        super.onCreate(savedInstanceState);
        super.setId(this.id);
        super.setIdInd(this.idInd);

        this.player = DataProvider.getInstance().getPlayer();

        generate();
    }

    public void onClick(View v) {
        super.onClick(v);
        if (v.getId()==this.choix1Button.getId()) {
            super.update(this.goodAnswerPosition == 0);
            generate();
        }
        else if (v.getId()==this.choix2Button.getId()) {
            super.update(this.goodAnswerPosition == 1);
            generate();
        }
        else if (v.getId()==this.choix3Button.getId()) {
            super.update(this.goodAnswerPosition == 2);
            generate();
        }
    }

    @Override
    public void generate(){
        super.generate();
        this.equation = (TextView) findViewById(R.id.EQ2);
        this.eq2 = new EquationSecond();

        writeiteration();
        writeInDisorder();
    }

    public void writeiteration(){
        this.equation.setText(this.eq2.getA() + "x²+" + this.eq2.getB() +"x+" + this.eq2.getC() + "=0");
    }

    public void writeInDisorder(){
        goodAnswerPosition = randomNumber(2);
        giveRandomValues();
        String[] prop = new String[3];
        if(this.goodAnswerPosition == 0){
            prop[0] = "x1 = " + this.eq2.getX1() + "\nx2 = " + this.eq2.getX2();
            prop[1] = "x1 = " + this.random1 + "\nx2 = " + this.random3;
            prop[2] = "x1 = " + this.random2 + "\nx2 = " + this.random4;
        }
        else if (this.goodAnswerPosition == 1){
            prop[1] = "x1 = " + this.eq2.getX1() + "\nx2 = " + this.eq2.getX2();
            prop[0] = "x1 = " + this.random1 + "\nx2 = " + this.random3;
            prop[2] = "x1 = " + this.random2 + "\nx2 = " + this.random4;
        }
        else {
            prop[2] = "x1 = " + this.eq2.getX1() + "\nx2 = " + this.eq2.getX2();
            prop[1] = "x1 = " + this.random1 + "\nx2 = " + this.random3;
            prop[0] = "x1 = " + this.random2 + "\nx2 = " + this.random4;
        }
        super.setProp(prop);
    }

    public int randomNumber(int x){     //Function to create random numbers between 0 and x
        Random random = new Random();
        return random.nextInt(x);
    }

    public void giveRandomValues(){     //Create 2 fake random answers to confuse the user
        do {
            random1 = randomNumber(10);
            random2 = randomNumber(10);
            random3 = randomNumber(10);
            random4 = randomNumber(10);
        } while(random1 == random2);
    }

    @Override
    public void checkSuccess(){
        super.checkSuccess();
        if(!this.player.getSuccess().getSuccessById("o100rceq2deg").isAcquired()){
            if(this.player.getStats().getGameStatsById(id).getTotalCorrects() >= 100)
                this.player.getSuccess().getSuccessById("o100rceq2deg").acquire();
        }
    }
}
