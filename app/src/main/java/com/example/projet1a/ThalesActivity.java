package com.example.projet1a;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.projet1a.ado_games.EquationPremier;
import com.example.projet1a.ado_games.Thales;
import com.example.projet1a.profile.PlayerProfile;


import java.util.Random;

public class ThalesActivity extends GameMaster {

    private TextView L1;
    private TextView L2;
    private TextView L3;
    private TextView L4;
    private TextView L5;
    private TextView L6;

    private int goodAnswerPosition;
    private int random1;
    private int random2;
    private int answer;
    private int whichAnswer;

    private PlayerProfile player;

    private Thales thales;

    public final static String id="Equation1Activity";
    public final static String idInd="thalès";

    protected void onCreate(Bundle savedInstanceState){

        setContentView(R.layout.activity_thales);
        super.onCreate(savedInstanceState);
        super.setId(this.id);
        super.setIdInd(this.idInd);

        this.player = DataProvider.getInstance().getPlayer();

        this.thales = new Thales();
        chooseRandomLine();
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
        else if(v.getId() == this.choix3Button.getId()){
            super.update(this.goodAnswerPosition == 2);
            generate();
        }
    }

    @Override
    public void generate(){
        super.generate();
        this.L1 = (TextView) findViewById(R.id.L1);
        this.L2 = (TextView) findViewById(R.id.L2);
        this.L3 = (TextView) findViewById(R.id.L3);
        this.L4 = (TextView) findViewById(R.id.L4);
        this.L5 = (TextView) findViewById(R.id.L5);
        this.L6 = (TextView) findViewById(R.id.L6);

        this.thales = new Thales();

        chooseRandomLine();
        writeTextOnImage();
        writeInDisorder();
    }

    public void writeInDisorder(){
        goodAnswerPosition = randomNumber(2);
        giveRandomValues();
        String[] prop = new String[3];
        if(this.goodAnswerPosition == 0){
            prop[0] = "x= " + this.answer;
            prop[1] = "x= " + this.random1;
            prop[2] = "x= " + this.random2;
        }
        else if (this.goodAnswerPosition == 1){
            prop[1] = "x= " + this.answer;
            prop[0] = "x= " + this.random1;
            prop[2] = "x= " + this.random2;
        }
        else {
            prop[2] = "x= " + this.answer;
            prop[1] = "x= " + this.random1;
            prop[0] = "x= " + this.random2;
        }
        super.setProp(prop);
    }

    public void writeTextOnImage(){
        this.L1.setText("" + this.thales.getAD());
        this.L2.setText("" + this.thales.getAB());
        this.L3.setText("" + this.thales.getAE());
        this.L4.setText("" + this.thales.getAC());
        this.L5.setText("" + this.thales.getDE());
        this.L6.setText("" + this.thales.getBC());
        if(this.whichAnswer == 0 ){ this.L2.setText("?");}
        else if(this.whichAnswer == 1 ){ this.L4.setText("?");}
        else if(this.whichAnswer == 2 ){ this.L1.setText("?");}
        else if(this.whichAnswer == 3 ){ this.L3.setText("?");}
        else if(this.whichAnswer == 4 ){ this.L5.setText("?");}
        else if(this.whichAnswer == 5 ){ this.L6.setText("?");}
        System.out.println(this.answer);
        System.out.println(this.thales.getBool());
        System.out.println(this.thales.get1());
        System.out.println(this.thales.get2());
        System.out.println(this.thales.get3());
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

    public void chooseRandomLine(){
        this.whichAnswer = randomNumber(6);
        if(this.whichAnswer == 0){ this.answer = this.thales.getAB(); }
        else if(this.whichAnswer == 1){ this.answer = this.thales.getAC(); }
        else if(this.whichAnswer == 2){ this.answer = this.thales.getAD(); }
        else if(this.whichAnswer == 3){ this.answer = this.thales.getAE(); }
        else if(this.whichAnswer == 4){ this.answer = this.thales.getDE(); }
        else if(this.whichAnswer== 5){ this.answer = this.thales.getBC(); }
    }

    @Override
    public void checkSuccess(){
        super.checkSuccess();
        if(!this.player.getSuccess().getSuccessById("o100rcthales").isAcquired()){
            if(this.player.getStats().getGameStatsById(id).getTotalCorrects() >= 100) {
                this.player.getSuccess().getSuccessById("o100rcthales").acquire();
                this.showSuccessPopup(this.player.getSuccess().getSuccessById("o100rcthales").getTitle());
            }
        }
    }

}
