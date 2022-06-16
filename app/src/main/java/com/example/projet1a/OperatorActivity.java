package com.example.projet1a;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.projet1a.ado_games.EquationPremier;
import com.example.projet1a.enfant.Operator;
import com.example.projet1a.profile.PlayerProfile;


import java.util.Random;


public class OperatorActivity extends GameMaster {

    private TextView equation;

    private PlayerProfile player;

    private Operator eq1;
    private int[] propInt;
    private String[] prop;

    public final static String id="OperatorActivity";
    public final static String idInd="opérateur";

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
            super.update(this.eq1.op(this.eq1.getOpInv(prop[0])));
            generate();
        }
        else if(v.getId() == this.choix2Button.getId()) {
            super.update(this.eq1.op(this.eq1.getOpInv(prop[1])));
            generate();
        }
        else if(v.getId() == this.choix3Button.getId()) {
            super.update(this.eq1.op(this.eq1.getOpInv(prop[2])));
            generate();
        }

    }

    @Override
    public void generate(){
        super.generate();
        this.equation = (TextView) findViewById(R.id.EQ1);
        this.eq1 = new Operator();

        writeiteration();
        writeInDisorder();
    }

    public void writeiteration(){
        this.equation.setText(this.eq1.getA() + " _ " + this.eq1.getB() + "=" + this.eq1.getC());
    }

    public void writeInDisorder(){
        prop = new String[3];
        propInt = this.eq1.prop().conv();
        for(int i = 0; i < 3; i++) {
            prop[i] = this.eq1.getOp(propInt[i]);
        }
        super.setProp(prop);
    }

    @Override
    public void checkSuccess(){
        super.checkSuccess();
        if(!this.player.getSuccess().getSuccessById("o100rcop").isAcquired()){
            if(this.player.getStats().getGameStatsById(id).getTotalCorrects() >= 100) {
                this.player.getSuccess().getSuccessById("o100rcop").acquire();
                this.showSuccessPopup(this.player.getSuccess().getSuccessById("o100rcop").getTitle());
            }
        }
    }

}
