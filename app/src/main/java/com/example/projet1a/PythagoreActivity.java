package com.example.projet1a;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.projet1a.ado_games.Pythagore;
import com.example.projet1a.profile.PlayerProfile;

import java.util.Random;

public class PythagoreActivity extends GameMaster {

    public final static String id="PythagoreActivity";
    public final static String idInd="pythagore";
    private Pythagore pythagore;
    private TextView cote1;
    private TextView cote2;
    private int correct;
    private Random random;
    private PlayerProfile player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_pythagore);
        super.onCreate(savedInstanceState);
        super.setId(this.id);
        super.setIdInd(this.idInd);
        this.cote1 = (TextView) findViewById(R.id.cote1);
        this.cote2 = (TextView) findViewById(R.id.cote2);
        this.random = new Random();
        this.player = DataProvider.getInstance().getPlayer();
        this.generate();
    }

    @Override
    public void generate(){
        super.generate();
        System.out.println(this.currentProgress[0]);
        this.pythagore = new Pythagore();
        if (this.pythagore.getSmaller()){
            this.cote1.setText(String.valueOf(this.pythagore.getA()));
            this.cote2.setText(String.valueOf(this.pythagore.getB()));
        }
        else{
            this.cote2.setText(String.valueOf(this.pythagore.getA()));
            this.cote1.setText(String.valueOf(this.pythagore.getB()));
        }
        int[] prop = new int[3];
        this.correct = random.nextInt(3);
        if (this.correct == 0){
            prop[0] = ((int) this.pythagore.getC());
            do {
                prop[1] = prop[0] + random.nextInt(20) - 10;
            } while (prop[1]==prop[0]);
            do {
                prop[2] = prop[0] + random.nextInt(20) - 10;
            } while (prop[2]==prop[0] || prop[2]==prop[1]);
        }
        else if (this.correct == 1){
            prop[1] = ((int) this.pythagore.getC());
            do {
            prop[0] = prop[1] + random.nextInt(20)-10;
            } while (prop[1]==prop[0]);
            do {
            prop[2] = prop[1] + random.nextInt(20)-10;
            } while (prop[2]==prop[0] || prop[2]==prop[1]);
        }
        else{
            prop[2] = ((int) this.pythagore.getC());
            do {
            prop[0] = prop[2] + random.nextInt(20)-10;
            } while (prop[2]==prop[0]);
            do {
            prop[1] = prop[2] + random.nextInt(20)-10;
            } while (prop[1]==prop[0] || prop[2]==prop[1]);
        }
        super.setProp(prop);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == this.choix1Button.getId()) {
            super.update(this.correct == 0);
            this.generate();
        }
        else if (v.getId() == this.choix2Button.getId()) {
            super.update(this.correct == 1);
            this.generate();
        }
        else if (v.getId() == this.choix3Button.getId()) {
            super.update(this.correct == 2);
            this.generate();
        }
    }

    @Override
    public void checkSuccess(){
        super.checkSuccess();
        if(!this.player.getSuccess().getSuccessById("o100rcpythagore").isAcquired()){
            if(this.player.getStats().getGameStatsById(id).getTotalCorrects() >= 100) {
                this.player.getSuccess().getSuccessById("o100rcpythagore").acquire();
                this.showSuccessPopup(this.player.getSuccess().getSuccessById("o100rcpythagore").getTitle());
            }
        }
    }

}