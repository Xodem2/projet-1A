package com.example.projet1a;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.projet1a.ado_games.EquationPremier;
import com.example.projet1a.profile.PlayerProfile;


import java.util.Random;

public class Equation1Activity extends GameMaster {

    private TextView equation;

    private int goodAnswerPosition;
    private int random1;
    private int random2;

    private PlayerProfile player;

    private EquationPremier eq1;

    public final static String id="Equation1Activity";
    public final static String idInd="équation";

    protected void onCreate(Bundle savedInstanceState){

        setContentView(R.layout.activity_equation1);
        super.onCreate(savedInstanceState);
        super.setId(this.id);
        super.setIdInd(this.idInd);

        this.player = DataProvider.getInstance().getPlayer();

        generate();
    }

    public void onClick(View v) {
        // Code here executes on main thread after user presses button
        super.onClick(v);
        if(v.getId() == this.choix1Button.getId()) {
            super.update(this.goodAnswerPosition == 0);
            generate();
        }
        else if(v.getId() == this.choix2Button.getId()) {
            super.update(this.goodAnswerPosition == 1);
            generate();
        }
        else if(v.getId() == this.choix3Button.getId()) {
            super.update(this.goodAnswerPosition == 2);
            generate();
        }

    }

    @Override
    public void generate(){
        super.generate();
        this.equation = (TextView) findViewById(R.id.EQ1);
        this.eq1 = new EquationPremier();

        writeiteration();
        writeInDisorder();
    }

    public void writeiteration(){
        this.equation.setText(this.eq1.getA() + "x+" + this.eq1.getB() + "=" + this.eq1.getC());
    }

    public void writeInDisorder(){
        goodAnswerPosition = randomNumber(2);
        giveRandomValues();
        String[] prop = new String[3];
        if(this.goodAnswerPosition == 0){
            prop[0] = "x= " + this.eq1.getX();
            prop[1] = "x= " + this.random1;
            prop[2] = "x= " + this.random2;
        }
        else if (this.goodAnswerPosition == 1){
            prop[1] = "x= " + this.eq1.getX();
            prop[0] = "x= " + this.random1;
            prop[2] = "x= " + this.random2;
        }
        else {
            prop[2] = "x= " + this.eq1.getX();
            prop[1] = "x= " + this.random1;
            prop[0] = "x= " + this.random2;
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
        } while(random1 == random2);
    }

    @Override
    public void checkSuccess(){
        super.checkSuccess();
        if(!this.player.getSuccess().getSuccessById("o100rceq1deg").isAcquired()){
            if(this.player.getStats().getGameStatsById(id).getTotalCorrects() >= 100) {
                this.player.getSuccess().getSuccessById("o100rceq1deg").acquire();
                this.showSuccessPopup(this.player.getSuccess().getSuccessById("o100rceq1deg").getTitle());
            }
        }
    }

}
