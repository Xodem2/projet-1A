package com.example.projet1a;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.projet1a.adult.Trigo;
import com.example.projet1a.profile.PlayerProfile;

import java.util.Random;

public class TrigoActivity extends GameMaster {

    public final static String id="TrigoActivity";
    public final static String idInd="trigo";
    private Trigo trigo;
    private TextView cote1;
    private TextView cote2;
    private TextView cote_hyp;
    private TextView angle1;
    private TextView angle2;
    private Random random;
    private PlayerProfile player;
    int[] prop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_trigo);
        super.onCreate(savedInstanceState);
        super.setId(this.id);
        super.setIdInd(this.idInd);
        this.cote1 = (TextView) findViewById(R.id.cote1);
        this.cote2 = (TextView) findViewById(R.id.cote2);
        this.cote_hyp = (TextView) findViewById(R.id.cote_hyp);
        this.angle1 = (TextView) findViewById(R.id.angle1);
        this.angle2 = (TextView) findViewById(R.id.angle2);
        this.random = new Random();
        this.player = DataProvider.getInstance().getPlayer();
        this.generate();
    }

    @Override
    public void generate(){
        super.generate();
        this.cote1.setText("");
        this.cote2.setText("");
        this.cote_hyp.setText("");
        this.angle1.setText("");
        this.angle2.setText("");
        System.out.println(this.currentProgress[0]);
        this.trigo = new Trigo();
        this.prop = new int[3];
        int ans = this.trigo.ansFct();
        String fctChoice = this.trigo.getFctChoice();
        if(fctChoice == "sin1") {
            this.prop = this.trigo.prop(ans).conv();
            this.cote_hyp.setText(String.valueOf(this.trigo.getSeg()));
            this.angle1.setText(String.valueOf(this.trigo.getAngle()) + "°");
            this.cote2.setText("?");
        }
        else if(fctChoice == "sin2") {
            this.prop = this.trigo.prop(ans).conv();
            this.cote1.setText(String.valueOf(this.trigo.getSeg()));
            this.angle2.setText(String.valueOf(this.trigo.getAngle()) + "°");
            this.cote_hyp.setText("?");
        }
        else if(fctChoice == "cos1") {
            this.prop = this.trigo.prop(ans).conv();
            this.cote_hyp.setText(String.valueOf(this.trigo.getSeg()));
            this.angle1.setText(String.valueOf(this.trigo.getAngle()) + "°");
            this.cote1.setText("?");
        }
        else if(fctChoice == "cos2") {
            this.prop = this.trigo.prop(ans).conv();
            this.cote1.setText(String.valueOf(this.trigo.getSeg()));
            this.angle1.setText(String.valueOf(this.trigo.getAngle()) + "°");
            this.cote_hyp.setText("?");
        }
        else if(fctChoice == "tan1") {
            this.prop = this.trigo.prop(ans).conv();
            this.cote1.setText(String.valueOf(this.trigo.getSeg()));
            this.angle1.setText(String.valueOf(this.trigo.getAngle()) + "°");
            this.cote2.setText("?");
        }
        else {
            this.prop = this.trigo.prop(ans).conv();
            this.cote1.setText(String.valueOf(this.trigo.getSeg()));
            this.angle2.setText(String.valueOf(this.trigo.getAngle()) + "°");
            this.cote2.setText("?");
        }
        super.setProp(this.prop);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == this.choix1Button.getId()) {
            super.update(this.trigo.fct(this.prop[0]));
            this.generate();
        }
        else if (v.getId() == this.choix2Button.getId()) {
            super.update(this.trigo.fct(this.prop[1]));
            this.generate();
        }
        else if (v.getId() == this.choix3Button.getId()) {
            super.update(this.trigo.fct(this.prop[2]));
            this.generate();
        }
    }

    @Override
    public void checkSuccess(){
        super.checkSuccess();
        /*if(!this.player.getSuccess().getSuccessById("o100rctrigo").isAcquired()){
            if(this.player.getStats().getGameStatsById(id).getTotalCorrects() >= 100)
                this.player.getSuccess().getSuccessById("o100rctrigo").acquire();
        }*/
    }

}
